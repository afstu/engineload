package cpuload;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import utils.*;

public class Load {

	Utils u = new Utils();
	Sigar s = new Sigar();
	
	public Load() {

	}

	public double getCPU() throws SigarException {
		
		double load = Math.round(s.getCpuPerc().getCombined() * 100);
		
		return load;
		
	}
	
}