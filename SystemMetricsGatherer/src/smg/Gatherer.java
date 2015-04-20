/**
 * 
 */
package smg;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Andrew
 *
 */
public class Gatherer {

	static Omgeving o;
	static Systeem s;
	final static Logger logger = LogManager.getLogger(Gatherer.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		logger.info("SMG starting...");
		
		s = new Systeem();
		o = new Omgeving(s.getLocalConf());
		
		try {
			getSystemLoad();
		} catch (InterruptedException e) {
			logger.warn("Thread was interrupted while trying to get system load!");
		} catch (IOException e) {
			logger.error("SMG encountered an IO exception!");
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static void getSystemLoad() throws IOException, InterruptedException {
		Load cl = new CpuLoad();
		cl.start(s, o);

		if (s.heeftGpu()) {
			Load gl = new GpuLoad();
			gl.start(s, o);
			
		}
	}
	
}
