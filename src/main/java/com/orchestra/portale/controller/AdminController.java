/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.sql.entities.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
public class AdminController {
    
    @Autowired
    PersistenceManager pm ;

    @Secured("ROLE_SUPERADMIN")
    @RequestMapping(value = "/admin")
    public ModelAndView admin() {
        ModelAndView model = new ModelAndView("admin");
        return model;
    }

    @RequestMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("home");
        return model;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/getOwnPois")
    public ModelAndView getOwnPois(HttpServletRequest request) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        Integer id_user = user.getId().intValue();
        
        pm.setLang(LocaleContextHolder.getLocale().toString());
        
        Iterable<? extends CompletePOI> pois = pm.getPoiByOwner(id_user);
        
                
        ModelAndView model = new ModelAndView("ownPois");
        model.addObject("pois", pois);
        
        return model;
    }

    
}
