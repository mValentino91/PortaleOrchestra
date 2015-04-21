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


    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public ModelAndView favorites(@RequestParam String idUser) {
        
        Iterable <Favorite> favorites = pm.findFavoritesByIdUser(Integer.parseInt(idUser));
        ArrayList<String> idlist = new ArrayList<String>();        
        Iterable<CompletePOI> poilist = new ArrayList<CompletePOI>();
        Map<String,List<CompletePOI>> m = new HashMap<String,List<CompletePOI>>();
        ModelAndView model = new ModelAndView("favorites");
        
        MapPoiCat map_cat = new MapPoiCat(m);
        
        
        for (Favorite f : favorites ) {
            idlist.add(f.getIdPoi());
        }
        poilist = pm.getCompletePoisById(idlist);
        
        List<String> main_category = new ArrayList<String>();
        main_category.add("culture");
        main_category.add("accomodation");
        main_category.add("food");
        main_category.add("craft");
        main_category.add("mobility");
        main_category.add("event");
        main_category.add("cultural_association");
        main_category.add("expo");
        
       
        
        
        /*Occorre definire un array di categorie stock ed estrapolare le main category dai poi*/
        for(CompletePOI cp : poilist){
            List<String>poi_category = cp.getCategories();
            String cat_map = MapPoiCat.checkCategory(main_category,poi_category);
            
            System.out.println("entrato");
            map_cat.insertPoi(cat_map, cp);
        }
        
        
        
   
        
        model.addObject("map_cat", map_cat);
        
        return model;
    }            
    
    @RequestMapping(value = "/ifFavorite", method = RequestMethod.GET)
    public @ResponseBody
    String ifFavorite(@RequestParam String id_user, @RequestParam String id_poi) {
        
        Integer rating = pm.ifFavorite(Integer.parseInt(id_user), id_poi);
        
        return rating.toString();
    }    
    
    @RequestMapping(value = "/deleteFavorite", method = RequestMethod.GET)
    public @ResponseBody
    String deleteFavorite(@RequestParam String id_user, @RequestParam String id_poi) {
        
        pm.deleteFavorite(Integer.parseInt(id_user), id_poi);
        
        return "ok";
    }      
    
}