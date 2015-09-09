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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author andzaccaro
 */

public class ItineraryManager{ 
    
    
    public static void createItinerary(PersistenceManager pm, String id_user, String name ){
        
        Itinerary it = new Itinerary();
        //passare il nome dell'itinerario nel set parametri
        it.setIdUser(Integer.parseInt(id_user));
        it.setStatus(0);
        it.setKeyString("blabla");
        it.setName(name);
        //pm.saveItinerary(it);
        pm.saveItinerary(it);   
    }
    
    public static void addPoi(PersistenceManager pm, int it, String idPoi){
        ItineraryDetail id = new ItineraryDetail();
        id.setIdPoi(idPoi);
        id.setIdItinerary(it);
        pm.savePoiItinerary(id);
         
    }
    
    public static void addOffer(PersistenceManager pm, int idItinerary, Integer idOffer, int qta, float sum, String type, String name, String desc){
        
        float tot = sum*qta;
        Integer iddetail = pm.findItDetail(idItinerary);
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
    
    public static void removeOffer(PersistenceManager pm, int idOffer, int idItinerary, String name, String type){
       
        Integer iddetail = pm.findItDetail(idItinerary);
        
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
        Iterable<String> pois_id = pm.findPoisByItinerary(idItinerary);
        Iterable<? extends CompletePOI> pois = pm.getCompletePoisById(pois_id);
        //x ogni id poi devo visualizzare le off stock e le off card

        model.addObject("id",idItinerary);
        model.addObject("pois", pois);
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
        
        
        List<Map<String,String>> price_comp = new ArrayList<Map<String, String>>();
        
        CompletePOI poi = pm.getCompletePoiById(idPoi);
        String poi_name = poi.getName();

        //off card
        List<DealerOffer> offers = pm.findOfferByIdPoi(idPoi);
        
        Integer iddetail = pm.findItDetail(idItinerary);
        //List<Integer>idOffer_choice = pm.findChoiceCardByUser(iddetail);
        List<Integer>idOffer_choice = pm.findChoiceCardByUser(iddetail);
        
        List<String>typeStock_choice = pm.findChoiceStockByUser(iddetail); 
        
        model.addObject("offers", offers);
        model.addObject("poi_name", poi_name);
        model.addObject("idPoi",idPoi);
        model.addObject("idItinerary",idItinerary);
        //model.addObject("user_cardChoices",user_cardChoices);
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

    public static void completeItinerary(PersistenceManager pm, Integer idItinerary, int idUser) {
        pm.completeItinerary(idItinerary,idUser);
    }

    public static int findStatusByIdItinerary(PersistenceManager pm, int idItinerary,int idUser) {
        return pm.findStatusByIdItinerary(idItinerary,idUser);
    }
    
    
}
