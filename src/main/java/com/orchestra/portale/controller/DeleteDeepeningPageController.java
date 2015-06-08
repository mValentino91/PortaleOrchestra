/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.DeepeningPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
    @Controller
@RequestMapping(value= "/admin")
public class DeleteDeepeningPageController {
    

    @RequestMapping(value= "/deletedpage")
    public ModelAndView deletePoi() {
        ModelAndView model = new ModelAndView("deletedpage");
        return model;
    }
    
    @RequestMapping(value= "/deldpage", params="name")
    public ModelAndView deletePoi(@RequestParam(value = "name") String name) {
        PersistenceManager pm = new ConcretePersistenceManager( LocaleContextHolder.getLocale().getDisplayLanguage() );
        ModelAndView model = new ModelAndView("deleted");
        try{
        DeepeningPage poi= pm.findDeepeningPageByName(name);
        pm.deleteDeepeningPage(poi);
        
        return model;
        }
        catch(RuntimeException e){
            ModelAndView model2= new ModelAndView("errorViewPoi");
            model2.addObject("err", "Errore impossibile trovare il poi: "+name);
            return model2;
        }
    }
   
    
    @RequestMapping(value= "/deldpage", params="id")
    public ModelAndView deletePoiById(@RequestParam(value = "id") String id) {
        try{
            PersistenceManager pm = new ConcretePersistenceManager( LocaleContextHolder.getLocale().getDisplayLanguage() );
        ModelAndView model = new ModelAndView("deleted");
        DeepeningPage poi= pm.findDeepeningPage(id);
        pm.deleteDeepeningPage(poi);
        return model;
    }
    catch(RuntimeException e){
            ModelAndView model2= new ModelAndView("errorViewPoi");
            model2.addObject("err", "Errore impossibile trovare il poi con id: "+id);
            return model2;
        }
    }
    
}

