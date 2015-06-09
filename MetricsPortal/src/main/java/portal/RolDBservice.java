package portal;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Interface RolDBservice.
 *
 * @param <T> the generic type
 */
public interface RolDBservice<T> {

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
	 * @param naam the naam
	 * @param Beschrijving the beschrijving
	 * @return the boolean
	 */
	public Boolean edit(String naam, String Beschrijving);
	
	/**
	 * Lees rol.
	 *
	 * @param naam the naam
	 * @return the t
	 */
	public T leesRol(String naam);
	
	/**
	 * Lees alles.
	 *
	 * @return the array list
	 */
	public ArrayList<T> leesAlles();
	
	/**
	 * Delete.
	 *
	 * @param naam the naam
	 * @return the boolean
	 */
	public Boolean delete(String naam);	
}
