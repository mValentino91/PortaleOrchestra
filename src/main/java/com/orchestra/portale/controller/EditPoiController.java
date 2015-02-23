/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.DescriptionComponent;
import com.orchestra.portale.persistence.mongo.documents.Section;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */

@Controller
@RequestMapping(value= "/admin")
public class EditPoiController {
    @Autowired
    PersistenceManager pm;

    @RequestMapping(value= "/editpoi")
    public ModelAndView editPoi() {
        ModelAndView model = new ModelAndView("editpoi");
        return model;
    }
    
    @RequestMapping(value= "/editpoi", params="name")
    public ModelAndView editPoi(@RequestParam(value = "name") String name) {
        ModelAndView model = new ModelAndView("editform");
        try {
        CompletePOI poi= pm.findOneCompletePoiByName(name);
        model.addObject("nome", poi.getName());
        model.addObject("loc", poi.getLocation());
        model.addObject("cat", poi.getCategories());
        model.addObject("id", poi.getId());
        model.addObject("shortD", poi.getShortDescription());
        model.addObject("addr", poi.getAddress());
        
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
    catch(RuntimeException e){
            ModelAndView model2= new ModelAndView("errorViewPoi");
            model2.addObject("err", "Errore impossibile trovare il poi: "+name);
            return model2;
        }
    }
    
    @RequestMapping(value= "/updatepoi", method = RequestMethod.POST)
    public ModelAndView updatePoi(@RequestParam Map<String,String> params) {

    CompletePOI poi= pm.getCompletePoiById(params.get("id"));
     ModelAndView model = new ModelAndView("editedpoi");
        
        poi.setId(params.get("id"));
        poi.setName(params.get("name"));
        poi.setAddress(params.get("address"));
        double lat= Double.parseDouble(params.get("latitude"));
        double longi= Double.parseDouble(params.get("longitude"));
        poi.setLocation(new double[] { lat, longi });
        poi.setShortDescription(params.get("shortd"));
        int i=1;
        ArrayList<String> categories=new ArrayList<String>();
        while(params.containsKey("category"+i)){
          
            categories.add(params.get("category"+i));
  
            
            i=i+1;
        }
        poi.setCategories(categories);
        ArrayList<AbstractPoiComponent> newlistComponent = new ArrayList<AbstractPoiComponent>();
         //DESCRIPTION COMPONENT
             i=1;
             if(params.containsKey("par"+i)) {
                 ArrayList<Section> list = new ArrayList<Section>();
               
                 while(params.containsKey("par"+i)){
                     Section section = new Section();
                     if(params.containsKey("titolo"+i)) {
                         section.setTitle(params.get("titolo"+i));
                     }
                     section.setDescription(params.get("par"+i));
                     list.add(section);
                     i=i+1;
                     
                 }
             DescriptionComponent description_component = new DescriptionComponent();
            description_component.setSectionsList(list);
            
            List<AbstractPoiComponent> listComponent = poi.getComponents();
            for (AbstractPoiComponent comp : poi.getComponents()) {

            //associazione delle componenti al model tramite lo slug
            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            if(!cname.equals("description"))
                newlistComponent.add(comp);
            }
            newlistComponent.add(description_component);
             }
             poi.setComponents(newlistComponent);
             pm.savePoi(poi);
             return model;
             }
    
}
