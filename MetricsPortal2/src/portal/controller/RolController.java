package portal.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import portal.DAO.IportalDAO;
import portal.model.Rol;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RolController {

	@Autowired
	private IportalDAO<Rol, Serializable> portalDao;

	@RequestMapping(value="/rollen", method=RequestMethod.GET)
	public ModelAndView rol() {

		List<Rol> listRol = portalDao.list();
		ModelAndView model = new ModelAndView("rol");
		model.addObject("rolList", listRol);
		return model;
	}

	@RequestMapping(value="/rollen/create", method=RequestMethod.GET)  
	public ModelAndView create() {  
		Rol rol = new Rol();
		return new ModelAndView("rolNew", "Rol", rol);  
	}
	
	@RequestMapping(value="/rollen/create", method=RequestMethod.POST)  
	public ModelAndView create(@ModelAttribute("Rol") Rol rol) {  
		
		// TODO Validate Rol corp key tegen database!
			
		portalDao.create(rol);
		
		return new ModelAndView("rolEnkel", "Rol", rol);  
	}  

	@RequestMapping(value="/rollen/read")  
	public ModelAndView read(@RequestParam Serializable id) {  
		long longId = Long.parseLong((String) id);
		Rol readRol = portalDao.read(longId);  
		return new ModelAndView("rolEnkel", "Rol", readRol);  
	} 
	
	@RequestMapping(value="/rollen/update", method=RequestMethod.GET)  
	public ModelAndView update(@ModelAttribute("update") Serializable id) {  
		long longId = Long.parseLong((String) id);
		Rol rol = portalDao.read(longId);
		return new ModelAndView("rolUpdate", "Rol", rol);  
	} 

	@RequestMapping(value="/rollen/update", method=RequestMethod.POST)  
	public ModelAndView update(@ModelAttribute("Rol") Rol rol) {  
		Rol updateRol = portalDao.update(rol);  
		return new ModelAndView("rolEnkel", "Rol", updateRol);  
	} 

	@RequestMapping(value="/rollen/delete", method=RequestMethod.POST)  
	public ModelAndView delete(@ModelAttribute("delete") Serializable id) {  
		
		long longId = Long.parseLong((String) id);
		portalDao.delete(longId);  

		return new ModelAndView ("redirect:/rollen");  
	} 
}
