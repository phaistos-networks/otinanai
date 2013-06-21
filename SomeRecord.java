import java.net.InetAddress;
import java.util.*;

class SomeRecord {

	public SomeRecord(InetAddress ip, String data) {
		timeStamp = System.currentTimeMillis();
		timeNano = Long.toString(System.nanoTime());
		myip = ip;
		theRecord = data;
		keyWords = new ArrayList<String>();
		findKeyWords(data);
	}

	private void findKeyWords(String str) {
		String[] Tokens = str.split("[ .\t\n]");
		for (String tok : Tokens ) {
			if (tok.length() >= 4 ) {
				keyWords.add(tok);
			}
		}
		Tokens = str.split("\\s");
		masterKey = Tokens[0];
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public String getTimeNano() {
		return timeNano;
	}

	public InetAddress getIP() {
		return myip;
	}

	public String getHostName() {
		return myip.getHostName();
	}

	public String getRecord() {
		return theRecord;
	}

	public boolean hasKeyword(String test) {
		return keyWords.contains(test);
	}

	public ArrayList<String> getKeyWords() {
		return keyWords;
	}

	public boolean containsWord(String test) {
		return theRecord.toLowerCase().contains(test.toLowerCase());
	}

	public String getKey() {
		return masterKey;
	}

	private long timeStamp;
	private String timeNano;
	private InetAddress myip;
	private String theRecord;
	private ArrayList<String> keyWords;
	private String masterKey;
}
