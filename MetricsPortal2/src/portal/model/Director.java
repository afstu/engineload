package portal.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

// TODO: Auto-generated Javadoc
/**
 * The Class Director.
 */
@Entity
@Table(name = "Director")
public class Director implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	/** The Id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DIRECTOR_ID")
	private long Id;
	
	/** The Director naam. */
	@Column(name = "DIRECTOR_NAAM", unique = true, nullable = false, length = 30)
	private String DirectorNaam;
	
	/** The Driver naam. */
	@Column(name = "DRIVER_NAAM", unique = false, nullable = false, length = 20)
	private String DriverNaam;
	
	/** The Driver wachtwoord. */
	@Column(name = "DRIVER_WACHTWOORD", unique = false, nullable = false, length = 20)
	private String DriverWachtwoord;
	
	/** The Director poort. */
	@Column(name = "DIRECTOR_POORT", unique = false, nullable = false, length = 5)
	private int DirectorPoort;
	
	/** The Director beschrijving. */
	@Column(name = "DIRECTOR_BESCHRIJVING", unique = false, nullable = true, length = 200)
	private String DirectorBeschrijving;
	
	/** The Cluster. */
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="ClusterDirector")
	private Cluster DirectorCluster;

	/**
	 * Instantiates a new director.
	 */
	public Director() {
		
	}

	/**
	 * @return the directorCluster
	 */
	public Cluster getDirectorCluster() {
		return DirectorCluster;
	}

	/**
	 * @param directorCluster the directorCluster to set
	 */
	public void setDirectorCluster(Cluster directorCluster) {
		DirectorCluster = directorCluster;
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

	/**
	 * Gets the director naam.
	 *
	 * @return the director naam
	 */
	public String getDirectorNaam() {
		return DirectorNaam;
	}

	/**
	 * Sets the director naam.
	 *
	 * @param directorNaam the new director naam
	 */
	public void setDirectorNaam(String directorNaam) {
		DirectorNaam = directorNaam;
	}

	/**
	 * Gets the driver naam.
	 *
	 * @return the driver naam
	 */
	public String getDriverNaam() {
		return DriverNaam;
	}

	/**
	 * Sets the driver naam.
	 *
	 * @param driverNaam the new driver naam
	 */
	public void setDriverNaam(String driverNaam) {
		DriverNaam = driverNaam;
	}

	/**
	 * Gets the driver wachtwoord.
	 *
	 * @return the driver wachtwoord
	 */
	public String getDriverWachtwoord() {
		return DriverWachtwoord;
	}

	/**
	 * Sets the driver wachtwoord.
	 *
	 * @param driverWachtwoord the new driver wachtwoord
	 */
	public void setDriverWachtwoord(String driverWachtwoord) {
		DriverWachtwoord = driverWachtwoord;
	}

	/**
	 * Gets the director poort.
	 *
	 * @return the director poort
	 */
	public int getDirectorPoort() {
		return DirectorPoort;
	}

	/**
	 * Sets the director poort.
	 *
	 * @param directorPoort the new director poort
	 */
	public void setDirectorPoort(int directorPoort) {
		DirectorPoort = directorPoort;
	}

	/**
	 * Gets the director beschrijving.
	 *
	 * @return the director beschrijving
	 */
	public String getDirectorBeschrijving() {
		return DirectorBeschrijving;
	}

	/**
	 * Sets the director beschrijving.
	 *
	 * @param directorBeschrijving the new director beschrijving
	 */
	public void setDirectorBeschrijving(String directorBeschrijving) {
		DirectorBeschrijving = directorBeschrijving;
	}

}
