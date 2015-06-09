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
import org.springframework.stereotype.Controller;
import com.orchestra.portale.persistence.mongo.repositories.PoiMongoRepository_It;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */

@Controller
public class AllPoisIdController {
       @Autowired
    PersistenceManager pm ;
    
    @RequestMapping(value= "/poiId")
    public ModelAndView listid () {
   Iterable<? extends CompletePOI> poilist =  pm.getAllCompletePoi();
   ArrayList<String> idlist = new ArrayList<String>();
   int i=1;
   for (CompletePOI poi : poilist ) {
        idlist.add(i+") "+poi.getId());
        i++;
   }
   ModelAndView model = new ModelAndView("poilist");
   model.addObject("lista", idlist);
   return model;
    }

}
