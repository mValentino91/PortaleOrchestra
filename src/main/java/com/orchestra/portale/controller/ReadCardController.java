/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.CartItinerarydetail;
import com.orchestra.portale.persistence.sql.entities.DealerOffer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReadCardController {
    
    @Autowired
    private PersistenceManager pm;
    
    @RequestMapping(value = "/readCard")
    public ModelAndView readCard(){
        ModelAndView model = new ModelAndView("readCard");
        return model;
    }
    
    @RequestMapping(value = "/accessOfferCard", method = RequestMethod.GET)
    public @ResponseBody String ValidateOffer(@RequestParam String code){
        //ModelAndView model = new ModelAndView("InfoCardTransition");
        //String code = request.getParameter(idItinerary);
        String msg="Nessuna offerta disponibile";
        String[] elem = code.split("-");
        if(elem == null){
            msg="errore!!";
            return msg;
        }
        String idcard = elem[1]; //1
        String iditi = elem[0]; //110
        System.out.println(elem[0]);
        Integer active_idcard = pm.findActiveCardByIdUser(Integer.parseInt(elem[1]));
        if(active_idcard != null){
            //dato che c'è un itinerario per ogni card attiva 
            int id_iti = pm.getIdItineraryByIdCard(active_idcard);
            
            /*
            per il momento tutti gli esercenti hanno codice esercente 0 quindi recupero tutte le offerte salvate
            e verifico quale offerta matcha con quella accettata dall'utente
            */
            List<DealerOffer>off_dealer = pm.findOfferByIdDealer(0);
            Iterable<CartItinerarydetail>offer_row = pm.selectActiveOffer(id_iti, active_idcard);
            for (CartItinerarydetail off : offer_row ) {
                for(int i=0;i<off_dealer.size();i++){
                    if(off_dealer.get(i).getIdOffer() == off.getIdOffer()){
                        pm.invalidateOffer(id_iti, off.getIdOffer());
                        msg="offerta validata correttamente";
                        
                    }
                }
            }
            
        }
        else
            msg="Card non attiva, offerta non valida";
        
        return msg;
    }
}
