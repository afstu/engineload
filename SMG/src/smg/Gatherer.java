/**
 * 
 */
package smg;

import java.io.DataOutputStream;
import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.nio.charset.Charset;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Andrew
 *
 */
public class Gatherer {

	public static Vector<Metric> v;
	public static Omgeving o;
	public static Metric m;
	private static Systeem s;	
	final static Logger logger = Logger.getLogger(Gatherer.class.toString());
	private final static int SIZE = 1;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		m = new Metric();
		v = new Vector<Metric>(SIZE);
		s = new Systeem();
		o = s.bouwOmgeving();

		try {
			startMetricVerzamelen();
		} catch (InterruptedException e) {
			logger.log(Level.SEVERE, "Thread was interrupted while trying to get system load!");
			
		} catch (IOException e) {
			logger.log(Level.SEVERE, "SMG encountered an IO exception!");
			System.exit(1);
		}

		while (true) {
			try {
				haalMetricUitVector();
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				logger.log(Level.SEVERE, "Something bad happened while waiting for metrics.");
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
		Load cl = new CpuLoad();
		cl.start(m, o, v);

		if (o.heeftGpu()) {
			Load gl = new GpuLoad();
			gl.start(m, o, v);
		}
	}

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

		// logger.log(Level.INFO, sb.toString());
		verstuurMetric(sb.toString());
	}

	private static void verstuurMetric(String completeMetric) {
		try {
			int port = o.getMetricPoort();
			String graphiteHost = o.getMetricURL();
			//Socket soc = new Socket(o.getMetricURL(), port);
			InetAddress addr = InetAddress.getByName(graphiteHost);
			
//			logger.log(Level.INFO, " Sending metric :" );
//			logger.log(Level.INFO, completeMetric );
//			logger.log(Level.INFO, " to :" );
//			logger.log(Level.INFO, " Host: " + graphiteHost + " inet: " + addr + " port " + port );
			
			
			Socket soc = new Socket(addr.getHostAddress(), port);
			//byte[] buf = completeMetric.getBytes(Charset.forName("UTF-8"));
			//DatagramPacket pac = new DatagramPacket(buf, buf.length, addr, port);
			//DatagramSocket soc =  new DatagramSocket();
			//soc.send(pac);
			// PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
			
			DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
			dos.writeBytes(completeMetric);
			//out.println(completeMetric);
			//out.flush();
			//out.close();
			soc.close();
			
		} catch (UnknownHostException e) {
			logger.log(Level.SEVERE, "I can not resolve " + o.getMetricURL() + " ! Exiting...");
			System.exit(1);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "I can not open a socket to " + o.getMetricURL() + " on port " + o.getMetricPoort() + "! Exiting...");
			System.exit(1);
		}
	}
}
