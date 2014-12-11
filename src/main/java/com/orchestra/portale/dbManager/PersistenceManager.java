/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.orchestra.portale.dbManager;

import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.sql.entities.Poi;

/**
 *
 * @author Marco Valentino
 */
public interface PersistenceManager {
    
    public Poi getPoiById(String Id);
    public Iterable<Poi> getAllPoi();
    public CompletePOI getCompletePoiById(String Id);
    public Iterable<CompletePOI> getAllCompletePoi();
    public void savePoi(CompletePOI poi);
    
}
