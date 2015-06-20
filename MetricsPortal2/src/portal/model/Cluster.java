package portal.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class Cluster.
 */
@Entity
@Table(name = "Cluster")
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
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "cluster_rol", joinColumns = {
			@JoinColumn(name = "CLUSTER_ID", insertable = false, nullable = false, updatable = false)},
//			@JoinColumn(name = "CLUSTER_STATUS", insertable = false, nullable = false, updatable = false),
//			@JoinColumn(name = "CLUSTER_NAAM", insertable = false, nullable = false, updatable = false)},			
			inverseJoinColumns = {
			@JoinColumn(name = "ROL_ID", 
					insertable = false, nullable = false, updatable = false) })
	private Set<Rol> ClusterRollen;
	
	/** The director. */
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "cluster_director", joinColumns = { 
			@JoinColumn(name = "CLUSTER_ID", insertable = false, nullable = false, updatable = false)},
//			@JoinColumn(name = "CLUSTER_STATUS", insertable = false, nullable = false, updatable = false),
//			@JoinColumn(name = "CLUSTER_NAAM", insertable = false, nullable = false, updatable = false)}, 
			inverseJoinColumns = { @JoinColumn(name = "DIRECTOR_NAAM", 
					insertable = false, nullable = false, updatable = false) })
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
	 * @param clusterNaam the clusterNaam to set
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
	 * @param clusterStatus the clusterStatus to set
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
		return ClusterDirector.getDirectorNaam();
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
	 * @param clusterId the clusterId to set
	 */
	public void setClusterId(long clusterId) {
		ClusterId = clusterId;
	}
	/**
	 * Gets the Cluster rollen as a string.
	 *
	 * @return the Cluster rollen
	 */
	public String getClusterRollenString() {

		StringBuilder sb = new StringBuilder();

		Set<Rol> sr = getClusterRollen();

		if (sr.isEmpty()) {
			sb.append("Dit Cluster heeft nog geen rol!");
		} else {

			for (Rol r : sr) {
				sb.append(r.getRolNaam().toString() + " ");
			}
		}

		return sb.toString();
	}
	
}
