import java.net.ServerSocket;
import java.net.DatagramSocket;
import java.util.logging.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

/**
 * Otinanai means Whatever, anything, who cares?
 * You pipe it data, it indexes the data, aggregates it, detects anomalies and generates graphs
 * well... it will anyway, someday
 */
class OtiNanai {
	/**
	 * Standard Constructor.
	 * @param	listenerPort	The udp port to pipe data to.
	 * @param	listenerThreads	Number of listener threads (not implemented yet)
	 * @param	webPort	The web interface port
	 * @param	webThreads	The number of web listener threads
	 */
	public OtiNanai(int listenerPort, int listenerThreads, int webPort, int webThreads, long cacheTime, int cacheItems, long alarmLife){
		setupLogger("/home/robert/otinanai.log", "INFO");
		try {
			// Listener
			logger.config("[Init]: Setting up new DatagramSocket Listener on port "+listenerPort);
			logger.config("[Init]: Deviation Alarm life: "+alarmLife + "s");
			DatagramSocket ds = new DatagramSocket(listenerPort);
			OtiNanaiListener onl = new OtiNanaiListener(ds, alarmLife, logger);
			new Thread(onl).start();

         // Ticker
         logger.config("[Init]: Setting up ticker");
         OtiNanaiTicker ont = new OtiNanaiTicker(onl, logger);
         new Thread(ont).start();

         // Cacher
         logger.config("[Init]: Setting up cacher (life: " +cacheTime+ "items: "+cacheItems+")");
         OtiNanaiCache onc = new OtiNanaiCache(cacheTime, cacheItems, logger);

			// Web Interface
			logger.config("[Init]: Setting up new Web Listener on port "+webPort);
			ServerSocket ss = new ServerSocket(webPort);
			OtiNanaiWeb onw = new OtiNanaiWeb(onl, onc, ss, logger);
			for (int i=1; i<=webThreads; i++) {
				logger.config("[Init]: Starting web thread: "+i+"/"+webThreads);
				new Thread(onw).start();
			}
		} catch (java.lang.Exception e) {
			System.err.println(e);
			logger.severe(e.getMessage());
			System.exit(1);
		}
		//OtiNanaiCommander onc = new OtiNanaiCommander(onl);
		//new Thread(onc).start();
	}

	/**
	 * This method does excactly what is says on the tin.
	 * Sets up a new logger to be used by OtiNanai.
	 * @param	fileName	The log filename (full path)
	 * @param	logLevel	The log level
	 */
	private void setupLogger(String fileName, String logLevel) {
		try {
			// This one is for log messages
			FileHandler fh = new FileHandler(fileName, 52428800, 2, true);
			logger = Logger.getLogger("OtiNanai");
			String lcll = logLevel.toLowerCase();
			if (lcll.equals("severe")) {
				logger.setLevel(Level.SEVERE);
			} else if  (lcll.equals("warning")) {
				logger.setLevel(Level.WARNING);
			} else if  (lcll.equals("info")) {
				logger.setLevel(Level.INFO);
			} else if  (lcll.equals("config")) {
				logger.setLevel(Level.CONFIG);
			} else if  (lcll.equals("fine")) {
				logger.setLevel(Level.FINE);
			} else if  (lcll.equals("finer")) {
				logger.setLevel(Level.FINER);
			} else if  (lcll.equals("finest")) {
				logger.setLevel(Level.FINEST);
			} else if  (lcll.equals("all")) {
				logger.setLevel(Level.ALL);
			} else {
				logger.setLevel(Level.WARNING);
			}

			fh.setFormatter(new MyLogFormatter());
			logger.setUseParentHandlers(false);
			logger.addHandler(fh);

		} catch (IOException e) {
			System.err.println("Cannot create log file");
			System.exit(1);
		}
	}

	/**
	 * Main
	 * Deals with command line arguments and creates a new OtiNanai instance
	 */
	public static void main(String args[]) {
		String arg;
		int webPort = 9876;
		int webThreads = 5;
		int udpPort = 9876;
		int tcpPort = 1010;
		int listenerThreads = 5;
      Long cacheTime = 120000L;
      Long alarmLife = 86400000L;
      int cacheItems = 50; 
		try {
			for (int i=0; i<args.length; i++) {
				arg = args[i];
				System.out.println("arg " +i+ ": " +arg);
				switch (arg) {
					case "-w":
						i++;
						webPort = Integer.parseInt(args[i]);
						System.out.println("Web port = " + webPort);
						break;	
					case "-p":
						i++;
						udpPort = Integer.parseInt(args[i]);
						tcpPort = Integer.parseInt(args[i]);
						System.out.println("Listener port = " + udpPort);
						break;
					case "-t":
						i++;
						webThreads = Integer.parseInt(args[i]);
						System.out.println("Web Threads = " + webThreads);
						break;
					case "-ct":
						i++;
						cacheTime = 1000*(Long.parseLong(args[i]));
						System.out.println("cacheTime = " + cacheTime);
						break;
					case "-ci":
						i++;
						cacheItems = Integer.parseInt(args[i]);
						System.out.println("cacheItems = " + cacheItems);
						break;
					case "-al":
						i++;
						alarmLife = 1000*(Long.parseLong(args[i]));
						System.out.println("alarmLife = " + alarmLife);
						break;
					default:
						System.out.println("-w <webPort> -p <listenerPort> -t <webThreads> -ct <cacheTime (s)> -ci <cacheItems> -al <alarmLife (s>)");
                  System.exit(0);
						break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
		OtiNanai non = new OtiNanai(udpPort, listenerThreads, webPort, webThreads, cacheTime, cacheItems, alarmLife);
	}

	/**
	 * Simple Log Formatter
	 */
	private class MyLogFormatter extends java.util.logging.Formatter {
		public String format(LogRecord rec) {
			StringBuffer buf = new StringBuffer(1024);
			buf.append(calcDate(rec.getMillis()));
//			buf.append(" ");
//			buf.append("["+rec.getLevel()+"]");
			buf.append(" : ");
			buf.append(formatMessage(rec));
			buf.append('\n');
			return buf.toString();
		}

		private String calcDate(long millisecs) {
			SimpleDateFormat date_format = new SimpleDateFormat("MMM dd HH:mm");
			Date resultdate = new Date(millisecs);
			return date_format.format(resultdate);
		}

		public String getHead(Handler h) {
			return "OtiNanai Logger Initiated : " + (new Date()) + "\n";
		}
		public String getTail(Handler h) {
			return "OtiNanai Logger Exiting : " + (new Date()) + "\n";
		}
	}

	private Logger logger;
}
