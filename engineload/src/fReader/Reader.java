package fReader;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Reader {

	final static String FILE_NAME = "/home/andrew/test.ini";
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	
	public static void main(String[] args) {
	
		String host = "";
		
		try {
			List<String> lines = readSmallTextFile(FILE_NAME);
			
			for (String line : lines) {
				if (line.startsWith("#")) {
					continue;		
				}
				
			log(new String("I read : " + line));
			host = line;
			
				pinger(host);
				
			}
		} catch (IOException e) {
			log(new String("Something is wrong with : " + FILE_NAME));
			e.printStackTrace();
		}  
	}

	/**
	 * @param host
	 * @throws IOException
	 */
	private static void pinger(String host) throws IOException {
		try {
			InetAddress addr = InetAddress.getByName(host);
			log(addr.isReachable(5000) ? "I can ping : " + host : "I can not ping : " + host);
		} catch (UnknownHostException e) {
			log("I can not resolve : " + host);	
		}
	}
	
	  private static List<String> readSmallTextFile(String aFileName) throws IOException {
		    Path path = Paths.get(aFileName);
		    
		    return Files.readAllLines(path, ENCODING);
		  }

	  private static void log(Object aMsg){
		    System.out.println(String.valueOf(aMsg));
		  }
}
