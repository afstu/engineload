package portal.DAO;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import portal.model.Cluster;

public class ClusterDAOImpl implements ClusterDAO {
	
	private SessionFactory sessionFactory;

	public ClusterDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Cluster> list() {
        @SuppressWarnings("unchecked")
        List<Cluster> listCluster = (List<Cluster>) sessionFactory.getCurrentSession()
                .createCriteria(Cluster.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listCluster;
	}

}
