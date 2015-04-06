package main;

import java.io.IOException;

import org.hyperic.sigar.SigarException;

import load.*;
import logger.LogToGraphite;
import utils.Utils;

public class LoadMonitor {

	static Utils u;
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
			
			Load cl = new CpuLoad(cpuName);
			cl.start(lg, u);
			
			if (u.getHasNvidia()) {
				String gpuName = "gpuLoad";
				Load gl = new GpuLoad(gpuName);
				gl.start(lg, u);
			}
	}
}
