package portal.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import portal.model.Director;

public class DirectorDAOImpl implements DirectorDAO {

	private SessionFactory sessionFactory;
	
	public DirectorDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Director> list() {
	    @SuppressWarnings("unchecked")
        List<Director> listDirector = (List<Director>) sessionFactory.getCurrentSession()
                .createCriteria(Director.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listDirector;
	}

}
