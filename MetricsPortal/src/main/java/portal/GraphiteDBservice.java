package portal;

public interface GraphiteDBservice<T> {

	public Boolean create(T entity);
	public Boolean edit(String url);
	public T leesGraphite(String naam);
	public Boolean delete(String url);	
}
