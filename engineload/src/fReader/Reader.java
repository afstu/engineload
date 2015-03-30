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
	// final static String FILE_NAME = "C:\\Users\\Andrew\\Dropbox\\Eclipse\\conf\\load.ini";
	// final static String FILE_NAME = "C:\\Users\\z752c41\\Apps\\conf\\load.ini";
	// final static String FILE_NAME = "/home/gsadmin/conf/load.ini";
	final static String FILE_NAME = "E:\\dev\\conf\\load.ini";
	final static Charset ENCODING = StandardCharsets.UTF_8;
		

	public Reader() {
		
	}

	public List<String> getPersistenceNodeFromConf(Utils u) {

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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	  private List<String> readSmallTextFile(String aFileName) throws IOException {
		    Path path = Paths.get(aFileName);
		    
		    return Files.readAllLines(path, ENCODING);
		  }


}
