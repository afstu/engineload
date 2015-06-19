package portal.DAO;

import java.io.Serializable;
import java.util.List;

import portal.model.Gebruiker;

public interface IportalDAO<T, PK extends Serializable> {
	public List<T> list();
	public T create(T t);
	public T read(PK id);
	public T update(T t);
	public void delete(Serializable id);
}
