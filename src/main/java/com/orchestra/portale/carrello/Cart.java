/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.orchestra.portale.carrello;

import com.orchestra.portale.persistence.mongo.documents.CompletePOI_It;
import java.util.ArrayList;

/**
 *
 * @author Marco Valentino
 */
public class Cart {
    
    private ArrayList<CompletePOI_It> poiList;
    
    public Cart(){
        poiList= new ArrayList<CompletePOI_It>();
    }

    /**
     * @return the poiList
     */
    public ArrayList<CompletePOI_It> getPoiList() {
        return poiList;
    }

    /**
     * @param poiList the poiList to set
     */
    public void setPoiList(ArrayList<CompletePOI_It> poiList) {
        this.poiList = poiList;
    }
    
    public void addPoi(CompletePOI_It poi){
        poiList.add(poi);
    }
    
    public void deletePoi(CompletePOI_It poi){
        poiList.remove(poi);
    }
    
}
