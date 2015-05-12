package hmg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Andrew
 *
 */

public class Gatherer {

	final static Logger logger = Logger.getLogger(Gatherer.class.toString());

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			startMetricVerzamelen();
		} catch (InterruptedException e) {
			logger.log(Level.SEVERE,"Thread was interrupted while trying to get HPC metrics!");
		} catch (IOException e) {
			logger.log(Level.SEVERE,"HMG encountered an IO exception!");
			System.exit(1);
		}
	}

	private static void startMetricVerzamelen() throws IOException, InterruptedException {
		
		ArrayList<DriverGegevens> dg = new ArrayList<DriverGegevens>();
		ConcurrentLinkedQueue<Metric> queue = new ConcurrentLinkedQueue<Metric>();
		DriverGegevens testDrv = new DriverGegevens();

		testDrv.setHPCDirectorUrl("director1");
		testDrv.setHPCDirectorStatus("D");
		testDrv.setHPCDirectorPoort(8000);
		testDrv.setDriverUser("admin");
		testDrv.setDriverPassword("admin");

		dg.add(testDrv);

		for (DriverGegevens d : dg) {
			HPCMetric hm = new HPCMetric();
			hm.start(d, queue);
			
			VerwerkMetrics v = new VerwerkMetrics();
			v.start(d, queue);	
		}
	}
}
