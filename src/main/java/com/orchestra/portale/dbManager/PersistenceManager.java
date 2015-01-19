/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.orchestra.portale.dbManager;

import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.sql.entities.Poi;
import org.springframework.data.geo.GeoResults;

/**
 *
 * @author Marco Valentino
 */
public interface PersistenceManager {
    
    public Poi getPoiById(String Id);
    public Iterable<Poi> getAllPoi();
    public CompletePOI getCompletePoiById(String Id);
    public Iterable<CompletePOI> getAllCompletePoi();
    public Iterable<CompletePOI> getCompletePoiByCategory(String category);
    public Iterable<CompletePOI> findCompletePoi(String name, String address, String category);
    public GeoResults<CompletePOI> findNearCompletePoi(String id,double radius);
    public CompletePOI findOneCompletePoiByName(String name);
    public void deletePoi(CompletePOI poi);
    public void savePoi(CompletePOI poi);
    
}
