package hmg;

public class DriverGegevens {

	private String DriverUser;
	private String DriverPassword;
	private String HPCDirectorUrl;
	private int HPCDirectorPoort;
	private String HPCDirectorStatus;
	
	
	public DriverGegevens() {
		
	}
	
	public String getDriverUser() {
		return DriverUser;
	}


	public void setDriverUser(String driverUser) {
		DriverUser = driverUser;
	}


	public String getDriverPassword() {
		return DriverPassword;
	}


	public void setDriverPassword(String driverPassword) {
		DriverPassword = driverPassword;
	}


	public String getHPCDirectorUrl() {
		return HPCDirectorUrl;
	}


	public void setHPCDirectorUrl(String hPCDirectorUrl) {
		HPCDirectorUrl = hPCDirectorUrl;
	}

	public int getHPCDirectorPoort() {
		return HPCDirectorPoort;
	}

	public void setHPCDirectorPoort(int hPCDirectorPoort) {
		HPCDirectorPoort = hPCDirectorPoort;
	}

	public String getHPCDirectorStatus() {
		return HPCDirectorStatus;
	}

	public void setHPCDirectorStatus(String string) {
		HPCDirectorStatus = string;
	}	
	
}
