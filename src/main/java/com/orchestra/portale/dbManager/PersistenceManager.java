/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.orchestra.portale.dbManager;

import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.EnCompletePOI;
import com.orchestra.portale.persistence.mongo.documents.Pages;
import com.orchestra.portale.persistence.sql.entities.Favorite;
import com.orchestra.portale.persistence.sql.entities.Poi;
import com.orchestra.portale.persistence.sql.entities.User;
import org.springframework.data.geo.GeoResults;

/**
 *
 * @author Marco Valentino
 */
public interface PersistenceManager {
    
    public Poi getPoiById(String Id);
    public Iterable<Poi> getAllPoi();
    public CompletePOI getCompletePoiById(String id);
    public Iterable<CompletePOI> getCompletePoisById(Iterable<String> id);
    public Iterable<CompletePOI> getAllCompletePoi();
    public Iterable<CompletePOI> getCompletePoiByCategories(String [] categories);
    public Iterable<CompletePOI> findCompletePoi(String name, String address, String category);
    public GeoResults<CompletePOI> findNearCompletePoi(String id,double radius);
    public CompletePOI findOneCompletePoiByName(String name);
    public void deletePoi(CompletePOI poi);
    public void savePoi(CompletePOI poi);
    public void saveEnPoi(EnCompletePOI enpoi);
    public User findUserByUsername(String username);
    public User findUserByFbUser(String fbUser);
    public User findUserByFbEmail(String fbEmail);
    public User findUserByFbEmailOrFbUser(String fbEmail, String fbUser);
    public void saveUser(User user);
    public void savePage(Pages page);
    public Pages findPageById(String id);
    public void saveFavorite(Favorite favorite);
    public void updateFavoriteRating(Integer rating, Integer id_user, String id_poi);
    public Iterable<Favorite> findFavoritesByIdUser(Integer idUser);
}
