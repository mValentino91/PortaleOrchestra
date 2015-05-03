/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

import java.util.ArrayList;

/**
 *
 * @author antonio
 */
public class PageColorSchema {
    private Integer id;
    private ArrayList<String> tileColors;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the tileColors
     */
    public ArrayList<String> getTileColors() {
        return tileColors;
    }

    /**
     * @param tileColors the tileColors to set
     */
    public void setTileColors(ArrayList<String> tileColors) {
        this.tileColors = tileColors;
    }
    
}
