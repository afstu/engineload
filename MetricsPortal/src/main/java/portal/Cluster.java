package portal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

// TODO: Auto-generated Javadoc
/**
 * The Class Cluster.
 */
@Entity
public class Cluster {
	
	/** The Id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	/** The Cluster naam. */
	private String ClusterNaam;
	
	/** The Cluster status. */
	private String ClusterStatus;
	
	/** The Rol. */
	private String Rol;
	
	/** The director. */
	private String director;
	
	/** The Cluster beschrijving. */
	private String ClusterBeschrijving;

	/**
	 * Instantiates a new cluster.
	 */
	public Cluster() {
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
	 * @param id the id to set
	 */
	public void setId(long id) {
		Id = id;
	}

	/**
	 * Gets the cluster naam.
	 *
	 * @return the cluster naam
	 */
	public String getClusterNaam() {
		return ClusterNaam;
	}

	/**
	 * Sets the cluster naam.
	 *
	 * @param clusterNaam the new cluster naam
	 */
	public void setClusterNaam(String clusterNaam) {
		ClusterNaam = clusterNaam;
	}

	/**
	 * Gets the cluster status.
	 *
	 * @return the cluster status
	 */
	public String getClusterStatus() {
		return ClusterStatus;
	}

	/**
	 * Sets the cluster status.
	 *
	 * @param string the new cluster status
	 */
	public void setClusterStatus(String string) {
		ClusterStatus = string;
	}

	/**
	 * Gets the rol.
	 *
	 * @return the rol
	 */
	public String getRol() {
		return Rol;
	}

	/**
	 * Adds the rol.
	 *
	 * @param rol the rol
	 */
	public void addRol(String rol) {
		this.Rol = rol;
	}

	/**
	 * Gets the cluster director.
	 *
	 * @return the cluster director
	 */
	public String getClusterDirector() {
		return director;
	}

	/**
	 * Sets the cluster director.
	 *
	 * @param director the new cluster director
	 */
	public void setClusterDirector(String director) {
		this.director = director;
	}

	/**
	 * Gets the cluster beschrijving.
	 *
	 * @return the cluster beschrijving
	 */
	public String getClusterBeschrijving() {
		return ClusterBeschrijving;
	}

	/**
	 * Sets the cluster beschrijving.
	 *
	 * @param clusterBeschrijving the new cluster beschrijving
	 */
	public void setClusterBeschrijving(String clusterBeschrijving) {
		ClusterBeschrijving = clusterBeschrijving;
	}

	/**
	 * Gets the edits the link.
	 *
	 * @return the edits the link
	 */
	public String getEditLink() {
		return "<a href='/cluster/edit/" + this.ClusterNaam + "'>Edit</a>";
	}

	/**
	 * Gets the delete link.
	 *
	 * @return the delete link
	 */
	public String getDeleteLink() {
		return "<a href='/cluster/delete/" + this.ClusterNaam + "'>Delete</a>";
	}

}
