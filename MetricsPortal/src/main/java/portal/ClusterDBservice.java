package portal;

import java.util.ArrayList;

public interface ClusterDBservice<T> {

	public Boolean create(T entity);
	public Boolean edit(String clusterName, char clusterStatus, ArrayList<Rol> rollen, Director director, String Beschrijving);
	public T leesCluster(String naam);
	public ArrayList<T> leesAlles();
	public Boolean delete(String clusterName);	
}
