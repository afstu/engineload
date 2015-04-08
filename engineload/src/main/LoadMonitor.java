package main;

import java.io.IOException;

import org.hyperic.sigar.SigarException;

import load.*;
import utils.Utils;

public class LoadMonitor {

	static Utils u;

	public static void main(String[] args) throws InterruptedException, IOException, SigarException {

		u = new Utils();

		u.setUpEnvironment(); 
		
		u.pinger(u.getHostPath().get(0));
		
		getSystemCpuGpuLoad();
	}

	private static void getSystemCpuGpuLoad()
			throws InterruptedException, SigarException, IOException  {
		String cpuName = "cpuLoad";
		
		Load cl = new CpuLoad(cpuName, u);
		cl.start();

		if (u.getHasNvidia()) {
			String gpuName = "gpuLoad";
			Load gl = new GpuLoad(gpuName, u);
			gl.start();
			
		}
	}
}
