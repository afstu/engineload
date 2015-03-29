package utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
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
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		return timeStamp;
	}

	
	/**
	 * @param host
	 * @throws IOException
	 */
	public void pinger(String host) throws IOException {
		try {
			InetAddress addr = InetAddress.getByName(host);
			log(addr.isReachable(5000) ? "I can ping : " + host : "I can not ping : " + host);
		} catch (UnknownHostException e) {
			log("I can not resolve : " + host);	
		}
	}
	
	
}
