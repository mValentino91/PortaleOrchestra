/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.dbManager;

import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.repositories.PoiMongoRepository;
import com.orchestra.portale.persistence.sql.entities.Poi;
import com.orchestra.portale.persistence.sql.repositories.CategoryRepository;
import com.orchestra.portale.persistence.sql.repositories.CompCategoryComponentRepository;
import com.orchestra.portale.persistence.sql.repositories.CompPoiCategoryRepository;
import com.orchestra.portale.persistence.sql.repositories.ComponentRepository;
import com.orchestra.portale.persistence.sql.repositories.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 *
 * @author Marco Valentino
 */
public class ConcretePersistenceManager implements PersistenceManager {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ComponentRepository componentRepository;

    @Autowired
    CompCategoryComponentRepository compCategoryComponentRepository;

    @Autowired
    PoiRepository poiRepository;

    @Autowired
    CompPoiCategoryRepository compPoiCategoryRepository;

    @Autowired
    MongoOperations mongoOps;

    @Autowired
    PoiMongoRepository poiMongoRepo;

    @Override
    public Poi getPoiById(String Id) {
        return null;

    }

    @Override
    public Iterable<Poi> getAllPoi() {
        return null;

    }

    @Override
    public CompletePOI getCompletePoiById(String Id) {

        return poiMongoRepo.findOne(Id);
    }

    @Override
    public Iterable<CompletePOI> getAllCompletePoi() {
        return poiMongoRepo.findAll();
    }

    @Override
    public void savePoi(CompletePOI poi) {
        poiMongoRepo.save(poi);
    }

}
