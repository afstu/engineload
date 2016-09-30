package portal.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import portal.DAO.IportalDAO;
import portal.model.Cluster;
import portal.model.Director;
import portal.model.Rol;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ClusterController {

	@Autowired
	private IportalDAO<Cluster, Serializable> portalDao;

	@RequestMapping(value="/clusters", method=RequestMethod.GET)
	public ModelAndView clusters() {

		List<Cluster> listClusters = portalDao.list();
		ModelAndView model = new ModelAndView("cluster");
		model.addObject("clusterList", listClusters);
		return model;
	}

	@RequestMapping(value="/clusters/create", method=RequestMethod.GET)  
	public ModelAndView create() {  

		Cluster cluster = new Cluster();

		return new ModelAndView("clusterNew", "Cluster", cluster);  
	}

	@RequestMapping(value="/clusters/create", method=RequestMethod.POST)  
	public ModelAndView create(@ModelAttribute("Cluster") Cluster cluster) {  

		// TODO Validate cluster naan tegen database!

		portalDao.create(cluster);

		return new ModelAndView("clusterEnkel", "Cluster", cluster);  
	}  

	@RequestMapping(value="/clusters/read")  
	public ModelAndView read(@RequestParam Serializable id) {
		long longId = Long.parseLong((String) id);
		Cluster readcluster = portalDao.read(longId);  
		return new ModelAndView("clusterEnkel", "Cluster", readcluster);  
	} 

	@RequestMapping(value="/clusters/update", method=RequestMethod.GET)  
	public ModelAndView update(@ModelAttribute("update") Serializable id) {  
		long longId = Long.parseLong((String) id);
		Cluster cluster = portalDao.read(longId);
		return new ModelAndView("clusterUpdate", "Cluster", cluster);  
	} 

	@RequestMapping(value="/clusters/update", method=RequestMethod.POST)  
	public ModelAndView update(@ModelAttribute("Cluster") Cluster cluster) {  
		Cluster updatecluster = portalDao.update(cluster);  
		return new ModelAndView("clusterEnkel", "Cluster", updatecluster);  
	} 

	@RequestMapping(value="/clusters/delete", method=RequestMethod.POST)  
	public ModelAndView delete(@RequestParam("delete") Serializable id) {  
		long longId = Long.parseLong((String) id);
		portalDao.delete(longId);  
		return new ModelAndView("redirect:/clusters");  
	} 
}
