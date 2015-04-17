/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.Favorite;
import com.orchestra.portale.persistence.sql.entities.User;
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


    @RequestMapping(value = "/getFavorites", method = RequestMethod.GET)
    public @ResponseBody
    String getFavorites(@RequestParam String id_user) {
        
        pm.findFavoritesByIdUser(Integer.parseInt(id_user));
        return "ok";
    }        
    
}
