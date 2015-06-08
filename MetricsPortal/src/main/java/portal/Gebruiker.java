package portal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gebruiker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private String CorpKey;
	private String Voornaam;
	private String Achternaam;
	private String GebruikerBeschrijving;
	private String WachtWoord;

	public Gebruiker() {
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getCorpKey() {
		return CorpKey;
	}

	public void setCorpKey(String corpKey) {
		CorpKey = corpKey;
	}

	public String getVoorNaam() {
		return Voornaam;
	}

	public void setVoorNaam(String voornaam) {
		Voornaam = voornaam;
	}

	public String getAchterNaam() {
		return Achternaam;
	}

	public void setAchterNaam(String achternaam) {
		Achternaam = achternaam;
	}

	public String getGebruikerBeschrijving() {
		return GebruikerBeschrijving;
	}

	public void setGebruikerBeschrijving(String gebruikerBeschrijving) {
		GebruikerBeschrijving = gebruikerBeschrijving;
	}

	public String getWachtWoord() {
		return WachtWoord;
	}

	public void setWachtWoord(String wachtWoord) {
		WachtWoord = wachtWoord;
	}

	public String getEditLink() {
		return "<a href='/gebruiker/edit/" + this.CorpKey + "'>Edit</a>";
	}

	public String getDeleteLink() {
		return "<a href='/gebruiker/delete/" + this.CorpKey + "'>Delete</a>";
	}

}
