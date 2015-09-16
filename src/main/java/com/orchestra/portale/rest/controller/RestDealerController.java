/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.rest.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class RestDealerController {
    @Autowired
    PersistenceManager pm ;
    
    
    @RequestMapping(value = "/rest/prova")
    public @ResponseBody
    String restProva(HttpServletRequest request) {    
        String msg = "fuck!";
        return msg;
    }   
    
    @Secured("ROLE_DEALER")
    @RequestMapping(value = "/rest/dealer/login")
    public @ResponseBody
    String dealerLogin(HttpServletRequest request) throws Exception {    
        
        String username ="ciccio";
        String password="pippo";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes()); 
        byte bytePassword[] = md.digest();
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytePassword.length; i++) {
         sb.append(Integer.toString((bytePassword[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        String hash_password = sb.toString();
     
        //username e password in ingresso + hash password
        pm.findUserByUsernameAndPassword(username, hash_password);
        
        
        
        return "hash_password";
    }       
    
    @Secured("ROLE_DEALER")
    @RequestMapping(value = "/dealertest")
    public @ResponseBody
    String dealertest2(HttpServletRequest request) {    
        String msg = "sono un dealer STATELESSDELCAZZO!";
        return msg;
    }           
}
