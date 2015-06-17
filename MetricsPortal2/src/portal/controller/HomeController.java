package portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import portal.DAO.GebruikerDAO;
import portal.model.Gebruiker;
 
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
     
    @Autowired
    private GebruikerDAO gebruikerDao;
     
    @RequestMapping(value="/home")
    public ModelAndView home() {
        List<Gebruiker> listGebruikers = gebruikerDao.list();
        ModelAndView model = new ModelAndView("home");
        model.addObject("gebruikerList", listGebruikers);
        return model;
    }

}
