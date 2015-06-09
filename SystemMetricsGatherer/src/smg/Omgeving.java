package smg;

// TODO: Auto-generated Javadoc
/**
 * The Class Omgeving.
 */
public class Omgeving {

	/** The Systeemnaam. */
	private String Systeemnaam;
	
	/** The Clusternaam. */
	private String Clusternaam;
	
	/** The Status. */
	private char Status;
	
	/** The Metric url. */
	private String MetricURL;
	
	/** The Metric poort. */
	private int MetricPoort;
	
	/** The Metric pad. */
	private String MetricPad;
	
	/** The Smi. */
	private String Smi;
	
	/** The Heeft gpu. */
	private boolean HeeftGpu = false;
	
	/**
	 * Instantiates a new omgeving.
	 */
	public Omgeving() {
	}

	/**
	 * Gets the clusternaam.
	 *
	 * @return the clusternaam
	 */
	public String getClusternaam() {
		return Clusternaam;
	}

	/**
	 * Sets the clusternaam.
	 *
	 * @param clusternaam the new clusternaam
	 */
	public void setClusternaam(String clusternaam) {
		Clusternaam = clusternaam;
	}

	/**
	 * Gets the systeemnaam.
	 *
	 * @return the systeemnaam
	 */
	public String getSysteemnaam() {
		return Systeemnaam;
	}

	/**
	 * Sets the systeemnaam.
	 *
	 * @param systeemnaam the new systeemnaam
	 */
	public void setSysteemnaam(String systeemnaam) {
		Systeemnaam = systeemnaam;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public char getStatus() {
		return Status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(char status) {
		Status = status;
	}

	/**
	 * Gets the metric url.
	 *
	 * @return the metric url
	 */
	public String getMetricURL() {
		return MetricURL;
	}

	/**
	 * Sets the metric url.
	 *
	 * @param uRL the new metric url
	 */
	public void setMetricURL(String uRL) {
		MetricURL = uRL;
	}

	/**
	 * Gets the metric poort.
	 *
	 * @return the metric poort
	 */
	public int getMetricPoort() {
		return MetricPoort;
	}

	/**
	 * Sets the metric poort.
	 *
	 * @param poort the new metric poort
	 */
	public void setMetricPoort(int poort) {
		MetricPoort = poort;
	}

	/**
	 * Gets the metric pad.
	 *
	 * @return the metric pad
	 */
	public String getMetricPad() {
		return MetricPad;
	}

	/**
	 * Sets the metric pad.
	 *
	 * @param metricPad the new metric pad
	 */
	public void setMetricPad(String metricPad) {
		MetricPad = metricPad;
	}
	
	/**
	 * Gets the smi.
	 *
	 * @return the smi
	 */
	public String getSmi() {
		return Smi;
	}

	/**
	 * Sets the smi.
	 *
	 * @param localSmi the new smi
	 */
	public void setSmi(String localSmi) {
		this.Smi = localSmi;
	}
	
	/**
	 * Heeft gpu.
	 *
	 * @return true, if successful
	 */
	public boolean heeftGpu() {
		return HeeftGpu;
	}

	/**
	 * Sets the heeft gpu.
	 *
	 * @param heeftGpu the new heeft gpu
	 */
	public void setHeeftGpu(boolean heeftGpu) {
		HeeftGpu = heeftGpu;
	}
	
}
