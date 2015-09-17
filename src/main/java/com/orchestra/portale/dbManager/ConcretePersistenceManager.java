/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.dbManager;

import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_En;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_It;
import com.orchestra.portale.persistence.mongo.documents.DeepeningPage;
import com.orchestra.portale.persistence.mongo.documents.Pages_En;
import com.orchestra.portale.persistence.mongo.documents.Home;
import com.orchestra.portale.persistence.mongo.documents.Pages_It;
import com.orchestra.portale.persistence.mongo.documents.Pages;
import com.orchestra.portale.persistence.mongo.repositories.DeepeningPageMongoRepository;
import com.orchestra.portale.persistence.mongo.repositories.PagesMongoRepository_En;
import com.orchestra.portale.persistence.mongo.repositories.HomeMongoRepository;
import com.orchestra.portale.persistence.mongo.repositories.PagesMongoRepository_It;
import com.orchestra.portale.persistence.mongo.repositories.PoiMongoRepository_En;
import com.orchestra.portale.persistence.mongo.repositories.PoiMongoRepository_It;
import com.orchestra.portale.persistence.sql.entities.Card;
import com.orchestra.portale.persistence.sql.entities.CardItinerary;
import com.orchestra.portale.persistence.sql.entities.Cart;
import com.orchestra.portale.persistence.sql.entities.CartItinerarydetail;
import com.orchestra.portale.persistence.sql.entities.DealerOffer;
import com.orchestra.portale.persistence.sql.entities.Favorite;
import com.orchestra.portale.persistence.sql.entities.Itinerary;
import com.orchestra.portale.persistence.sql.entities.ItineraryDetail;
import com.orchestra.portale.persistence.sql.entities.Ownership;
import com.orchestra.portale.persistence.sql.entities.Poi;
import com.orchestra.portale.persistence.sql.entities.Token;
import com.orchestra.portale.persistence.sql.entities.Top10;
import com.orchestra.portale.persistence.sql.entities.User;
import com.orchestra.portale.persistence.sql.entities.UserOfferChoice;
import com.orchestra.portale.persistence.sql.repositories.CardItineraryRepository;
import com.orchestra.portale.persistence.sql.repositories.CardRepository;
import com.orchestra.portale.persistence.sql.repositories.CartItinerarydetailRepository;
import com.orchestra.portale.persistence.sql.repositories.CartRepository;
import com.orchestra.portale.persistence.sql.repositories.CategoryRepository;
import com.orchestra.portale.persistence.sql.repositories.CompCategoryComponentRepository;
import com.orchestra.portale.persistence.sql.repositories.CompCatsRepository;
import com.orchestra.portale.persistence.sql.repositories.CompPoiCategoryRepository;
import com.orchestra.portale.persistence.sql.repositories.ComponentRepository;
import com.orchestra.portale.persistence.sql.repositories.DealerOfferRepository;
import com.orchestra.portale.persistence.sql.repositories.FavoriteRepository;
import com.orchestra.portale.persistence.sql.repositories.ItineraryDetailRepository;
import com.orchestra.portale.persistence.sql.repositories.ItineraryRepository;
import com.orchestra.portale.persistence.sql.repositories.OwnershipRepository;
import com.orchestra.portale.persistence.sql.repositories.PoiRepository;
import com.orchestra.portale.persistence.sql.repositories.TokenRepository;
import com.orchestra.portale.persistence.sql.repositories.Top10Repository;
//import com.orchestra.portale.persistence.sql.repositories.UserItineraryRepository;
import com.orchestra.portale.persistence.sql.repositories.UserOfferChoiceRepository;
import com.orchestra.portale.persistence.sql.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;

/**
 *
 * @author Marco Valentino
 */
public class ConcretePersistenceManager implements PersistenceManager {
    
    private String lang = "it";

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
    private PoiMongoRepository_It poiMongoRepo;
    
    @Autowired
    private PoiMongoRepository_En enpoiMongoRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PagesMongoRepository_It pagesRepo;
    
