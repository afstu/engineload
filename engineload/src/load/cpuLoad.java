package load;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import utils.Utils;

public class cpuLoad implements Runnable {

	Sigar s;
	Utils u;
	Thread t;
	String threadName;
	
	public cpuLoad(String name) {
		threadName = name;
	}

	public void start() {
		
		u = new Utils();
		s = new Sigar();
		
		if (t == null) {
			t = new Thread(this, threadName);
			u.log("Starting load monitor thread..." + t.getName());
			t.start();
		}
	}
	
	@Override
	public void run() {
		
		double[] loadCache = new double[12];
		
		try {
			
			while (true) {
				for (int i = 0; i < 12; i++) {
			
				loadCache[i] = Math.round(s.getCpuPerc().getCombined() * 100);
				
					Thread.sleep(5000);	
			}
			
			double max = 0.0;
			
			for (int i = 0 ; i < loadCache.length ; i++) {
				
				if (max < loadCache[i]) {
					max = loadCache[i];
				}
			}
			
			u.log("The CPU load is : " + (int) max);
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SigarException s) {
			// TODO Auto-generated catch block
			s.printStackTrace();
		}
	}
}
