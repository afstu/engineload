package smg;

import java.io.IOException;
import java.util.Calendar;
import java.util.Vector;

abstract class Load implements Runnable {

	private Thread t;
	protected Omgeving o;
	protected Vector<Metric> vector;
	private Calendar currenttime;
	protected Metric m;

	public void start(Metric m, Omgeving o, Vector<Metric> vector) throws IOException, InterruptedException {
		
		this.m = m;
		this.o = o;
		this.vector = vector;
		
		if (t == null) {
			t = new Thread(this);
			t.start();
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
