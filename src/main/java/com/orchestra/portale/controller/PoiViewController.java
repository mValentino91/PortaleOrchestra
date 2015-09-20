/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.componentManagers.LinkedEntities;
import com.orchestra.portale.componentManagers.LinkedEntitiesManager;
import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_It;
import com.orchestra.portale.persistence.mongo.documents.LinkedPoi;
import com.orchestra.portale.persistence.mongo.documents.LinkedPoiComponent;
import com.orchestra.portale.persistence.sql.entities.Itinerary;
import com.orchestra.portale.persistence.sql.entities.User;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * Classe contenente i controller per la visualizzazione dei poi
 */
@Controller
public class PoiViewController {

    //Manager della persistenza
        @Autowired
    PersistenceManager pm ;

    //Richiesta per la visualizzazione di un singolo poi
    @RequestMapping(value = "/getPoi", params = "id")
    public ModelAndView getPoi(@RequestParam(value = "id") String id, HttpServletRequest request) throws FileNotFoundException {
        //Creo la view che sarÃ  mostrata all'utente
        pm.setLang(LocaleContextHolder.getLocale().toString());
        ModelAndView model = new ModelAndView("infopoi");
        ModelAndView error = new ModelAndView("errorViewPoi");
        CompletePOI poi =  pm.getCompletePoiById(id);
        //aggiungo il poi al model
        model.addObject("poi", poi);

        //ciclo sulle componenti del poi
        for (AbstractPoiComponent comp : poi.getComponents()) {

            //associazione delle componenti al model tramite lo slug
            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            if (cname.equals("workingtime")) {
                GregorianCalendar gc = new GregorianCalendar();
                
                String oggi = "";
                
                int giorno =gc.get(Calendar.DAY_OF_WEEK);
                switch (giorno) {
                    case 2:
                        oggi = "Lunedì";
                        break;
                    case 3:
                        oggi = "Martedì";
                        break;
                    case 4:
                        oggi = "Mercoledì";
                        break;
                    case 5:
                        oggi = "Giovedì";
                        break;
                    case 6:
                        oggi = "Venerdì";
                        break;
                    case 7:
                        oggi = "Sabato";
                        break;
                    case 1:
                        oggi = "Domenica";
                        break;
                }
                String data=gc.get(Calendar.DAY_OF_MONTH)+"/"+(gc.get(Calendar.MONTH)+1)+"/"+gc.get(Calendar.YEAR);
                model.addObject("oggi", oggi);
                model.addObject("data", data);
                
            }
            if(cname.equals("linkedpoi")) {
                HttpSession session = request.getSession();
                    ServletContext sc = session.getServletContext();
                ArrayList<LinkedEntities> linkent = LinkedEntitiesManager.linkedmanager((LinkedPoiComponent) comp, sc, pm);
                model.addObject("linkent", linkent);
            }
            Class c;
            try {
                c = Class.forName(slug);
                model.addObject(cname, c.cast(comp));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PoiViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        
        ArrayList<CompletePOI> poivicini = new ArrayList<CompletePOI>();
      
        for (GeoResult<? extends CompletePOI> p : pm.findNearCompletePoi(id, 0.3)) {
            poivicini.add(p.getContent());
            
        }
        model.addObject("poivicini", poivicini);
        
        
        //check if POI is favorite
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.isAuthenticated()){
             
            if(auth.getName()!=null && !auth.getName().equals("anonymousUser") && auth.getName().trim()!=""){
                
               User user= pm.findUserByUsername(auth.getName());
               String id_user = user.getId().toString();

               Integer rating = pm.ifFavorite(Integer.parseInt(id_user), poi.getId());   
               model.addObject("fav_rating", rating);     
               
               Iterable<Itinerary>userItinerary = pm.findActiveItinerariesByIdUser(Integer.parseInt(id_user));
               
               if(userItinerary!=null){
                   model.addObject("userItinerary", userItinerary);
               }
                   
            }
            
        }
        
        return model;
    }
}
