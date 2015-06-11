/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.orchestra.portale.controller;

import com.google.gson.Gson;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Marco
 */
@Controller
@RequestMapping("/itToEn")
public class ItToEnBackup {
    
    @Autowired
    PersistenceManager pm;
    
    @RequestMapping(value = "/pois")
    public @ResponseBody
    String getJsonForCategory(@RequestParam String[] category) {
        
        pm.setLang(LocaleContextHolder.getLocale().toString());
        
        for (CompletePOI poi : pm.getAllCompletePoi()) {
                
            CompletePOI_En poiEn = new CompletePOI_En();
            poiEn.setId(poi.getId());
            poiEn.setAddress(poi.getAddress());
            poiEn.setCategories(poi.getCategories());
            poiEn.setComponents(poi.getComponents());
            poiEn.setEnd_date(poi.getEnd_date());
            poiEn.setExternalUrl(poi.getExternalUrl());
            poiEn.setLocation(poi.getLocation());
            poiEn.setName(poi.getName());
            poiEn.setShortDescription(poi.getShortDescription());
            poiEn.setStart_date(poi.getStart_date());
            poiEn.setVisibility(poi.getVisibility());
            
            pm.saveEnPoi(poiEn);
            
            }
  
        return "OK";
    }
    
}