    @Autowired
    PagesMongoRepository_En enpagesRepo;
    
    @Autowired
    HomeMongoRepository homeRepo;

    @Autowired
    DeepeningPageMongoRepository deepRepo;

    @Autowired
    private FavoriteRepository favoriteRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private DealerOfferRepository dealerRepo;

    @Autowired
    private CartItinerarydetailRepository cartdetailRepo;

    @Autowired
    private CardItineraryRepository cardItineraryRepo;

    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private Top10Repository topRepo;
    
    @Autowired
    private CompCatsRepository compCats;
    
    @Autowired
    private OwnershipRepository ownRepo;
    
    @Autowired
    private ItineraryRepository itRepo;
    
    /*
    @Autowired
    private UserItineraryRepository uitRepo;
    */
    @Autowired
    private ItineraryDetailRepository itdRepo;
    
    @Autowired
    private UserOfferChoiceRepository ucRepo;
    
    @Autowired
    private TokenRepository tokenRepo;
    
    @Override
    public void setLang(String lang) {
        if (lang == null) {
            this.lang = "it";
        } else {
            this.lang = lang;
        }
    }

    @Override
    public Poi getPoiById(String Id) {
       return  null;
}

    @Override
    public Iterable<Poi> getAllPoi() {
        return null;

    }

    @Override
    public Iterable<? extends CompletePOI> getCompletePoisById(Iterable<String> id) {
        return poiMongoRepo.findAll(id);
        }
    

    @Override
    public Iterable<DeepeningPage> getDeepeningPagesById(Iterable<String> id) {
        return deepRepo.findAll(id);
    }

    @Override
    public CompletePOI getCompletePoiById(String id) {
        switch(this.lang) {
        case "it":
        return poiMongoRepo.findOne(id);
        case "en":
            return enpoiMongoRepo.findOne(id);
        default:
        return poiMongoRepo.findOne(id);
    }
    }
    @Override
    public Iterable<? extends CompletePOI> getAllCompletePoi() {
        switch(this.lang) {
        case "it":
        return mongoOps.findAll(CompletePOI_It.class);
        case "en" :
        return mongoOps.findAll(CompletePOI_En.class);
        default :
        return mongoOps.findAll(CompletePOI_It.class);  
    }
        
    }

    @Override
    public void savePoi(CompletePOI_It poi) {
        poiMongoRepo.save(poi);
    }
    
    @Override
    public void saveEnPoi(CompletePOI_En poi) {
        enpoiMongoRepo.save(poi);
    }

    @Override
    public Iterable<? extends CompletePOI> getCompletePoiByCategories(String[] categories) {
            switch(this.lang){
                case "it" :
        return mongoOps.find(new Query(where("categories").in(java.util.Arrays.asList(categories))), CompletePOI_It.class);
                case "en" :
        return mongoOps.find(new Query(where("categories").in(java.util.Arrays.asList(categories))), CompletePOI_En.class);            
                default :
       return mongoOps.find(new Query(where("categories").in(java.util.Arrays.asList(categories))), CompletePOI_It.class);          
    }
    }

