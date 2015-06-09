/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_En;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_It;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
public class SetVisibilityController {

        @Autowired
    PersistenceManager pm ;

    @RequestMapping("/setvis")
    public ModelAndView setvis() {
        pm.setLang(LocaleContextHolder.getLocale().toString());
        ModelAndView model = new ModelAndView("insertpoi");
        ArrayList<CompletePOI_It> poilist =(ArrayList<CompletePOI_It>) pm.getAllCompletePoi();
        for (CompletePOI_It p : poilist) {
            p.setVisibility("1");
            pm.savePoi(p);
        }
        String[] bho = new String[5];
        bho[1] = "mobility";
        Iterable<CompletePOI_It> poilistmob = (Iterable<CompletePOI_It>) pm.getCompletePoiByCategories(bho);
        for (CompletePOI_It p : poilistmob) {
            p.setVisibility("0");
           pm.savePoi(p);
        }
        return model;

    }
    
    @RequestMapping("/setenvis")
    public ModelAndView setenvis() {
        pm.setLang(LocaleContextHolder.getLocale().toString());
        ModelAndView model = new ModelAndView("insertpoi");
        ArrayList<CompletePOI_En> poilist =(ArrayList<CompletePOI_En>) pm.getAllCompletePoi();
        for (CompletePOI_En p : poilist) {
            p.setVisibility("1");
            pm.saveEnPoi(p);
        }
        String[] bho = new String[5];
        bho[1] = "mobility";
        Iterable<CompletePOI_En> poilistmob = (Iterable<CompletePOI_En>) pm.getCompletePoiByCategories(bho);
        for (CompletePOI_En p : poilistmob) {
            p.setVisibility("0");
           pm.saveEnPoi(p);
        }
        return model;

    }
}
