package portal.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;


import portal.model.Graphite;

public class GraphiteDAOImpl implements GraphiteDAO {

	private SessionFactory sessionFactory;
	
	public GraphiteDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Graphite> list() {
	    @SuppressWarnings("unchecked")
        List<Graphite> listGraphite = (List<Graphite>) sessionFactory.getCurrentSession()
                .createCriteria(Graphite.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listGraphite;
	}

}
