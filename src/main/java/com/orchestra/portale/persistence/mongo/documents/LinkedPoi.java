/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

import com.orchestra.portale.utils.CouplePOI;
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class LinkedPoi {
    private String description;
    private ArrayList<CouplePOI> poilist;

    public String getDescription() {
        return description;
    }

   

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the poilist
     */
    public ArrayList<CouplePOI> getPoilist() {
        return poilist;
    }

    /**
     * @param poilist the poilist to set
     */
    public void setPoilist(ArrayList<CouplePOI> poilist) {
        this.poilist = poilist;
    }

    
            
}
