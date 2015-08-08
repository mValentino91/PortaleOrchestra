package com.orchestra.portale.managers;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.Itinerary;
import com.orchestra.portale.persistence.sql.entities.ItineraryDetail;
import com.orchestra.portale.persistence.sql.entities.User;
import com.orchestra.portale.persistence.sql.entities.UserOfferChoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    public static void addOffer(PersistenceManager pm, int idItinerary, String idPoi, int idOffer, int qta, int sum){
        //calcolare la somma qui dentro
        UserOfferChoice uc = new UserOfferChoice();
        //Itinerary_detail id = new Itinerary_detail();
        
        //il poi ke agg l'offerta è gia esistente.. lo devo recuperare ed aggiornare il campo user choice
        
        /*
        id.setIdItinerary(idItinerary);
        id.setIdPoi(idPoi);
        pm.savePoiItinerary(id);
        */
        
        //select idItinerary_detail where idPoi and iditinerary
       
        
        Integer iddetail = pm.findItDetail(idItinerary);
        System.out.println(iddetail);
        
        //inserimento dell'offerta scelta nella tabella
        uc.setIdItineraryDetail(iddetail);
        uc.setIdOffer(idOffer);
        uc.setQta(qta);
        uc.setSum(sum);
        uc.setStatus(0);
        pm.saveUserChoice(uc);   
    }
    
    public static void removeOffer(PersistenceManager pm, int idOffer, int idItineraryDetail){
        
       pm.deleteOfferIt(idOffer, idItineraryDetail);
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
    
}
