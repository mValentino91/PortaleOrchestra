/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.repositories;

import com.orchestra.portale.persistence.sql.entities.Favorite;
import com.orchestra.portale.persistence.sql.entities.UserOfferChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andzaccaro
 */
public interface UserOfferChoiceRepository extends JpaRepository<UserOfferChoice, Integer> {

    @Modifying
    @Transactional(readOnly=false)
    @Query("delete from UserOfferChoice u where u.idOffer =?1 AND u.idItineraryDetail =?2")
    void deleteOfferCard(Integer idOffer, Integer idItineraryDetail);
    
    @Modifying
    @Transactional(readOnly=false)
    @Query("delete from UserOfferChoice u where u.stockType =?1 AND u.idItineraryDetail =?2")
    void deleteOfferStock(String name, Integer idItineraryDetail);
    
    @Query("select u.idItineraryDetail from UserOfferChoice u where u.idOffer=?1")
    Integer findIdDetailByidOffer(Integer idOffer);
    
    @Query("select u.idUserOfferChoice  from UserOfferChoice u where u.idOffer=?1 AND u.idItineraryDetail=?2")
    public Integer retreiveUserChoiceCard(int idOffer, int idItinerary);

    @Query("select u.idUserOfferChoice  from UserOfferChoice u where u.stockType=?1 AND u.idItineraryDetail=?2")
    public Integer retreiveUserChoiceStock(String type, int idItinerary);
    
    @Modifying
    @Transactional(readOnly=false)
    @Query("update UserOfferChoice u set u.qta=?1, u.sum=?2 where u.idOffer=?3")
    public void updateUserChoiceCard(int qta, float tot, int idOffer);
    
    @Modifying
    @Transactional(readOnly=false)
    @Query("update UserOfferChoice u set u.qta=?1, u.sum=?2 where u.stockType=?3")
    public void updateUserChoiceStock(int qta, float tot, String type);

    
    
}
