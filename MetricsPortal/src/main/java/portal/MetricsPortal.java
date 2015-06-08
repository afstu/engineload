package portal;

import static spark.Spark.*;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
		Logger log = Logger.getLogger("Portal -- ");

		log.info("Setting up Demo Database...");
		setupDemoDatabase();
		
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
		
		//
		// Generic Rapporten
		//
		get("/rapporten", (request, response) -> {

			Calendar cal = Calendar.getInstance();
			Date date = cal.getTime();
			
			Map<String, Object> viewObjects = new HashMap<String, Object>();

			// int myId = request.queryParams("gebruiker-id");
			
			// SQL for Graphite URL
			List<Graphite> graphiteUrl = session.createQuery("from Graphite").list();
						
			// SQL for Cluster data
			// SQL to look up which cluster a user can see a user can see
			
//			Query clusterData = session.createQuery("select g.RolId, r.RolId, c.RolId, c.ClusterName, c.ClusterStatus, c.director from Cluster as c" + 
//													"join Rol as r" +
//													"join Gebruiker as g" +
//													"where g.id = 'myId' " 
//													);
					
			// Demo	1 hour report	
			List<Cluster> clusterData = session.createQuery("from Cluster c where c.ClusterNaam='kvm' and c.ClusterStatus='D'").list();
			
			ArrayList<Rapport> rapportList = new ArrayList<Rapport>();
			
			for (int i = 0; i < clusterData.size(); i++) {
				
				Rapport r = new Rapport();
				r.setGraphiteUrl(graphiteUrl.get(0).getGraphiteUrl());
					
				r.setClusterNaam(clusterData.get(i).getClusterNaam());
				r.setClusterStatus(clusterData.get(i).getClusterStatus());
				r.setClusterDirector(clusterData.get(i).getClusterDirector());
				r.setClusterBeschrijving(clusterData.get(i).getClusterBeschrijving());
						
				r.setEindTijd(date.getTime());
				r.setBeginTijd(date.getTime() - 3600 * 1000L);
				
				rapportList.add(r);
			}
			
			if (rapportList.size() == 0) {
				viewObjects.put("hasNoRapporten", "Er zijn geen rapporten!");
			} else {
				viewObjects.put("templateName", "rapportList.ftl");
				viewObjects.put("Rapporten", rapportList);
			}

			return new ModelAndView(viewObjects, "admin.ftl");
		}, new FreeMarkerEngine());
	}
	
	static void setupDemoDatabase() {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Graphite g = new Graphite();
		Cluster c = new Cluster();
		
		g.setGraphiteUrl("http://graphite");
		c.setClusterNaam("kvm");
		c.setClusterStatus("D");
		c.setClusterDirector("director1");
		c.setClusterBeschrijving("Demo Cluster!");
		
        session.beginTransaction();
        session.persist(g);
        session.persist(c);
        session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
}