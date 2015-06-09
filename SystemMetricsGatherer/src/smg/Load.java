package smg;

import java.io.IOException;
import java.util.Calendar;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class Load.
 */
abstract class Load implements Runnable {

	/** The t. */
	private Thread t;
	
	/** The o. */
	protected Omgeving o;
	
	/** The vector. */
	protected Vector<Metric> vector;
	
	/** The currenttime. */
	private Calendar currenttime;
	
	/** The m. */
	protected Metric m;

	/**
	 * Start.
	 *
	 * @param m the m
	 * @param o the o
	 * @param vector the vector
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public void start(Metric m, Omgeving o, Vector<Metric> vector) throws IOException, InterruptedException {
		
		this.m = m;
		this.o = o;
		this.vector = vector;
		
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	/**
	 * Gets the epoch time stamp.
	 *
	 * @return the epoch time stamp
	 */
	protected String getEpochTimeStamp () {
		currenttime = Calendar.getInstance();		
		long time = currenttime.getTime().getTime() / 1000;
		
		return String.valueOf(time);
	}
			
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		getLoad();
	}

	/**
	 * Gets the load.
	 *
	 * @return the load
	 */
	public abstract void getLoad();

}
