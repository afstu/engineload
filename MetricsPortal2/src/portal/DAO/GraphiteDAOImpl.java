package portal.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import portal.model.Cluster;
import portal.model.Graphite;

@Repository
@Transactional
public class GraphiteDAOImpl implements IportalDAO<Graphite, Serializable> {

	@Autowired
	private SessionFactory sessionFactory;
	
	public GraphiteDAOImpl() {
		
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
	public List<Graphite> list() {
	    @SuppressWarnings("unchecked")
        List<Graphite> listGraphite = (List<Graphite>) sessionFactory.getCurrentSession()
                .createCriteria(Graphite.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listGraphite;
	}

	@Override
	public Graphite create(Graphite g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graphite read(Serializable id) {
		Graphite g = (Graphite) sessionFactory.getCurrentSession().get(Graphite.class, id);
		return g;
	}

	@Override
	public Graphite update(Graphite g) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(g);
		sessionFactory.getCurrentSession().flush();
		return g;
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
	}
}
