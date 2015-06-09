package hmg;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceData.
 */
public class ServiceData {

	/** The Session id. */
	private long SessionID;
	
	/** The invocation id. */
	private long[] invocationID;
	
	/** The Session name. */
	private String SessionName;
	
	/**
	 * Instantiates a new service data.
	 */
	public ServiceData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	public long getSessionID() {
		return SessionID;
	}

	/**
	 * Sets the session id.
	 *
	 * @param sessionID the new session id
	 */
	public void setSessionID(long sessionID) {
		SessionID = sessionID;
	}

	/**
	 * Gets the session name.
	 *
	 * @return the session name
	 */
	public String getSessionName() {
		return SessionName;
	}

	/**
	 * Sets the session name.
	 *
	 * @param sessionName the new session name
	 */
	public void setSessionName(String sessionName) {
		SessionName = sessionName;
	}

	/**
	 * Gets the invocation id.
	 *
	 * @return the invocation id
	 */
	public long[] getInvocationID() {
		return invocationID;
	}

	/**
	 * Sets the invocation id.
	 *
	 * @param invocationID the new invocation id
	 */
	public void setInvocationID(long[] invocationID) {
		this.invocationID = invocationID;
	}
	
	/**
	 * Adds the invocation id.
	 *
	 * @param invocationID the invocation id
	 */
	public void addInvocationID(long invocationID) {
		
		int l = this.invocationID.length;
		
		if (l == 0) {
			this.invocationID[l] = invocationID;
		} else {
			this.invocationID[l + 1] = invocationID;
		}
	}

}
