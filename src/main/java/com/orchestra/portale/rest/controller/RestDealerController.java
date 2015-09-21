/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.rest.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.Role;
import com.orchestra.portale.persistence.sql.entities.User;
import com.orchestra.portale.persistence.sql.entities.UserOfferChoice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author antonio
 */
@RestController
public class RestDealerController {
    @Autowired
    PersistenceManager pm ;
    
    
    @RequestMapping(value = "/rest/prova")
    String restProva(HttpServletRequest request) {    
        String msg = "ciao bello!";
        return msg;
    }   
 
    @Secured("ROLE_DEALER")
    @RequestMapping(value = "/rest/dealer/prova")
    public @ResponseBody
    String dealertest2(HttpServletRequest request) {    
        String msg = "sono un dealer e funziona il token!";
        return msg;
    }           
    
    
    @Secured("ROLE_DEALER")
    @RequestMapping(value = "/rest/dealer/getOwnPoi")
    List<String> RetrieveOwnPoi(HttpServletRequest request){
        
        //Ricavo utente attuale
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        
        List<String>own_pois = pm.getDealerOwnPoi(user.getId().intValue());
        return own_pois;
        
    }
    
    @Secured("ROLE_DEALER")
    @RequestMapping(value = "/rest/dealer/getUserOffer")
    List<UserOfferChoice> ViewUserChoices(HttpServletRequest request, @RequestParam String keyString){
        List<UserOfferChoice>user_choices = new ArrayList<UserOfferChoice>();
        //Ricavo utente attuale
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        
        //Ricavo poi che gestisco
        List<String>own_pois = pm.getDealerOwnPoi(user.getId().intValue());
        List<Integer>user_idd= new ArrayList<Integer>();
                
        //recupero l'itinerario dell'utente recuperato dalla keystring
        Integer idItinerary = pm.getUserItinerary(keyString);
        if(idItinerary!=null){
            
            for(String idPoi: own_pois){
                Integer idd = pm.findIdItineraryDetailByIdItineraryAndIdPoi(idItinerary, idPoi);
                if(idd!=null)
                    user_idd.add(idd);
            }
            
            for(Integer iddetail: user_idd){
                Iterable<UserOfferChoice>off = pm.findChoiceCardByUser(iddetail);
                for(UserOfferChoice uc: off){
                    user_choices.add(uc);
                }
            }
            
            return user_choices;
        }
        else{
            throw new AccessDeniedException("Invalid User not found"); 
        }  
        
    }    
    
}
