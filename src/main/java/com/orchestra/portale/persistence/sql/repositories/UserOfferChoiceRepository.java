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
    @Query("delete from UserOfferChoice u where u.idItineraryDetail =?1 AND u.idOffer =?2")
    void deleteOfferItinerary(Integer idOffer, Integer idItineraryDetail);
    
    @Query("select u.idItineraryDetail from UserOfferChoice u where u.idOffer=?1")
    Integer findIdDetailByidOffer(Integer idOffer);
}
