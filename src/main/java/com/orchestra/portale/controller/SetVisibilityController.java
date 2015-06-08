/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
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

    

    @RequestMapping("/setvis")
    public ModelAndView setvis() {
        PersistenceManager pm = new ConcretePersistenceManager( LocaleContextHolder.getLocale().getDisplayLanguage() );
        ModelAndView model = new ModelAndView("insertpoi");
        ArrayList<CompletePOI> poilist = (ArrayList<CompletePOI>) pm.getAllCompletePoi();
        for (CompletePOI p : poilist) {
            p.setVisibility("1");
            pm.savePoi(p);
        }
        String[] bho = new String[5];
        bho[1] = "mobility";
        ArrayList<CompletePOI> poilistmob = (ArrayList<CompletePOI>) pm.getCompletePoiByCategories(bho);
        for (CompletePOI p : poilistmob) {
            p.setVisibility("0");
            pm.savePoi(p);
        }
        return model;

    }
}
