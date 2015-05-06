package hmg;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Vector;

	/**
	 * @author Andrew
	 *
	 */
	public class Gatherer {

		public static Vector<Metric> v;
		public static Metric m;
		public static GraphiteGegevens gg;
		public static ArrayList<DriverGegevens> dg;
		final static Logger logger = LogManager.getLogger(Gatherer.class);
		private final static int SIZE = 20;
		
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			
			
			v = new Vector<Metric>(SIZE);
			gg = new GraphiteGegevens();
			dg = new ArrayList<DriverGegevens>();
			
			try {
				startMetricVerzamelen();
			} catch (InterruptedException e) {
				logger.warn("Thread was interrupted while trying to get HPC metrics!");
			} catch (IOException e) {
				logger.error("HMG encountered an IO exception!");
				System.exit(1);
			}

			while (true) {
				try {
					haalMetricUitVector();
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					logger.warn("Something bad happened while waiting for HPC metrics.");			}
			}
		}

		private synchronized static void haalMetricUitVector() 
				throws InterruptedException { 
		Metric metric = null;
		
			try { 
				if (! v.isEmpty() ) {
					metric = v.get(0); 
					v.clear(); 
					v.notifyAll();
				} else {			
					return;
				}

			} catch (IllegalMonitorStateException e) {
				e.getStackTrace();
			}
			if (metric != null) {
				bouwMetric(metric);
			}
		} 

		private static void startMetricVerzamelen() throws IOException, InterruptedException {
			
			for (DriverGegevens d : dg) {
				
				m = new Metric();
				HPCMetric hm = new HPCMetric();
				hm.start(m, d, v);
			}
		}

		private static void bouwMetric(Metric metric) {

			StringBuilder sb = new StringBuilder();

			// Graphite pad bouwen
			// uit Graphite Gegevens
			sb.append(gg.getGraphitePad())

			// waarden voor Graphite
			.append(" ").append(metric.getWaarde())
			.append(" ").append(metric.getTijdStip())
			.append("\n");

			// logger.info(sb.toString());
			verstuurMetric(sb.toString());
		}

		private static void verstuurMetric(String completeMetric) {
			try {
				int port = gg.getGraphitePoort();
				Socket soc = new Socket(gg.getGraphiteUrl(),port);
				PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

				out.println(completeMetric);
				out.flush();
				out.close();
				soc.close();
				
			} catch (UnknownHostException e) {
				logger.warn("I can not resolve " + gg.getGraphiteUrl() + " !");
				logger.warn("Exiting...");
				System.exit(1);
			} catch (IOException e) {
				logger.warn("I can not open a socket to " + gg.getGraphiteUrl() + " on port " + gg.getGraphitePoort());
				logger.warn("Exiting...");
				System.exit(1);
			}
		}

}
