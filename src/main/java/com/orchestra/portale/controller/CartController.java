/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.carrello.Cart;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
@Scope("session")
public class CartController {

    private Cart poiCart = new Cart();
    @Autowired
    private PersistenceManager pm;

    //Richiesta per la visualizzazione di un singolo poi
    @RequestMapping(value = "/CartView")
    public ModelAndView getCartView() {
        
        //Creo la view che sarà  mostrata all'utente
        ModelAndView model = new ModelAndView("cartView");
        ArrayList<CompletePOI> cart = poiCart.getPoiList();
        //aggiungo i poi al model
        model.addObject("cart", cart);
        
        return model;
    }

    @RequestMapping(value = "/Cart/GetCart")
    public @ResponseBody
    String getCart() {
        StringBuilder jsonPois = new StringBuilder();

        Iterator<CompletePOI> poiList = poiCart.getPoiList().iterator();

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

    @RequestMapping(value = "/Cart/AddPoi")
    public @ResponseBody
    String addPoi(@RequestParam String id) {

        StringBuilder jsonPois = new StringBuilder();

        try {
            CompletePOI poi = pm.getCompletePoiById(id);
            poiCart.addPoi(poi);
            jsonPois.append("{\"code\":\"0\"}");

        } catch (RuntimeException e) {
            jsonPois.append("{\"code\":\"1\", \"error\":\"").append(e.getMessage()).append("\"}");
        }

        return jsonPois.toString();
    }

}
