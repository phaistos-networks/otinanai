package gr.phaistosnetworks.admin.otinanai;

import java.util.logging.*;
import java.util.*;
import redis.clients.jedis.*;

class RedisTracker implements KeyWordTracker {

	public RedisTracker(String key, int as, float atl, float ath, int acs, String rh, Jedis j, Logger l) {
		mean = 0f;
		alarmSamples = as;
		lowAlarmThreshold = atl;
		highAlarmThreshold = ath;
		alarmConsecutiveSamples = acs;
		lowAlarmCount = 0;
		highAlarmCount = 0;
                zeroesCount = 0;
		keyWord = new String(key);
		logger = l;
		redisHost = rh;
		jedis = j;
		sampleCount = 1;
		currentFloat = 0f;
		currentDataCount = 0;
		lastTimeStamp = 0l;
		curTS = 0l;
		zeroPct = 0f;
		recordType = OtiNanai.UNSET;
		step1Key = keyWord + "thirtySec";
		step2Key = keyWord + "fiveMin";
		step3Key = keyWord + "thirtyMin";
		alarmKey = keyWord + "alarmTS";
                try {
                        String alarmSaved = jedis.get(alarmKey);
                        if (alarmSaved == null) {
                                alarm = 0L;
                        } else {
                                        alarm = Long.parseLong(alarmSaved);
                        }
                } catch (Exception e) {
                        logger.severe("[RedisTracker]: Unable to retrieve stored alarm state\n"+e);
                        alarm = 0L;
                }

		logger.finest("[RedisTracker]: new RedisTracker initialized for \"" +keyWord+"\"");
	}

