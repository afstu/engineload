package hmg;

public class EngineData {

	private String EngineName;
	private long ComputeTime;
	private long ServiceID;
	private String SessionName;
	
	public EngineData() {
		
	}

	public String getEngineName() {
		return EngineName;
	}

	public void setEngineName(String engineName) {
		EngineName = engineName;
	}

	public long getComputeTime() {
		return ComputeTime;
	}

	public void setComputeTime(long computeTime) {
		ComputeTime = computeTime;
	}

	public long getServiceID() {
		return ServiceID;
	}

	public void setServiceID(long serviceID) {
		ServiceID = serviceID;
	}

	public String getSessionName() {
		return SessionName;
	}

	public void setSessionName(String sessionName) {
		SessionName = sessionName;
	}

}
