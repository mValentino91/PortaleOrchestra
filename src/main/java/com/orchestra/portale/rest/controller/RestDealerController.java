/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.rest.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author antonio
 */
@Controller
public class RestDealerController {
    @RequestMapping(value = "/rest/prova")
    public @ResponseBody
    String restProva(HttpServletRequest request) {    
        String msg = "fuck!";
        return msg;
    }   
    
    @Secured("ROLE_DEALER")
    @RequestMapping(value = "/rest/dealer/test")
    public @ResponseBody
    String dealertest(HttpServletRequest request) {    
        String msg = "sono un dealer!";
        return msg;
    }       
    
    @Secured("ROLE_DEALER")
    @RequestMapping(value = "/dealertest")
    public @ResponseBody
    String dealertest2(HttpServletRequest request) {    
        String msg = "sono un dealer STATELESSDELCAZZO!";
        return msg;
    }           
}
