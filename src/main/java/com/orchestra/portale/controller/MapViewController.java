/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Marco Valentino
 */
@Controller
@RequestMapping("/Map")
public class MapViewController {

    //Manager della persistenza
    @Autowired
    PersistenceManager pm;

    @RequestMapping(params="category=all")
    public ModelAndView getAllPoi(
            @RequestParam(value = "lat") String lat,
            @RequestParam(value = "lon") String lon,
            @RequestParam(value = "lat1") String lat1,
            @RequestParam(value = "lon1") String lon1) {
        //Creo la view che sarà mostrata all'utente
        ModelAndView model = new ModelAndView("mapView");
        Iterable<CompletePOI> poiList = pm.getAllCompletePoi();
        //aggiungo la lista al model
        model.addObject("poiList", poiList);

        return model;
    }
    
    @RequestMapping()
    public ModelAndView getPoiForCategory(
            @RequestParam(value = "category") String category,
            @RequestParam(value = "lat") String lat,
            @RequestParam(value = "lon") String lon,
            @RequestParam(value = "lat1") String lat1,
            @RequestParam(value = "lon1") String lon1) {
        //Creo la view che sarà mostrata all'utente
        ModelAndView model = new ModelAndView("mapView");
        Iterable<CompletePOI> poiList = pm.getAllCompletePoi();
        //aggiungo la lista al model
        model.addObject("poiList", poiList);
        model.addObject("category", category);

        return model;
    }

}
