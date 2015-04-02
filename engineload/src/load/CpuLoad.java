package load;

import java.util.Arrays;

import logger.LogToGraphite;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import utils.Utils;

public class CpuLoad implements Runnable {

	Utils u;
	Thread t;
	String threadName;
	LogToGraphite lg;
	private Sigar s;
	private CpuPerc cpu;
	
	public CpuLoad(String name) {
		threadName = name;
	}

	public void start(LogToGraphite lg, Utils u) throws SigarException {

		this.u = u;
		this.s = new Sigar();
		this.lg = lg;
		
		if (t == null) {
			t = new Thread(this, threadName);
			u.log("Starting load monitor thread..." + t.getName());
			t.start();
		}
	}

	@Override
	public void run() {

		double[] idleCache = new double[12];
		double load = 0;
		double idle = 0;
		
		try {

			while (true) {
				
				for (int i = 0; i < 12; i++) {
					cpu = s.getCpuPerc();
					idleCache[i] = cpu.getIdle() * 100;
					Thread.sleep(5000);	
				}

				Arrays.sort(idleCache);
				idle = idleCache[idleCache.length-1];
				load = 100 - idle;
				lg.buildMetricAndWrite((int) load, "CPU");
			}
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SigarException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
