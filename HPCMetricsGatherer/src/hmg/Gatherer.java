package hmg;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
	/**
	 * @author Andrew
	 *
	 */
	public class Gatherer {

		public static Vector<Metric> v;
		public static Metric m;
		public static GraphiteGegevens gg;
		public static ArrayList<DriverGegevens> dg;
		private final static int SIZE = 20;
		final static Logger logger = Logger.getLogger(Gatherer.class.toString());
		
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			
			v = new Vector<Metric>(SIZE);
			gg = new GraphiteGegevens();
			dg = new ArrayList<DriverGegevens>();
			
			DriverGegevens testDrv = new DriverGegevens();
			
			testDrv.setHPCDirectorUrl("director1");
			testDrv.setHPCDirectorPoort(8000);
			testDrv.setDriverUser("admin");
			testDrv.setDriverPassword("admin");
			
			dg.add(testDrv);
			
			gg.setGraphitePad("SE.HPC.kvm.D." + testDrv.getHPCDirectorUrl());
			gg.setGraphiteUrl("graphite");
			gg.setGraphitePoort(2003);
						
			
			try {
				startMetricVerzamelen();
			} catch (InterruptedException e) {
				logger.log(Level.SEVERE,"Thread was interrupted while trying to get HPC metrics!");
			} catch (IOException e) {
				logger.log(Level.SEVERE,"HMG encountered an IO exception!");
				System.exit(1);
			}

			while (true) {
				try {
					haalMetricUitVector();
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					logger.log(Level.SEVERE,"Something bad happened while waiting for HPC metrics.");
				}
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

			logger.log(Level.SEVERE,sb.toString());
			verstuurMetric(sb.toString());
		}

		private static void verstuurMetric(String completeMetric) {
			try {
				int port = gg.getGraphitePoort();
				Socket soc = new Socket(gg.getGraphiteUrl(),port);
				
				DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
				
				dos.writeBytes(completeMetric);
				soc.close();
				
			} catch (UnknownHostException e) {
				logger.log(Level.SEVERE,"I can not resolve " + gg.getGraphiteUrl() + "! Exiting...");
				System.exit(1);
			} catch (IOException e) {
				logger.log(Level.SEVERE,"I can not open a socket to " + gg.getGraphiteUrl() + " on port " + gg.getGraphitePoort() + " Exiting...");
				System.exit(1);
			}
		}

}
