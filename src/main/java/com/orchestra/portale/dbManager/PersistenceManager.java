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
import com.orchestra.portale.persistence.sql.entities.Card;
import com.orchestra.portale.persistence.sql.entities.CardItinerary;
import com.orchestra.portale.persistence.sql.entities.Cart;
import com.orchestra.portale.persistence.sql.entities.CartItinerarydetail;
import com.orchestra.portale.persistence.sql.entities.DealerOffer;
import com.orchestra.portale.persistence.sql.entities.Favorite;
import com.orchestra.portale.persistence.sql.entities.Poi;
import com.orchestra.portale.persistence.sql.entities.Top10;
import com.orchestra.portale.persistence.sql.entities.User;
import com.orchestra.portale.utils.CoupleString;
import java.util.ArrayList;
import java.util.List;
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
    public Iterable<DeepeningPage> getDeepeningPagesById(Iterable<String> id);
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
    public Pages findPageBySlug(String slug);
    public void saveHome(Home home);
    public void saveDeepeningPage(DeepeningPage dp);
    public Iterable<DeepeningPage> findAllDeepeningPages();
    public DeepeningPage findDeepeningPage(String id);
    public DeepeningPage findDeepeningPageByName(String name);
    public Iterable<DeepeningPage> findDeepeningPagesByName(String name);
    public void saveFavorite(Favorite favorite);
    public void updateFavoriteRating(Integer rating, Integer id_user, String id_poi);
    public Iterable<Favorite> findFavoritesByIdUser(Integer idUser);
    public Favorite findFavorite(Integer id);
    public Integer ifFavorite(int idUser, String idPoi);
    public void deleteFavorite(int idUser, String idPoi);
    public void deleteDeepeningPage(DeepeningPage dp);
    public Iterable<CompletePOI> findCompletePoiByNameAndCategories(String name, String [] categories);
    public EnCompletePOI findEnCompletePoiById(String id);
    public void deleteEnCompletePOI(EnCompletePOI enpoi);
    public void saveCart(Cart cart);
    public List<DealerOffer>findOfferByIdPoi(String idPoi);
    public DealerOffer findOfferByIdOffer(int idOffer);
    public void saveCartDetail(CartItinerarydetail cart_detail);
    public void saveCardItinerary(CardItinerary card_itinerary);
    public Integer getIdItineraryByIdCard(int id_card);
    public Integer findStatusCardByIdUser(int id_user);    
    public Integer findActiveCardByIdUser(int idUser);
    public void deleteCart(int idUser);
    public void updateItemItinerary(int idItinerary, int id_user);  
    public Iterable<CartItinerarydetail> selectActiveOffer(int idItinerary, int idUser);
    public void activeCard(int id_user);
    public List<DealerOffer> findOfferByIdDealer(int idDealer);
    public void invalidateOffer(int idItinerary, int idOffer);
    public Iterable<Top10> selectTopPoi(String val);
}
