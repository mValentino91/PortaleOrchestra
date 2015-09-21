/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.repositories;

import com.orchestra.portale.persistence.sql.entities.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andzaccaro
 */
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {
    @Query("select i.idUser from Itinerary i where i.idItinerary =?1")
    Long findUserByIdItinerary(Integer idItinerary);
    
    @Query("select i from Itinerary i where i.idUser =?1 ORDER BY i.idItinerary desc")
    Iterable<Itinerary> findByIdUser(int idUser);
    
    @Modifying
    @Transactional(readOnly=false)
    @Query("delete from Itinerary i where i.idItinerary =?1 AND i.idUser=?2")
    void deleteItinerary(int idItinerary,int idUser);
    
    @Modifying
    @Transactional(readOnly=false)
    @Query("update Itinerary i set i.status=1 where i.idItinerary=?1 AND i.idUser=?2")
    void completeItinerary(int idItinerary, int idUser);

    @Query("select i.status from Itinerary i where i.idItinerary=?1 AND i.idUser=?2")
    int findStatusByIdItinerary(int idItinerary, int idUser);

    @Query("select i.idItinerary from Itinerary i where i.keyString=?1")
    Integer findByKeyString(String keyString);

    Itinerary findByIdItinerary(int idItinerary);

    @Query("select i from Itinerary i where i.idUser=?1 AND i.status=0")
    Iterable<Itinerary> findActiveByIdItinerary(int idUser);
    
}
