package main;

import java.io.IOException;

import org.hyperic.sigar.SigarException;

import load.CpuLoad;
import load.GpuLoad;
import logger.LogToGraphite;
import utils.Utils;

public class LoadMonitor {

	static Utils u;
	static CpuLoad cl;
	static GpuLoad gl;
	static LogToGraphite lg;
	
	public static void main(String[] args) throws InterruptedException, IOException, SigarException {
		
		u = new Utils();
		
		u.setUpEnvironment();
		lg = new LogToGraphite(u);
		
		getSystemCpuGpuLoad();
	}

	private static void getSystemCpuGpuLoad()
			throws InterruptedException, SigarException {
			String cpuName = "cpuLoad";
						
			cl = new CpuLoad(cpuName);
			cl.start(lg, u);
			
			if (u.getHasNvidia()) {
				String gpuName = "gpuLoad";
				gl = new GpuLoad(gpuName);
				gl.start(lg, u);
			}
	}
}
