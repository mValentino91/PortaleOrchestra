/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.rest.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author antonio
 */
@Controller
public class NewRestTestController {
    @Autowired
    PersistenceManager pm;    
    
    @Secured("ROLE_DEALER")
    @RequestMapping(value = "/rest/dealer/prova2")
    public @ResponseBody
    String dealertest3(HttpServletRequest request) {    
        String msg = "TestNEW: sono un dealer e funziona il token!";
        return msg;
    }     
    
}
