package portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

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
import portal.model.Rol;

/**
 * Handles requests for the application home page.
 */
@Controller
public class GebruikerController {

	@Resource(name="gebruikerDao")
	private IportalDAO<Gebruiker, Serializable> portalDao;
	
	
	@Resource(name="rolDao")
	private IportalDAO<Rol, Serializable> portalRolDao;

	@RequestMapping(value="/gebruikers", method=RequestMethod.GET)
	public ModelAndView gebruikers() {

		List<Gebruiker> listGebruikers = portalDao.list();
		ModelAndView model = new ModelAndView("gebruiker");
		model.addObject("gebruikerList", listGebruikers);
		return model;
	}

	@RequestMapping(value="/gebruikers/create", method=RequestMethod.GET)  
	public ModelAndView create() {  
		List<Rol> rl = portalRolDao.list();
		
//		Map<String, Rol> rolList = new LinkedHashMap<String,Rol>();
//		
//		for (Rol r : rl) {
//			rolList.put(r.getRolNaam(), r);
//		}
		
		Gebruiker gebruiker = new Gebruiker();
		ModelAndView model = new ModelAndView("gebruikerNew");
		model.addObject("Gebruiker", gebruiker);
		model.addObject("RolList", rl);
		
		return model;  
	}
	
	@RequestMapping(value="/gebruikers/create", method=RequestMethod.POST)  
	public ModelAndView create(@ModelAttribute("gebruiker") Gebruiker gebruiker)  {  
//		public ModelAndView create(@ModelAttribute("gebruiker") Gebruiker gebruiker, @RequestParam("RolList") List<String> rolIds)  {		
//		Set<Rol> sr = new HashSet<Rol>();
//		gebruiker.setGebruikerRollen(sr);
//		
//		for (String id : rolIds) {
//			long l = Long.parseLong(id);
//			gebruiker.addRol(portalRolDao.read(l));
//		}
		
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
			return new ModelAndView("redirect:/gebruikers");  
		}
		
		portalDao.delete(corpkey);  
		return new ModelAndView("redirect:/gebruikers");  
	} 
}
