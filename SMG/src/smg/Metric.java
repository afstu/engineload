package smg;

public class Metric {

	private String TijdStip;
	private int Waarde;
	private String Type;
	private String Bron;
	private String MetricPad;
	
	public Metric() {
		
	}

	public String getTijdStip() {
		return TijdStip;
	}

	public void setTijdStip(String string) {
		TijdStip = string;
	}

	public int getWaarde() {
		return Waarde;
	}

	public void setWaarde(int waarde) {
		Waarde = waarde;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getBron() {
		return Bron;
	}

	public void setBron(String bron) {
		Bron = bron;
	}

	public String getMetricPad() {
		return MetricPad;
	}

	public void setMetricPad(String metricPad) {
		MetricPad = metricPad;
	}
	
}
