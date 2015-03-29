package fReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import utils.*;

public class Reader {

	// final static String FILE_NAME = "/home/andrew/test.ini";
	final static String FILE_NAME = "C:\\Users\\Andrew\\Dropbox\\Eclipse\\conf\\load.ini";
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	Utils u = new Utils();
	
	public Reader() {
		
	}

	public List<String> getPersistenceNodeFromConf() {

		
		List<String> hostPath = new ArrayList<String>();
		
		try {
			List<String> lines = readSmallTextFile(FILE_NAME);
			
			for (String line : lines) {
				
				if (line.startsWith("#")) {
					continue;
				}
				
					if (line.startsWith("NODE")) {
						String[] parts = line.split(":");
						hostPath.add(parts[1]);					
					}
					
					if (line.startsWith("PATH")) {
						String[] parts = line.split(":");
						hostPath.add(parts[1]);					
					}
				}
				
				u.pinger(hostPath.get(0));
				
				return hostPath;
				
		} catch (IOException e) {
			u.log("Something is wrong with : " + FILE_NAME);
		}
		return null;
	}
	
	  private List<String> readSmallTextFile(String aFileName) throws IOException {
		    Path path = Paths.get(aFileName);
		    
		    return Files.readAllLines(path, ENCODING);
		  }


}
