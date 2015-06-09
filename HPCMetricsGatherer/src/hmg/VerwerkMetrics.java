package hmg;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class VerwerkMetrics.
 *
 * @author andrew
 */
public class VerwerkMetrics  implements Runnable  {
	
	/** The t. */
	private Thread t;
	
	/** The queue. */
	private ConcurrentLinkedQueue<Metric> queue;
	
	/** The Constant logger. */
	final static Logger logger = Logger.getLogger(VerwerkMetrics.class.toString());
	
	/** The gg. */
	private GraphiteGegevens gg;
	
	/** The d. */
	private DriverGegevens d;

	/**
	 * Instantiates a new verwerk metrics.
	 */
	public VerwerkMetrics() {

	}

	/**
	 * VerwerkMetrics draait als een thread. Deze class controleert de gedeelde queue
	 * wanneer metrics verschijnen worden deze naar Graphite gestuurd.
	 *
	 * @param d the d
	 * @param queue the queue
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public void start(DriverGegevens d, ConcurrentLinkedQueue<Metric> queue) throws IOException, InterruptedException {

		gg = new GraphiteGegevens();

		gg.setGraphitePad("SE.HPC.kvm");
		gg.setGraphiteUrl("graphite");
		gg.setGraphitePoort(2003);

		this.d = d;
		this.queue = queue;

		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			try {
			Thread.sleep(1000);
			haalMetricUitQueue();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Haal metric uit queue.
	 */
	private void haalMetricUitQueue() {
		Metric m;
		while ((m = queue.poll()) != null ) {
					bouwMetric(m);
		}
	}

	/**
	 * bouwMetric voorziet de ruwe metric van extra gegevens
	 * deze gegevens zorgen er voor dat de metric goed land in graphite.
	 *
	 * @param metric the metric
	 */
	private void bouwMetric(Metric metric) {

		StringBuilder sb = new StringBuilder();

		// Graphite pad bouwen uit Graphite en driver Gegevens
		sb.append(gg.getGraphitePad())
		.append(".").append(d.getHPCDirectorStatus())
		.append(".").append(metric.getBron())

		// waarden voor Graphite
		.append(".").append(metric.getType())
		.append(" ").append(metric.getWaarde())
		.append(" ").append(metric.getTijdStip())
		.append("\n");

		logger.log(Level.SEVERE,sb.toString());
		verstuurMetric(sb.toString());
	}
	
	
	/**
	 * verstuurMetric maakt een verbinding met Graphite en stuurt een ontvangen metric er naar toe.
	 *
	 * @param completeMetric the complete metric
	 */
	private void verstuurMetric(String completeMetric) {
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
