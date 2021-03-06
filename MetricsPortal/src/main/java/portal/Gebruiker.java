package portal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class Gebruiker.
 */
@Entity
public class Gebruiker {
	
	/** The Id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	/** The Corp key. */
	private String CorpKey;
	
	/** The Voornaam. */
	private String Voornaam;
	
	/** The Achternaam. */
	private String Achternaam;
	
	/** The Gebruiker beschrijving. */
	private String GebruikerBeschrijving;
	
	/** The Wacht woord. */
	private String WachtWoord;

	/**
	 * Instantiates a new gebruiker.
	 */
	public Gebruiker() {
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
	public String getVoorNaam() {
		return Voornaam;
	}

	/**
	 * Sets the voor naam.
	 *
	 * @param voornaam the new voor naam
	 */
	public void setVoorNaam(String voornaam) {
		Voornaam = voornaam;
	}

	/**
	 * Gets the achter naam.
	 *
	 * @return the achter naam
	 */
	public String getAchterNaam() {
		return Achternaam;
	}

	/**
	 * Sets the achter naam.
	 *
	 * @param achternaam the new achter naam
	 */
	public void setAchterNaam(String achternaam) {
		Achternaam = achternaam;
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
	 * Gets the edits the link.
	 *
	 * @return the edits the link
	 */
	public String getEditLink() {
		return "<a href='/gebruiker/edit/" + this.CorpKey + "'>Edit</a>";
	}

	/**
	 * Gets the delete link.
	 *
	 * @return the delete link
	 */
	public String getDeleteLink() {
		return "<a href='/gebruiker/delete/" + this.CorpKey + "'>Delete</a>";
	}

}
