package portal.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import portal.model.Rol;


public class RolDAOImpl implements RolDAO {

	private SessionFactory sessionFactory;
	
	public RolDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<Rol> list() {
	    @SuppressWarnings("unchecked")
        List<Rol> listRol = (List<Rol>) sessionFactory.getCurrentSession()
                .createCriteria(Rol.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listRol;
	}

}
