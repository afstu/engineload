package portal;

// TODO: Auto-generated Javadoc
/**
 * The Interface GraphiteDBservice.
 *
 * @param <T> the generic type
 */
public interface GraphiteDBservice<T> {

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
	 * @param url the url
	 * @return the boolean
	 */
	public Boolean edit(String url);
	
	/**
	 * Lees graphite.
	 *
	 * @param naam the naam
	 * @return the t
	 */
	public T leesGraphite(String naam);
	
	/**
	 * Delete.
	 *
	 * @param url the url
	 * @return the boolean
	 */
	public Boolean delete(String url);	
}
