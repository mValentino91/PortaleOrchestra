/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.sql.entities.Poi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author andrea
 */
@Controller
public class empty {
    @Autowired
    PersistenceManager pm ;
    
    
    @RequestMapping(value = "/empty", method = RequestMethod.GET)
    public @ResponseBody
    void empty(){
        
        
        Iterable<? extends CompletePOI>pois=pm.getAllCompletePoi();
        for(CompletePOI cpoi : pois){
            
            Poi poi = new Poi();
            poi.setIdMongo(cpoi.getId());
            poi.setName(cpoi.getName());
            
            poi.setAddress(cpoi.getAddress());
            poi.setPhone(null);
            pm.saveAllPoi(poi);
        }
        
        
    }
    
}
