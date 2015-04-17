/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.repositories;
import com.orchestra.portale.persistence.sql.entities.Favorite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author antonio
 */
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Modifying
    @Transactional(readOnly=false)
    @Query("update Favorite f set f.rating= ?1 where f.idUser = ?2 AND f.idPoi = ?3")
    Integer updateFavoriteRating(Integer rating, Integer id_user, String id_poi);    
    
    
    Iterable<Favorite> findByIdUser(Integer idUser);
 
}
