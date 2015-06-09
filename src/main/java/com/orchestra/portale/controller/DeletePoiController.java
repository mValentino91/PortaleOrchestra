/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_It;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_En;
import com.orchestra.portale.persistence.mongo.repositories.PoiMongoRepository_It;
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
@RequestMapping(value = "/admin")
public class DeletePoiController {
    @Autowired
    PersistenceManager pm ;
    @RequestMapping(value = "/deletepoi")
    public ModelAndView deletePoi() {
        ModelAndView model = new ModelAndView("deleteform");
        return model;
    }

    @RequestMapping(value = "/deleteevent")
    public ModelAndView deleteEvt() {
        ModelAndView model = new ModelAndView("deleteeventform");
        return model;
    }

    @RequestMapping(value = "/deletepoi", params = "name")
    public ModelAndView deletePoi(@RequestParam(value = "name") String name) {
        ModelAndView model = new ModelAndView("deleted");
        try {
            CompletePOI_It poi = (CompletePOI_It) pm.findOneCompletePoiByName(name);
            pm.deletePoi(poi);
            return model;
        } catch (RuntimeException e) {
            ModelAndView model2 = new ModelAndView("errorViewPoi");
            model2.addObject("err", "Errore impossibile trovare il poi: " + name);
            return model2;
        }
    }

    @RequestMapping(value = "/deletepoi", params = "id")
    public ModelAndView deletePoiById(@RequestParam(value = "id") String id) {
        try {
            ModelAndView model = new ModelAndView("deleted");
            CompletePOI_It poi = (CompletePOI_It) pm.getCompletePoiById(id);

            pm.deletePoi(poi);
            return model;
        } catch (RuntimeException e) {
            ModelAndView model2 = new ModelAndView("errorViewPoi");
            model2.addObject("err", "Errore impossibile trovare il poi con id: " + id);
            return model2;
        }
    }

}
