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
    String addPoiNewItinerary(@RequestParam String idPoi, @RequestParam int idItinerary) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        if(idItinerary!=-1){
            ItineraryManager.addPoi(pm, idItinerary, idPoi);
            return "ok";
        }
        return "no";
    }
    
    @RequestMapping(value = "/removePoiItinerary", method = RequestMethod.GET)
    public @ResponseBody
    void removePoiNewItinerary(@RequestParam int idItinerary, @RequestParam String idPoi) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        int status=0;
        ItineraryManager.removePoi(pm, idItinerary, idPoi);
    }

    @RequestMapping(value = "/addOfferItinerary", method = RequestMethod.GET)
    public @ResponseBody
    int addOfferItinerary(@RequestParam int qta, @RequestParam String idPoi,@RequestParam Integer id_offer, @RequestParam float sum, @RequestParam String type, @RequestParam Integer idItinerary, @RequestParam String name, @RequestParam String desc) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        int status=0;
        
        if(idItinerary != null){
            status = ItineraryManager.addOffer(pm, idItinerary,idPoi,id_offer, qta, sum, type, name, desc);
            //se l'offerta ke inserisco e gia presente aggiorno solo qta e prezzo
        }
        return status;
        
    }
    
    
    @RequestMapping(value = "/removeOfferItinerary", method = RequestMethod.GET)
    public @ResponseBody
    int removeOfferItinerary(@RequestParam Integer id_offer, @RequestParam Integer idItinerary, @RequestParam String idPoi, @RequestParam String name, @RequestParam String type) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        int status=0;
        
        if(idItinerary != null){
            status=ItineraryManager.removeOffer(pm, id_offer,idPoi,idItinerary, name,type);
        }
        return status;
        
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
