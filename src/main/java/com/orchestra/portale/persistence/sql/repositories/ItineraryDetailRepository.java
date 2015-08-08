/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.repositories;

import com.orchestra.portale.persistence.sql.entities.ItineraryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author andzaccaro
 */
public interface ItineraryDetailRepository extends JpaRepository<ItineraryDetail, Integer> {
    @Query("select i.idItineraryDetail from ItineraryDetail i where i.idItinerary=?1")
    Integer findItDetailByIdItineraryAndIdPoi(int idItinerary);
    
   @Query("select i.idItinerary from ItineraryDetail i where i.idItineraryDetail=?1")
   Integer findItineraryByIdItineraryDetail(int idItineraryDetail);
   
   @Query("select i.idPoi from ItineraryDetail i where i.idItinerary=?1")
   Iterable<String>findPoiByIdItinerary(int idItinerary);
    
}