    @Override
    public CompletePOI findOneCompletePoiByName(String name) {

        switch (this.lang) {
            
            case "it" :
        Iterable<? extends CompletePOI> poisit = mongoOps.find(new Query(where("name").regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))), CompletePOI_It.class);
        for (CompletePOI p : poisit) {
            if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                return p;
            }
        }
            case "en" :
                Iterable<? extends CompletePOI> pois = mongoOps.find(new Query(where("name").regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))), CompletePOI_En.class);
        for (CompletePOI p : pois) {
            if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                return p;
            }
        }
            default :
                Iterable<? extends CompletePOI> poisitd = mongoOps.find(new Query(where("name").regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))), CompletePOI_It.class);
        for (CompletePOI p : poisitd) {
            if (p.getName().toLowerCase().equals(name.toLowerCase())) {
                return p;
            }
        }
                
        return null;
    }
    }

    @Override
    public Iterable<? extends CompletePOI> findCompletePoiByNameAndCategories(String name, String[] categories) {
        switch(this.lang) {
            case "it" :
                    return mongoOps.find(new Query(where("categories").in(java.util.Arrays.asList(categories)).and("name").regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))), CompletePOI_It.class);
            case "en" :
                   return mongoOps.find(new Query(where("categories").in(java.util.Arrays.asList(categories)).and("name").regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))), CompletePOI_En.class);
            default :
                   return mongoOps.find(new Query(where("categories").in(java.util.Arrays.asList(categories)).and("name").regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))), CompletePOI_It.class);
        }
    }
    @Override
    public void deletePoi(CompletePOI_It poi) {
        poiMongoRepo.delete(poi);
    }
    
    @Override
    public void deleteEnPoi(CompletePOI_En poi) {
        enpoiMongoRepo.delete(poi);
    }

    @Override
    public Iterable<? extends CompletePOI> findCompletePoi(String name, String address, String category) {
            switch(this.lang) {
                
                case "it" :
        return mongoOps.find(new Query(where("categories").regex(Pattern.compile(category, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))
                .and("name").regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))
                .and("address").regex(Pattern.compile(address, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))),
                CompletePOI_It.class);
                case "en" :
        return mongoOps.find(new Query(where("categories").regex(Pattern.compile(category, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))
                .and("name").regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))
                .and("address").regex(Pattern.compile(address, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))),
                CompletePOI_En.class);
                default :
        return mongoOps.find(new Query(where("categories").regex(Pattern.compile(category, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))
                .and("name").regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))
                .and("address").regex(Pattern.compile(address, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))),
                CompletePOI_It.class);    
        /*
        return mongoOps.find(new Query(where("categories").regex(Pattern.compile(category, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))
                .and("name").regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))
                .and("address").regex(Pattern.compile(address, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))),
                CompletePOI.class);
        */
    }
    }
    @Override
    public GeoResults<? extends CompletePOI> findNearCompletePoi(String id, double radius) {
        
        switch(this.lang) {
            case "it":
        CompletePOI poi = getCompletePoiById(id);
        Point point = new Point(poi.getLocation()[0], poi.getLocation()[1]);
        NearQuery query = NearQuery.near(point).maxDistance(new Distance(radius, Metrics.KILOMETERS));

        return mongoOps.geoNear(query, CompletePOI_It.class);
            case "en" :
        CompletePOI poien = getCompletePoiById(id);
        Point pointen = new Point(poien.getLocation()[0], poien.getLocation()[1]);
        NearQuery queryen = NearQuery.near(pointen).maxDistance(new Distance(radius, Metrics.KILOMETERS));

        return mongoOps.geoNear(queryen, CompletePOI_En.class);
          
            default :
        CompletePOI poid = getCompletePoiById(id);
        Point pointd = new Point(poid.getLocation()[0], poid.getLocation()[1]);
        NearQuery queryd = NearQuery.near(pointd).maxDistance(new Distance(radius, Metrics.KILOMETERS));

        return mongoOps.geoNear(queryd, CompletePOI_It.class);
    }
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
    public void savePage(Pages_It page) {
        pagesRepo.save(page);
    }
    @Override
    public void saveEnPage(Pages_En page) {
        enpagesRepo.save(page);
    }

    @Override
    public Pages findPageById(String id) {
        Pages page = null;
        switch(this.lang) {
            case "it" :
                 page= pagesRepo.findOne(id);
                break;
            case "en" :
                page = enpagesRepo.findOne(id);
                break;
        }
        return page;
    }

    @Override
    public Pages findPageBySlug(String slug) {
        switch(this.lang) {
            case "it":
                return mongoOps.findOne(new Query(where("slug").is(slug)), Pages_It.class);
                
            case "en":
                return mongoOps.findOne(new Query(where("slug").is(slug)), Pages_En.class);
                
            default:
                return mongoOps.findOne(new Query(where("slug").is(slug)), Pages_It.class);
    }
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
    public void saveCart(Cart cart) {
        cartRepo.save(cart);

    }

    @Override
    public void saveFavorite(Favorite favorite) {
        favoriteRepo.save(favorite);
    }

    @Override
    public void updateFavoriteRating(Integer rating, Integer id_user, String id_poi) {
        favoriteRepo.updateFavoriteRating(rating, id_user, id_poi);
    }

    @Override
    public Iterable<Favorite> findFavoritesByIdUser(Integer idUser) {
        Iterable<Favorite> favorites = favoriteRepo.findByIdUser(idUser);
        return favorites;
    }

    @Override
    public Favorite findFavorite(Integer id) {
        Favorite favorite = favoriteRepo.findOne(id);
        return favorite;
    }

    @Override
    public Integer ifFavorite(int idUser, String idPoi) {
        Favorite favorite = favoriteRepo.findByIdUserAndIdPoi(idUser, idPoi);
        if (favorite == null) {
            return 0;
        } else {
            return favorite.getRating();
        }
    }

    @Override
    public void deleteFavorite(int idUser, String idPoi) {
        favoriteRepo.deleteFavorite(idUser, idPoi);

    }

    @Override
    public Iterable<DeepeningPage> findDeepeningPagesByName(String name) {

        return mongoOps.find(new Query(where("name").regex(Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))), DeepeningPage.class);
    }

    @Override
    public Iterable<DeepeningPage> findAllDeepeningPages() {
        return deepRepo.findAll();
    }

    @Override
    public void deleteDeepeningPage(DeepeningPage dp) {
        deepRepo.delete(dp);
    }

    @Override
    public List<DealerOffer> findOfferByIdPoi(String idPoi) {
        List<DealerOffer> offers = dealerRepo.findOfferByIdPoi(idPoi);
        return offers;
    }

    @Override
    public DealerOffer findOfferByIdOffer(int idOffer) {
        DealerOffer offer = dealerRepo.findOfferByIdOffer(idOffer);
        return offer;
    }

    @Override
    public List<DealerOffer> findOfferByIdDealer(String idDealer) {
        List<DealerOffer> off = dealerRepo.findOfferByIdDealer(idDealer);
        return off;
    }

    @Override
    public void saveCartDetail(CartItinerarydetail cart_detail) {
        cartdetailRepo.save(cart_detail);
    }

    @Override
    public void saveCardItinerary(CardItinerary card_itinerary) {
        cardItineraryRepo.save(card_itinerary);
    }

    @Override
    public Integer getIdItineraryByIdCard(int id_card) {
        Integer id_iti = cardItineraryRepo.getIdItineraryByIdCard(id_card);
        return id_iti;
    }

    @Override
    public Integer findStatusCardByIdUser(int id_user) {
        Integer status = cardRepo.findStatusCardByIdUser(id_user);
        return status;
    }

    @Override
    public Integer findActiveCardByIdUser(int idUser) {
        Integer card_key = cardRepo.findActiveCardByIdUser(idUser);
        return card_key;
    }

    @Override
    public void deleteCart(int idUser) {
        cartRepo.deleteCart(idUser);
    }

    @Override
    public void updateItemItinerary(int idItinerary, int id_user) {
        cartdetailRepo.updateItemItinerary(idItinerary, id_user);
    }

    @Override
    public Iterable<CartItinerarydetail> selectActiveOffer(int idItinerary, int idUser) {
        Iterable<CartItinerarydetail> offerts = cartdetailRepo.selectActiveOffer(idItinerary, idUser);
        return offerts;
    }

    @Override
    public void activeCard(int id_user) {
        cardRepo.activeCard(id_user);
    }

    @Override
    public void invalidateOffer(int idItinerary, int idOffer) {
        cartdetailRepo.invalidateOffer(idItinerary, idOffer);
    }

    @Override
    public Iterable<Top10> selectTopPoi(String val) {
        return topRepo.findByTipo(val);

    }

    @Override
    public Iterable<? extends CompletePOI> getAll() {
        return poiMongoRepo.findAll();
    }

    @Override
    public Iterable<CartItinerarydetail> findCartItineraryByIdUser(int idUser) {
        Iterable<CartItinerarydetail> c = cartdetailRepo.findByIdUser(idUser);
        return c;
    }

    @Override
    public void updateQuantity(int qta, float tot, int id_user, int idOffer) {
        cartdetailRepo.updateQta(qta, tot, id_user, idOffer);
    }

    @Override
    public void UpdateOfferStockByType(int qta, float tot, String type, String idPoi, int idUser) {
        cartdetailRepo.updateQtaByType(qta, tot, type, idPoi, idUser);

    }
    @Override
    public Iterable<Object[]> getMostFavorites() {
        return favoriteRepo.getMostFavorites();
    }
    
    
    @Override
    public void saveCard(Card c){
        cardRepo.save(c);
    }    
/*
    @Override
    public void deleteOfferCard(Integer id_offer, Integer id_user) {
        cartdetailRepo.deleteOfferCard(id_offer, id_user);
    }
*/
    @Override
    public void deleteOfferStock(Integer id_user, String idPoi, String type) {
        cartdetailRepo.deleteOfferStock(id_user, idPoi, type);
    }
    @Override
    public ArrayList<Object[]> getCompByCat(String cat) {
       return compCats.getComponentsByCategory(cat);
    }
    
    
    @Override
    public Iterable<? extends CompletePOI> getPoiByOwner(Integer id_user){
        Iterable<String> idPois = ownRepo.getIdPoiByOwner(id_user);
        
        Iterable<? extends CompletePOI> pois = this.getCompletePoisById(idPois);
        
        return pois;
    }
    
    @Override
    public Boolean ifOwner(Integer id_user, String id_poi){
        Iterable<String> idPois = ownRepo.getIdPoiByOwner(id_user);
        
        return idPois.iterator().hasNext();
        
    }
    
    @Override
    public void saveOwnership(Ownership o){
        ownRepo.save(o);
    }

    @Override
    public void saveItinerary(Itinerary it) {
        itRepo.save(it);
    }
/*
    @Override
    public void saveUserItinerary(UserItinerary uit) {
        uitRepo.save(uit);
    }
    */

    @Override
    public void savePoiItinerary(ItineraryDetail id) {
        itdRepo.save(id);
    }

    @Override
    public void saveUserChoice(UserOfferChoice uc) {
        ucRepo.save(uc);
    }

    @Override
    public Integer findItDetail(int idItinerary, String idPoi) {
        return itdRepo.findItDetailByIdItineraryAndIdPoi(idItinerary,idPoi);
    }

    @Override
    public void deleteOfferCard(Integer idOffer, Integer idItineraryDetail) {
        ucRepo.deleteOfferCard(idOffer, idItineraryDetail);
    }

    @Override
    public Integer findIdDetailByidOffer(Integer idOffer) {
       return ucRepo.findIdDetailByidOffer(idOffer);
    }

    @Override
    public Integer findItineraryByIdItineraryDetail(int idItineraryDetail) {
      return itdRepo.findItineraryByIdItineraryDetail(idItineraryDetail);
    }

    @Override
    public Long findUserByIdItinerary(Integer idItinerary) {
       return itRepo.findUserByIdItinerary(idItinerary);
    }

    @Override
    public Iterable<Itinerary> findItinerariesByIdUser(int idUser) {
        Iterable<Itinerary> itineraries = itRepo.findByIdUser(idUser);
        return itineraries;
    }

    @Override
    public Iterable<String>findPoisByItinerary(int idItinerary) {
        return itdRepo.findPoiByIdItinerary(idItinerary);
    }

    @Override
    public Integer retreiveUserChoiceCard(int idOffer, int idItinerary) {
        return ucRepo.retreiveUserChoiceCard(idOffer,idItinerary);
    }

    @Override
    public void updateUserChoiceCard(int qta, float tot, int idOffer) {
        ucRepo.updateUserChoiceCard(qta,tot,idOffer);
    }

    @Override
    public Integer retreiveUserChoiceStock(String type, int idItinerary) {
        return ucRepo.retreiveUserChoiceStock(type, idItinerary);
    }

    @Override
    public void updateUserChoiceStock(int qta, float tot, String type) {
        ucRepo.updateUserChoiceStock(qta,tot,type);
    }

    @Override
    public void deleteOfferStock(String name, Integer idItineraryDetail) {
        ucRepo.deleteOfferStock(name,idItineraryDetail);
    }

    @Override
    public Iterable<UserOfferChoice> findChoiceCardByUser(Integer iddetail) {
        return ucRepo.findChoiceCardByIdItineraryDetail(iddetail);
    }
    
    @Override
    public Iterable<UserOfferChoice> findChoiceStockByUser(Integer iddetail) {
        return ucRepo.findTypeStockByIdItineraryDetail(iddetail);
    }

    @Override
    public void deleteItinerary(Integer idItinerary,int idUser) {
        itRepo.deleteItinerary(idItinerary,idUser);
    }

    @Override
    public int countOfferCard(String idPoi) {
        return dealerRepo.countOfferByIdPoi(idPoi);
    }

    @Override
    public void completeItinerary(Integer idItinerary, int idUser) {
        itRepo.completeItinerary(idItinerary,idUser);
    }

    @Override
    public int findStatusByIdItinerary(int idItinerary, int idUser) {
        return itRepo.findStatusByIdItinerary(idItinerary,idUser);
    }

    @Override
    public Iterable<Integer> findIdDetailByIdItinerary(int idItinerary) {
        return itdRepo.findIdItineraryDetailByIdItinerary(idItinerary);
    }

    @Override
    public List<UserOfferChoice> findByIdItineraryDetail(Integer idItineraryDetail) {
        return ucRepo.findByIdItineraryDetail(idItineraryDetail);
    }

    @Override
    public Iterable<Integer> findIdOfferByIdItineraryDetail(Integer idItineraryDetail) {
        return ucRepo.findIdOfferByIdItineraryDetail(idItineraryDetail);
    }

    @Override
    public DealerOffer findDealerOfferByidOffer(Integer idOffer) {
        return dealerRepo.findByidOffer(idOffer);
    }

    @Override
    public int findIdItineraryDetailByIdItineraryAndIdPoi(int idItinerary, String idPoi) {
        return itdRepo.findIdItineraryDetailByIdItineraryAndIdPoi(idItinerary,idPoi);
    }

    @Override
    public void deletePoiItinerary(String idPoi, int idItinerary) {
        itdRepo.detetePoi(idPoi,idItinerary);
    }

    @Override
    public void deleteOffersPoi(Integer idItineraryDetail) {
        ucRepo.deleteOffersPoi(idItineraryDetail);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public void saveToken(Token t) {
        tokenRepo.save(t);
    }

    @Override
    public Token getTokenByToken(String token) {
        return tokenRepo.findByToken(token);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Token getTokenByIdUser(Integer id_user) {
        return tokenRepo.findByIdUser(id_user);
    }

    @Override
    public void updateToken(Integer id, String token, Date validity) {
        tokenRepo.updateToken(id, token, validity);
    }

    @Override
    public List<String> getDealerOwnPoi(int idDealer) {
      return dealerRepo.findByIdDealer(idDealer);  
    }

    @Override
    public Integer getUserItinerary(String keyString) {
      return itRepo.findByKeyString(keyString);  
    }

    @Override
    public Iterable<Integer> findIdDetailByIdItineraryByDealer(Integer idItinerary) {
        return 
    }

}
