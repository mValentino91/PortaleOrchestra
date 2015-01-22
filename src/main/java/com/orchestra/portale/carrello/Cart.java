/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.orchestra.portale.carrello;

import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import java.util.ArrayList;

/**
 *
 * @author Marco Valentino
 */
public class Cart {
    
    private ArrayList<CompletePOI> poiList;
    
    public Cart(){
        poiList= new ArrayList<CompletePOI>();
    }

    /**
     * @return the poiList
     */
    public ArrayList<CompletePOI> getPoiList() {
        return poiList;
    }

    /**
     * @param poiList the poiList to set
     */
    public void setPoiList(ArrayList<CompletePOI> poiList) {
        this.poiList = poiList;
    }
    
    public void addPoi(CompletePOI poi){
        poiList.add(poi);
    }
    
    public void deletePoi(CompletePOI poi){
        poiList.remove(poi);
    }
    
}
