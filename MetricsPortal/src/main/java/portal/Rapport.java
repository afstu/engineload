package portal;


public class Rapport {

	private String graphiteUrl;
	private String clusterNaam;
	private String clusterStatus;
	private String clusterDirector;
	private String clusterBeschrijving;
	private long beginTijd;
	private long eindTijd;
//	private ArrayList<String> grafiekUris;
	
	public Rapport() {
		
	}

	public String getGraphiteUrl() {
		return graphiteUrl;
	}

	public void setGraphiteUrl(String graphiteUrl) {
		this.graphiteUrl = graphiteUrl;
	}

	/**
	 * @return the clusterNaam
	 */
	public String getClusterNaam() {
		return clusterNaam;
	}

	/**
	 * @param clusterNaam the clusterNaam to set
	 */
	public void setClusterNaam(String clusterNaam) {
		this.clusterNaam = clusterNaam;
	}

	/**
	 * @return the clusterStatus
	 */
	public String getClusterStatus() {
		return clusterStatus;
	}

	/**
	 * @param clusterStatus the clusterStatus to set
	 */
	public void setClusterStatus(String clusterStatus) {
		this.clusterStatus = clusterStatus;
	}

	/**
	 * @return the clusterDirector
	 */
	public String getClusterDirector() {
		return clusterDirector;
	}

	/**
	 * @param clusterDirector the clusterDirector to set
	 */
	public void setClusterDirector(String clusterDirector) {
		this.clusterDirector = clusterDirector;
	}

	/**
	 * @return the clusterBeschrijving
	 */
	public String getClusterBeschrijving() {
		return clusterBeschrijving;
	}

	/**
	 * @param clusterBeschrijving the clusterBeschrijving to set
	 */
	public void setClusterBeschrijving(String clusterBeschrijving) {
		this.clusterBeschrijving = clusterBeschrijving;
	}

	/**
	 * @return the beginTijd
	 */
	public long getBeginTijd() {
		return beginTijd;
	}

	/**
	 * @param beginTijd the beginTijd to set
	 */
	public void setBeginTijd(long beginTijd) {
		this.beginTijd = beginTijd;
	}

	/**
	 * @return the eindTijd
	 */
	public long getEindTijd() {
		return eindTijd;
	}

	/**
	 * @param eindTijd the eindTijd to set
	 */
	public void setEindTijd(long eindTijd) {
		this.eindTijd = eindTijd;
	}

//	/**
//	 * @return the grafiekUris
//	 */
//	public ArrayList<String> getGrafiekUris() {
//		return grafiekUris;
//	}
//
//	/**
//	 * @param grafiekUris the grafiekUris to set
//	 */
//	public void setGrafiekUris(ArrayList<String> grafiekUris) {
//		this.grafiekUris = grafiekUris;
//	}

}
