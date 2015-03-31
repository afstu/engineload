package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {

	Boolean win = false;
	Boolean lin = false;
	Boolean sol = false;
	Boolean hasNvidia = false;
	
	final String WIN_SMI = "C:\\Program Files\\NVIDIA Corporation\\NVSMI\\nvidia-smi.exe";
	final String WIN_SMI_NUMGPU = "C:\\Program Files\\NVIDIA Corporation\\NVSMI\\nvidia-smi.exe -L";
	final String WIN_SMI_LOADGPU = "C:\\Program Files\\NVIDIA Corporation\\NVSMI\\nvidia-smi.exe -i";
	final String LIN_SMI = "/usr/bin/nvidia-smi";
	final String LIN_SMI_NUMGPU = "/usr/bin/nvidia-smi -L";
	final String LIN_SMI_LOADGPU = "/usr/bin/nvidia-smi -i";
	final String WIN_PING = "ping -n 1";
	final String LIN_PING = "ping -c 1";
	
	final String CONF_FILE = "conf/load.ini";
	final String LIBS_DIR = "libs/";
	
	final Charset ENCODING = StandardCharsets.UTF_8;
	
	public Utils() {
	
	}
	
	public void setUpEnvironment() {
		log("Detecting my environment...");
		
		checkConfLibs();
		setLocalOs();
		getNvidia();	
		getNodeAndPathFromConf();
		
		log("Complete. Running load monitor...");
	}

	public String getConfFile() {
		return CONF_FILE;
	}
	public String getLibsDir() {
		return LIBS_DIR;
	}
	public String getWIN_SMI() {
		return WIN_SMI;
	}
	public String getWIN_SMI_NUMGPU() {
		return WIN_SMI_NUMGPU;
	}
	public String getWIN_SMI_LOADGPU() {
		return WIN_SMI_LOADGPU;
	}
	public String getLIN_SMI() {
		return LIN_SMI;
	}
	public String getLIN_SMI_NUMGPU() {
		return LIN_SMI_NUMGPU;
	}
	public String getLIN_SMI_LOADGPU() {
		return LIN_SMI_LOADGPU;
	}
	public Boolean getWin() {
		return win;
	}
	public Boolean getLin() {
		return lin;
	}
	public Boolean getSol() {
		return sol;
	}
	public Boolean getHasNvidia() {
		return hasNvidia;
	}
	public void setHasNvidia(Boolean hasNvidia) {
		this.hasNvidia = hasNvidia;
	}
	private void setWin(Boolean win) {
		this.win = win;
	}
	private void setLin(Boolean lin) {
		this.lin = lin;
	}
	private void setSol(Boolean sol) {
		this.sol = sol;
	}
	public String getWIN_PING() {
		return WIN_PING;
	}

	public String getLIN_PING() {
		return LIN_PING;
	}
	
	private void checkConfLibs() {
		try {
			if (new File(getConfFile()).isFile()) {
				log("I have a configuration file...");
			}
			
			if(new File(getLibsDir()).isFile()) {
				log("I have a libs directory...");
			}
		} catch (Exception e) {
			log("There is a problem accessing the configuration file " + getConfFile() + " or the library directory " + getLibsDir() );
		}
		
	}
	
	private void setLocalOs() {
		 	String osName= System.getProperty("os.name");
		 	
		 	if (osName.startsWith("Linux")) {
		 		setLin(true);
		 	} else if (osName.startsWith("Windows")) {
		 		setWin(true);
		 	} else {
		 		setSol(true);
		 	}
	}
	
	private void getNvidia() {
		if (getLin() && new File(getLIN_SMI()).isFile() ) {
			setHasNvidia(true);
		}
		else if (getWin() && new File(getWIN_SMI()).isFile() ) {
			setHasNvidia(true);
			}
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
	
	private String getEpochTimeStamp () {
		Calendar currenttime = Calendar.getInstance();
		
		long time = currenttime.getTime().getTime() / 1000;
		
		return String.valueOf(time);
	}
	
	/**
	 * @param host
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	private void pinger(String host) throws IOException, InterruptedException {
		
		ArrayList<String> command = new ArrayList<String>();
		
		try { 	
		 	if (getLin() || getSol()) {
				command.add(getLIN_PING());
				command.add(host);
		 	} else if (getWin()) {
				command.add(getWIN_PING());
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
			log("Something went wrong trying to ping " + host);
			log("Exiting...");
			System.exit(1);
		}
	}
	
	private List<String> getNodeAndPathFromConf() {

		List<String> hostPath = new ArrayList<String>();
		
		try {
			List<String> lines = readSmallTextFile(getConfFile());
			
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
				
			return hostPath;
				
		} catch (IOException e) {
			log("Something is wrong with : " + getConfFile());
			log("Exiting...");
			System.exit(1);
		}
		return null;
	}
	
	  private List<String> readSmallTextFile(String aFileName) throws IOException {
		    Path path = Paths.get(aFileName);
		    
		    return Files.readAllLines(path, ENCODING);
		  }

}
