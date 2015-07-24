/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.repositories;

import com.orchestra.portale.persistence.sql.entities.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author andzaccaro
 */
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {
    @Query("select i.idUser from Itinerary i where i.idItinerary =?1")
    Long findUserByIdItinerary(Integer idItinerary);
    
}
