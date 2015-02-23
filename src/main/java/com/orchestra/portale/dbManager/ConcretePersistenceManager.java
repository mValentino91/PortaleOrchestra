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
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.NearQuery;
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

        Iterable<CompletePOI> pois = mongoOps.find(new Query(where("name").regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))), CompletePOI.class);
        for (CompletePOI p : pois) {
            if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void deletePoi(CompletePOI poi) {
        poiMongoRepo.delete(poi);
    }

    @Override
    public Iterable<CompletePOI> findCompletePoi(String name, String address, String category) {

        return mongoOps.find(new Query(where("categories").regex(Pattern.compile(category, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))
                .and("name").regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))
                .and("address").regex(Pattern.compile(address, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))),
                CompletePOI.class);
    }

    @Override
    public GeoResults<CompletePOI> findNearCompletePoi(String id, double radius) {

        CompletePOI poi = getCompletePoiById(id);
        Point point = new Point(poi.getLocation()[0], poi.getLocation()[1]);
        NearQuery query = NearQuery.near(point).maxDistance(new Distance(radius, Metrics.KILOMETERS));

        return mongoOps.geoNear(query, CompletePOI.class);
    }
}
