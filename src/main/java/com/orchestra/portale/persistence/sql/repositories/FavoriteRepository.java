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
    
    Iterable<Favorite> findByIdUser(int idUser);
    
    Favorite findByIdUserAndIdPoi(int idUser, String idPoi);
    
    @Modifying
    @Transactional(readOnly=false)
    @Query("delete from Favorite f where f.idUser = ?1 AND f.idPoi = ?2")
    void deleteFavorite(Integer id_user, String id_poi);      
    
    @Query("SELECT count(f) , f.idPoi FROM Favorite f GROUP BY f.idPoi ORDER BY count(f) DESC")
    Iterable<Object[]> getMostFavorites(); 

    @Query("Select f from Favorite f where f.idPoi=?1 AND f.idUser=?2")
    Favorite findByIdPoiAndIdUser(String idPoi, int idUser);
}
