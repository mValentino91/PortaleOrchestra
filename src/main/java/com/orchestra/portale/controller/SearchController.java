/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.DeepeningPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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
    String find(@RequestParam String name, @RequestParam String address, @RequestParam String category) {
        Gson pois = new Gson();
        pm.setLang(LocaleContextHolder.getLocale().toString());
        return pois.toJson(pm.findCompletePoi(name, address, category));
    }

    @RequestMapping(value = "/Autocomplete")
    public @ResponseBody
    String autocomplete(@RequestParam String query) {
        pm.setLang(LocaleContextHolder.getLocale().toString());
        Gson pois = new Gson();
        JsonObject j = new JsonObject();
        JsonArray array = new JsonArray();
        query = query.replaceAll("'", "&#039;");
        query = query.replaceAll("\"", "&quot;");
        Iterable<? extends CompletePOI> results = pm.findCompletePoi(query, "", "");
        for (CompletePOI c : results) {
            JsonObject json = new JsonObject();
            String name = c.getName();
            name = name.replaceAll("&#039;", "'");
            name = name.replaceAll("&quot;", "\"");
            
            json.addProperty("value", name);
            json.addProperty("data", c.getId());
            array.add(json);
        }
        j.add("suggestions", array);
        return pois.toJson(j);
    }

    @RequestMapping(value = "/Autocompleteevent")
    public @ResponseBody
    String autocompleteevent(@RequestParam String query) {
        pm.setLang(LocaleContextHolder.getLocale().toString());
        Gson pois = new Gson();
        JsonObject j = new JsonObject();
        JsonArray array = new JsonArray();
        String[] categories = new String[10];
        query = query.replaceAll("'", "&#039;");
        query = query.replaceAll("\"", "&quot;");
        categories[1] = "event";
        categories[2] = "expo";
        Iterable<? extends CompletePOI> results = pm.findCompletePoiByNameAndCategories(query, categories);
        for (CompletePOI c : results) {
            JsonObject json = new JsonObject();
            String name = c.getName();
            name = name.replaceAll("&#039;", "'");
            name = name.replaceAll("&quot;", "\"");
            
            json.addProperty("value", name);
            json.addProperty("data", c.getId());
            array.add(json);
        }
        j.add("suggestions", array);
        return pois.toJson(j);
    }

    @RequestMapping(value = "/Autocompletedpage")
    public @ResponseBody
    String autocompletedpage(@RequestParam String query) {
        pm.setLang(LocaleContextHolder.getLocale().toString());
        Gson pois = new Gson();
        JsonObject j = new JsonObject();
         query = query.replaceAll("'", "&#039;");
        query = query.replaceAll("\"", "&quot;");
        JsonArray array = new JsonArray();
        Iterable<DeepeningPage> results = pm.findDeepeningPagesByName(query);
        for (DeepeningPage c : results) {
            JsonObject json = new JsonObject();
            String name = c.getName();
            name = name.replaceAll("&#039;", "'");
            name = name.replaceAll("&quot;", "\"");
            
            json.addProperty("value", name);
            json.addProperty("data", c.getId());
            array.add(json);
        }
        j.add("suggestions", array);
        return pois.toJson(j);
    }

    @RequestMapping(value = "/JSON/Near")
    public @ResponseBody
    String near(@RequestParam String id, @RequestParam double radius) {
        Gson pois = new Gson();
        pm.setLang(LocaleContextHolder.getLocale().toString());
        return pois.toJson(pm.findNearCompletePoi(id, radius));

    }
}
