/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.repositories;

import com.orchestra.portale.persistence.sql.entities.CartItinerarydetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andrea
 */
public interface CartItinerarydetailRepository  extends JpaRepository<CartItinerarydetail, Integer>{
    @Modifying
    @Transactional(readOnly=false)
    @Query("update CartItinerarydetail c set c.idItinerary= ?1, c.status=1 where c.idUser = ?2")
    void updateItemItinerary(int idItinerary, int id_user);  
    
    @Query("select c from CartItinerarydetail c where c.idItinerary=?1 AND c.idUser = ?2 AND c.status = 1")
    Iterable<CartItinerarydetail> selectActiveOffer(int idItinerary, int idUser);
    
    @Modifying
    @Transactional(readOnly=false)
    @Query("update CartItinerarydetail c set c.status= 2 where c.idItinerary = ?1 AND c.idOffer = ?2")
    void invalidateOffer(int idItinerary, int idOffer);
    
    
    Iterable<CartItinerarydetail> findByIdUser(int idUser);

    
    @Modifying
    @Transactional(readOnly=false)
    @Query("update CartItinerarydetail c set c.qta= ?1, c.sum =?2 where c.idUser = ?3 AND c.idOffer = ?4")
    public void updateQta(int qta, float tot, int id_user,int idOffer); 
    
    @Modifying
    @Transactional(readOnly=false)
    @Query("update CartItinerarydetail c set c.qta= ?1, c.sum = ?2 where c.tipoStock = ?3 AND c.idPoi = ?4 AND c.idUser= ?5")
    public void updateQtaByType(int qta, float tot, String type, String idPoi, int idUser);

}
