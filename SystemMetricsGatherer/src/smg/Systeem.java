package smg;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Systeem {

	private enum Files {
	//	WIN ("O:\\HPCTeam\\Apps\\conf\\load.ini", "O:\\HPCTeam\\Apps\\libs"),
		WIN ("conf\\load.ini", "libs"),
		SOL ("/opt/datasynapse/HPCTeam/Apps/conf/load.ini", "/opt/datasynapse/HPCTeam/Apps/libs"),
	//	LIN ("/home/gsadm/HPCTeam/conf/load.ini", "/home/gsadm/HPCTeam/libs");
		LIN ("./conf/load.ini", "./libs");

		private final String conf;
		private final String libs;

		Files (String conf, String libs) {
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

	private String SysteemNaam;
	private String OsType;
	private boolean HeeftGpu = false;
	private String localConf;
	private String localLibs;
	private String localSmi;

	public Systeem() {
		setLocalOs();
		getMyHostName();
	}

	private void setLocalOs() {
		String osName= System.getProperty("os.name");

		if (osName.startsWith("Linux")) {
			setOsType("Linux");
			setLocalConf(Files.LIN.conf());
			setLocalLibs(Files.LIN.libs());

			try {
				if (new File(Smi.LIN.path()).isFile()) {
					setHeeftGpu(true);
					setLocalSmi(Smi.LIN.path());
				}
			} catch (NullPointerException e) {
				Logger.getLogger("log").warning("Path to Smi was null!");
			}
		}

		if (osName.startsWith("Windows")) {
			setOsType("Windows");
			setLocalConf(Files.WIN.conf());
			setLocalLibs(Files.WIN.libs());

			try {
				if (new File(Smi.WIN.path()).isFile()) {
					setHeeftGpu(true);
					setLocalSmi(Smi.WIN.path());
				}
			} catch (NullPointerException e) {
				Logger.getLogger("log").warning("Path to Smi was null!");
			}
		}

		if (osName.startsWith("SunOS")) {
			setOsType("Solaris");
			setLocalConf(Files.SOL.conf());
			setLocalLibs(Files.SOL.libs());
		}
	}

	public void getMyHostName() {
		try {
			
			String name = InetAddress.getLocalHost().getHostName();
			
			if (getOsType().equalsIgnoreCase("Windows") && name.contains(".")) {
				String[] win = name.split(".");
				name = win[0];
			}
			
			setSysteemNaam(name);
			
		} catch (UnknownHostException e) {
			Logger.getAnonymousLogger().severe("I can't get my own hostname!");
			Logger.getAnonymousLogger().severe("Exiting...");
			System.exit(1);
		}
	}

	public String getSysteemNaam() {
		return SysteemNaam;
	}

	public void setSysteemNaam(String systeemNaam) {
		SysteemNaam = systeemNaam;
	}

	public String getOsType() {
		return OsType;
	}

	public void setOsType(String osType) {
		OsType = osType;
	}

	public boolean heeftGpu() {
		return HeeftGpu;
	}

	public void setHeeftGpu(boolean heeftGpu) {
		HeeftGpu = heeftGpu;
	}

	public String getLocalConf() {
		return localConf;
	}

	public void setLocalConf(String localConf) {
		this.localConf = localConf;
	}

	public String getLocalLibs() {
		return localLibs;
	}

	public void setLocalLibs(String localLibs) {
		this.localLibs = localLibs;
	}
	
	public String getLocalSmi() {
		return localSmi;
	}

	public void setLocalSmi(String localSmi) {
		this.localSmi = localSmi;
	}

}
