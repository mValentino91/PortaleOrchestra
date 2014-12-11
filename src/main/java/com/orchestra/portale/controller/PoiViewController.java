/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import org.springframework.beans.factory.annotation.Autowired;
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
    PersistenceManager pm;

    //Richiesta per la visualizzazione di un singolo poi
    @RequestMapping(value = "/getPoi", params = "id")
    public ModelAndView getPoi(@RequestParam(value = "id") String id) {

        //Creo la view che sarà mostrata all'utente
        ModelAndView model = new ModelAndView("infopoi");
        ModelAndView error = new ModelAndView("errorViewPoi");
        CompletePOI poi = pm.getCompletePoiById(id);
        //aggiungo il poi al model
        model.addObject("poi", poi);
        try {
            //ciclo sulle componenti del poi
            for (AbstractPoiComponent comp : poi.getComponents()) {

                //associazione delle componenti al model tramite lo slug
                String slug = comp.slug();
                int index = slug.lastIndexOf(".");
                String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();

                Class c = Class.forName(slug);
                model.addObject(cname, c.cast(comp));

            }
        } catch (Exception e) {
            return error;
        }
        return model;
    }
}
