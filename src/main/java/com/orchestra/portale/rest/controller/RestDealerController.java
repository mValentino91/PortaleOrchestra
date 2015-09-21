/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.rest.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.Token;
import com.orchestra.portale.persistence.sql.entities.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.UUID;
import java.util.Date;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @RequestMapping(value = "/dealer/restlogin", method = RequestMethod.POST)
    public @ResponseBody
    String dealerLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request) throws Exception {    
        
        //ore di validità del token
        Integer token_h_val = 3;
        
        //calcola hash password
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes()); 
        byte bytePassword[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytePassword.length; i++) {
         sb.append(Integer.toString((bytePassword[i] & 0xff) + 0x100, 16).substring(1));
        }
        String hash_password = sb.toString();
        
        
        //recuera user da username e hash password
        User user = pm.findUserByUsernameAndPassword(username, hash_password);
        
        if(user != null){
            //crea token
            String token = UUID.randomUUID().toString();
            
            //calcola scadenza
            Date expiration= new Date(new Date().getTime() + token_h_val*3600*1000);
            
            //cerca il vecchio token
            Token tokenOld = pm.getTokenByIdUser(user.getId().intValue());
            
            //se l'user ha già un token
            if(tokenOld!=null){
                //update
                pm.updateToken(tokenOld.getId(), token, expiration);
            }
            else{
                //save
                Token tokenObj = new Token();
                tokenObj.setIdUser(user.getId().intValue());
                tokenObj.setToken(token);
                tokenObj.setValidity(expiration);                
                pm.saveToken(tokenObj);
            }
            
            System.out.println("HASH PWD: " + hash_password);
            System.out.println("USERID: " + user.getId());
            System.out.println("TOKEN: " + token);
            System.out.println("CURR: " + new Date().getTime());
            System.out.println("EXP: " + expiration.getTime());
        }
        
        
        //TO-DO: RITORNARE JSON
        return "ciao";
    }       
    
    @Secured("ROLE_DEALER")
    @RequestMapping(value = "/rest/dealer/prova")
    public @ResponseBody
    String dealertest2(HttpServletRequest request) {    
        String msg = "sono un dealer e funziona il token!";
        return msg;
    }           
    
}
