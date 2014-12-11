/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.repositories.PoiMongoRepository;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
public class InfoPoiController {
    @Autowired
    PoiMongoRepository mongoRepo;

    @RequestMapping(value= "/infopoi", params= "id")
    public ModelAndView InfoPoi(@RequestParam(value = "id") String id) {
        ModelAndView model = new ModelAndView("infopoi");
        CompletePOI poi = mongoRepo.findOne(id);
        
        model.addObject("poi",poi);
       for (AbstractPoiComponent comp : poi.getComponents()) {
            try {
               String slug = comp.slug();
                int index = slug.lastIndexOf(".");
                String cname = slug.substring(index+1).replace("Component", "").toLowerCase();
                
                Class c = Class.forName(slug);
                model.addObject(cname, c.cast(comp));
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return model;
        
       
}
    
    @RequestMapping("/infopoi")
    public ModelAndView InfoPoiNA(){
        ModelAndView model = new ModelAndView("infopoiNA");
        return model;
    }
    
    @RequestMapping(value= "/infopoi", params= "nome")
    public ModelAndView InfoPoiNome(@RequestParam(value = "nome") String nome) {
        ModelAndView model = new ModelAndView("infopoi");
        Iterator poi_iterator=mongoRepo.findAll().iterator();
        boolean ok=false;
        while(poi_iterator.hasNext() && !ok){
            CompletePOI poi= (CompletePOI) poi_iterator.next();
            if(poi.getName().equals(nome)){
                ok=true;
                model.addObject("poi",poi);
               for (AbstractPoiComponent comp : poi.getComponents()) {
            try {
               String slug = comp.slug();
                int index = slug.lastIndexOf(".");
                String cname = slug.substring(index+1).replace("Component", "").toLowerCase();
                
                Class c = Class.forName(slug);
                model.addObject(cname, c.cast(comp));
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            }
        }
        
      
               
         return model;
        
      
}
                 
                }
 