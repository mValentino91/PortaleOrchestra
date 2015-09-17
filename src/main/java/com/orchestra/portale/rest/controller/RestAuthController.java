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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author antonio
 */
@RestController
public class RestAuthController {
   
    @Autowired
    PersistenceManager pm ;
   
    @RequestMapping(value = "/restlogin", method = RequestMethod.POST)
    public Map dealerLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request) throws Exception {    
        
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
        
        //credenziali sbagliate o utente non esistente
        if(user == null){
            throw new AccessDeniedException("Invalid Credentials or User not found");
        }
        
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

        /*
        System.out.println("HASH PWD: " + hash_password);
        System.out.println("USERID: " + user.getId());
        System.out.println("TOKEN: " + token);
        System.out.println("CURR: " + new Date().getTime());
        System.out.println("EXP: " + expiration.getTime());
        */
      
        //object to return
        Map<String, String> tokenResp = new HashMap<String,String>();  
        tokenResp.put("validity", expiration.toString());
        tokenResp.put("token", token);
        tokenResp.put("username", username);
        
        return tokenResp;
    }       
    

    
}
