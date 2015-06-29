/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.repositories;
import com.orchestra.portale.persistence.sql.entities.Ownership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author antonio
 */
public interface OwnershipRepository extends JpaRepository<Ownership, Integer> {
    
    @Query("SELECT idPoi FROM Ownership WHERE idUser = ?1")
    Iterable<String> getIdPoiByOwner(Integer id_user); 
    
    
    
}
