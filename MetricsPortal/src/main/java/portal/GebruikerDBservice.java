package portal;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Interface GebruikerDBservice.
 *
 * @param <T> the generic type
 */
public interface GebruikerDBservice<T> {

	/**
	 * Creates the.
	 *
	 * @param entity the entity
	 * @return the boolean
	 */
	public Boolean create(T entity);
	
	/**
	 * Edits the.
	 *
	 * @param corpKey the corp key
	 * @param voorNaam the voor naam
	 * @param achterNaam the achter naam
	 * @param gebruikerBeschrijving the gebruiker beschrijving
	 * @param wachtWoord the wacht woord
	 * @return the boolean
	 */
	public Boolean edit(String corpKey, String voorNaam, String achterNaam, String gebruikerBeschrijving, String wachtWoord);
	
	/**
	 * Lees gebruiker.
	 *
	 * @param corpKey the corp key
	 * @return the t
	 */
	public T leesGebruiker(String corpKey);
	
	/**
	 * Lees alles.
	 *
	 * @return the array list
	 */
	public ArrayList<T> leesAlles();
	
	/**
	 * Delete.
	 *
	 * @param corpKey the corp key
	 * @return the boolean
	 */
	public Boolean delete(String corpKey);	
}
