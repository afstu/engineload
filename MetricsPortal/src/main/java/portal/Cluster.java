package portal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Cluster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private String ClusterNaam;
	private String ClusterStatus;
	private String Rol;
	private String director;
	private String ClusterBeschrijving;

	public Cluster() {
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		Id = id;
	}

	public String getClusterNaam() {
		return ClusterNaam;
	}

	public void setClusterNaam(String clusterNaam) {
		ClusterNaam = clusterNaam;
	}

	public String getClusterStatus() {
		return ClusterStatus;
	}

	public void setClusterStatus(String string) {
		ClusterStatus = string;
	}

	public String getRol() {
		return Rol;
	}

	public void addRol(String rol) {
		this.Rol = rol;
	}

	public String getClusterDirector() {
		return director;
	}

	public void setClusterDirector(String director) {
		this.director = director;
	}

	public String getClusterBeschrijving() {
		return ClusterBeschrijving;
	}

	public void setClusterBeschrijving(String clusterBeschrijving) {
		ClusterBeschrijving = clusterBeschrijving;
	}

	public String getEditLink() {
		return "<a href='/cluster/edit/" + this.ClusterNaam + "'>Edit</a>";
	}

	public String getDeleteLink() {
		return "<a href='/cluster/delete/" + this.ClusterNaam + "'>Delete</a>";
	}

}
