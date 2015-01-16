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

/**
 *
 * @author Marco Valentino
 */
@Controller
@RequestMapping("/Search")
public class SearchController {

    //Manager della persistenza
    @Autowired
    PersistenceManager pm;
    
    @RequestMapping(value = "/JSON/Find")
    public @ResponseBody
    String find(@RequestParam String name,@RequestParam String address,@RequestParam String category) {

        StringBuilder jsonPois = new StringBuilder();

        Iterator<CompletePOI> poiList = pm.findCompletePoi(name, address, category).iterator();

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
    
    @RequestMapping(value = "/JSON/Near")
    public @ResponseBody
    String near(@RequestParam String id,@RequestParam double radius) {

        StringBuilder jsonPois = new StringBuilder();

        Iterator<CompletePOI> poiList = pm.findNearCompletePoi(id,radius).iterator();

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
