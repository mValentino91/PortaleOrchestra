package com.orchestra.portale.managers;

import com.orchestra.portale.controller.CartController;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.PricesComponent;
import com.orchestra.portale.persistence.mongo.documents.TicketPrice;
import com.orchestra.portale.persistence.sql.entities.DealerOffer;
import com.orchestra.portale.persistence.sql.entities.Itinerary;
import com.orchestra.portale.persistence.sql.entities.ItineraryDetail;
import com.orchestra.portale.persistence.sql.entities.UserOfferChoice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author andzaccaro
 */

public class ItineraryManager{ 
    
    
    public static void createItinerary(PersistenceManager pm, String id_user, String name ){
        String[] colors = {"#81CFE0","#F4D03E","#BF90D4","#87D37C","#F2784B","#ED5565"};
        String col_value = (colors[new Random().nextInt(colors.length)]);
        
        Itinerary it = new Itinerary();
        //passare il nome dell'itinerario nel set parametri
        it.setIdUser(Integer.parseInt(id_user));
        it.setStatus(0);
        it.setKeyString("blabla");
        it.setName(name);
        it.setColor(col_value);
        //pm.saveItinerary(it);
        pm.saveItinerary(it);   
    }
    
    public static void addPoi(PersistenceManager pm, int it, String idPoi){
        ItineraryDetail id = new ItineraryDetail();
        id.setIdPoi(idPoi);
        id.setIdItinerary(it);
        pm.savePoiItinerary(id);
         
    }
    
    public static void addOffer(PersistenceManager pm, int idItinerary, String idPoi,Integer idOffer, int qta, float sum, String type, String name, String desc){
        
        float tot = sum*qta;
        Integer iddetail = pm.findItDetail(idItinerary, idPoi);
        Integer uc_id=null;
        
        
        switch (type) {
            case "STOCK":
                uc_id = pm.retreiveUserChoiceStock(name,iddetail);
                if(uc_id != null){
                    pm.updateUserChoiceStock(qta,tot,name);
                }
                else{
                    //insert
                    UserOfferChoice uc = new UserOfferChoice();
                    uc.setIdItineraryDetail(iddetail);
                    uc.setQta(qta);
                    uc.setStatus(0);
                    uc.setType(type);
                    uc.setStockType(name);
                    uc.setSum(tot);
                    uc.setDescription(desc);
                    uc.setPrice(sum);
                    pm.saveUserChoice(uc);
                    
                }   break;
            case "CARD":
                uc_id = pm.retreiveUserChoiceCard(idOffer,iddetail);
                if(uc_id != null){
                    pm.updateUserChoiceCard(qta,tot,idOffer);
                }
                else{
                    UserOfferChoice uc = new UserOfferChoice();
                    uc.setIdItineraryDetail(iddetail);
                    uc.setQta(qta);
                    uc.setSum(tot);
                    uc.setStatus(0);
                    uc.setType(type);
                    uc.setIdOffer(idOffer);
                pm.saveUserChoice(uc);  
            }   break;
        }
         
    }
    
    
    public static void removePoi(PersistenceManager pm, int idItinerary, String idPoi) {
        Integer iddetail = pm.findItDetail(idItinerary, idPoi);
        pm.deletePoiItinerary(idPoi,idItinerary);
        pm.deleteOffersPoi(iddetail);
        
    }

    
    public static void removeOffer(PersistenceManager pm, int idOffer, String idPoi, int idItinerary, String name, String type){
       
        Integer iddetail = pm.findItDetail(idItinerary,idPoi);
        
        switch (type) {
            case "CARD":
                //rimozione offerta card
                pm.deleteOfferCard(idOffer, iddetail);
                break;
            case "STOCK":
                pm.deleteOfferStock(name,iddetail);
                break;
        }
        
        
    }
    
    public static Iterable<Itinerary> retreiveItinerary(PersistenceManager pm,int idUser){
        return pm.findItinerariesByIdUser(idUser);
    }
    
    
    
    /*
    
    public String removeOffer(int id_user, int idItinerary, int idItineraryDetail, String idPoi, int idOffer)
    public String removeItinerary(int id_user, int idItinerary)
    public String completeItinerary(int id_user, int idItinerary)
    public void viewItinerary(int id_user, int idItinerary)
    public void viewItineraryDetail(int id_user, int idItinerary, int idItineraryDetail)
    public void printItineraryDetail(int id_user, int idItinerary)
    
    //aggiungere i metodi per la visualizzazione dell'itinerario sull'app
   
    
    */

