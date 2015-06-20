package portal.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import portal.model.Cluster;


@Repository
@Transactional
public class ClusterDAOImpl implements IportalDAO<Cluster, Serializable> {
	
	@Autowired
	private SessionFactory sessionFactory;

	public ClusterDAOImpl() {

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
	public List<Cluster> list() {
        @SuppressWarnings("unchecked")
        List<Cluster> listCluster = (List<Cluster>) sessionFactory.getCurrentSession()
                .createCriteria(Cluster.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listCluster;
	}

	@Override
	public Cluster create(Cluster c) {
		sessionFactory.getCurrentSession().persist(c);
		sessionFactory.getCurrentSession().flush();
		return c;
	}

	@Override
	public Cluster read(Serializable id) {
		Cluster c = (Cluster) sessionFactory.getCurrentSession().get(Cluster.class, id);
		return c;
		}

	@Override
	public Cluster update(Cluster c) {
		sessionFactory.getCurrentSession().update(c);
		sessionFactory.getCurrentSession().flush();
		return c;
	}

	@Override
	public void delete(Serializable id) {
		Cluster c = (Cluster) sessionFactory.getCurrentSession().get(Cluster.class, id);
		sessionFactory.getCurrentSession().delete(c);
		sessionFactory.getCurrentSession().flush();
	}
}
