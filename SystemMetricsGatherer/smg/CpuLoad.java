package smg;

import java.util.Arrays;
import java.util.logging.Logger;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class CpuLoad extends Load {

	private Metric metric;
	private Sigar s;
	private CpuPerc cpu;

	public CpuLoad() {
		super();
		metric = new Metric();
	}

	@Override
	public synchronized void getLoad() {
		this.s = new Sigar();
		double[] idleCache = new double[12];

		while (true) {

			for (int i = 0; i < 12; i++) {
				try {
					cpu = s.getCpuPerc();
				} catch (SigarException e) {
					Logger.getAnonymousLogger().severe("Something bad happened while trying to run Sigar.");
				}

				idleCache[i] = cpu.getCombined() * 100;

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					Logger.getAnonymousLogger().severe("Something bad happened while waiting for the vector.");
				}	
			}

			Arrays.sort(idleCache);

			metric.setTijdStip(getEpochTimeStamp());
			metric.setType("CPU");
			metric.setWaarde((int) idleCache[idleCache.length-1]);

			while ( ! vector.isEmpty() ) {
				try {
					wait();
				} catch (InterruptedException e) {
					Logger.getAnonymousLogger().severe("Something bad happened while waiting for the vector.");
				} 
			}
			vector.add(0, metric);
			notifyAll();
		}
	}
}
