/**
 * 
 */
package smg;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class Gatherer.
 *
 * @author Andrew
 */
public class Gatherer {

	/** The v. */
	public static Vector<Metric> v;
	
	/** The o. */
	public static Omgeving o;
	
	/** The m. */
	public static Metric m;
	
	/** The s. */
	private static Systeem s;	
	
	/** The Constant logger. */
	final static Logger logger = LogManager.getLogger(Gatherer.class);
	
	/** The Constant SIZE. */
	private final static int SIZE = 1;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		m = new Metric();
		v = new Vector<Metric>(SIZE);
		s = new Systeem();
		o = s.bouwOmgeving();

		try {
			startMetricVerzamelen();
		} catch (InterruptedException e) {
			logger.warn("Thread was interrupted while trying to get system load!");
		} catch (IOException e) {
			logger.error("SMG encountered an IO exception!");
			System.exit(1);
		}

		while (true) {
			try {
				haalMetricUitVector();
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				logger.warn("Something bad happened while waiting for metrics.");			}
		}
	}

	/**
	 * Haal metric uit vector.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
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

	/**
	 * Start metric verzamelen.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	private static void startMetricVerzamelen() throws IOException, InterruptedException {
		Load cl = new CpuLoad();
		cl.start(m, o, v);

		if (o.heeftGpu()) {
			Load gl = new GpuLoad();
			gl.start(m, o, v);
		}
	}

	/**
	 * Bouw metric.
	 *
	 * @param metric the metric
	 */
	private static void bouwMetric(Metric metric) {

		StringBuilder sb = new StringBuilder();

		// Graphite pad bouwen
		// uit de omgeving
		sb.append(o.getMetricPad()).append(".").append(o.getClusternaam()).append(".").append(o.getStatus())

		// uit het systeem
		.append(".").append(o.getSysteemnaam()).append(".")

		// uit de metric
		.append(metric.getType())

		// waarden voor Graphite
		.append(" ").append(metric.getWaarde())
		.append(" ").append(metric.getTijdStip())
		.append("\n");

		// logger.info(sb.toString());
		verstuurMetric(sb.toString());
	}

	/**
	 * Verstuur metric.
	 *
	 * @param completeMetric the complete metric
	 */
	private static void verstuurMetric(String completeMetric) {
		try {
			int port = o.getMetricPoort();
			Socket soc = new Socket(o.getMetricURL(), port);
			PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

			out.println(completeMetric);
			out.flush();
			out.close();
			soc.close();
			
		} catch (UnknownHostException e) {
			logger.warn("I can not resolve " + o.getMetricURL() + " !");
			logger.warn("Exiting...");
			System.exit(1);
		} catch (IOException e) {
			logger.warn("I can not open a socket to " + o.getMetricURL() + " on port " + o.getMetricPoort());
			logger.warn("Exiting...");
			System.exit(1);
		}
	}
}
