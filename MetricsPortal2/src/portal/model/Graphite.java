package portal.model;

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
	
	/** The Id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	/** The Graphite url. */
	private String GraphiteUrl;
	
	/**
	 * Instantiates a new graphite.
	 */
	public Graphite() {
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return Id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		Id = id;
	}
	
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

	 /**
 	 * Gets the edits the link.
 	 *
 	 * @return the edits the link
 	 */
 	public String getEditLink() {
	        return "<a href='/graphite/edit/" + this.GraphiteUrl + "'>Edit</a>";
	    }
	 
	    /**
    	 * Gets the delete link.
    	 *
    	 * @return the delete link
    	 */
    	public String getDeleteLink() {
	        return "<a href='/graphite/delete/" + this.GraphiteUrl + "'>Delete</a>";
	    }
	
}
