package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {

	private Boolean win = false;
	private Boolean lin = false;
	private Boolean sol = false;
	private Boolean hasNvidia = false;
	
	private final String WIN_SMI = "C:\\Program Files\\NVIDIA Corporation\\NVSMI\\nvidia-smi.exe";
	private final ArrayList<String> WIN_SMI_NUMGPU = new ArrayList<String>(Arrays.asList("C:\\Program Files\\NVIDIA Corporation\\NVSMI\\nvidia-smi.exe", "-L"));
	private final ArrayList<String> WIN_SMI_LOADGPU = new ArrayList<String>(Arrays.asList("C:\\Program Files\\NVIDIA Corporation\\NVSMI\\nvidia-smi.exe", "-i"));
	private final String LIN_SMI = "/usr/bin/nvidia-smi";
	private final ArrayList<String> LIN_SMI_NUMGPU = new ArrayList<String>(Arrays.asList("/usr/bin/nvidia-smi", "-L"));
	private final ArrayList<String> LIN_SMI_LOADGPU = new ArrayList<String>(Arrays.asList("/usr/bin/nvidia-smi", "-i"));
	private final ArrayList<String> WIN_PING = new ArrayList<String>(Arrays.asList("ping", "-n", "1"));
	private final ArrayList<String> LIN_PING = new ArrayList<String>(Arrays.asList("ping", "-c", "1"));
	private final ArrayList<String> SOL_PING = new ArrayList<String>(Arrays.asList("/usr/sbin/ping"));
	
	private final String CONF_FILE = "conf/load.ini";
	private final String LIBS_DIR = "libs/";
	
	private final Charset ENCODING = StandardCharsets.UTF_8;
	
	public Utils() {
		
	}
	
	public void setUpEnvironment() {
		log("Detecting my environment...");
		
		checkConfLibs();
		setLocalOs();
		getNvidia();	
		
		log("Detection complete...");
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
	public List<String> getWIN_SMI_NUMGPU() {
		return WIN_SMI_NUMGPU;
	}
	public List<String> getWIN_SMI_LOADGPU() {
		return WIN_SMI_LOADGPU;
	}
	public String getLIN_SMI() {
		return LIN_SMI;
	}
	public List<String> getLIN_SMI_NUMGPU() {
		return LIN_SMI_NUMGPU;
	}
	public List<String> getLIN_SMI_LOADGPU() {
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
	public List<String> getWIN_PING() {
		return WIN_PING;
	}

	public List<String> getLIN_PING() {
		return LIN_PING;
	}
	
	public ArrayList<String> getSOL_PING() {
		return SOL_PING;
	}
	
	private void checkConfLibs() {
		try {
			if (new File(getConfFile()).isFile()) {
				// log("I have a configuration file...");
				
			} else {
				log("Bad configuration file..." + getConfFile());
			}
			
			if(new File(getLibsDir()).isFile()) {
				// log("I have a libs directory...");
			} else {
				log("Bad libs dir..." + getLibsDir());
			}
		} catch (Exception e) {
			log("There is a problem accessing the configuration file " + getConfFile() + " or the library directory " + getLibsDir() );
		}
		
	}
	
	private void setLocalOs() {
		 	String osName= System.getProperty("os.name");
		 	
		 	if (osName.startsWith("Linux")) {
		 		setLin(true);
		 	}
		 	
		 	if (osName.startsWith("Windows")) {
		 		setWin(true);
		 		}
		 	
		 	if (osName.startsWith("SunOS")) {
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
	
	public String getEpochTimeStamp () {
		Calendar currenttime = Calendar.getInstance();
		
		long time = currenttime.getTime().getTime() / 1000;
		
		return String.valueOf(time);
	}
	
	/**
	 * @param host
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public void pinger(String host) throws IOException, InterruptedException {
		
		List<String> command = null;
		
		try { 	
		 	if (getLin()) {
				command = getLIN_PING();
				command.add(host);
		 	}
		 	
		 	if (getWin()) {
				command = getWIN_PING();
				command.add(host);
		 	}	
		 	
		 	if (getSol()) {
		 		command = getSOL_PING();
		 		command.add(host);
		 	}
		 	
			Process p = new ProcessBuilder(command).start();
			int val = p.waitFor();
			
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			
			String result;
			
			if ((result = stdErr.readLine()) != null) {
				
				log(result);
			}
			
			stdIn.close();
			stdErr.close();
			
			log( val == 0 ? "I can ping " + host : "I can not ping " + host); 
		 	
		} catch (Exception e) {
			log("Something went wrong trying to ping " + host);
			log("Exiting...");
			log(e);
			System.exit(1);
		}
	}
	
	public List<String> getNodePortPathEnvFromConf() {

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
					
					if (line.startsWith("PORT")) {
						String[] parts = line.split(":");
						hostPath.add(parts[1]);					
					}
					
					if (line.startsWith("PATH")) {
						String[] parts = line.split(":");
						hostPath.add(parts[1]);					
					}
					
					if (line.startsWith("CLUSTER")) {
						String[] parts = line.split(":");
						hostPath.add(parts[1]);					
					}
					
					if (line.startsWith("ENV")) {
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
	
	public String getMyHostName() {
		
		String name = "foo";
		
		try {
			name =  InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			log("I can't get my own hostname!");
			log("Exiting...");
			System.exit(1);
		}
		return name;
	}
	
	
	  private List<String> readSmallTextFile(String aFileName) throws IOException {
		    Path path = Paths.get(aFileName);
		    
		    return Files.readAllLines(path, ENCODING);
		  }

}
