package hmg;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VerwerkMetrics  implements Runnable  {
	private Thread t;
	private ConcurrentLinkedQueue<Metric> queue;
	final static Logger logger = Logger.getLogger(VerwerkMetrics.class.toString());
	private GraphiteGegevens gg;
	private DriverGegevens d;

	public VerwerkMetrics() {

	}

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

	private void haalMetricUitQueue() {
		Metric m;
		while ((m = queue.poll()) != null ) {
					bouwMetric(m);
		}
	}

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
