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
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;

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

    @Override
    public Iterable<CompletePOI> getCompletePoiByCategory(String category) {

        return mongoOps.find(new Query(where("categories").is(category)), CompletePOI.class);
    }

    @Override
    public CompletePOI findOneCompletePoiByName(String name) {

        return mongoOps.findOne(new Query(where("name").is(name)), CompletePOI.class);
    }

    @Override
    public void deletePoi(CompletePOI poi) {
        poiMongoRepo.delete(poi);
    }

    @Override
    public Iterable<CompletePOI> findCompletePoi(String name, String address, String category) {

        return mongoOps.find(new Query(where("categories").regex(category)
                .and("name").regex(name)
                .and("address").regex(address)),
                CompletePOI.class);
    }

    @Override
    public Iterable<CompletePOI> findNearCompletePoi(String id,double radius) {

        CompletePOI poi = getCompletePoiById(id);

        Circle circle;
        circle = new Circle(poi.getLocation()[0], poi.getLocation()[1],radius);

        return mongoOps.find(new Query(where("location").withinSphere(circle)),
                CompletePOI.class);
    }
}
