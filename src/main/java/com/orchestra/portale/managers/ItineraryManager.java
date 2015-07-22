package com.orchestra.portale.managers;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.Itinerary;
import com.orchestra.portale.persistence.sql.entities.User;
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
    
    
    public void createItinerary(PersistenceManager pm, String id_user ){
        //istanziazione itinerario e salvataggio in persistenza
        
        
            
           
        
        Itinerary it = new Itinerary();
        //passare il nome dell'itinerario nel set parametri
        it.setIdUser(Integer.parseInt(id_user));
        it.setStatus(0);
        it.setKeyString("blabla");
        it.setName("cacca");
        //pm.saveItinerary(it);
        pm.saveItinerary(it);
        
        
    }
    
    /*
    
    public String addPoi(int id_user,idItineraryDetail)
    public String addOffer(int id_user, int idItinerary, int idItineraryDetail, String idPoi, int idOffer, int qta)
    public String removeOffer(int id_user, int idItinerary, int idItineraryDetail, String idPoi, int idOffer)
    public String removeItinerary(int id_user, int idItinerary)
    public String completeItinerary(int id_user, int idItinerary)
    public void viewItinerary(int id_user, int idItinerary)
    public void viewItineraryDetail(int id_user, int idItinerary, int idItineraryDetail)
    public void printItineraryDetail(int id_user, int idItinerary)
    
    //aggiungere i metodi per la visualizzazione dell'itinerario sull'app
   
    
    */
    
}
