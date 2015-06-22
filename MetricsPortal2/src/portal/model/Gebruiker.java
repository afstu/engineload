package portal.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

// TODO: Auto-generated Javadoc
/**
 * The Class Gebruiker.
 */
@Entity
@Table(name = "Gebruikers")
public class Gebruiker {

	//	/** The Id. */
	//	@Id
	//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//	@Column(name = "GEBRUIKER_ID")
	//	private long Id;

	/** The Corp key. */
	@Id
	@Column(name = "GEBRUIKER_CORPKEY", unique = true, nullable = false, length = 6)
	private String CorpKey;

	/** The Voornaam. */
	@Column(name = "GEBRUIKER_VOORNAAM", unique = false, nullable = false, length = 20)
	private String VoorNaam;

	/** The Achternaam. */
	@Column(name = "GEBRUIKER_ACHTERNAAM", unique = false, nullable = false, length = 30)
	private String AchterNaam;

	/** The Gebruiker beschrijving. */
	@Column(name = "GEBRUIKER_BESCHRIJVING", unique = false, nullable = true, length = 200 )
	private String GebruikerBeschrijving;

	/** The Wacht woord. */
	@Column(name = "GEBRUIKER_WACHTWOORD", unique = false, nullable = false, length = 20)
	private String WachtWoord;

	/** The Gebruiker rollen. */
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="GEBRUIKER_ROL",joinColumns=@JoinColumn(name="GEBRUIKER_CORPKEY"),inverseJoinColumns=@JoinColumn(name="ROL_ID") )
	private Set<Rol> GebruikerRollen;

	/**
	 * Instantiates a new gebruiker.
	 */
	public Gebruiker() {
	}


	/**
	 * Gets the corp key.
	 *
	 * @return the corp key
	 */
	public String getCorpKey() {
		return CorpKey;
	}

	/**
	 * Sets the corp key.
	 *
	 * @param corpKey the new corp key
	 */
	public void setCorpKey(String corpKey) {
		CorpKey = corpKey;
	}

	/**
	 * Gets the voor naam.
	 *
	 * @return the voor naam
	 */
	public String getVoornaam() {
		return VoorNaam;
	}

	/**
	 * Sets the voor naam.
	 *
	 * @param voornaam the new voor naam
	 */
	public void setVoornaam(String voornaam) {
		VoorNaam = voornaam;
	}

	/**
	 * Gets the achter naam.
	 *
	 * @return the achter naam
	 */
	public String getAchternaam() {
		return AchterNaam;
	}

	/**
	 * Sets the achter naam.
	 *
	 * @param achternaam the new achter naam
	 */
	public void setAchternaam(String achternaam) {
		AchterNaam = achternaam;
	}

	/**
	 * Gets the gebruiker beschrijving.
	 *
	 * @return the gebruiker beschrijving
	 */
	public String getGebruikerBeschrijving() {
		return GebruikerBeschrijving;
	}

	/**
	 * Sets the gebruiker beschrijving.
	 *
	 * @param gebruikerBeschrijving the new gebruiker beschrijving
	 */
	public void setGebruikerBeschrijving(String gebruikerBeschrijving) {
		GebruikerBeschrijving = gebruikerBeschrijving;
	}

	/**
	 * Gets the wacht woord.
	 *
	 * @return the wacht woord
	 */
	public String getWachtWoord() {
		return WachtWoord;
	}

	/**
	 * Sets the wacht woord.
	 *
	 * @param wachtWoord the new wacht woord
	 */
	public void setWachtWoord(String wachtWoord) {
		WachtWoord = wachtWoord;
	}

	/**
	 * Gets the gebruiker rollen.
	 *
	 * @return the gebruiker rollen
	 */
	public Set<Rol> getGebruikerRollen() {
		return GebruikerRollen;
	}

	/**
	 * Sets the gebruiker rollen.
	 *
	 * @param gebruikerRollen the new gebruiker rollen
	 */
	public void setGebruikerRollen(Set<Rol> gebruikerRollen) {
		GebruikerRollen = gebruikerRollen;
	}
	
	public void addRol(Rol r) {
		GebruikerRollen.add(r);
	}
}
