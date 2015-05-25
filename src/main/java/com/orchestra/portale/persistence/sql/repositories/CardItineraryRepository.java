package com.orchestra.portale.persistence.sql.repositories;

import com.orchestra.portale.persistence.sql.entities.CardItinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author andrea
 */
public interface CardItineraryRepository extends JpaRepository<CardItinerary, Integer>{
 
    @Query("select c.idCard from Card c where c.idUser = ?1 AND c.status = 0")
    Integer findActiveCardByIdUser(int id_user);    
    
}
