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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Rol.
 */
@Entity
@Table(name = "Rollen")
public class Rol implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		/** The Id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROL_ID", nullable=false)
	private long Id;
	
	/** The Rol naam. */
	@Column(name = "ROL_NAAM", unique = true, updatable = true, nullable = false, length = 20)
	private String RolNaam;
	
	/** The Rol beschrijving. */
	@Column(name = "ROL_BESCHRIJVING" , unique = false, nullable = true, length = 200)
	private String RolBeschrijving;
	
	/** The Cluster. */
	@Column(name = "ROL_CLUSTERS" , unique = false, nullable = true)
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Cluster> RolClusters;
	
	/** The Gebruiker. */
	@Column(name = "ROL_GEBRUIKERS" , unique = false, nullable = true)
	@OneToMany(mappedBy="GebruikerRollen", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Gebruiker> RolGebruikers;
	
	
	/**
	 * Instantiates a new rol.
	 */
	public Rol() {		
	}
	

	/**
	 * @return the rolClusters
	 */
	public Set<Cluster> getRolClusters() {
		return RolClusters;
	}


	/**
	 * @param rolClusters the rolClusters to Set
	 */
	public void setRolClusters(Set<Cluster> rolClusters) {
		RolClusters = rolClusters;
	}


	/**
	 * @return the rolGebruikers
	 */
	public Set<Gebruiker> getRolGebruikers() {
		return RolGebruikers;
	}


	/**
	 * @param rolGebruikers the rolGebruikers to Set
	 */
	public void setRolGebruikers(Set<Gebruiker> rolGebruikers) {
		RolGebruikers = rolGebruikers;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return Id;
	}


	/**
	 * @param id the id to Set
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

}
