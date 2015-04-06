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

		getSystemCpuGpuLoad();
	}

	private static void getSystemCpuGpuLoad()
			throws InterruptedException, SigarException, IOException {
		String cpuName = "cpuLoad";

		Load cl = new CpuLoad(cpuName);
		cl.start(u);

		if (u.getHasNvidia()) {
			String gpuName = "gpuLoad";
			Load gl = new GpuLoad(gpuName);
			gl.start(u);
		}
	}
}
