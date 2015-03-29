package cpuload;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import utils.*;

public class Load {

	Utils u = new Utils();
	Sigar s = new Sigar();
	
	public Load() {

	}

	public double getCPU() throws SigarException, InterruptedException {
		
		double[] loadCache = new double[12];
		
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
			
		return max;
	}
}
