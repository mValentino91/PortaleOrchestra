/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping("/security")
    public ModelAndView security( /*@RequestParam(value = "lat") String lat,
             @RequestParam(value = "lon") String lon,
             @RequestParam(value = "lat1") String lat1,
             @RequestParam(value = "lon1") String lon1*/) {
        //Creo la view che sarà mostrata all'utente
        ModelAndView model = new ModelAndView("security");
        return model;
    }
    
    @RequestMapping(params = "category=all")
    public ModelAndView getAllPoi( /*@RequestParam(value = "lat") String lat,
             @RequestParam(value = "lon") String lon,
             @RequestParam(value = "lat1") String lat1,
             @RequestParam(value = "lon1") String lon1*/) {
        //Creo la view che sarà mostrata all'utente
        ModelAndView model = new ModelAndView("mapView");
        Iterable<CompletePOI> poiList = pm.getAllCompletePoi();
        //aggiungo la lista al model
        model.addObject("poiList", poiList);

        return model;
    }

     @RequestMapping()
    public ModelAndView getPoiForCategory(
            @RequestParam(value = "category") String category
    /*@RequestParam(value = "lat") String lat,
     @RequestParam(value = "lon") String lon,
     @RequestParam(value = "lat1") String lat1,
     @RequestParam(value = "lon1") String lon1*/) {
        //Creo la view che sarà mostrata all'utente
        ModelAndView model = new ModelAndView("mapView");
        Iterable<CompletePOI> poiList = pm.getCompletePoiByCategory(category);
        //aggiungo la lista al model
        model.addObject("poiList", poiList);
        model.addObject("category", category);

        return model;
    }

    @RequestMapping(value = "/JSON", params = "category=all")
    public @ResponseBody
    String getJson(@RequestParam String category) {

        StringBuilder jsonPois = new StringBuilder();

        Iterator<CompletePOI> poiList = pm.getAllCompletePoi().iterator();

        jsonPois.append("[");

        while (poiList.hasNext()) {

            CompletePOI poi = poiList.next();

            jsonPois.append("{ \"id\":\"")
                    .append(poi.getId())
                    .append("\",\"name\":\"")
                    .append(poi.getName())
                    .append("\",\"address\":\"")
                    .append(poi.getAddress())
                    .append("\",\"location\":[")
                    .append(poi.getLocation()[0])
                    .append(",")
                    .append(poi.getLocation()[1])
                    .append("]")
                    .append(",\"shortDescription\":\"")
                    .append(poi.getShortDescription())
                    .append("\"}");

            if (poiList.hasNext()) {

                jsonPois.append(",");
            }

        }

        jsonPois.append("]");

        return jsonPois.toString();

    }

    @RequestMapping(value = "/JSON")
    public @ResponseBody
    String getJsonForCategory(@RequestParam String category) {

        StringBuilder jsonPois = new StringBuilder();

        Iterator<CompletePOI> poiList = pm.getCompletePoiByCategory(category).iterator();

        jsonPois.append("[");

        while (poiList.hasNext()) {

            CompletePOI poi = poiList.next();

            jsonPois.append("{ \"id\":\"")
                    .append(poi.getId())
                    .append("\",\"name\":\"")
                    .append(poi.getName())
                    .append("\",\"address\":\"")
                    .append(poi.getAddress())
                    .append("\",\"location\":[")
                    .append(poi.getLocation()[0])
                    .append(",")
                    .append(poi.getLocation()[1])
                    .append("]")
                    .append(",\"shortDescription\":\"")
                    .append(poi.getShortDescription())
                    .append("\"}");

            if (poiList.hasNext()) {

                jsonPois.append(",");
            }

        }

        jsonPois.append("]");

        return jsonPois.toString();

    }

}
