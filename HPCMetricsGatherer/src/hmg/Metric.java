package hmg;

import java.util.Comparator;

// TODO: Auto-generated Javadoc
/**
 * The Class Metric.
 */
public class Metric implements Comparator<Metric>, Comparable<Metric> {

	/** The Tijd stip. */
	private String TijdStip;
	
	/** The Waarde. */
	private int Waarde;
	
	/** The Type. */
	private String Type;
	
	/** The Bron. */
	private String Bron;
	
	/** The Metric pad. */
	private String MetricPad;
	
	/**
	 * Instantiates a new metric.
	 */
	public Metric() {
		
	}

	/**
	 * Gets the tijd stip.
	 *
	 * @return the tijd stip
	 */
	public String getTijdStip() {
		return TijdStip;
	}

	/**
	 * Sets the tijd stip.
	 *
	 * @param string the new tijd stip
	 */
	public void setTijdStip(String string) {
		TijdStip = string;
	}

	/**
	 * Gets the waarde.
	 *
	 * @return the waarde
	 */
	public int getWaarde() {
		return Waarde;
	}

	/**
	 * Sets the waarde.
	 *
	 * @param waarde the new waarde
	 */
	public void setWaarde(int waarde) {
		Waarde = waarde;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return Type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		Type = type;
	}

	/**
	 * Gets the bron.
	 *
	 * @return the bron
	 */
	public String getBron() {
		return Bron;
	}

	/**
	 * Sets the bron.
	 *
	 * @param bron the new bron
	 */
	public void setBron(String bron) {
		Bron = bron;
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
		
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Metric m1, Metric m2) {
		return m1.getType().compareTo(m2.getType());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Metric m1) {
		return this.getType().compareTo(m1.getType());
	}
}


