package portal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	private String RolNaam;
	private String RolBeschrijving;
	
	public Rol() {		
	}
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getRolNaam() {
		return RolNaam;
	}
	public void setRolNaam(String rolNaam) {
		RolNaam = rolNaam;
	}
	public String getRolBeschrijving() {
		return RolBeschrijving;
	}
	public void setRolBeschrijving(String rolBeschrijving) {
		RolBeschrijving = rolBeschrijving;
	}

	public String getEditLink() {
		return "<a href='/rol/edit/" + this.RolNaam + "'>Edit</a>";
	}

	public String getDeleteLink() {
		return "<a href='/rol/delete/" + this.RolNaam + "'>Delete</a>";
	}

}
