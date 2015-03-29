package fReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import utils.*;

public class Reader {

	final static String FILE_NAME = "/home/andrew/test.ini";
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	
	public static void main(String[] args) {
	
		Utils u = new Utils();
		
		String host = "";
		
		try {
			List<String> lines = readSmallTextFile(FILE_NAME);
			
			for (String line : lines) {
				if (line.startsWith("#")) {
					continue;		
				}
				
			u.log(new String("I read : " + line));
			host = line;
			
				u.pinger(host);
				
			}
		} catch (IOException e) {
			u.log(new String("Something is wrong with : " + FILE_NAME));
			e.printStackTrace();
		}  
	}
	
	  private static List<String> readSmallTextFile(String aFileName) throws IOException {
		    Path path = Paths.get(aFileName);
		    
		    return Files.readAllLines(path, ENCODING);
		  }


}
