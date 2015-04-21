package smg;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.logging.Logger;

abstract class Load implements Runnable {

	Thread t;
	Calendar currenttime;	
	Omgeving o;

	public void start(Omgeving o) throws IOException, InterruptedException {
		
		this.o = o;
		
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	protected void buildMetricAndWrite(Metric metric) {
		
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
		
		Logger.getAnonymousLogger().info(sb.toString());
		writeGraphite(sb.toString());
	}

	private void writeGraphite(String completeMetric) {
		try {
			int port = o.getMetricPoort();
			Socket soc = new Socket(o.getMetricURL(), port);
			PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

			out.println(completeMetric);
			out.flush();
			out.close();
			soc.close();
		} catch (UnknownHostException e) {
			Logger.getAnonymousLogger().warning("I can not resolve " + o.getMetricURL() + " !");
			Logger.getAnonymousLogger().severe("Exiting...");
			System.exit(1);
		} catch (IOException e) {
			Logger.getAnonymousLogger().severe("I can not open a socket to " + o.getMetricURL() + " on port " + o.getMetricPoort());
			Logger.getAnonymousLogger().severe("Exiting...");
			System.exit(1);
		}
	}

	protected String getEpochTimeStamp () {
		currenttime = Calendar.getInstance();		
		long time = currenttime.getTime().getTime() / 1000;
		
		return String.valueOf(time);
	}
	
	@Override
	public void run() {
		getLoad();
	}

	public abstract void getLoad();

}
