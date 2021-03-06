package portal.model;

import java.io.Serializable;
import java.util.Set;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class Cluster.
 */
@Entity
@Table(name = "Clusters")
public class Cluster implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLUSTER_ID", nullable=false)
	private long ClusterId;
	


	/** The Cluster naam. */
	@Column(name = "CLUSTER_NAAM",  unique = false, nullable = false, length = 20)
	private String ClusterNaam;
	
	
	/** The Cluster status. */
	@Column(name = "CLUSTER_STATUS", unique = true, nullable = false, length = 1)
	private String ClusterStatus;
	
	/** The Rol. */
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="CLUSTER_ROL",joinColumns=@JoinColumn(name="CLUSTER_ID"),inverseJoinColumns=@JoinColumn(name="ROL_ID") )
	private Set<Rol> ClusterRollen;
	
	/** The director. */
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="CLUSTER_DIRECTOR",joinColumns=@JoinColumn(name="CLUSTER_ID"),inverseJoinColumns=@JoinColumn(name="DIRECTOR_ID") )
	private Director ClusterDirector;
	
	/** The Cluster beschrijving. */
	@Column(name = "CLUSTER_BESCHRIJVING")
	private String ClusterBeschrijving;
	
	/**
	 * Instantiates a new cluster.
	 */
	public Cluster() {
	}

	/**
	 * @return the clusterNaam
	 */
	public String getClusterNaam() {
		return ClusterNaam;
	}

	/**
	 * @param clusterNaam the clusterNaam to Set
	 */
	public void setClusterNaam(String clusterNaam) {
		ClusterNaam = clusterNaam;
	}

	/**
	 * @return the clusterStatus
	 */
	public String getClusterStatus() {
		return ClusterStatus;
	}

	/**
	 * @param clusterStatus the clusterStatus to Set
	 */
	public void setClusterStatus(String clusterStatus) {
		ClusterStatus = clusterStatus;
	}

	/**
	 * Gets the cluster director.
	 *
	 * @return the cluster director
	 */
	public Director getClusterDirector() {
		return ClusterDirector;
	}

	/**
	 * Sets the cluster director.
	 *
	 * @param director the new cluster director
	 */
	public void setClusterDirectorNaam(String directorNaam) {
		this.ClusterDirector.setDirectorNaam(directorNaam);
	}
	
	/**
	 * Gets the cluster director naam.
	 *
	 * @return the cluster director naam
	 */
	public String getClusterDirectorNaam() {
		String dirNaam = getClusterDirector().getDirectorNaam();
		
		if (dirNaam.isEmpty()) {
			dirNaam = "Dit Cluster heeft nog geen director!";
		}
		
		return dirNaam;
	}

	/**
	 * Sets the cluster director.
	 *
	 * @param director the new cluster director
	 */
	public void setClusterDirector(Director director) {
		this.ClusterDirector = director;
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

	public Set<Rol> getClusterRollen() {
		return ClusterRollen;
	}

	public void setClusterRollen(Set<Rol> clusterRollen) {
		ClusterRollen = clusterRollen;
	}	
	
	/**
	 * @return the clusterId
	 */
	public long getClusterId() {
		return ClusterId;
	}

	/**
	 * @param clusterId the clusterId to Set
	 */
	public void setClusterId(long clusterId) {
		ClusterId = clusterId;
	}	
}
