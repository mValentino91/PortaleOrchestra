/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.CartItinerarydetail;
import javax.servlet.http.HttpServletRequest;
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
        String msg=null;
        String[] elem = code.split("-");
        String idcard = elem[1]; //1
        String iditi = elem[0]; //110
        System.out.println(elem[0]);
        Integer active_idcard = pm.findActiveCardByIdUser(Integer.parseInt(elem[1]));
        if(active_idcard != null){
            int id_iti = pm.getIdItineraryByIdCard(active_idcard);
                
            Iterable<CartItinerarydetail>offer_row = pm.selectActiveOffer(id_iti, active_idcard);
            for (CartItinerarydetail off : offer_row ) {
                //  int idOffer = off.getIdOffer();
                
            }
        }
        else
            msg="Card non attiva, offerta non valida";
        /*
        model.addObject("idcard",idcard);
        model.addObject("iditi", iditi);
        return model;
        */
        return msg;
    }
}
