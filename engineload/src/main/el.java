package main;

import java.io.IOException;

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
			String myHost = r.getPersistenceNodeFromConf().get(0);
			String myPath = r.getPersistenceNodeFromConf().get(1);
			
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
			Thread.sleep(3000);
		}
	}

}
