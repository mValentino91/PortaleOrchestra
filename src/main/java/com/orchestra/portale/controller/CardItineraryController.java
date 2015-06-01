/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.sql.entities.Card;
import com.orchestra.portale.persistence.sql.entities.CardItinerary;
import com.orchestra.portale.persistence.sql.entities.CartItinerarydetail;
import com.orchestra.portale.persistence.sql.entities.DealerOffer;
import com.orchestra.portale.persistence.sql.entities.User;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView saveInCard() {
        ModelAndView model = new ModelAndView("cardDetail");
        ModelAndView error = new ModelAndView("errorCardDetail");
        String error_info = "Nessuna card attiva";
        
        List<Integer>offerte_id = new ArrayList<Integer>();
        ArrayList<String>poi_id = new ArrayList<String>();
        List<DealerOffer>offerte = new ArrayList<DealerOffer>();
        Iterable<CompletePOI>elenco_poi = new ArrayList<CompletePOI>();
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        Integer idcard = pm.findActiveCardByIdUser(Integer.parseInt(id_user));
        if (idcard != null) {
            Integer status = pm.findStatusCardByIdUser(Integer.parseInt(id_user));
            
            if(status == 0){
                //card creata ma nn attiva
                CardItinerary card_itinerary = new CardItinerary();
                card_itinerary.setIdCard(idcard);
                pm.saveCardItinerary(card_itinerary);            
                int id_iti = pm.getIdItineraryByIdCard(idcard);
                pm.updateItemItinerary(id_iti, Integer.parseInt(id_user));
                pm.activeCard(Integer.parseInt(id_user));
            }
            
            //puo essere definito un controller separato per acccedere sempre al riepilogo
           
            int id_iti = pm.getIdItineraryByIdCard(idcard);
                
            Iterable<CartItinerarydetail>offer_row = pm.selectActiveOffer(id_iti, Integer.parseInt(id_user));
            
            //recupero gli id delle offerte
            for (CartItinerarydetail off : offer_row ) {
                offerte.add(pm.findOfferByIdOffer(off.getIdOffer()));
                poi_id.add(off.getIdPoi());
            }
            
            //ho ottenuto tutti i poi 
            elenco_poi = pm.getCompletePoisById(poi_id);
            
            //ciclo ottenendo una lista di offerte
            model.addObject("idcard",idcard);
            model.addObject("id_iti",id_iti);
            model.addObject("user",user);
            model.addObject("elenco_poi",elenco_poi);
            model.addObject("offerte",offerte);
            
            return model;
        } else {
            //status card = 2 --> scaduta
            model.addObject("error",error_info);
            return model;
        }

    }
}
