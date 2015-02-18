/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */

@Controller
public class EditPoiController {
    @Autowired
    PersistenceManager pm;

    @RequestMapping(value= "/editpoi")
    public ModelAndView deletePoi() {
        ModelAndView model = new ModelAndView("deleteform");
        return model;
    }
    
    @RequestMapping(value= "/editpoi", params="name")
    public ModelAndView deletePoi(@RequestParam(value = "name") String name) {
        ModelAndView model = new ModelAndView("editpoi");
        CompletePOI poi= pm.findOneCompletePoiByName(name);
        model.addObject("nome", poi.getName());
         for (AbstractPoiComponent comp : poi.getComponents()) {

            //associazione delle componenti al model tramite lo slug
            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            
            try {
                Class c = Class.forName(slug);
                model.addObject(cname, c.cast(comp));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PoiViewController.class.getName()).log(Level.SEVERE, null, ex);
            
            }
         }
        return model;
    }
}
