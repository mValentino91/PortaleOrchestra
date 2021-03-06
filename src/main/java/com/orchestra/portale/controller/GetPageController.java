/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_It;
import com.orchestra.portale.persistence.mongo.documents.Pages;
import com.orchestra.portale.persistence.mongo.documents.TopTenComponent;
import com.orchestra.portale.persistence.sql.entities.Top10;
import com.orchestra.portale.utils.CoupleString;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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
    PersistenceManager pm ;

    @RequestMapping(value= "/getPage", params="id")
    public ModelAndView getPagebyId(@RequestParam(value = "id") String id) {
      ModelAndView model = new ModelAndView("page");
      Pages page=pm.findPageById(id);
      model.addObject("pages", page);
       ArrayList<CompletePOI_It> pois=new ArrayList<CompletePOI_It>();
       
      if(page.getIdPoiList() != null ){
         pois= (ArrayList<CompletePOI_It>) pm.getCompletePoisById(page.getIdPoiList());
         model.addObject("poivicini", pois);
      }
      else {
          if (page.getCategorySlugList()!=null) {
              
            Iterable<? extends CompletePOI> poilist =  pm.getCompletePoiByCategories(page.getCategorySlugList());
             model.addObject("poivicini", poilist);
          }
      }
     
      
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
          pm.setLang(LocaleContextHolder.getLocale().toString());
      Pages page=pm.findPageBySlug(sluginp);
      model.addObject("pages", page);
      ArrayList<CompletePOI_It> pois=new ArrayList<CompletePOI_It>();
       
      if(page.getIdPoiList() != null ){
         pois= (ArrayList<CompletePOI_It>) pm.getCompletePoisById(page.getIdPoiList());
         model.addObject("poivicini", pois);
      }
      else {
          if (page.getCategorySlugList()!=null) {
              
            Iterable<? extends CompletePOI> poilist =  pm.getCompletePoiByCategories(page.getCategorySlugList());
             model.addObject("poivicini", poilist);
          }
      }
      if(page.getComponents()!=null) {
      for (AbstractPoiComponent comp : page.getComponents()) {

            //associazione delle componenti al model tramite lo slug
            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            Class c;
            if(cname.equals("topten")){
                TopTenComponent tp = (TopTenComponent) comp;
                String top_name =tp.getTable();
                
                Iterable<Top10> pois_id = pm.selectTopPoi(top_name);
                
                ArrayList<CompletePOI_It> cp= new ArrayList<CompletePOI_It>();
                
                for (Top10 cs : pois_id) {
                    CompletePOI_It complete= (CompletePOI_It) pm.getCompletePoiById(cs.getIdpoi());
                    complete.setShortDescription(cs.getDescr());
                    cp.add(complete);
                }
                model.addObject("toppois",cp);
                
            }
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
