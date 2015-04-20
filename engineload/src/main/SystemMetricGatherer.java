package main;

import java.io.File;
import java.io.IOException;

import org.hyperic.sigar.SigarException;

import load.*;
import utils.Utils;

public class SystemMetricGatherer {

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

		if (getNvidia()) {
			String gpuName = "gpuLoad";
			Load gl = new GpuLoad(gpuName, u);
			gl.start();
			
		}
	}
	
	private static boolean getNvidia() {
		if (u.getLin() && new File(u.getLIN_SMI()).isFile() ) {
			return true;
		}
		else if (u.getWin() && new File(u.getWIN_SMI()).isFile() ) {
			return true;
		}
		return false;
	}
}
