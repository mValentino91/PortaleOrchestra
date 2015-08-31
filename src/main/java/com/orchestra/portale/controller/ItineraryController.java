/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.managers.ItineraryManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.PricesComponent;
import com.orchestra.portale.persistence.mongo.documents.TicketPrice;
import com.orchestra.portale.persistence.sql.entities.DealerOffer;
import com.orchestra.portale.persistence.sql.entities.Itinerary;
import com.orchestra.portale.persistence.sql.entities.User;
import com.orchestra.portale.persistence.sql.entities.UserOfferChoice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
 * @author andzaccaro
 */
@Controller
@Secured("ROLE_USER")
public class ItineraryController {

    @Autowired
    PersistenceManager pm;

    @RequestMapping(value = "/myItinerary", method = RequestMethod.GET)
    public ModelAndView viewMyItinerary() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        ModelAndView model = new ModelAndView("myItinerary");

        Iterable<Itinerary> i = ItineraryManager.retreiveItinerary(pm, Integer.parseInt(id_user));
        model.addObject("itinerary", i);

        return model;

    }

    @RequestMapping(value = "/newItinerary", method = RequestMethod.GET)
    public @ResponseBody
    String createItinerary(@RequestParam String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        ItineraryManager.createItinerary(pm, id_user, name);
        return "ok";
    }

    @RequestMapping(value = "/addPoiItinerary", method = RequestMethod.GET)
    public @ResponseBody
    String addPoiNewItinerary() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        ItineraryManager.addPoi(pm, 22, "5496cfecdf6ef624f2d63de7");
        return "ok";
    }

    @RequestMapping(value = "/addOfferItinerary", method = RequestMethod.GET)
    public @ResponseBody
    void addOfferItinerary(@RequestParam int qta, @RequestParam Integer id_offer, @RequestParam float sum, @RequestParam String type, @RequestParam Integer idItinerary, @RequestParam String name, @RequestParam String desc) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        
        if(idItinerary != null){
            ItineraryManager.addOffer(pm, idItinerary, id_offer, qta, sum, type, name, desc);
            //se l'offerta ke inserisco e gia presente aggiorno solo qta e prezzo
        }
        
    }
    
    
    @RequestMapping(value = "/removeOfferItinerary", method = RequestMethod.GET)
    public @ResponseBody
    void removeOfferItinerary(@RequestParam Integer id_offer, @RequestParam Integer idItinerary, @RequestParam String name, @RequestParam String type) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        
        if(idItinerary != null){
            ItineraryManager.removeOffer(pm, id_offer,idItinerary, name,type);
        }
        
    }
    
    /*
    @RequestMapping(value = "/addOfferItinerary", method = RequestMethod.GET)
    public @ResponseBody
    void addOfferItinerary(@RequestParam int id_offer, @RequestParam int id_poi, @RequestParam int qta, @RequestParam float sum, @RequestParam String type, @RequestParam String idItinerary) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();

        
        System.out.println(id_poi);
        System.out.println(id_offer);
        System.out.println(qta);
        System.out.println(sum);
        System.out.println(type);
        System.out.println(idItinerary);
        
        
        
        
        
        //aggiungere parametri input funzione string
        //ItineraryManager.addOffer(pm, 22, "aaa", 16, 5, 100);
        //aggiornare il valore di id offer choice all'itinerary detail

    }

    @RequestMapping(value = "/removeOfferItinerary", method = RequestMethod.GET)
    public @ResponseBody
    String removeOfferItinerary() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());

        //recupero itinerario selezionato dall'utente ottenendo la key it detail 
        Integer idDetail = pm.findIdDetailByidOffer(16);
        Integer idItinerary = pm.findItineraryByIdItineraryDetail(idDetail);
        Long idUser = pm.findUserByIdItinerary(idItinerary);

        if (user.getId().longValue() == idUser.longValue()) {
            //utente leggittimato a fare l'operazione
            //signature removeOffer(persistence, id itinerary detail)
            ItineraryManager.removeOffer(pm, 16, idDetail);
            return "ok";
        }
        return "no";
    }
    */

    @RequestMapping(value = "/myItineraryDetail", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView viewItineraryDetail(@RequestParam int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        ModelAndView model = new ModelAndView("itineraryDetail");

        Iterable<String> pois_id = ItineraryManager.findPoiByItinerary(pm, id);
        Iterable<? extends CompletePOI> pois = pm.getCompletePoisById(pois_id);
        //x ogni id poi devo visualizzare le off stock e le off card

        model.addObject("id",id);
        model.addObject("pois", pois);

        return model;
    }

    @RequestMapping(value = "/viewOfferPoi", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView viewItineraryDetail(@RequestParam String idPoi, @RequestParam int idItinerary) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        ModelAndView model = new ModelAndView("viewOffers");

        
        
        List<Map<String,String>> price_comp = new ArrayList<Map<String, String>>();
        
        
        
        CompletePOI poi = pm.getCompletePoiById(idPoi);
        String poi_name = poi.getName();

        //off card
        List<DealerOffer> offers = pm.findOfferByIdPoi(idPoi);
        
        Integer iddetail = pm.findItDetail(idItinerary);
        
        
        
        List<Integer>idOffer_choice = pm.findChoiceCardByUser(iddetail);
        List<String>typeStock_choice = pm.findChoiceStockByUser(iddetail); 
        
        model.addObject("offers", offers);
        model.addObject("poi_name", poi_name);
        model.addObject("idPoi",idPoi);
        model.addObject("idItinerary",idItinerary);
        model.addObject("idOffer_choice",idOffer_choice);
        model.addObject("typeStock_choice",typeStock_choice);

        //off stock
        

        PricesComponent tp = null;
        for (AbstractPoiComponent comp : poi.getComponents()) {
            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            Class c;
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            if (cname.equals("prices")) {
                try {
                    c = Class.forName(slug);
                    tp = (PricesComponent) comp;

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (tp != null) {
            for (TicketPrice p : tp.getPrices()) {
                    Map<String, String> mo = new HashMap<String, String>();
                    mo.put("type", p.getType());
                    mo.put("desc", p.getType_description());
                    Double pr = p.getPrice();
                    mo.put("price", pr.toString());

                    price_comp.add(mo);
                    
                    
                }
            
            }
            model.addObject("price_comp",price_comp);

                            

        return model;
    }
    
    
    
    @RequestMapping(value = "/removeItinerary", method = RequestMethod.GET)
    public @ResponseBody
    void removeItinerary(@RequestParam Integer idItinerary) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        
        int idUser = user.getId().intValue();
        ItineraryManager.removeItinerary(pm,idItinerary,idUser);
    }
        
    
     @RequestMapping(value = "/empty", method = RequestMethod.GET)
     public @ResponseBody
    ModelAndView emptyPage(){
        ModelAndView model = new ModelAndView("empty");
        return model;
        
    }
     
}
