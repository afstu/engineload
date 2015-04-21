package smg;

public class Omgeving {

	private String Systeemnaam;
	private String Clusternaam;
	private char Status;
	private String MetricURL;
	private int MetricPoort;
	private String MetricPad;
	private String Smi;
	private boolean HeeftGpu = false;
	
	public Omgeving() {
	}

	public String getClusternaam() {
		return Clusternaam;
	}

	public void setClusternaam(String clusternaam) {
		Clusternaam = clusternaam;
	}

	public String getSysteemnaam() {
		return Systeemnaam;
	}

	public void setSysteemnaam(String systeemnaam) {
		Systeemnaam = systeemnaam;
	}

	public char getStatus() {
		return Status;
	}

	public void setStatus(char status) {
		Status = status;
	}

	public String getMetricURL() {
		return MetricURL;
	}

	public void setMetricURL(String uRL) {
		MetricURL = uRL;
	}

	public int getMetricPoort() {
		return MetricPoort;
	}

	public void setMetricPoort(int poort) {
		MetricPoort = poort;
	}

	public String getMetricPad() {
		return MetricPad;
	}

	public void setMetricPad(String metricPad) {
		MetricPad = metricPad;
	}
	
	public String getSmi() {
		return Smi;
	}

	public void setSmi(String localSmi) {
		this.Smi = localSmi;
	}
	
	public boolean heeftGpu() {
		return HeeftGpu;
	}

	public void setHeeftGpu(boolean heeftGpu) {
		HeeftGpu = heeftGpu;
	}
	
}
