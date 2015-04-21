package smg;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Systeem {

	private enum ConfigFiles {
	//	WIN ("O:\\HPCTeam\\Apps\\conf\\load.ini", "O:\\HPCTeam\\Apps\\libs"),
		WIN ("conf\\load.ini", "libs"),
		SOL ("/opt/datasynapse/HPCTeam/Apps/conf/load.ini", "/opt/datasynapse/HPCTeam/Apps/libs"),
	//	LIN ("/home/gsadm/HPCTeam/conf/load.ini", "/home/gsadm/HPCTeam/libs");
		LIN ("./conf/load.ini", "./libs");

		private final String conf;
		private final String libs;

		ConfigFiles (String conf, String libs) {
			this.conf = conf;
			this.libs = libs;
		}

		private String conf() { return conf; }
		private String libs() { return libs; }
	}

	private enum Smi {
		WIN ("C:\\Program Files\\NVIDIA Corporation\\NVSMI\\nvidia-smi.exe"),
		LIN ("/usr/bin/nvidia-smi");

		private final String path;

		Smi (String path) {
			this.path = path;
		}

		private String path() { return path; }
	}

	private String OsType;
	private String localConf;
	private String localLibs;
	private Omgeving o;
	
	private final Charset ENCODING = StandardCharsets.UTF_8;

	public Systeem() {
		o = new Omgeving();
		setLocalOs();
		checkForGpu();
		haalSysteemNaam();
	}

	private void setLocalOs() {
		String osName= System.getProperty("os.name");

		if (osName.startsWith("Linux")) {
			setOsType("Linux");
			setConfigFile(ConfigFiles.LIN.conf());
			setLibsDir(ConfigFiles.LIN.libs());
		}

		if (osName.startsWith("Windows")) {
			setOsType("Windows");
			setConfigFile(ConfigFiles.WIN.conf());
			setLibsDir(ConfigFiles.WIN.libs());
		}

		if (osName.startsWith("SunOS")) {
			setOsType("Solaris");
			setConfigFile(ConfigFiles.SOL.conf());
			setLibsDir(ConfigFiles.SOL.libs());
		}
	}

	/**
	 * 
	 */
	private void checkForGpu() {
		try {
			if (new File(Smi.WIN.path()).isFile()) {
				o.setHeeftGpu(true);
				o.setSmi(Smi.WIN.path());
			}
			
			if (new File(Smi.LIN.path()).isFile()) {
				o.setHeeftGpu(true);
				o.setSmi(Smi.LIN.path());
			}
		} catch (NullPointerException e) {
			Logger.getLogger("log").warning("Path to Smi was null!");
		}
	}

	public void haalSysteemNaam() {
		try {
			
			String name = InetAddress.getLocalHost().getHostName();
			
			if (getOsType().equalsIgnoreCase("Windows") && name.contains(".")) {
				String[] win = name.split(".");
				name = win[0];
			}
			
			o.setSysteemnaam(name);
			
		} catch (UnknownHostException e) {
			Logger.getAnonymousLogger().severe("I can't get my own hostname!");
			Logger.getAnonymousLogger().severe("Exiting...");
			System.exit(1);
		}
	}
	
	public void getNodePortPathEnvFromConf() {

		List<String> lines = new ArrayList<String>();

		try {
			lines = leesConfigFile(getConfigFile());

			for (String line : lines) {

				if (line.startsWith("#")) {
					continue;
				}
				if (line.startsWith("NODE")) {
					String[] parts = line.split(":");
					o.setMetricURL(parts[1]);					
				}

				if (line.startsWith("PORT")) {
					String[] parts = line.split(":");
					o.setMetricPoort(Integer.parseInt(parts[1]));					
				}

				if (line.startsWith("PATH")) {
					String[] parts = line.split(":");
					o.setMetricPad(parts[1]);					
				}

				if (line.startsWith("CLUSTER")) {
					String[] parts = line.split(":");
					o.setClusternaam(parts[1]);					
				}

				if (line.startsWith("ENV")) {
					String[] parts = line.split(":");
					o.setStatus(parts[1].charAt(0));					
				}
			}
		} catch (IOException e) {
			Logger.getAnonymousLogger().severe("Something is wrong with the configuration file!");
			Logger.getAnonymousLogger().severe("Exiting...");
			System.exit(1);
		}
	}

	private List<String> leesConfigFile(String file) throws IOException {
		Path path = Paths.get(file);

		return Files.readAllLines(path, ENCODING);
		
	}

	public Omgeving bouwOmgeving() {

		getNodePortPathEnvFromConf();
		
		return o;
	}

	public String getOsType() {
		return OsType;
	}

	public void setOsType(String osType) {
		OsType = osType;
	}

	public String getConfigFile() {
		return localConf;
	}

	public void setConfigFile(String localConf) {
		this.localConf = localConf;
	}

	public String getLibsDir() {
		return localLibs;
	}

	public void setLibsDir(String localLibs) {
		this.localLibs = localLibs;
	}
}
