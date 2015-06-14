package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.CartItinerarydetail;
import com.orchestra.portale.persistence.sql.entities.DealerOffer;
import com.orchestra.portale.persistence.sql.entities.User;
import com.orchestra.portale.persistence.sql.repositories.DealerOfferRepository;
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


@Controller
@Secured("ROLE_USER")
public class CartItinerarydetailController {
    
    @Autowired
    PersistenceManager pm ;
    @RequestMapping(value = "/saveOffer", method = RequestMethod.GET)
    public @ResponseBody
    String saveOffer(@RequestParam String id_offer, @RequestParam String id_poi, @RequestParam String qta, @RequestParam String sum, @RequestParam String type) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        CartItinerarydetail cart_detail = new CartItinerarydetail();
        Float total = Float.parseFloat(sum) * Integer.parseInt(qta);
        
        //DealerOffer offer = pm.findOfferByIdOffer(Integer.parseInt(id_offer));
        Iterable<CartItinerarydetail> c = pm.findCartItineraryByIdUser(Integer.parseInt(id_user));
        
        
        /*
        for(CartItinerarydetail ci : c){
           
           
            
            
            if(type.equals("CARD")){
                System.out.println("ENTATOOO");
                //offerte nn stock
                if(Integer.parseInt(id_offer) == ci.getIdOffer()){
                    pm.updateQuantity(Integer.parseInt(qta), Integer.parseInt(id_user), Integer.parseInt(id_offer));
                    return "ok";
                }
                
            }
            else{
               //offerte stock 
                if(type.equals(ci.getTipoStock())){
                    pm.UpdateOfferStockByType(Integer.parseInt(qta),type,id_poi);
                    return "ok";
                
                }
            }
        }
        if(type.equals("CARD")){
            cart_detail.setIdPoi(id_poi);
            cart_detail.setIdUser(Integer.parseInt(id_user));
            cart_detail.setIdOffer(Integer.parseInt(id_offer));
            cart_detail.setQta(Integer.parseInt(qta));
            cart_detail.setSum(Float.parseFloat(sum));
            cart_detail.setStatus(1);
            cart_detail.setTipoStock("CARD");
            pm.saveCartDetail(cart_detail);

            return "ok";
            
        }
        else{
            cart_detail.setIdPoi(id_poi);
            cart_detail.setIdUser(Integer.parseInt(id_user));
            cart_detail.setQta(Integer.parseInt(qta));
            cart_detail.setSum(Float.parseFloat(sum));
            cart_detail.setStatus(1);
            cart_detail.setTipoStock(type);
            pm.saveCartDetail(cart_detail);
            return "ok";
        }
        
        
        */
        
        
        
        if(type.equals("CARD")){
            //offerte nn stock
            for(CartItinerarydetail ci : c){
                System.out.println("CIAOOOOO33");
                
                if(ci.getIdOffer()!=null){
                    if(Integer.parseInt(id_offer) == ci.getIdOffer()){

                        pm.updateQuantity(Integer.parseInt(qta), total,Integer.parseInt(id_user), Integer.parseInt(id_offer));
                        return "ok";
                    }
                }
            }
            cart_detail.setIdPoi(id_poi);
            cart_detail.setIdUser(Integer.parseInt(id_user));
            cart_detail.setIdOffer(Integer.parseInt(id_offer));
            cart_detail.setQta(Integer.parseInt(qta));
            cart_detail.setSum(total);
            cart_detail.setStatus(1);
            cart_detail.setTipoStock("CARD");
            pm.saveCartDetail(cart_detail);

            return "ok";
        }
        else{
            //offerte stock
            for(CartItinerarydetail ci : c){
                if(type.equals(ci.getTipoStock())){
                    pm.UpdateOfferStockByType(Integer.parseInt(qta),total,type,id_poi,Integer.parseInt(id_user));
                    return "ok";
                
                }
            }
            cart_detail.setIdPoi(id_poi);
            cart_detail.setIdUser(Integer.parseInt(id_user));
            cart_detail.setQta(Integer.parseInt(qta));
            cart_detail.setSum(total);
            cart_detail.setStatus(1);
            cart_detail.setTipoStock(type);
            pm.saveCartDetail(cart_detail);
            return "ok";
                
            
            
        }
        
       
        
    }
    
    @RequestMapping(value = "/deleteOffer", method = RequestMethod.GET)
    public @ResponseBody
    String deleteOffer(@RequestParam String id_offer, @RequestParam String id_poi, @RequestParam String type) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        
        CartItinerarydetail cart_detail = new CartItinerarydetail();
        Iterable<CartItinerarydetail> c = pm.findCartItineraryByIdUser(Integer.parseInt(id_user));
        
        if(c.iterator().hasNext()){
            if(type.equals("CARD")){
                //offerte nn stock
                for(CartItinerarydetail ci : c){
                    if(ci.getIdOffer() == Integer.parseInt(id_offer)){
                        pm.deleteOfferCard(Integer.parseInt(id_offer), Integer.parseInt(id_user));
                        return "Offerta rimossa";
                        //cancella row
                    }
                }
            }
            else{
                for(CartItinerarydetail ci : c){
                    if(ci.getIdPoi().equals(id_poi) && ci.getTipoStock().equals(type)){
                        //cancella row
                        pm.deleteOfferStock(Integer.parseInt(id_user), id_poi, type);
                        return "Offerta rimossa";
                    }
                }
                
            }
            
        }
        else
            return "Offerta non presente";
        
        
        
        return "ok";
    }
}
