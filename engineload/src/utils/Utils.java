package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utils {

	public Utils() {
		//Utils u = new Utils();
	}
	
	/**
	 * @param aMsg
	 * @throws 
	 */
	public void log(Object aMsg){
		System.out.println(getTimeStamp() + " : " + String.valueOf(aMsg));
	}
	
	private String getTimeStamp() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
		return timeStamp;
	}

	
	/**
	 * @param host
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public void pinger(String host) throws IOException, InterruptedException {
		
		try {
		 	
			ArrayList<String> command = new ArrayList<String>();
			String osName= System.getProperty("os.name");
		 	
		 	if (osName.startsWith("Linux")) {
				
				command.add("ping");
				command.add("-c");
				command.add("1");
				command.add(host);
		 	} else if (osName.startsWith("Windows")) {
				
				command.add("ping");
				command.add("-n");
				command.add("1");
				command.add(host);
		 	}	
		 	
			Process p = new ProcessBuilder(command).start();
			int val = p.waitFor();
			
			BufferedReader stdErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			String result;
			
			if ((result = stdErr.readLine()) != null) {
				
				log(result);
			}
			
			log( val == 0 ? "I can ping " + host : "I can not ping " + host); 
		 	
		} catch (Exception e) {
			 System.out.println("Exception caught ="+e.getMessage());
		}
	}
}