    public static Iterable<String> findPoiByItinerary(PersistenceManager pm, int id) {
        return pm.findPoisByItinerary(id);
    }

    public static void showItDetail(PersistenceManager pm, String idPoi, String idItinerary) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void removeItinerary(PersistenceManager pm, Integer idItinerary, int idUser) {
        pm.deleteItinerary(idItinerary,idUser);
    }
    
    public static ModelAndView viewCompleteItineraryDetail(PersistenceManager pm, int idItinerary){
        ModelAndView model = new ModelAndView("completeItineraryDetail");
        
        Map<String,CompletePOI>map_poi = new HashMap<String,CompletePOI>();
        Map<Integer,DealerOffer>map_dealerOffer = new HashMap<Integer,DealerOffer>();
        Map<String,Iterable<UserOfferChoice>>map_stockChoice = new HashMap<String,Iterable<UserOfferChoice>>();
        Map<String,Iterable<UserOfferChoice>>map_cardChoice = new HashMap<String,Iterable<UserOfferChoice>>();
        
        Iterable<String> pois_id = pm.findPoisByItinerary(idItinerary);
        Iterable<Integer> details = pm.findIdDetailByIdItinerary(idItinerary);
        Iterable<Integer>dealerChoice = null;
        //carico poi dell'itinerario
        for(String pid: pois_id){
            CompletePOI poi = pm.getCompletePoiById(pid);
            map_poi.put(pid, poi);
            
            //recupero detail dato idpoi ed iditinerary
            int idd = pm.findIdItineraryDetailByIdItineraryAndIdPoi(idItinerary,pid);
            Iterable<UserOfferChoice>stockChoice = pm.findChoiceStockByUser(idd);
            Iterable<UserOfferChoice>cardChoice = pm.findChoiceCardByUser(idd);
            map_stockChoice.put(pid, stockChoice);
            map_cardChoice.put(pid, cardChoice);
            dealerChoice = pm.findIdOfferByIdItineraryDetail(idd);
            
            for(Integer idOffer: dealerChoice){
                DealerOffer off = pm.findDealerOfferByidOffer(idOffer);
                map_dealerOffer.put(idOffer, off);
                
            }
        
            //
            
            /*
            for(Integer idd: details){
                List<UserOfferChoice>stockChoice = pm.findChoiceStockByUser(idd);
                System.out.println("numero="+stockChoice.size());
                List<UserOfferChoice>cardChoice = pm.findChoiceCardByUser(idd);
                dealerChoice = pm.findIdOfferByIdItineraryDetail(idd);
                map_stockChoice.put(pid, stockChoice);
                map_cardChoice.put(pid, cardChoice);
            }
            
            for(Integer idOffer: dealerChoice){
                DealerOffer off = pm.findDealerOfferByidOffer(idOffer);
                map_dealerOffer.put(idOffer, off);
                
            }
                    */
        }
        String prova = "ddd";
        model.addObject("map_poi",map_poi);
        model.addObject("map_dealerOffer",map_dealerOffer);
        model.addObject("map_stockChoice",map_stockChoice);
        model.addObject("map_cardChoice",map_cardChoice);
        model.addObject("prova",prova);
        
        
        
        
        
        
        
        
        
        /*
        Iterable<String> pois_id = pm.findPoisByItinerary(idItinerary);
        Iterable<? extends CompletePOI> pois = pm.getCompletePoisById(pois_id);
        Map<CompletePOI, List<UserOfferChoice>> map_choices = new HashMap<CompletePOI,List<UserOfferChoice>>();
        //x ogni id poi devo visualizzare le off stock e le off card
        List<UserOfferChoice>user_offers;
        String idPoi;
        Iterable<Integer> details = pm.findIdDetailByIdItinerary(idItinerary);
        //user_offers=pm.findByIdItineraryDetail(4);
        Map<String,CompletePOI>map_poi = new HashMap<String,CompletePOI>();
        //Map<String,List
        
        
        for(Integer idd: details){
            user_offers=pm.findByIdItineraryDetail(idd);
            idPoi = pm.findIdPoiByIdItineraryDetail(idd);
            CompletePOI poi = pm.getCompletePoiById(idPoi);
            map_choices.put(poi, user_offers);
        }
        
        
        
        model.addObject("id",idItinerary);
        model.addObject("map_choices",map_choices);
                */
        return model;
    }
        
    
    public static ModelAndView viewItineraryDetail(PersistenceManager pm, int idItinerary){
        ModelAndView model = new ModelAndView("itineraryDetail");

        Iterable<String> pois_id = ItineraryManager.findPoiByItinerary(pm, idItinerary);
        Iterable<? extends CompletePOI> pois = pm.getCompletePoisById(pois_id);
        //x ogni id poi devo visualizzare le off stock e le off card

        model.addObject("id",idItinerary);
        model.addObject("pois", pois);
        
        
               
        return model;
    }
    
    
    
