package load;

import logger.LogToGraphite;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import utils.Utils;

public class CpuLoad implements Runnable {

	Sigar s;
	Utils u;
	Thread t;
	String threadName;
	LogToGraphite lg;
	
	public CpuLoad(String name) {
		threadName = name;
	}

	public void start(LogToGraphite lg, Utils u) {
		
		this.u = u;
		s = new Sigar();
		this.lg = lg;
		
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
			
			lg.buildMetricAndWrite((int) max, "CPU");
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
