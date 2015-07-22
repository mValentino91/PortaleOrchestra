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

/**
 *
 * @author andzaccaro
*/

@Controller
@Secured("ROLE_USER")
public class ItineraryController {
    
    
    @Autowired PersistenceManager pm ;

    @RequestMapping(value = "/newItinerary", method = RequestMethod.GET)
    public @ResponseBody
    String createItinerary(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
             
        ItineraryManager.createItinerary(pm, id_user);

        return "ok";
    }
    
    @RequestMapping(value = "/addPoiItinerary", method = RequestMethod.GET)
    public @ResponseBody
    String addPoiNewItinerary(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        ItineraryManager.addPoi(pm, 22, id_user);
        return "ok";
    }
    
    @RequestMapping(value = "/addOfferItinerary", method = RequestMethod.GET)
    public @ResponseBody
    String addOfferItinerary(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        
        ItineraryManager.addOffer(pm, 22, id_user, 22, 5, 100);
        
        
        return "ok";
    }
    
    
}