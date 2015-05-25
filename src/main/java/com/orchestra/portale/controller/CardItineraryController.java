/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.Card;
import com.orchestra.portale.persistence.sql.entities.CardItinerary;
import com.orchestra.portale.persistence.sql.entities.User;
import javax.persistence.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author andrea
 */
@Controller
@Secured("ROLE_USER")
public class CardItineraryController {

    @Autowired
    private PersistenceManager pm;

    @RequestMapping(value = "/saveInCard", method = RequestMethod.GET)
    public @ResponseBody
    String saveInCard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        Integer idcard = pm.findActiveCardByIdUser(Integer.parseInt(id_user));
        if (idcard != null) {
            CardItinerary card_itinerary = new CardItinerary();
            card_itinerary.setIdCard(idcard);
            pm.saveCardItinerary(card_itinerary);
            int id_iti = card_itinerary.getIdItinerary();
            pm.updateItemItinerary(id_iti, Integer.parseInt(id_user));
            
            return "ok"+id_iti;
        } else {
            return "nessuna card attiva";
        }

    }
}
