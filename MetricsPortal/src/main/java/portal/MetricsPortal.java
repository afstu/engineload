package portal;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class MetricsPortal {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Logger log = Logger.getLogger("Portal");

		//
		// Site Root
		//
		get("/", (request, response) -> {

			Map<String, Object> viewObjects = new HashMap<String, Object>();

			viewObjects.put("templateName", "mainTemplate.ftl");

			return new ModelAndView(viewObjects, "admin.ftl");
		}, new FreeMarkerEngine());

		
		//
		// Gebruikers
		//
		get("/gebruikers", (request, response) -> {

			Map<String, Object> viewObjects = new HashMap<String, Object>();

			List<Gebruiker> gebruikerList;
			
			Query q = session.createQuery("from Gebruiker");
			gebruikerList = q.list();
		
			if (gebruikerList.size() == 0) {
				viewObjects.put("hasNoGebruikers", "Please define Gebruikers!");
				viewObjects.put("templateName", "gebruikerForm.ftl");
			} else {
				viewObjects.put("templateName", "gebruikerList.ftl");
				viewObjects.put("Gebruikers", gebruikerList);
			}
			
			return new ModelAndView(viewObjects, "admin.ftl");
		}, new FreeMarkerEngine());
		
		get("/gebruikers/create", (request, response) -> {

			Map<String, Object> viewObjects = new HashMap<String, Object>();
			
			viewObjects.put("templateName", "gebruikerForm.ftl");
			
			return new ModelAndView(viewObjects, "admin.ftl");
		}, new FreeMarkerEngine());
		
		post("/gebruikers/create", (request, response) -> {
            
			//	log.info("POST sent this: " + request.queryParams("rol-naam"));
				
                String gebruikerCorpKey = request.queryParams("gebruiker-corpkey");
                String gebruikerVoornaam = request.queryParams("gebruiker-voornaam");
                String gebruikerAchternaam = request.queryParams("gebruiker-achternaam");
                String gebruikerWachtwoord = request.queryParams("gebruiker-wachtwoord");
                String gebruikerBeschrijving = request.queryParams("gebruiker-beschrijving");
            
                Gebruiker g = new Gebruiker();
                
                g.setCorpKey(gebruikerCorpKey);
                g.setVoorNaam(gebruikerVoornaam);
                g.setAchterNaam(gebruikerAchternaam);
                g.setWachtWoord(gebruikerWachtwoord);
                g.setGebruikerBeschrijving(gebruikerBeschrijving);
                
                session.beginTransaction();
                session.persist(g);
                session.getTransaction().commit();
                
                response.status(201);
                response.redirect("/gebruikers");
                return "";
        });	

		//
		// Graphite
		//
		get("/graphite", (request, response) -> {

			Map<String, Object> viewObjects = new HashMap<String, Object>();

			List<Graphite> graphiteList;
			
			Query q = session.createQuery("from Graphite");
			graphiteList = q.list();
			
			if (graphiteList.size() == 0) {
				viewObjects.put("hasNoGraphite", "Please define the Graphite!");
				viewObjects.put("templateName", "graphiteForm.ftl");
			} else {
				viewObjects.put("templateName", "graphiteList.ftl");
				viewObjects.put("Graphites", graphiteList);
			}

			return new ModelAndView(viewObjects, "admin.ftl");
		}, new FreeMarkerEngine());

		get("/graphite/create", (request, response) -> {

			Map<String, Object> viewObjects = new HashMap<String, Object>();
			
			viewObjects.put("templateName", "clusterForm.ftl");
			
			return new ModelAndView(viewObjects, "admin.ftl");
		}, new FreeMarkerEngine());
		
		post("/graphite/create", (request, response) -> {
            
			//	log.info("POST sent this: " + request.queryParams("rol-naam"));
				
                String graphiteUrl = request.queryParams("graphite-url");
                
                Graphite g = new Graphite();
                
                g.setGraphiteUrl(graphiteUrl);
                
                session.beginTransaction();
                session.persist(g);
                session.getTransaction().commit();
                
                response.status(201);
                response.redirect("/graphite");
                return "";
        });	
		
		//
		// Clusters
		//
		get("/clusters", (request, response) -> {

			Map<String, Object> viewObjects = new HashMap<String, Object>();
			List<Cluster> clusterList;
			
			Query q = session.createQuery("from Cluster");
			clusterList = q.list();
		
			if (clusterList.size() == 0) {
				viewObjects.put("hasNoClusters", "Please define a Cluster!");
				viewObjects.put("templateName", "clusterForm.ftl");
			} else {
				viewObjects.put("templateName", "clusterList.ftl");
				viewObjects.put("Clusters", clusterList);
			}

			return new ModelAndView(viewObjects, "admin.ftl");
		}, new FreeMarkerEngine());

		get("/clusters/create", (request, response) -> {

			Map<String, Object> viewObjects = new HashMap<String, Object>();
			
			viewObjects.put("templateName", "clusterForm.ftl");
			
			return new ModelAndView(viewObjects, "admin.ftl");
		}, new FreeMarkerEngine());
		
		post("/clusters/create", (request, response) -> {
            
			//	log.info("POST sent this: " + request.queryParams("rol-naam"));
				
                String clusterNaam = request.queryParams("cluster-naam");
                String clusterStatus = request.queryParams("cluster-status");
                String clusterDirector = request.queryParams("cluster-director");
                String clusterBeschrijving = request.queryParams("cluster-beschrijving");
            
                Cluster c = new Cluster();
                
                c.setClusterNaam(clusterNaam);
                c.setClusterStatus(clusterStatus);
                c.setClusterDirector(clusterDirector);
                c.setClusterBeschrijving(clusterBeschrijving);
                
                session.beginTransaction();
                session.persist(c);
                session.getTransaction().commit();
                
                response.status(201);
                response.redirect("/clusters");
                return "";
        });	

		//
		// Rollen
		//
		get("/rollen", (request, response) -> {

			Map<String, Object> viewObjects = new HashMap<String, Object>();
			List<Rol> rolList;
			
			Query q = session.createQuery("from Rol");
			rolList = q.list();
			
			if (rolList.size() == 0) {
				viewObjects.put("hasNoRoles", "Please define a Role!");
				viewObjects.put("templateName", "roleForm.ftl");
			} else {
				viewObjects.put("templateName", "roleList.ftl");
				viewObjects.put("Rollen", rolList);
			}

			return new ModelAndView(viewObjects, "admin.ftl");
		}, new FreeMarkerEngine());

		get("/rollen/create", (request, response) -> {

			Map<String, Object> viewObjects = new HashMap<String, Object>();
			
			viewObjects.put("templateName", "roleForm.ftl");
			
			return new ModelAndView(viewObjects, "admin.ftl");
		}, new FreeMarkerEngine());
		
		
		post("/rollen/create", (request, response) -> {
            
			//	log.info("POST sent this: " + request.queryParams("rol-naam"));
				
                String rolNaam = request.queryParams("rol-naam");
                String rolBeschrijving = request.queryParams("rol-beschrijving");
            
                Rol r = new Rol();
                
                r.setRolNaam(rolNaam);
                r.setRolBeschrijving(rolBeschrijving);
                
                session.beginTransaction();
                session.persist(r);
                session.getTransaction().commit();
                
                response.status(201);
                response.redirect("/rollen");
                return "";
        });
	}
}