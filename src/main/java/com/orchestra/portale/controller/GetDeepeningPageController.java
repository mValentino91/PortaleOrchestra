/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.DeepeningPage;
import java.util.ArrayList;
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
public class GetDeepeningPageController {
    //Manager della persistenza
    @Autowired
    private PersistenceManager pm;

    //Richiesta per la visualizzazione di un singolo poi
    @RequestMapping(value = "/getDP", params = "id")
    public ModelAndView getDp(@RequestParam(value = "id") String id) {

        //Creo la view che sarÃ  mostrata all'utente
        ModelAndView model = new ModelAndView("infopoi");
        ModelAndView error = new ModelAndView("errorViewPoi");
        DeepeningPage dp = pm.findDeepeningPage(id);
        //aggiungo il poi al model
        model.addObject("poi", dp);

        //ciclo sulle componenti del poi
        for (AbstractPoiComponent comp : dp.getComponents()) {

            //associazione delle componenti al model tramite lo slug
            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            Class c;
            try {
                c = Class.forName(slug);
                model.addObject(cname, c.cast(comp));
            } catch (ClassNotFoundException ex) {
                
            }

        }

     model.addObject("vartype", dp.getClass().toString().substring(dp.getClass().toString().lastIndexOf(".")+1) );
    return model;
    
}
    @RequestMapping("/admin/viewallDP")
    public ModelAndView viewAll() {
         ArrayList<DeepeningPage> list = (ArrayList<DeepeningPage>) pm.findAllDeepeningPages();
         ModelAndView model =  new ModelAndView("viewdps");
         model.addObject("list", list);
        return model;
    }
}