    public static ModelAndView viewOfferPoi(PersistenceManager pm, int idItinerary, String idPoi){
       
        ModelAndView model = new ModelAndView("viewOffers");
        CompletePOI poi = pm.getCompletePoiById(idPoi);
        String poi_name = poi.getName();
        Integer iddetail = pm.findItDetail(idItinerary,idPoi);
        
        List<Map<String,String>> price_comp = new ArrayList<Map<String, String>>();
        List<DealerOffer> offers = pm.findOfferByIdPoi(idPoi);
        
        Map<String,UserOfferChoice>map_userChoice_stock = new HashMap<String,UserOfferChoice>();
        Map<Integer,UserOfferChoice>map_userChoice_card = new HashMap<Integer,UserOfferChoice>();
        
        
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
        
        Iterable<UserOfferChoice>cardChoices = pm.findChoiceCardByUser(iddetail);
        Iterable<UserOfferChoice>stockChoices = pm.findChoiceStockByUser(iddetail);
        
        for(UserOfferChoice uc: cardChoices){
            map_userChoice_card.put(uc.getIdOffer(), uc);
        }
        
        for(UserOfferChoice uc: stockChoices){
            map_userChoice_stock.put(uc.getStockType(), uc);
        }
        
        model.addObject("poi", poi);
        model.addObject("price_comp",price_comp);
        model.addObject("offers",offers);
        model.addObject("map_userChoice_stock",map_userChoice_stock);
        model.addObject("map_userChoice_card",map_userChoice_card);
        model.addObject("idItinerary",idItinerary);
        return model;
        
        /*
        List<Map<String,String>> price_comp = new ArrayList<Map<String, String>>();
        Map<String,String> map_off_stock = new HashMap<String,String>();
        Map<String,Integer>map_off_card = new HashMap<String,Integer>();
        Map<String,String>map_userChoice_stock = new HashMap<String,String>();
        
        Map<String,List<DealerOffer>>map_userChoice_card = new HashMap<String,List<DealerOffer>>();
        
        
        CompletePOI poi = pm.getCompletePoiById(idPoi);
        String poi_name = poi.getName();

        //off card
        List<DealerOffer> offers = pm.findOfferByIdPoi(idPoi);
        
        Integer iddetail = pm.findItDetail(idItinerary,idPoi);
        //List<Integer>idOffer_choice = pm.findChoiceCardByUser(iddetail);
        //List<Integer>idOffer_choice = pm.findChoiceCardByUser(iddetail);
        List<UserOfferChoice>user_cardChoices = pm.findChoiceCardByUser(iddetail);
        List<UserOfferChoice>user_stockChoices = pm.findChoiceStockByUser(iddetail);
        
        
        
        
        model.addObject("offers", offers);
        model.addObject("poi_name", poi_name);
        model.addObject("idPoi",idPoi);
        model.addObject("idItinerary",idItinerary);
        model.addObject("user_cardChoices",user_cardChoices);
        model.addObject("user_stockChoices",user_stockChoices);
        //model.addObject("idOffer_choice",idOffer_choice);
        //model.addObject("typeStock_choice",typeStock_choice);

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
        */
        
    }

    public static void completeItinerary(PersistenceManager pm, Integer idItinerary, int idUser) {
        pm.completeItinerary(idItinerary,idUser);
    }

    public static int findStatusByIdItinerary(PersistenceManager pm, int idItinerary,int idUser) {
        return pm.findStatusByIdItinerary(idItinerary,idUser);
    }    
    
}
