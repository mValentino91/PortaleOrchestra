package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.TicketPrice;
import com.orchestra.portale.persistence.sql.entities.Cart;
import com.orchestra.portale.persistence.sql.entities.DealerOffer;
import com.orchestra.portale.persistence.sql.entities.Favorite;
import com.orchestra.portale.persistence.sql.entities.User;
import com.orchestra.portale.utils.MapPoiCat;
import com.orchestra.portale.utils.MapPoiOff;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Marco Valentino
 */
@Controller
@Secured("ROLE_USER")
public class CartController {
    @Autowired
    PersistenceManager pm ;
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView cart() {

        /*
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         User user= pm.findUserByUsername(auth.getName());
         String id_user = user.getId().toString();
    
        
         Iterable <Cart> carts = pm.findCartsByIdUser(Integer.parseInt(id_user));
         ArrayList<String> idlist = new ArrayList<String>();        
         Iterable<CompletePOI> poilist = new ArrayList<CompletePOI>();
         Map<String,String>cat_slug_ita = new HashMap<String,String>();
        
         cat_slug_ita.put("culture","Cultura");
         cat_slug_ita.put("accomodation","Pernottamento");
         cat_slug_ita.put("food","Enogastronomia");
         cat_slug_ita.put("craft","Artigiani");
         cat_slug_ita.put("mobility","Mobilità");
         cat_slug_ita.put("event","Eventi");
         cat_slug_ita.put("cultural_association","Associazioni Culturali");
         cat_slug_ita.put("expo","Expo");
        
         */
        ModelAndView model = new ModelAndView("cart");
        /*
         MapPoiCat map_cat = new MapPoiCat();
        
        
         for (Cart c : carts ) {
         idlist.add(c.getIdPoi());
            
         }
        
         poilist = pm.getCompletePoisById(idlist);
        
         List<String> main_category = new ArrayList<String>();
         main_category.add("culture");
         main_category.add("accomodation");
         main_category.add("food");
         main_category.add("craft");
         main_category.add("mobility");
         main_category.add("event");
         main_category.add("cultural_association");
         main_category.add("expo");
        
       
        
        
         for(CompletePOI cp : poilist){
         List<String>poi_category = cp.getCategories();
         String cat_map = MapPoiCat.checkCategory(main_category,poi_category);
            
         System.out.println("entrato");
         map_cat.insertPoi(cat_map, cp);
         }
        
        
        
         model.addObject("map_slug",cat_slug_ita);
         model.addObject("map_cat", map_cat);
         */
        return model;
    }

    @RequestMapping(value = "/saveInCart")
    public ModelAndView saveInCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();

        ModelAndView model = new ModelAndView("cart");
        String idpoi = null;
        CompletePOI poi;
        List<DealerOffer> list_off = new ArrayList<DealerOffer>();

        MapPoiOff map = new MapPoiOff();
        Map<String, List<DealerOffer>> map_poi_off = new HashMap<String, List<DealerOffer>>();
        Map<String, CompletePOI> map_poi = new HashMap<String, CompletePOI>();
        Map<String, List<TicketPrice>> map_poi_stockPrice = new HashMap<String, List<TicketPrice>>();
        Map<String, AbstractPoiComponent> map_comp = new HashMap<String, AbstractPoiComponent>();
        //Map<String,String> map_comp2 = new HashMap<String,String>();

        Map<CompletePOI, List<DealerOffer>> detailOffer = new HashMap<CompletePOI, List<DealerOffer>>();
        Cart cart = new Cart();

        Iterable<Favorite> favorites = pm.findFavoritesByIdUser(Integer.parseInt(id_user));

        for (Favorite f : favorites) {
            cart.setIdUser(Integer.parseInt(id_user));
            cart.setIdPoi(f.getIdPoi());
            idpoi = f.getIdPoi(); //stringa del poi
            poi = pm.getCompletePoiById(idpoi); //CompletePOI

            for (AbstractPoiComponent comp : poi.getComponents()) {
                String slug = comp.slug();
                int index = slug.lastIndexOf(".");
                Class c;
                String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
                if (cname.equals("prices")) {
                    try {
                        c = Class.forName(slug);
                        //map_comp2.put(idpoi, "cacca");
                        map_comp.put(idpoi, comp);

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

            list_off = pm.findOfferByIdPoi(idpoi); //List<DealerOffer>

            map_poi_off.put(idpoi, list_off);
            map_poi.put(idpoi, poi);
            map.setMap_off(map_poi_off);
            map.setMap_poi(map_poi);
            //map.setMap_poi_stockPrice(map_comp);

            pm.saveCart(cart);
        }

        model.addObject("id_user", id_user);
        model.addObject("map", map);
        model.addObject("map_comp", map_comp);
        //model.addObject("map_comp2",map_comp2);
        return model;
    }

}
