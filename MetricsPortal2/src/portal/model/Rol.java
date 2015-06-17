package portal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class Rol.
 */
@Entity
public class Rol {
	
	/** The Id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	/** The Rol naam. */
	private String RolNaam;
	
	/** The Rol beschrijving. */
	private String RolBeschrijving;
	
	/**
	 * Instantiates a new rol.
	 */
	public Rol() {		
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
	 * Gets the rol naam.
	 *
	 * @return the rol naam
	 */
	public String getRolNaam() {
		return RolNaam;
	}
	
	/**
	 * Sets the rol naam.
	 *
	 * @param rolNaam the new rol naam
	 */
	public void setRolNaam(String rolNaam) {
		RolNaam = rolNaam;
	}
	
	/**
	 * Gets the rol beschrijving.
	 *
	 * @return the rol beschrijving
	 */
	public String getRolBeschrijving() {
		return RolBeschrijving;
	}
	
	/**
	 * Sets the rol beschrijving.
	 *
	 * @param rolBeschrijving the new rol beschrijving
	 */
	public void setRolBeschrijving(String rolBeschrijving) {
		RolBeschrijving = rolBeschrijving;
	}

	/**
	 * Gets the edits the link.
	 *
	 * @return the edits the link
	 */
	public String getEditLink() {
		return "<a href='/rol/edit/" + this.RolNaam + "'>Edit</a>";
	}

	/**
	 * Gets the delete link.
	 *
	 * @return the delete link
	 */
	public String getDeleteLink() {
		return "<a href='/rol/delete/" + this.RolNaam + "'>Delete</a>";
	}

}
