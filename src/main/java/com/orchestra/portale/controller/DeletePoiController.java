/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.repositories.PoiMongoRepository;
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
@RequestMapping(value= "/admin")
public class DeletePoiController {
    @Autowired
    PersistenceManager pm;

    @RequestMapping(value= "/deletepoi")
    public ModelAndView deletePoi() {
        ModelAndView model = new ModelAndView("deleteform");
        return model;
    }
    
    @RequestMapping(value= "/deletepoi", params="name")
    public ModelAndView deletePoi(@RequestParam(value = "name") String name) {
        ModelAndView model = new ModelAndView("deleted");
        CompletePOI poi= pm.findOneCompletePoiByName(name);
        pm.deletePoi(poi);
        return model;
    }
}