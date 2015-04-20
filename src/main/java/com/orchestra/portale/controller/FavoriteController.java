/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.sql.entities.Favorite;
import com.orchestra.portale.persistence.sql.entities.User;
import com.orchestra.portale.utils.MapPoiCat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author antonio
 */
@Controller
public class FavoriteController {
    //Manager della persistenza
    @Autowired
    PersistenceManager pm;

    @RequestMapping(value = "/saveFavorite", method = RequestMethod.GET)
    public @ResponseBody
    String saveFavorite(@RequestParam String id_user, @RequestParam String id_poi) {
        Favorite favorite = new Favorite();
        favorite.setIdUser(Integer.parseInt(id_user));
        favorite.setIdPoi(id_poi);
        pm.saveFavorite(favorite);
        
        return "ok";
    }    
    

    @RequestMapping(value = "/saveFavoriteRating", method = RequestMethod.GET)
    public @ResponseBody
    String saveFavoriteRating(@RequestParam String id_user, @RequestParam String id_poi, @RequestParam String rating) {
    
        pm.updateFavoriteRating(Integer.parseInt(rating), Integer.parseInt(id_user), id_poi);
        
        return "ok";
    }        

    /*
    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public ModelAndView favorites(@RequestParam String id_user) {
        
        Iterable <Favorite> favorites = pm.findFavoritesByIdUser(Integer.parseInt(id_user));
                
        ModelAndView model = new ModelAndView("favorites");
        model.addObject("favorites", favorites);
        
        return model;
    }     
    */
    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public ModelAndView favorites(@RequestParam String idUser) {
        
        Iterable <Favorite> favorites = pm.findFavoritesByIdUser(Integer.parseInt(idUser));
        ArrayList<String> idlist = new ArrayList<String>();        
        Iterable<CompletePOI> poilist = new ArrayList<CompletePOI>();
        ModelAndView model = new ModelAndView("favorites");
        
        MapPoiCat map_cat = new MapPoiCat();
        
        
        for (Favorite f : favorites ) {
            idlist.add(f.getIdPoi());
        }
        poilist = pm.getCompletePoisById(idlist);
        
        map_cat.setPoi(poilist);
        map_cat.setMain_category("coat");
        
        
   
        
        model.addObject("map_cat", map_cat);
        
        return model;
    }        
    
}