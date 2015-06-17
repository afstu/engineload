package portal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class Director.
 */
@Entity
public class Director {
	
	/** The Id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	/** The Director naam. */
	private String DirectorNaam;
	
	/** The Driver naam. */
	private String DriverNaam;
	
	/** The Driver wachtwoord. */
	private String DriverWachtwoord;
	
	/** The Director poort. */
	private int DirectorPoort;
	
	/** The Director beschrijving. */
	private String DirectorBeschrijving;

	/**
	 * Instantiates a new director.
	 */
	public Director() {
		
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

	/**
	 * Gets the edits the link.
	 *
	 * @return the edits the link
	 */
	public String getEditLink() {
		return "<a href='/director/edit/" + this.DirectorNaam + "'>Edit</a>";
	}

	/**
	 * Gets the delete link.
	 *
	 * @return the delete link
	 */
	public String getDeleteLink() {
		return "<a href='/director/delete/" + this.DirectorNaam + "'>Delete</a>";
	}

}
