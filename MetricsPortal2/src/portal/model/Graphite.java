package portal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class Graphite.
 */
@Entity
public class Graphite {
	
//	/** The Id. */
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "GRAPHITE_ID")
//	private long Id;
	
	/** The Graphite url. */
	@Id
	@Column(name = "GRAPHITE_URL")
	private String GraphiteUrl;
	
	/**
	 * Instantiates a new graphite.
	 */
	public Graphite() {
	}
	
//	/**
//	 * Gets the id.
//	 *
//	 * @return the id
//	 */
//	public long getId() {
//		return Id;
//	}
//	
//	/**
//	 * Sets the id.
//	 *
//	 * @param id the new id
//	 */
//	public void setId(long id) {
//		Id = id;
//	}
	
	/**
	 * Gets the graphite url.
	 *
	 * @return the graphite url
	 */
	public String getGraphiteUrl() {
		return GraphiteUrl;
	}
	
	/**
	 * Sets the graphite url.
	 *
	 * @param graphiteUrl the new graphite url
	 */
	public void setGraphiteUrl(String graphiteUrl) {
		this.GraphiteUrl = graphiteUrl;
	}
}
