import java.net.InetAddress;
import java.util.*;
import java.lang.*;
import java.text.SimpleDateFormat;

/**
 * Data Storage Class
 * Keeps self data, keywords, timestamp, ip of host and has access methods
 */
class SomeRecord {

	/**
	 * Constructor.
	 * @param	ip	IPAddress
	 * @param	data	arbitrary data as a string
	 */
	public SomeRecord(InetAddress ip, String data) {
		timeStamp = System.currentTimeMillis();
		theDate = calcDate(timeStamp);
		timeNano = Long.toString(System.nanoTime());
		myip = ip;
		theRecord = data;
      keyWords = new ArrayList<String>();
      IAmMetric = false;
      process(data.replaceAll("[\r\n]",""), 3, 48);
   }

   private void process(String str, int min, int max) {
      storeMetric(str, min, max);
      if (!IAmMetric) {
         findKeyWords(str, min, max);
      }
   }


	/**
	 * Method to break down the string into keywords.
    * @param   min   the minimum length of a word to be considered as a keyword
    * @param   max   the maximum ...
	 * @param	str	the data to be broken down
	 */
	private void storeMetric(String str, int min, int max) {
      str = str.toLowerCase();
      String[] Tokens = str.split("[ \t]");
      if (Tokens.length == 2) {
         if (isKeyWord(Tokens[0], min, max)) {
            Float w2 = toMetric(Tokens[1]);
            if (w2 != null) {
               theMetric = w2;
               IAmMetric = true;
            }
         }
      }
	}

	/**
	 * Method to break down the string into keywords.
    * @param   min   the minimum length of a word to be considered as a keyword
    * @param   max   the maximum ...
	 * @param	str	the data to be broken down
	 */
	private void findKeyWords(String str, int min, int max) {
      str = str.toLowerCase();
		String[] Tokens = str.split("[ \t]");
      int i=0;
      boolean indexAll = false;
      if (Tokens[i].equals("index")) {
         indexAll = true;
         i++;
      }
		for (; i<Tokens.length; i++ ) {
			String tok = Tokens[i];
         if (toMetric(tok) == null && isKeyWord(tok, min, max)) {
            keyWords.add(tok);
            if (!indexAll)
               break;
			}
		}
		Tokens = str.split("\\s");
		masterKey = Tokens[0];
	}

   /**
    * Returns trus if a word is a metric
    * @param   str   the word
    */
   private Float toMetric(String str) {
      try {
         return Float.parseFloat(str);
      } catch (NumberFormatException nfe) {
         return null;
      }
   }

   /**
    * Returns true if a word has the right length to be a keyword
    * @param   str   the keyword
    * @param   min   min word length
    * @param   max   max word length
    */
   private boolean isKeyWord(String str, int min, int max) {
      if ( str.length() >= min && str.length() < max )
         return true;
      return false;
   }

	/**
	 * Changes milliseconds into date with format: MM/dd/YY HH:mm:ss
	 * @param	millisecs the millisecs to change
	 * @return	String containing the date.
	 */
	private String calcDate(long millisecs) {
		SimpleDateFormat date_format = new SimpleDateFormat("MM/dd/YY HH:mm:ss");
		Date resultdate = new Date(millisecs);
		return date_format.format(resultdate);
	}

	/**
	 * Access Method
	 * @return the date of the record in format: MM/dd/YY HH:mm:ss
	 */
	public String getDate() {
		return theDate;
	}

	/**
	 * Access Method
	 * @return timestamp (millisecond) of record.
	 */
	public long getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Access Method
	 * @return nanosecond time of record. Used as unique id
	 */
	public String getTimeNano() {
		return timeNano;
	}

	/**
	 * Access Method
	 * @return record source ip
	 */
	public InetAddress getIP() {
		return myip;
	}

	/**
	 * Access Method
	 * @return host name lookup of the record source ip
	 */
	public String getHostName() {
		return myip.getHostName();
	}

	/**
	 * Access Method
	 * @return	the whole data
	 */
	public String getRecord() {
		return theRecord;
	}

	/**
	 * Access Method
	 * @return	the nth word from the data or null.
	 */
	public String getRecord(int m) {
		String toks[] = theRecord.split("\\s");
		if (m < toks.length || m < 0) 
			return toks[m];
		return null;	
	}

	/**
	 * A check if the word exists in the list of detected keywords
	 * @param test	the string to test
	 */
	public boolean hasKeyword(String test) {
		return keyWords.contains(test);
	}

	/**
	 * Access Method
	 * @return 	ArrayList containing the keyword for this
	 */
	public ArrayList<String> getKeyWords() {
		return keyWords;
	}

	/**
	 * A check if the word is contained in the dato.
	 * This will be slower than hasKeyword, but does not require a precise match
	 * @param test	the string to test
	 */
	public boolean containsWord(String test) {
		return theRecord.toLowerCase().contains(test.toLowerCase());
	}

	/**
	 * This will most likely not be used
	 * @return the first word of data (could be useful is all data is org.gnome.desktop.wm.raiseOnFocus 0)
	 */
	public String getKey() {
		return masterKey;
	}

   /**
    * Access Method
    */
   public boolean isMetric() {
      return IAmMetric;
   }

   /**
    * Access Method
    */
   public Float getMetric() {
      if (IAmMetric)
         return theMetric;
      return null;
   }

	private long timeStamp;
	private String timeNano;
	private InetAddress myip;
	private String theRecord;
   private Float theMetric;
   private boolean IAmMetric;
	private String theDate;
	private ArrayList<String> keyWords;
	private String masterKey;
}