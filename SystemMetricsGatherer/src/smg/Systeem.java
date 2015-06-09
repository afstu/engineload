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

// TODO: Auto-generated Javadoc
/**
 * The Class Systeem.
 */
public class Systeem {

	/**
	 * The Enum ConfigFiles.
	 */
	private enum ConfigFiles {
	//	WIN ("O:\\HPCTeam\\Apps\\conf\\load.ini", "O:\\HPCTeam\\Apps\\libs"),
		/** The win. */
	WIN ("conf\\load.ini", "libs"),
		
		/** The sol. */
		SOL ("/opt/datasynapse/HPCTeam/Apps/conf/load.ini", "/opt/datasynapse/HPCTeam/Apps/libs"),
	//	LIN ("/home/gsadm/HPCTeam/conf/load.ini", "/home/gsadm/HPCTeam/libs");
		/** The lin. */
	LIN ("./conf/load.ini", "./libs");

		/** The conf. */
		private final String conf;
		
		/** The libs. */
		private final String libs;

		/**
		 * Instantiates a new config files.
		 *
		 * @param conf the conf
		 * @param libs the libs
		 */
		ConfigFiles (String conf, String libs) {
			this.conf = conf;
			this.libs = libs;
		}

		/**
		 * Conf.
		 *
		 * @return the string
		 */
		private String conf() { return conf; }
		
		/**
		 * Libs.
		 *
		 * @return the string
		 */
		private String libs() { return libs; }
	}

	/**
	 * The Enum Smi.
	 */
	private enum Smi {
		
		/** The win. */
		WIN ("C:\\Program Files\\NVIDIA Corporation\\NVSMI\\nvidia-smi.exe"),
		
		/** The lin. */
		LIN ("/usr/bin/nvidia-smi");

		/** The path. */
		private final String path;

		/**
		 * Instantiates a new smi.
		 *
		 * @param path the path
		 */
		Smi (String path) {
			this.path = path;
		}

		/**
		 * Path.
		 *
		 * @return the string
		 */
		private String path() { return path; }
	}

	/** The Os type. */
	private String OsType;
	
	/** The local conf. */
	private String localConf;
	
	/** The local libs. */
	private String localLibs;
	
	/** The o. */
	private Omgeving o;
	
	/** The encoding. */
	private final Charset ENCODING = StandardCharsets.UTF_8;

	/**
	 * Instantiates a new systeem.
	 */
	public Systeem() {
		o = new Omgeving();
		setLocalOs();
		checkForGpu();
		haalSysteemNaam();
	}

	/**
	 * Sets the local os.
	 */
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
	 * Check for gpu.
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

	/**
	 * Haal systeem naam.
	 */
	public void haalSysteemNaam() {
		try {
			String name = InetAddress.getLocalHost().getHostName();
			ArrayList<String> hostOnly = new ArrayList<String>();
			
			if (name.contains(".")) {
				
				for (String s : name.split("\\.")) {
				 hostOnly.add(s);
			}
				
				if (!hostOnly.isEmpty()) {
					name = hostOnly.get(0);
				} 
		}
			
			o.setSysteemnaam(name);
			
		} catch (UnknownHostException e) {
			Logger.getAnonymousLogger().severe("I can't get my own hostname!");
			Logger.getAnonymousLogger().severe("Exiting...");
			System.exit(1);
		}
}
	
	/**
	 * Gets the node port path env from conf.
	 *
	 * @return the node port path env from conf
	 */
	private void getNodePortPathEnvFromConf() {

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

	/**
	 * Lees config file.
	 *
	 * @param file the file
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private List<String> leesConfigFile(String file) throws IOException {
		Path path = Paths.get(file);

		return Files.readAllLines(path, ENCODING);
		
	}

	/**
	 * Bouw omgeving.
	 *
	 * @return the omgeving
	 */
	public Omgeving bouwOmgeving() {

		getNodePortPathEnvFromConf();
		
		return o;
	}

	/**
	 * Gets the os type.
	 *
	 * @return the os type
	 */
	public String getOsType() {
		return OsType;
	}

	/**
	 * Sets the os type.
	 *
	 * @param osType the new os type
	 */
	public void setOsType(String osType) {
		OsType = osType;
	}

	/**
	 * Gets the config file.
	 *
	 * @return the config file
	 */
	public String getConfigFile() {
		return localConf;
	}

	/**
	 * Sets the config file.
	 *
	 * @param localConf the new config file
	 */
	public void setConfigFile(String localConf) {
		this.localConf = localConf;
	}

	/**
	 * Gets the libs dir.
	 *
	 * @return the libs dir
	 */
	public String getLibsDir() {
		return localLibs;
	}

	/**
	 * Sets the libs dir.
	 *
	 * @param localLibs the new libs dir
	 */
	public void setLibsDir(String localLibs) {
		this.localLibs = localLibs;
	}
}
