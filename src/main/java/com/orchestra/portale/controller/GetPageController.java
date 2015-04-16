/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.Pages;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
public class GetPageController {
    @Autowired
    PersistenceManager pm;
   
    
    @RequestMapping(value= "/getPage", params="id")
    public ModelAndView getPagebyId(@RequestParam(value = "id") String id) {
      ModelAndView model = new ModelAndView("page");
      Pages page=pm.findPageById(id);
      model.addObject("pages", page);
       ArrayList<CompletePOI> pois=new ArrayList<CompletePOI>();
      if(page.getIdPoiList() != null ){
         pois= (ArrayList<CompletePOI>) pm.getCompletePoisById(page.getIdPoiList());
      }
      else {
          if (page.getCategorySlugList()!=null) {
              
            pois= (ArrayList<CompletePOI>) pm.getCompletePoiByCategories(page.getCategorySlugList());
          }
      }
      model.addObject("poivicini", pois);
      
      if(page.getComponents()!=null) {
      for (AbstractPoiComponent comp : page.getComponents()) {

            //associazione delle componenti al model tramite lo slug
            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            Class c;
            try {
                c = Class.forName(slug);
                model.addObject(cname, c.cast(comp));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GetPageController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
      }
      return model;
    } 
     @RequestMapping(value= "/page", params="sec")
      public ModelAndView getPagebySlug(@RequestParam(value = "sec") String sluginp) {
          ModelAndView model = new ModelAndView("page");
          if(sluginp.equals("home")){
           model = new ModelAndView("home");
          }
      Pages page=pm.findPageBySlug(sluginp);
      model.addObject("pages", page);
       ArrayList<CompletePOI> pois=new ArrayList<CompletePOI>();
      if(page.getIdPoiList() != null ){
         pois= (ArrayList<CompletePOI>) pm.getCompletePoisById(page.getIdPoiList());
      }
      else {
          if (page.getCategorySlugList()!=null) {
              
            pois= (ArrayList<CompletePOI>) pm.getCompletePoiByCategories(page.getCategorySlugList());
          }
      }
      model.addObject("poivicini", pois);
      
      if(page.getComponents()!=null) {
      for (AbstractPoiComponent comp : page.getComponents()) {

            //associazione delle componenti al model tramite lo slug
            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            Class c;
            try {
                c = Class.forName(slug);
                model.addObject(cname, c.cast(comp));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GetPageController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
      }
      return model;
    } 
}
