
package com.orchestra.portale.utils;

import com.orchestra.portale.persistence.mongo.documents.CompletePOI;

/**
 *
 * @author andrea
 */
public class MapPoiCat {
    private String main_category;
    private Iterable<CompletePOI> poi;

    /**
     * @return the main_category
     */
    public String getMain_category() {
        return main_category;
    }

    /**
     * @param main_category the main_category to set
     */
    public void setMain_category(String main_category) {
        this.main_category = main_category;
    }

    /**
     * @return the poi
     */
    public Iterable<CompletePOI> getPoi() {
        return poi;
    }

    /**
     * @param poi the poi to set
     */
    public void setPoi(Iterable<CompletePOI> poi) {
        this.poi = poi;
    }
    
    
}
