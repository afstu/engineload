package portal;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Interface ClusterDBservice.
 *
 * @param <T> the generic type
 */
public interface ClusterDBservice<T> {

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
	 * @param clusterName the cluster name
	 * @param clusterStatus the cluster status
	 * @param rollen the rollen
	 * @param director the director
	 * @param Beschrijving the beschrijving
	 * @return the boolean
	 */
	public Boolean edit(String clusterName, char clusterStatus, ArrayList<Rol> rollen, Director director, String Beschrijving);
	
	/**
	 * Lees cluster.
	 *
	 * @param naam the naam
	 * @return the t
	 */
	public T leesCluster(String naam);
	
	/**
	 * Lees alles.
	 *
	 * @return the array list
	 */
	public ArrayList<T> leesAlles();
	
	/**
	 * Delete.
	 *
	 * @param clusterName the cluster name
	 * @return the boolean
	 */
	public Boolean delete(String clusterName);	
}
