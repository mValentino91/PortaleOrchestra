/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.PricesComponent;
import com.orchestra.portale.persistence.mongo.documents.TicketPrice;
import com.orchestra.portale.persistence.sql.entities.Card;
import com.orchestra.portale.persistence.sql.entities.CardItinerary;
import com.orchestra.portale.persistence.sql.entities.CartItinerarydetail;
import com.orchestra.portale.persistence.sql.entities.DealerOffer;
import com.orchestra.portale.persistence.sql.entities.Favorite;
import com.orchestra.portale.persistence.sql.entities.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NamedQuery;
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
 * @author andrea
 */
@Controller
@Secured("ROLE_USER")
public class CardItineraryController {

    @Autowired
    PersistenceManager pm;

    @RequestMapping(value = "/saveInCard", method = RequestMethod.GET)
    public ModelAndView saveInCard() {
        ModelAndView model = new ModelAndView("cardDetail");
        ModelAndView error = new ModelAndView("error");
        String error_info = "Nessuna card attiva";

        List<String> offerte_id = new ArrayList<String>();
        ArrayList<String> poi_id = new ArrayList<String>();
        List<DealerOffer> offerte = new ArrayList<DealerOffer>();
        Map<String, CompletePOI> map_poi = new HashMap<String, CompletePOI>();
        Map<String, List<DealerOffer>> map_off = new HashMap<String, List<DealerOffer>>();
        //Map<String, AbstractPoiComponent> map_comp = new HashMap<String, AbstractPoiComponent>();
        Map<String, List<Map<String, String>>> map_comp = new HashMap<String, List<Map<String, String>>>();

        Map<String, List<CartItinerarydetail>> m2 = new HashMap<String, List<CartItinerarydetail>>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = pm.findUserByUsername(auth.getName());
        String id_user = user.getId().toString();
        Integer idcard = pm.findActiveCardByIdUser(Integer.parseInt(id_user));
        if (idcard != null) {
            Integer status = pm.findStatusCardByIdUser(Integer.parseInt(id_user));

            if (status == 0) {
                //card creata ma nn attiva - creo nuovo itinerario ed attivo la card

                CardItinerary ci = new CardItinerary();
                ci.setIdCard(idcard);
                pm.saveCardItinerary(ci);

                int id_iti = pm.getIdItineraryByIdCard(idcard);
                pm.updateItemItinerary(id_iti, Integer.parseInt(id_user));
                pm.activeCard(Integer.parseInt(id_user));
            }
            
            status = pm.findStatusCardByIdUser(Integer.parseInt(id_user));
            if (status == 1) {

                //puo essere definito un controller separato per acccedere sempre al riepilogo
                int id_iti = pm.getIdItineraryByIdCard(idcard);
                Iterable<CartItinerarydetail> offer_row = pm.selectActiveOffer(id_iti, Integer.parseInt(id_user));

                if (offer_row.iterator().hasNext()) {
                    //se off_row è zero allora devo caricare i poi dai preferiti

                    //recupero gli id delle offerte
                    for (CartItinerarydetail off : offer_row) {
                        if (!offerte_id.contains(off.getIdPoi())) {
                            offerte_id.add(off.getIdPoi());
                        }

                        CompletePOI poi = pm.getCompletePoiById(off.getIdPoi());
                        map_poi.put(off.getIdPoi(), poi);

                        if (off.getTipoStock().equals("CARD")) {
                            System.out.println("ENTRO CARD");
                            //offerte nn stock
                            List<DealerOffer> x = map_off.get(off.getIdPoi());

                            if (x == null) {
                                List<DealerOffer> L1 = new ArrayList<DealerOffer>();
                                DealerOffer id_off = pm.findOfferByIdOffer(off.getIdOffer());
                                System.out.println(id_off.getDesc());
                                L1.add(id_off);
                                map_off.put(off.getIdPoi(), L1);
                            } else {
                                x.add(pm.findOfferByIdOffer(off.getIdOffer()));
                            }
                        } else {
                            //offerte stock
                            System.out.println("ENTRO STOCK");
                            List<Map<String, String>> x = map_comp.get(off.getIdPoi());

                            if (x == null) {
                                x = new ArrayList<Map<String, String>>();
                                map_comp.put(off.getIdPoi(), x);
                            }

                            PricesComponent tp = null;
                            for (AbstractPoiComponent comp : poi.getComponents()) {
                                String slug = comp.slug();
                                int index = slug.lastIndexOf(".");
                                Class c;
                                String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
                                if (cname.equals("prices")) {
                                    try {
                                        c = Class.forName(slug);
                                        tp = (PricesComponent) comp;

                                    } catch (ClassNotFoundException ex) {
                                        Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                            if (tp != null) {
                                for (TicketPrice p : tp.getPrices()) {
                                    if (p.getType().equals(off.getTipoStock())) {
                                        Map<String, String> mo = new HashMap<String, String>();
                                        mo.put("type", p.getType());
                                        mo.put("desc", p.getType_description());
                                        Double pr = p.getPrice();
                                        mo.put("price", pr.toString());

                                        x.add(mo);
                                    }
                                }

                            }

                        }

                    }
                    model.addObject("idcard", idcard);
                    model.addObject("id_iti", id_iti);
                    model.addObject("user", user);
                    model.addObject("offerte_id", offerte_id);

                    if (map_poi.size() > 0) {
                        model.addObject("map_poi", map_poi);
                    }

                    if (map_off.size() > 0) {
                        model.addObject("map_off", map_off);
                    }

                    if (map_comp.size() > 0) {
                        model.addObject("map_comp", map_comp);
                    }
                    /*model.addObject("elenco_poi",elenco_poi);
                     model.addObject("offerte",offerte);
                     */
                } else {
                    //non ho ancora inserito offerte quindi li recupero dai favoriti
                    Iterable<Favorite> favorites = pm.findFavoritesByIdUser(Integer.parseInt(id_user));
                    CartItinerarydetail cart_detail = new CartItinerarydetail();
                    if (favorites.iterator().hasNext()) {
                        for (Favorite f : favorites) {
                            cart_detail.setIdCard(idcard);
                            cart_detail.setIdPoi(f.getIdPoi());
                            cart_detail.setIdUser(Integer.parseInt(id_user));
                            cart_detail.setIdItinerary(id_iti);
                            pm.saveCartDetail(cart_detail);

                            offerte_id.add(f.getIdPoi());
                            CompletePOI poi = pm.getCompletePoiById(f.getIdPoi());
                            map_poi.put(f.getIdPoi(), poi);

                        }

                        model.addObject("idcard", idcard);
                        model.addObject("id_iti", id_iti);
                        model.addObject("user", user);
                        model.addObject("offerte_id", offerte_id);
                        

                        if (map_poi.size() > 0) {
                            model.addObject("map_poi", map_poi);
                        }
                        return model;
                    } else {
                        String err_fav = "Errore";
                        error.addObject("err", err_fav);
                        return error;
                    }
                }

            }
            else{
                //status card 2
                String card_expires = "La tua card non è più attiva";
                error.addObject("err",card_expires);
            }

        } else {
            //card non esistente
            String error_card="Si è verificato un errore";
            error.addObject("err", error_card);
            return error;
        }
        return model;

    }
}
