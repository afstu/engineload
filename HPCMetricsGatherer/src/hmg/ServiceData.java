package hmg;

public class ServiceData {

	private long SessionID;
	private long[] invocationID;
	private String SessionName;
	
	public ServiceData() {
		// TODO Auto-generated constructor stub
	}

	public long getSessionID() {
		return SessionID;
	}

	public void setSessionID(long sessionID) {
		SessionID = sessionID;
	}

	public String getSessionName() {
		return SessionName;
	}

	public void setSessionName(String sessionName) {
		SessionName = sessionName;
	}

	public long[] getInvocationID() {
		return invocationID;
	}

	public void setInvocationID(long[] invocationID) {
		this.invocationID = invocationID;
	}
	
	public void addInvocationID(long invocationID) {
		
		int l = this.invocationID.length;
		
		if (l == 0) {
			this.invocationID[l] = invocationID;
		} else {
			this.invocationID[l + 1] = invocationID;
		}
	}

}
