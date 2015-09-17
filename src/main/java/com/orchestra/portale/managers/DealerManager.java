/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.managers;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.UserOfferChoice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author andzaccaro
 */
public class DealerManager {
    
    public static List<String>getPois(PersistenceManager pm,int idDealer){
        return pm.getDealerOwnPoi(idDealer);
    }
    
    public static List<UserOfferChoice>getUserChoice(PersistenceManager pm, int idDealer, String keyString){
        
        
        List<UserOfferChoice>user_choices = null;
        return user_choices;
    }
    
    
    
}
