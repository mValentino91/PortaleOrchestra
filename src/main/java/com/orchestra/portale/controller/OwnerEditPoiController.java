/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.components.LinkedEntities;
import com.orchestra.portale.components.LinkedEntitiesManager;
import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_It;
import com.orchestra.portale.persistence.mongo.documents.LinkedPoiComponent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
public class OwnerEditPoiController {

    //Manager della persistenza
    @Autowired
    PersistenceManager pm;

    @RequestMapping(value = "/UpdatePoi", params = "id")
    public ModelAndView getPoi(@RequestParam(value = "id") String id, HttpServletRequest request) throws FileNotFoundException {
        //Creo la view che sarÃ  mostrata all'utente
        ModelAndView model = new ModelAndView("updatepoi");
        ModelAndView error = new ModelAndView("errorViewPoi");
        pm.setLang("it");
        CompletePOI_It poi = (CompletePOI_It) pm.getCompletePoiById(id);
        //aggiungo il poi al model
        model.addObject("complist", pm.getCompByCat(poi.getCategories().get(0)));
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

                int giorno = gc.get(Calendar.DAY_OF_WEEK);
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
                String data = gc.get(Calendar.DAY_OF_MONTH) + "/" + (gc.get(Calendar.MONTH) + 1) + "/" + gc.get(Calendar.YEAR);
                model.addObject("oggi", oggi);
                model.addObject("data", data);

            }
            if (cname.equals("linkedpoi")) {
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
        return model;
    }

}
