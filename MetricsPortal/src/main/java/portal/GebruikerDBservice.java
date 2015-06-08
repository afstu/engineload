package portal;

import java.util.ArrayList;

public interface GebruikerDBservice<T> {

	public Boolean create(T entity);
	public Boolean edit(String corpKey, String voorNaam, String achterNaam, String gebruikerBeschrijving, String wachtWoord);
	public T leesGebruiker(String corpKey);
	public ArrayList<T> leesAlles();
	public Boolean delete(String corpKey);	
}
