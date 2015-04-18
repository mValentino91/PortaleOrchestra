/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.dbManager;

import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.DeepeningPage;
import com.orchestra.portale.persistence.mongo.documents.EnCompletePOI;
import com.orchestra.portale.persistence.mongo.documents.Home;
import com.orchestra.portale.persistence.mongo.documents.Pages;
import com.orchestra.portale.persistence.mongo.repositories.DeepeningPageMongoRepository;
import com.orchestra.portale.persistence.mongo.repositories.EnPoiMongoRepository;
import com.orchestra.portale.persistence.mongo.repositories.HomeMongoRepository;
import com.orchestra.portale.persistence.mongo.repositories.PagesMongoRepository;
import com.orchestra.portale.persistence.mongo.repositories.PoiMongoRepository;
import com.orchestra.portale.persistence.sql.entities.Favorite;
import com.orchestra.portale.persistence.sql.entities.Poi;
import com.orchestra.portale.persistence.sql.entities.User;
import com.orchestra.portale.persistence.sql.repositories.CategoryRepository;
import com.orchestra.portale.persistence.sql.repositories.CompCategoryComponentRepository;
import com.orchestra.portale.persistence.sql.repositories.CompPoiCategoryRepository;
import com.orchestra.portale.persistence.sql.repositories.ComponentRepository;
import com.orchestra.portale.persistence.sql.repositories.FavoriteRepository;
import com.orchestra.portale.persistence.sql.repositories.PoiRepository;
import com.orchestra.portale.persistence.sql.repositories.UserRepository;
import java.util.ArrayList;
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
    private CategoryRepository categoryRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private CompCategoryComponentRepository compCategoryComponentRepository;

    @Autowired
    private PoiRepository poiRepository;

    @Autowired
    private CompPoiCategoryRepository compPoiCategoryRepository;

    @Autowired
    private MongoOperations mongoOps;

    @Autowired
    private PoiMongoRepository poiMongoRepo;

    @Autowired
    private EnPoiMongoRepository enPoiMongoRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PagesMongoRepository pagesRepo;
    
    @Autowired
    HomeMongoRepository homeRepo;

    @Autowired
    DeepeningPageMongoRepository deepRepo;
    
    @Autowired
    private FavoriteRepository favoriteRepo;
    
    @Override
    public Poi getPoiById(String Id) {
        return null;

    }

    @Override
    public Iterable<Poi> getAllPoi() {
        return null;

    }

    @Override
    public Iterable<CompletePOI> getCompletePoisById(Iterable<String> id) {
        return poiMongoRepo.findAll(id);
    }

    @Override
    public CompletePOI getCompletePoiById(String id) {

        return poiMongoRepo.findOne(id);
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
    public ArrayList<CompletePOI> getCompletePoiByCategories(ArrayList<String> categories) {

        return (ArrayList<CompletePOI>) mongoOps.find(new Query(where("categories").in(java.util.Arrays.asList(categories))), CompletePOI.class);
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

    @Override
    public void saveEnPoi(EnCompletePOI enpoi) {
        enPoiMongoRepo.save(enpoi);
    }

    @Override
    public User findUserByUsername(String username) {
        User usr = userRepository.findByUsername(username);
        return usr;
    }

    @Override
    public User findUserByFbUser(String fbUser) {
        User usr = userRepository.findByFbUser(fbUser);
        return usr;
    }

    @Override
    public User findUserByFbEmail(String fbEmail) {
        User usr = userRepository.findByFbEmail(fbEmail);
        return usr;
    }

    @Override
    public User findUserByFbEmailOrFbUser(String fbEmail, String fbUser) {
        User usr = userRepository.findByFbEmailOrFbUser(fbEmail, fbUser);
        return usr;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void savePage(Pages page) {
        pagesRepo.save(page);
    }

    @Override
    public Pages findPageById(String id) {
        Pages page = pagesRepo.findOne(id);
        return page;
    }

    @Override
    public Pages findPageBySlug(String slug) {
        return mongoOps.findOne(new Query(where("slug").is(slug)), Pages.class);

    }
    @Override
    public void saveHome(Home home) {
        homeRepo.save(home);
    }
    
    @Override
    public void saveDeepeningPage(DeepeningPage dp) {
        deepRepo.save(dp);
    }
    @Override
    public DeepeningPage findDeepeningPage(String id) {
        return deepRepo.findOne(id);
    }
    @Override
    public DeepeningPage findDeepeningPageByName(String name) {
         Iterable<DeepeningPage> dp = mongoOps.find(new Query(where("name").regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))), DeepeningPage.class);
        for (DeepeningPage p : dp) {
            if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                return p;
            }
        }
        return null;
    }
    
@Override
    public void saveFavorite(Favorite favorite){
        favoriteRepo.save(favorite);
    }    
    
    @Override
    public void updateFavoriteRating(Integer rating, Integer id_user, String id_poi){
        favoriteRepo.updateFavoriteRating(rating, id_user, id_poi);
    }
        
    @Override
    public Iterable<Favorite> findFavoritesByIdUser(Integer idUser){
        Iterable<Favorite> favorites = favoriteRepo.findByIdUser(idUser);
        return favorites;
    }
    
    @Override
    public Favorite findFavorite(Integer id){
        Favorite favorite = favoriteRepo.findOne(id);
        return favorite;
    }
    
    @Override
    public Boolean ifFavorite(int idUser, String idPoi){
        Favorite favorite = favoriteRepo.findByIdUserOrIdPoi(idUser, idPoi);
        if(favorite == null){
            return false;
        }
        else{
            return true;
        }
    }
    
}
