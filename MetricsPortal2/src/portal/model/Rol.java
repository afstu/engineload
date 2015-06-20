package portal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class Rol.
 */
@Entity
public class Rol implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	/** The Id. */
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "ROL_ID")
//	private long Id;
	
	/** The Rol naam. */
	@Id
	@Column(name = "ROL_NAAM", unique = true, nullable = false, length = 20)
	private String RolNaam;
	
	/** The Rol beschrijving. */
	@Column(name = "ROL_BESCHRIJVING" , unique = false, nullable = true, length = 200)
	private String RolBeschrijving;
	
	
	/**
	 * Instantiates a new rol.
	 */
	public Rol() {		
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
}
