/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.profiler.FbProfiler;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
@Scope("request")
@Secured("ROLE_USER")
public class RecommenderController {

    @Autowired
    private FbProfiler fbProfiler;

    @Autowired
    private PersistenceManager pm;

    @RequestMapping("/getRecommandedPoi")
    public ModelAndView getRecommendedPoi(HttpServletRequest request) {

        ModelAndView model = new ModelAndView("FavoriteTopListComponent");
        List<String> listid;
        /*FACEBOOK USER*/
        if (request.isUserInRole("ROLE_FB")) {
            listid = fbProfiler.getPoiStereotype();
            model.addObject("listRTitle", "recFb");
        } 
        /*INTERNAL USER*/ 
        else {
            listid = new ArrayList<String>();
            for (Object[] s : pm.getMostFavorites()) {
                listid.add(s[1].toString());
            }
            model.addObject("listRTitle", "recU");   
        }

         ArrayList<CompletePOI> listpoi = (ArrayList<CompletePOI>) pm.getCompletePoisById(listid);
         model.addObject("listR",listpoi);
         
        return model;

    }
}
