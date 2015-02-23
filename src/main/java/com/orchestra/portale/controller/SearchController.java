/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.google.gson.Gson;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
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
    private PersistenceManager pm;

    @RequestMapping(value = "/JSON/Find")
    public @ResponseBody
    String find(@RequestParam String name, @RequestParam String address, @RequestParam String category) {

        Gson pois=new Gson();
        return pois.toJson(pm.findCompletePoi(name, address, category));
    }

    @RequestMapping(value = "/JSON/Near")
    public @ResponseBody
    String near(@RequestParam String id, @RequestParam double radius) {
        
        Gson pois=new Gson();
        return pois.toJson(pm.findNearCompletePoi(id, radius));
        
    }
}
