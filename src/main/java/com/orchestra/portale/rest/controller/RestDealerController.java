/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.rest.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    Map dealertest2(HttpServletRequest request) {    
        
        //Ricavo utente attuale
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        
        String msg = "sono un dealer, il token funziona e sono: " + user.getUsername() + " (" +user.getId() + ")";
        
        Map<String, String> ret = new HashMap<String,String>();
        
        ret.put("name", user.getUsername());
        ret.put("id", user.getId().toString());
        
        return ret;
    }      
    
    
    @Secured("ROLE_DEALER")
    @RequestMapping(value = "/rest/dealer/getOwnPoi")
    List RetrieveOwnPoi(HttpServletRequest request){
        
        //Ricavo utente attuale
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        
        List<String>own_pois = pm.getDealerOwnPoi(user.getId().intValue());
        return own_pois;
        
    }
}
