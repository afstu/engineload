package main;

import java.io.IOException;
import java.util.List;

import org.hyperic.sigar.SigarException;

import cpuload.Load;
import fReader.Reader;
import utils.Utils;

public class el {

	static Utils u = new Utils();
	static Load l = new Load();
	static Reader r = new Reader();
	
	public static void main(String[] args) throws SigarException, InterruptedException {
		
		getPersistenceNode();
		getSystemCpuLoad();
		
	}
	
	private static void getPersistenceNode() {
		
		try {
			
			List<String> myInfo = r.getPersistenceNodeFromConf();
			
			String myHost = myInfo.get(0);
			String myPath = myInfo.get(1);
			
			if (!myHost.isEmpty() && !myPath.isEmpty()) {
				u.log("I will log to : " + myHost + " : " + myPath);
			}
			
		} catch (Exception e) {
			u.log("An error happened while reading from the conf file!");
			System.exit(1);
		}
	}

	private static void getSystemCpuLoad()
			throws SigarException, InterruptedException {
		for ( int i = 0 ; i < 10 ; i++ ) {
			u.log("My load is : " + l.getCPU());
			Thread.sleep(1000);
		}
	}

}
