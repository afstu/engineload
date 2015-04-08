package load;

import java.util.Arrays;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import utils.Utils;

public class CpuLoad extends Load {

	Sigar s;
	CpuPerc cpu;

	public CpuLoad(String name, Utils utils) {
		super(name, utils);
	}

	@Override
	public void getLoad() {
		this.s = new Sigar();
		double[] idleCache = new double[12];
		double load = 0;
		// double idle = 0;
		while (true) {

			for (int i = 0; i < 12; i++) {
				try {
					cpu = s.getCpuPerc();
				} catch (SigarException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				idleCache[i] = cpu.getCombined() * 100;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}

			Arrays.sort(idleCache);
			load = idleCache[idleCache.length-1];
			
			buildMetricAndWrite((int) load, "CPU");
		}
	}
}