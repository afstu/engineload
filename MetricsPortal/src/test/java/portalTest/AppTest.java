package portalTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import portal.Cluster;
import portal.Director;
import portal.Rol;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

// TODO: Auto-generated Javadoc
/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    
    /**
     * Create the test case.
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * Suite.
     *
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-).
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
	/**
	 * Test db.
	 */
	public void testDB() {
		SessionFactory sessionFactory;
		ServiceRegistry serviceRegistry;
		Configuration configuration = new Configuration();
		configuration.configure();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Cluster myTestCluster = new Cluster();
		Director myTestDirector = new Director();
		Rol myTestRol = new Rol();
		
		// testrole
		myTestRol.setRolNaam("maven test role");
		myTestRol.setRolBeschrijving("maven test role beschrijving");
		
		//test director
		myTestDirector.setDirectorNaam("Maven Test Director");
		myTestDirector.setDirectorPoort(1337);
		myTestDirector.setDirectorBeschrijving("Maven test Director Beschrijving");
		myTestDirector.setDriverNaam("Maven TEst Driver");
		myTestDirector.setDriverWachtwoord("Driver Password");
		
		// test cluster
		myTestCluster.setClusterNaam("testCluster");
		myTestCluster.setClusterStatus("T");
		myTestCluster.setClusterBeschrijving("maven test cluster");
		myTestCluster.setClusterDirector(myTestDirector.getDirectorNaam());
			
		session.save(myTestRol);
		session.save(myTestDirector);
		session.save(myTestCluster);

		session.getTransaction().commit();
		session.close();
	}
    
}
