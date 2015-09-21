/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.rest.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.Role;
import com.orchestra.portale.persistence.sql.entities.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author antonio
 */
@RestController
public class TestController {
    @Autowired
    PersistenceManager pm ;    
    
    @Secured("hasRole('ROLE_DEALER')")
    @RequestMapping(value = "/rest/test")
    Map test(HttpServletRequest request) {    
        
        //Ricavo utente attuale
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        
        
        String msg = "sono un dealer, il token funziona e sono: " + user.getUsername() + " (" +user.getId() + ")";
        System.out.println(msg);
        System.out.println("RUOLI:");
        for(Role r : user.getRoles()){
            System.out.println(r.getRole());
        }
        
        if(!request.isUserInRole("ROLE_DEALER"))
            System.out.println("NON è vero, non sono un dealer");
        
        Map<String, String> ret = new HashMap<String,String>();
        
        ret.put("name", user.getUsername());
        ret.put("id", user.getId().toString());
        
        return ret;
        
    }       
}
