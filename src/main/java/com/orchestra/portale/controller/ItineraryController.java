/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.managers.ItineraryManager;
import com.orchestra.portale.persistence.sql.entities.Itinerary;
import com.orchestra.portale.persistence.sql.entities.User;
import com.orchestra.portale.persistence.sql.entities.UserItinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author andzaccaro
*/

@Controller
@Secured("ROLE_USER")
public class ItineraryController {
    
    
    @Autowired PersistenceManager pm ;
    
    @RequestMapping(value = "/myItinerary", method = RequestMethod.GET)
    public ModelAndView viewMyItinerary(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        ModelAndView model = new ModelAndView("myItinerary");
        
        Iterable<Itinerary> i = ItineraryManager.retreiveItinerary(pm, Integer.parseInt(id_user));
        model.addObject("itinerary",i);
        
        return model;
        
                
    }
    

    @RequestMapping(value = "/newItinerary", method = RequestMethod.GET)
    public @ResponseBody
    String createItinerary(@RequestParam String name){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        System.out.println("caccapupu");
        ItineraryManager.createItinerary(pm, id_user,name);
        return "ok";
    }
    
    @RequestMapping(value = "/addPoiItinerary", method = RequestMethod.GET)
    public @ResponseBody
    String addPoiNewItinerary(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        ItineraryManager.addPoi(pm, 22, "5496cfecdf6ef624f2d63de7");
        return "ok";
    }
    
    @RequestMapping(value = "/addOfferItinerary", method = RequestMethod.GET)
    public @ResponseBody
    String addOfferItinerary(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        
        //aggiungere parametri input funzione string
        ItineraryManager.addOffer(pm, 22, "aaa", 16, 5, 100);
        //aggiornare il valore di id offer choice all'itinerary detail
        
        return "ok";
    }
   
    @RequestMapping(value = "/removeOfferItinerary", method = RequestMethod.GET)
    public @ResponseBody
    String removeOfferItinerary(){
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        
        //recupero itinerario selezionato dall'utente ottenendo la key it detail 
        Integer idDetail = pm.findIdDetailByidOffer(16);
        Integer idItinerary = pm.findItineraryByIdItineraryDetail(idDetail);
        Long idUser = pm.findUserByIdItinerary(idItinerary);
        
        if(user.getId().longValue() == idUser.longValue()){
            //utente leggittimato a fare l'operazione
            //signature removeOffer(persistence, id itinerary detail)
            ItineraryManager.removeOffer(pm, 16, idDetail);
            return "ok";    
        }
        return "no";
    }
}