/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.repositories;

import com.orchestra.portale.persistence.sql.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andrea
 */
public interface CardRepository extends JpaRepository<Card, Integer>{
    @Modifying
    @Transactional(readOnly=false)
    @Query("update Card c set c.status= 1, c.status=1 where c.idUser = ?1")
    void activeCard(int id_user);  
    
}
