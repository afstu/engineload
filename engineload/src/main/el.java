package main;

import java.util.List;

import org.hyperic.sigar.SigarException;

import cpuload.cpuLoad;
import fReader.Reader;
import gpuload.gpuLoad;
import utils.Utils;

public class el {

	static Utils u = new Utils();
	static cpuLoad cl;
	static gpuLoad gl;
	static Reader r = new Reader();
	
	public static void main(String[] args) throws SigarException, InterruptedException {
		
		getPersistenceNode();
		getSystemCpuGpuLoad();
		
	}
	
	private static void getPersistenceNode() {
		
		try {
			
			List<String> myInfo = r.getPersistenceNodeFromConf(u);
			
			String myHost = myInfo.get(0);
			String myPath = myInfo.get(1);
			
			if (!myHost.isEmpty() && !myPath.isEmpty()) {
				u.log("I could log to : " + myHost + " : " + myPath);
			}
			
		} catch (Exception e) {
			u.log("An error happened while reading from the conf file!");
			System.exit(1);
		}
	}

	private static void getSystemCpuGpuLoad()
			throws SigarException, InterruptedException {
			String cpuName = "cpuLoad";
			String gpuName = "gpuLoad";
			
			cl = new cpuLoad(cpuName);
			cl.start();
			
			gl = new gpuLoad(gpuName);
			gl.start();

	}
}
