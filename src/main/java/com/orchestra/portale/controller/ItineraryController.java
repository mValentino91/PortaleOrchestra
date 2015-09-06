/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.managers.ItineraryManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.PricesComponent;
import com.orchestra.portale.persistence.mongo.documents.TicketPrice;
import com.orchestra.portale.persistence.sql.entities.DealerOffer;
import com.orchestra.portale.persistence.sql.entities.Itinerary;
import com.orchestra.portale.persistence.sql.entities.User;
import com.orchestra.portale.persistence.sql.entities.UserOfferChoice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @Autowired
    PersistenceManager pm;

    @RequestMapping(value = "/myItinerary", method = RequestMethod.GET)
    public ModelAndView viewMyItinerary() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        ModelAndView model = new ModelAndView("myItinerary");

        Iterable<Itinerary> i = ItineraryManager.retreiveItinerary(pm, Integer.parseInt(id_user));
        model.addObject("itinerary", i);

        return model;

    }

    @RequestMapping(value = "/newItinerary", method = RequestMethod.GET)
    public @ResponseBody
    String createItinerary(@RequestParam String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        ItineraryManager.createItinerary(pm, id_user, name);
        return "ok";
    }

    @RequestMapping(value = "/addPoiItinerary", method = RequestMethod.GET)
    public @ResponseBody
    String addPoiNewItinerary() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        ItineraryManager.addPoi(pm, 22, "5496cfecdf6ef624f2d63de7");
        return "ok";
    }

    @RequestMapping(value = "/addOfferItinerary", method = RequestMethod.GET)
    public @ResponseBody
    void addOfferItinerary(@RequestParam int qta, @RequestParam Integer id_offer, @RequestParam float sum, @RequestParam String type, @RequestParam Integer idItinerary, @RequestParam String name, @RequestParam String desc) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        
        if(idItinerary != null){
            ItineraryManager.addOffer(pm, idItinerary, id_offer, qta, sum, type, name, desc);
            //se l'offerta ke inserisco e gia presente aggiorno solo qta e prezzo
        }
        
    }
    
    
    @RequestMapping(value = "/removeOfferItinerary", method = RequestMethod.GET)
    public @ResponseBody
    void removeOfferItinerary(@RequestParam Integer id_offer, @RequestParam Integer idItinerary, @RequestParam String name, @RequestParam String type) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        
        if(idItinerary != null){
            ItineraryManager.removeOffer(pm, id_offer,idItinerary, name,type);
        }
        
    }
    

    @RequestMapping(value = "/myItineraryDetail", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView viewItineraryDetail(@RequestParam int idItinerary) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        int idUser = user.getId().intValue();
        ModelAndView model = null;
        
        int status = ItineraryManager.findStatusByIdItinerary(pm,idItinerary,idUser);
        System.out.println("status="+status);
        if (status == 0){
            model = ItineraryManager.viewItineraryDetail(pm, idItinerary);
        }
        else{
            if(status == 1){
                //dettaglio it completato
                model = ItineraryManager.viewCompleteItineraryDetail(pm, idItinerary);
            }
        }
        return model;
    }

    @RequestMapping(value = "/viewOfferPoi", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView viewOfferPoi(@RequestParam String idPoi, @RequestParam int idItinerary) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        ModelAndView model = ItineraryManager.viewOfferPoi(pm, idItinerary, idPoi);
        return model;
    }
    
    @RequestMapping(value = "/removeItinerary", method = RequestMethod.GET)
    public @ResponseBody
    void removeItinerary(@RequestParam Integer idItinerary) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        
        int idUser = user.getId().intValue();
        ItineraryManager.removeItinerary(pm,idItinerary,idUser);
    }
    
    @RequestMapping(value = "/completeItinerary", method = RequestMethod.GET)
    public @ResponseBody
    void completeItinerary(@RequestParam Integer idItinerary) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        int idUser = user.getId().intValue();
        ItineraryManager.completeItinerary(pm,idItinerary,idUser);
        
        
    }
    
    
}
