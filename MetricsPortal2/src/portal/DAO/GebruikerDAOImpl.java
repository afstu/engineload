package portal.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import portal.model.Gebruiker;

@Repository
@Transactional
public class GebruikerDAOImpl implements IportalDAO<Gebruiker, Serializable> {

	@Autowired
	private SessionFactory sessionFactory;

	public GebruikerDAOImpl() {

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
	public List<Gebruiker> list() {
		@SuppressWarnings("unchecked")
		List<Gebruiker> listGebruiker = (List<Gebruiker>) sessionFactory.getCurrentSession()
		.createCriteria(Gebruiker.class)
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listGebruiker;
	}

	@Override
	public Gebruiker create(Gebruiker g) {
		sessionFactory.getCurrentSession().persist(g);
		sessionFactory.getCurrentSession().flush();
		return g;
	}

	@Override
	public Gebruiker read(Serializable id) {
		Gebruiker g = (Gebruiker) sessionFactory.getCurrentSession().get(Gebruiker.class, id);
		return g;
	}

	@Override
	public Gebruiker update(Gebruiker g) {
		sessionFactory.getCurrentSession().update(g);
		sessionFactory.getCurrentSession().flush();
		return g;
	}

	@Override
	public void delete(Serializable id) {
		Gebruiker g = (Gebruiker) sessionFactory.getCurrentSession().get(Gebruiker.class, id);
		sessionFactory.getCurrentSession().delete(g);
		sessionFactory.getCurrentSession().flush();
		
	}

}
