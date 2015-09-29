/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.rest.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.DealerOffer;
import com.orchestra.portale.persistence.sql.entities.Itinerary;
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
    @RequestMapping(value="/rest/dealer/validateOffer")
    Map<String,String> validateOffer(HttpServletRequest request, @RequestParam int idUserOfferChoice, int codeValidity){
        //Ricavo utente attuale
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        Map<String,String> s = new HashMap<String,String>();
        UserOfferChoice uc = pm.findByIdUserOfferChoice(idUserOfferChoice);
        int iddetail = uc.getIdItineraryDetail();
        Integer idItinerary = pm.findIdItineraryByIdItineraryDetail(iddetail);
        Itinerary itinerary = pm.findItineraryByIdItinerary(idItinerary);
        
        Integer id_user = itinerary.getIdUser();
        User client_user = pm.findUserById(id_user.longValue());
      
        Integer v_code = client_user.getPin();
        if(v_code !=null){
            if(v_code == codeValidity){
                pm.updateStatusOffer(idUserOfferChoice);
                s.put("status","ok");
                
            }
            else
                s.put("status","no");
        }
        return s;  
    }
    
    
    @Secured("ROLE_DEALER")
    @RequestMapping(value="/rest/dealer/getUserOffer")
    Map<String,String> viewUserChoice(HttpServletRequest request, @RequestParam int idUserOfferChoice){
        //Ricavo utente attuale
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        Map<String,String>off_detail = new HashMap<String,String>();

        UserOfferChoice uc = pm.findByIdUserOfferChoice(idUserOfferChoice);
        String off_name = pm.getOfferNameById(uc.getIdOffer());
        String off_desc = pm.getDescById(uc.getIdOffer());
        DealerOffer d = pm.findDealerOfferByidOffer(uc.getIdOffer());
        
        
        off_detail.put("Nome",off_name);
        off_detail.put("Desc",off_desc);
        off_detail.put("qta",Integer.toString(uc.getQta()));
        off_detail.put("prezzo_intero",Float.toString(d.getFullPrice()));
        off_detail.put("prezzo_ridotto",Float.toString(d.getDiscountedPrice()));
        off_detail.put("totale", Float.toString(uc.getSum()));
        off_detail.put("status",Integer.toString(uc.getStatus()));
        
        return off_detail;
        
    }
    
    @Secured("ROLE_DEALER")
    @RequestMapping(value = "/rest/dealer/getUserOffers")
    // List<UserOfferChoice> ViewUserChoices(HttpServletRequest request, @RequestParam String keyString){
    List<Map<Integer,String>> ViewUserChoices(HttpServletRequest request, @RequestParam String keyString){
        List<Map<Integer,String>>user_c = new ArrayList<Map<Integer,String>>();
        Map<Integer,String> userChoice_row = new HashMap<Integer,String>();
        
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
                //trovare le scelte cn status 0
                Iterable<UserOfferChoice>off = pm.findActiveChoiceCardByUser(iddetail);
                //Iterable<UserOfferChoice>off = pm.findChoiceCardByUser(iddetail);
                for(UserOfferChoice uc: off){
                    String off_name = pm.getOfferNameById(uc.getIdOffer());
                    userChoice_row.put(uc.getIdUserOfferChoice(), off_name);
                    
                    
                    user_choices.add(uc);
                    
                }
                user_c.add(userChoice_row);
                
            }
            
            
            
            return user_c;
        }
        else{
            throw new RuntimeException("Errore itinerario non trovato"); 
        }  
        
    }    
    
}
