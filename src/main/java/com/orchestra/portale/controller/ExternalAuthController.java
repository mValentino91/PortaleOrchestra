/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import org.springframework.security.core.userdetails.User;
import com.orchestra.portale.externalauth.FbAuthenticationManager;
import com.orchestra.portale.persistence.sql.repositories.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.orchestra.portale.externalauth.FbAuthentication;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 * @author antonio
 */
@Controller
@RequestMapping
public class ExternalAuthController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/fblogin")
    public void fblogin(HttpServletRequest request, HttpServletResponse response){
        FbAuthenticationManager.fbLogin(request, response);
        
    }           
    
    @RequestMapping(value="/fbLoginJs", produces="application/json")
    public @ResponseBody
    String fbLoginJs(HttpServletRequest request, HttpServletResponse response){
        User user = FbAuthenticationManager.fbLoginJs(request, response, userRepository);
        
        Authentication auth = new FbAuthentication(user);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);                    

        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);         
        
        return "{\"login\":\"ok\"}";
    }           
    
}
