package portal.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import portal.model.Gebruiker;

public class GebruikerDAOImpl implements GebruikerDAO {

	private SessionFactory sessionFactory;
	
	public GebruikerDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Gebruiker> list() {
	    @SuppressWarnings("unchecked")
        List<Gebruiker> listGebruiker = (List<Gebruiker>) sessionFactory.getCurrentSession()
                .createCriteria(Gebruiker.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listGebruiker;
	}

}
