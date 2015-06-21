package portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import portal.model.Rapport;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RapportController {

//	@Autowired
//	private IportalDAO<Rapport, Serializable> portalDao;

	@RequestMapping(value="/rapporten", method=RequestMethod.GET)
	public ModelAndView rapport() {

		List<Rapport> listRapport = new ArrayList<Rapport>();
		ModelAndView model = new ModelAndView("rapport");
		model.addObject("rapportList", listRapport);
		return model;
	}

	@RequestMapping(value="/rapporten/create", method=RequestMethod.GET)  
	public ModelAndView create() {  
		Rapport Rapport = new Rapport();
		return new ModelAndView("rapportNew", "Rapport", Rapport);  
	}
	
	@RequestMapping(value="/rapporten/create", method=RequestMethod.POST)  
	public ModelAndView create(@ModelAttribute("Rapport") Rapport rapport) {  
		
		List<Rapport> listRapport = new ArrayList<Rapport>();
		
		listRapport.add(rapport);
		
		ModelAndView model = new ModelAndView("rapport");
		model.addObject("rapportList", listRapport);
	
		return new ModelAndView("rapport", "Rapport", rapport);  
	}  
}
