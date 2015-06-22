package portal.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import portal.model.Cluster;
import portal.model.Gebruiker;
import portal.model.Rol;

@Repository
@Transactional
public class RolDAOImpl implements IportalDAO<Rol, Serializable> {

	@Autowired
	private SessionFactory sessionFactory;

	public RolDAOImpl() {

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
	public List<Rol> list() {
		@SuppressWarnings("unchecked")
		List<Rol> listRol = (List<Rol>) sessionFactory.getCurrentSession()
		.createCriteria(Rol.class)
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listRol;
	}

	@Override
	public Rol create(Rol t) {
		sessionFactory.getCurrentSession().persist(t);
		return t;
	}

	@Override
	public Rol read(Serializable id) {
		Rol g = (Rol) sessionFactory.getCurrentSession().get(Rol.class, id);
		return g;
	}

	@Override
	public Rol update(Rol t) {
		sessionFactory.getCurrentSession().update(t);
		return t;
	}

	@Override
	public void delete(Serializable id) {
			
		Rol r = (Rol) sessionFactory.getCurrentSession().get(Rol.class, id);
		
//		for (Cluster c : r.getRolClusters()) {
//			if (c != null) {
//			c.getClusterRollen().remove(r);
//			sessionFactory.getCurrentSession().update(c);
//			}
//		}
//		
//		for (Gebruiker g : r.getRolGebruikers()) {
//			if (g != null) {
//			g.getGebruikerRollen().remove(r);
//			sessionFactory.getCurrentSession().update(g);
//			}
//		}
		
		sessionFactory.getCurrentSession().delete(r);		
	}

}
