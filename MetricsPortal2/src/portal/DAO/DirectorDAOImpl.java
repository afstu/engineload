package portal.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import portal.model.Director;
import portal.model.Gebruiker;

@Repository
@Transactional
public class DirectorDAOImpl implements IportalDAO<Director, Serializable> {

	@Autowired
	private SessionFactory sessionFactory;
	
	public DirectorDAOImpl() {
	
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Director> list() {
	    @SuppressWarnings("unchecked")
        List<Director> listDirector = (List<Director>) sessionFactory.getCurrentSession()
                .createCriteria(Director.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listDirector;
	}

	@Override
	public Director create(Director t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Director read(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Director update(Director t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
	}

}
