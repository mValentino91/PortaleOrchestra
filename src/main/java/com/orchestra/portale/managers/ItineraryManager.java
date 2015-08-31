package com.orchestra.portale.managers;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.Itinerary;
import com.orchestra.portale.persistence.sql.entities.ItineraryDetail;
import com.orchestra.portale.persistence.sql.entities.UserOfferChoice;

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
    
}
