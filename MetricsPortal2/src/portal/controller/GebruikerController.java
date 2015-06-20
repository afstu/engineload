package portal.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import portal.DAO.IportalDAO;
import portal.model.Gebruiker;

/**
 * Handles requests for the application home page.
 */
@Controller
public class GebruikerController {

	@Autowired
	private IportalDAO<Gebruiker, Serializable> portalDao;

	@RequestMapping(value="/gebruikers", method=RequestMethod.GET)
	public ModelAndView gebruikers() {

		List<Gebruiker> listGebruikers = portalDao.list();
		ModelAndView model = new ModelAndView("gebruiker");
		model.addObject("gebruikerList", listGebruikers);
		return model;
	}

	@RequestMapping(value="/gebruikers/create", method=RequestMethod.GET)  
	public ModelAndView create() {  
		Gebruiker gebruiker = new Gebruiker();
		return new ModelAndView("gebruikerNew", "gebruiker", gebruiker);  
	}
	
	@RequestMapping(value="/gebruikers/create", method=RequestMethod.POST)  
	public ModelAndView create(@ModelAttribute("gebruiker") Gebruiker gebruiker) {  
		
		// TODO Validate gebruiker corp key tegen database!
		
		
		portalDao.create(gebruiker);
		
		return new ModelAndView("gebruikerEnkel", "gebruiker", gebruiker);  
	}  

	@RequestMapping(value="/gebruikers/read")  
	public ModelAndView read(@RequestParam String corpkey) {  
		Gebruiker readGebruiker = portalDao.read(corpkey);  
		return new ModelAndView("gebruikerEnkel", "gebruiker", readGebruiker);  
	} 
	
	@RequestMapping(value="/gebruikers/update", method=RequestMethod.GET)  
	public ModelAndView update(@ModelAttribute("update") String corpkey) {  
		
		Gebruiker gebruiker = portalDao.read(corpkey);
		return new ModelAndView("gebruikerUpdate", "gebruiker", gebruiker);  
	} 

	@RequestMapping(value="/gebruikers/update", method=RequestMethod.POST)  
	public ModelAndView update(@ModelAttribute("gebruiker") Gebruiker gebruiker) {  
		Gebruiker updateGebruiker = portalDao.update(gebruiker);  
		return new ModelAndView("gebruikerEnkel", "gebruiker", updateGebruiker);  
	} 

	@RequestMapping(value="/gebruikers/delete", method=RequestMethod.POST)  
	public ModelAndView delete(@ModelAttribute("delete") String corpkey) {  
		
		if (corpkey.equalsIgnoreCase("admin")) {
			return new ModelAndView ("redirect:/gebruikers");  
		}
		
		portalDao.delete(corpkey);  
		return new ModelAndView ("redirect:/gebruikers");  
	} 
}