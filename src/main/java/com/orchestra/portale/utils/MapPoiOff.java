/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.utils;

import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.TicketPrice;
import com.orchestra.portale.persistence.sql.entities.DealerOffer;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andrea
 */
public class MapPoiOff {
    private Map<String,List<DealerOffer>> map_off;
    private Map<String,CompletePOI> map_poi;
    private Map<String,AbstractPoiComponent> map_poi_stockPrice;

    /**
     * @return the map_off
     */
    public Map<String,List<DealerOffer>> getMap_off() {
        return map_off;
    }

    /**
     * @param map_off the map_off to set
     */
    public void setMap_off(Map<String,List<DealerOffer>> map_off) {
        this.map_off = map_off;
    }

    /**
     * @return the map_poi
     */
    public Map<String,CompletePOI> getMap_poi() {
        return map_poi;
    }

    /**
     * @param map_poi the map_poi to set
     */
    public void setMap_poi(Map<String,CompletePOI> map_poi) {
        this.map_poi = map_poi;
    }

    /**
     * @return the map_poi_stockPrice
     */
    public Map<String,AbstractPoiComponent> getMap_poi_stockPrice() {
        return map_poi_stockPrice;
    }

    /**
     * @param map_poi_stockPrice the map_poi_stockPrice to set
     */
    public void setMap_poi_stockPrice(Map<String,AbstractPoiComponent> map_poi_stockPrice) {
        this.map_poi_stockPrice = map_poi_stockPrice;
    }

    
    
}
