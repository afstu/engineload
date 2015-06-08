package portal;

import java.util.ArrayList;

public interface RolDBservice<T> {

	public Boolean create(T entity);
	public Boolean edit(String naam, String Beschrijving);
	public T leesRol(String naam);
	public ArrayList<T> leesAlles();
	public Boolean delete(String naam);	
}
