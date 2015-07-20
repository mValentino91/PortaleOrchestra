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

@Controller
@Secured("ROLE_USER")
public class ItineraryController {
    @Autowired PersistenceManager pm ;
    @Autowired ItineraryManager im;
    
    @RequestMapping(value = "/saveFavorite", method = RequestMethod.GET)
    public @ResponseBody String createItinerary() {
        
        //@RequestParam String id_poi
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        
        im.createItinerary(Integer.parseInt(id_user));
        return "ok";
    }
    
}
 */