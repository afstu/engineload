package load;

import java.util.Arrays;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import logger.LogToGraphite;
import utils.Utils;

public class CpuLoad extends Load {

	Sigar s;
	CpuPerc cpu;
	
	public CpuLoad(String name) {
		super(name);
	}

	@Override
	public void getLoad() {
		this.s = new Sigar();
		double[] idleCache = new double[12];
		double load = 0;
		double idle = 0;
		while (true) {
			
			for (int i = 0; i < 12; i++) {
				try {
					cpu = s.getCpuPerc();
				} catch (SigarException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				idleCache[i] = cpu.getIdle() * 100;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}

			Arrays.sort(idleCache);
			idle = idleCache[idleCache.length-1];
			load = 100 - idle;
			lg.buildMetricAndWrite((int) load, "CPU");
		}
	}
}