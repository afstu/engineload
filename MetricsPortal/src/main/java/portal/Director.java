package portal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Director {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private String DirectorNaam;
	private String DriverNaam;
	private String DriverWachtwoord;
	private int DirectorPoort;
	private String DirectorBeschrijving;

	public Director() {
		
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

	public String getDirectorNaam() {
		return DirectorNaam;
	}

	public void setDirectorNaam(String directorNaam) {
		DirectorNaam = directorNaam;
	}

	public String getDriverNaam() {
		return DriverNaam;
	}

	public void setDriverNaam(String driverNaam) {
		DriverNaam = driverNaam;
	}

	public String getDriverWachtwoord() {
		return DriverWachtwoord;
	}

	public void setDriverWachtwoord(String driverWachtwoord) {
		DriverWachtwoord = driverWachtwoord;
	}

	public int getDirectorPoort() {
		return DirectorPoort;
	}

	public void setDirectorPoort(int directorPoort) {
		DirectorPoort = directorPoort;
	}

	public String getDirectorBeschrijving() {
		return DirectorBeschrijving;
	}

	public void setDirectorBeschrijving(String directorBeschrijving) {
		DirectorBeschrijving = directorBeschrijving;
	}

	public String getEditLink() {
		return "<a href='/director/edit/" + this.DirectorNaam + "'>Edit</a>";
	}

	public String getDeleteLink() {
		return "<a href='/director/delete/" + this.DirectorNaam + "'>Delete</a>";
	}

}
