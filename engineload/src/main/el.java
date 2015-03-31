package main;

import load.cpuLoad;
import load.gpuLoad;
import utils.Utils;

public class el {

	static Utils u = new Utils();
	static cpuLoad cl;
	static gpuLoad gl;
	
	public static void main(String[] args) throws InterruptedException {
		u.setUpEnvironment();
		getSystemCpuGpuLoad();
	}

	private static void getSystemCpuGpuLoad()
			throws InterruptedException {
			String cpuName = "cpuLoad";
			String gpuName = "gpuLoad";
			
			cl = new cpuLoad(cpuName);
			cl.start();
			
			gl = new gpuLoad(gpuName);
			gl.start();
	}
}