	private void resetJedis() {
                logger.info("[RedisTracker]: Resetting jedis for \"" + keyWord+"\"");
		jedis.disconnect();
		jedis = new Jedis(redisHost);
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void delete() {
		jedis.del(step1Key);
		jedis.del(step2Key);
		jedis.del(step3Key);
	}

	public void putFreq() {
		if (recordType == OtiNanai.UNSET) {
			recordType = OtiNanai.FREQ;
			currentCount = 0;
		}
		currentCount ++;
	}

	public void putCounter(long value) {
		if (recordType == OtiNanai.UNSET) {
			recordType = OtiNanai.COUNTER;
			currentLong = 0l;
			currentPrev = 0l;
		}
		currentLong = value;
		curTS = System.currentTimeMillis();
		logger.finest("[RedisTracker]: currentLong is now " +currentLong);
	}

	public void putGauge(float value) {
		if (recordType == OtiNanai.UNSET) {
			recordType = OtiNanai.GAUGE;
			currentFloat = 0f;
			currentDataCount = 0;
		}
		currentFloat += value;
		currentDataCount ++;
		curTS = System.currentTimeMillis();
		logger.finest("[RedisTracker]: currentFloat is now " +currentFloat);
	}

	public void putSum(float value) {
		if (recordType == OtiNanai.UNSET) {
			recordType = OtiNanai.SUM;
			currentFloat = 0f;
		}
		currentFloat += value;
	}


	public void tick() {
		logger.fine("[RedisTracker]: ticking " + keyWord );
		if (recordType == OtiNanai.COUNTER || recordType == OtiNanai.GAUGE)
			flush(curTS);
		flush(System.currentTimeMillis());
	}

	private void flush(long ts) {
		if (recordType == OtiNanai.UNSET)
			return;
		if (recordType == OtiNanai.COUNTER && currentLong == 0l)
			return;
		if (recordType == OtiNanai.GAUGE && currentDataCount == 0)
			return;
		if (recordType == OtiNanai.SUM && currentFloat == 0f)
			return;

		if (lastTimeStamp == 0l && recordType != OtiNanai.GAUGE) {
			lastTimeStamp = ts;
			currentFloat = 0f;
			currentDataCount = 0;
			currentCount = 0;
			currentPrev = currentLong;
			currentLong = 0l;
			return;
		}
			
		float perSec = 0f;
		float timeDiff = (float)(ts - lastTimeStamp);
		lastTimeStamp = ts;

		if (recordType == OtiNanai.GAUGE) {
			logger.fine("[RedisTracker]: currentFloat = " +currentFloat);
			perSec = (currentFloat / currentDataCount);
			currentFloat = 0f;
			currentDataCount = 0;
		} else if (recordType == OtiNanai.SUM) {
			perSec = ((float)((1000f*currentFloat)/timeDiff));
			currentFloat = 0f;
		} else if (recordType == OtiNanai.COUNTER) {
			if (currentLong != currentPrev) {
				logger.fine("[RedisTracker]: currentLong = " + currentLong);
				if (currentPrev == 0l || currentPrev > currentLong) {
					logger.fine("Last count is 0 or decrementing. Setting and Skipping");
				} else {
					float valueDiff = (float)(currentLong - currentPrev);
					perSec = ((float)((valueDiff*1000f)/timeDiff));
				}
				currentPrev = currentLong;
				currentLong = 0l;
			}

		} else if (recordType == OtiNanai.FREQ ) {
			perSec = ((float)currentCount*1000f)/timeDiff;
			logger.finest("[RedisTracker]: perSec = " +perSec);
			currentCount = 0;
		}

		logger.fine("[RedisTracker]: "+keyWord+" timeDiff: " +timeDiff+ " perSec: "+perSec);
		String toPush = new String(ts+" "+String.format("%.3f", perSec));
		//      jedis.lpush(step1Key, new String(ts+" "+String.format("%.2f", perSec)));

                /*
		try {
			if (jedis.llen(step1Key) > 1) {
				//ugly deduplication
				String dato1 = jedis.lindex(step1Key,0);
				String dato2 = jedis.lindex(step1Key,1);
				String dato0 = toPush.substring(toPush.indexOf(" ") +1);
				dato1 = dato1.substring(dato1.indexOf(" ") +1);
				dato2 = dato2.substring(dato2.indexOf(" ") +1);
				if (dato0.equals(dato1)) {
					if (dato1.equals(dato2)) {
						jedis.lpop(step1Key);
					}
				}
			}
		} catch (Exception e) {
			logger.severe("[RedisTracker]: tick(): "+e);
			logger.severe("toPush: "+toPush);
			System.err.println("[RedisTracker]: tick(): "+e.getMessage());
			resetJedis();
		}
                */
		logger.finest("[RedisTracker]: lpush "+step1Key+" "+toPush);
		jedis.lpush(step1Key, toPush);



		/*
		 * Aggregate old 30sec samples and make 5min samples
		 */

		float lastMerge;
		long lastts;
		long tsMerge;
		String lastDato = new String();
		String lastDatoString = new String();

		if (jedis.llen(step1Key) >= OtiNanai.STEP1_MAX_SAMPLES) {
			lastMerge = 0;
			lastts = 0l;
			tsMerge = 0l;

			for (int i=1; i<=OtiNanai.STEP1_SAMPLES_TO_MERGE ; i++) {
				lastDatoString=jedis.rpop(step1Key);
				lastts = Long.parseLong(lastDatoString.substring(0,lastDatoString.indexOf(" ")));
				lastDato=lastDatoString.substring(lastDatoString.indexOf(" ")+1);

				logger.fine("[RedisTracker]: Data: "+lastMerge+" += "+lastDato+" ts: "+tsMerge+" += "+lastts);
				lastMerge += Float.parseFloat(lastDato);
				tsMerge += lastts;
			}
			float finalSum = lastMerge/OtiNanai.STEP1_SAMPLES_TO_MERGE;
			long finalts = tsMerge/OtiNanai.STEP1_SAMPLES_TO_MERGE;

			logger.fine("[RedisTracker]: "+keyWord+": Aggregated dataSum:"+ lastMerge + " / "+OtiNanai.STEP1_SAMPLES_TO_MERGE+" = "+finalSum+". tsSum: "+tsMerge+" / "+OtiNanai.STEP1_SAMPLES_TO_MERGE+" = "+ finalts);

			toPush = new String(finalts+" "+String.format("%.3f", finalSum));
			jedis.lpush(step2Key, toPush);
		}


		/*
		 * Aggregate old 5min samples and make 30min samples
		 */

		if (jedis.llen(step2Key) >= OtiNanai.STEP2_MAX_SAMPLES) {
			lastMerge = 0;
			lastts = 0l;
			tsMerge = 0;

			for (int i=1; i<=OtiNanai.STEP2_SAMPLES_TO_MERGE ; i++) {
				lastDatoString = jedis.rpop(step2Key);
				lastts = Long.parseLong(lastDatoString.substring(0,lastDatoString.indexOf(" ")));
				lastDato = lastDatoString.substring(lastDatoString.indexOf(" ")+1);
				lastMerge += Float.parseFloat(lastDato);
				tsMerge += lastts;
			}
			float finalSum = lastMerge/OtiNanai.STEP2_SAMPLES_TO_MERGE;
			long finalts = tsMerge/OtiNanai.STEP2_SAMPLES_TO_MERGE;

			logger.fine("[RedisTracker]: "+keyWord+": Aggregated dataSum:"+ lastMerge + " / "+OtiNanai.STEP2_SAMPLES_TO_MERGE+" = "+finalSum+". tsSum: "+tsMerge+" / "+OtiNanai.STEP2_SAMPLES_TO_MERGE+" = "+ finalts);
			toPush = new String(finalts+" "+String.format("%.3f", finalSum));
			jedis.lpush(step3Key, toPush);
		}



		/*
		 * Alarm detection
		 */

                if (mean == 0f && perSec != 0f) {
			logger.fine("[RedisTracker]: mean is 0, setting new value");
			mean = perSec;
			sampleCount = 1;
                        return;
                } 

		if ( sampleCount < alarmSamples ) {
			sampleCount++;
			mean += (perSec-mean)/alarmSamples;
                        if (perSec == 0f) {
                                zeroesCount ++;
                                zeroPct = 100.0f * ((float)zeroesCount / (float)sampleCount);
                        }
                } else {
                        if ((perSec < (mean / lowAlarmThreshold)) && (perSec != 0f || zeroPct < 2.0f)) {
                                lowAlarmCount++;
                                highAlarmCount = 0;
                        } else if (perSec > (highAlarmThreshold * mean)) {
                                highAlarmCount++;
                                lowAlarmCount = 0;
                        } else {
                                if (perSec != 0f)
                                        mean += (perSec-mean)/alarmSamples;
                                highAlarmCount = 0;
                                lowAlarmCount = 0;
                        }
                        if (lowAlarmCount >= alarmConsecutiveSamples || highAlarmCount >= alarmConsecutiveSamples ) {
                                if ( alarm == 0 || (ts - alarm > OtiNanai.ALARMLIFE) ) {
                                        logger.info("[RedisTracker]: Error conditions met for " + keyWord + " mean: "+mean +" value: "+perSec+" zeroPct: "+zeroPct+" zeroesCount: "+zeroesCount+" sampleCount: "+sampleCount+" highCount: "+highAlarmCount+" lowCount: "+lowAlarmCount);
                                        OtiNanaiNotifier onn = new OtiNanaiNotifier((highAlarmCount >= alarmConsecutiveSamples ? "High " : "Low " ) + "Alarm: *"+keyWord+" value:"+String.format("%.2f", perSec)+" (mean: "+String.format("%.3f", mean) +") url: "+OtiNanai.WEBURL+"/"+keyWord);
                                        onn.send();
                                        alarm=ts;
                                        jedis.set(alarmKey, Long.toString(ts));
                                }
                        }
                }
	}

	public long getAlarm() {
		return alarm;
	}

	public ArrayList<String> getMemory(Long startTime) {
		ArrayList<String> returner = new ArrayList<String>();
                int maxtries = 3;
                for (int i=0;i<maxtries;i++) {
                        try {
                                returner.addAll(jedis.lrange(step1Key,0,-1));

                                String ldp = returner.get(returner.size()-1);
                                Long lastts = Long.parseLong(ldp.substring(0,ldp.indexOf(" ")));

                                if (lastts < startTime)
                                        return returner;

                                returner.addAll(jedis.lrange(step2Key,0,-1));
                                ldp = returner.get(returner.size()-1);
                                lastts = Long.parseLong(ldp.substring(0,ldp.indexOf(" ")));

                                if (lastts < startTime)
                                        return returner;

                                returner.addAll(jedis.lrange(step3Key,0,-1));

                                break;

                        } catch (Exception e) {
                                logger.severe("[RedisTracker]: getMemory(): "+keyWord + ": " + e);
                                System.err.println("[RedisTracker]: getMemory(): "+keyWord + ": "+e.getMessage());
                                returner = new ArrayList<String>();
                                resetJedis();
                        }
                }
		return returner;
	}

	public long getCurrentCount() {
		return currentCount;
	}

	public short getType() {
		return recordType;
	}

	public void setType(short type) {
		if (recordType == OtiNanai.UNSET)
			recordType = type;
	}

	private long alarm;
	private String keyWord;
	private long currentCount;
	private float mean;
	private int sampleCount;
	private int currentDataCount;
	private float currentFloat;
	private float zeroPct;
	private long currentLong;
	private long currentPrev;
	private long lastTimeStamp;
	private long curTS;
	private Logger logger;
	private int alarmSamples;
	private float lowAlarmThreshold;
	private float highAlarmThreshold;
	private short recordType;
	private int alarmConsecutiveSamples;
	private int highAlarmCount;
	private int lowAlarmCount;
        private int zeroesCount;
	private Jedis jedis;
	private String redisHost;
	private String step1Key;
	private String step2Key;
	private String step3Key;
	private String alarmKey;
}
