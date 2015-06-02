package com.orchestra.portale.persistence.sql.repositories;

import com.orchestra.portale.persistence.sql.entities.CardItinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author andrea
 */
public interface CardItineraryRepository extends JpaRepository<CardItinerary, Integer>{
 
       
    
    @Query("select c.idItinerary from CardItinerary c where c.idCard = ?1")
    Integer getIdItineraryByIdCard(int id_card);
    
    
}
