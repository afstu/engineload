package portal.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import portal.DAO.IportalDAO;
import portal.model.Graphite;

/**
 * Handles requests for the application home page.
 */
@Controller
public class GraphiteController {

	@Autowired
	private IportalDAO<Graphite, Serializable> portalDao;

	@RequestMapping(value="/graphite", method=RequestMethod.GET)
	public ModelAndView graphite() {

		List<Graphite> listGraphite = portalDao.list();
		ModelAndView model = new ModelAndView("graphite");
		model.addObject("graphiteList", listGraphite);
		return model;
	}

//	@RequestMapping(value="/graphite/create", method=RequestMethod.GET)  
//	public ModelAndView create() {  
//		Graphite graphite = new Graphite();
//		return new ModelAndView("graphiteNew", "Graphite", graphite);  
//	}
//	
//	@RequestMapping(value="/graphite/create", method=RequestMethod.POST)  
//	public ModelAndView create(@ModelAttribute("Graphite") Graphite graphite) {  
//		
//			
//		portalDao.create(graphite);
//		
//		return new ModelAndView ("redirect:/graphite");  
//	}  

	@RequestMapping(value="/graphite/update", method=RequestMethod.GET)  
	public ModelAndView update(@ModelAttribute("update") Serializable id) {  
		long longId = Long.parseLong((String) id);
		Graphite graphite = portalDao.read(longId);
		return new ModelAndView("graphiteUpdate", "Graphite", graphite);    
	} 

	@RequestMapping(value="/graphite/update", method=RequestMethod.POST)  
	public ModelAndView update(@ModelAttribute("Graphite") Graphite graphite) {  
		Graphite updateGraphite = portalDao.update(graphite);  
		return new ModelAndView("redirect:/graphite");  
	} 
}
