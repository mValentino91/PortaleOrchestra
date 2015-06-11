/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

import java.util.ArrayList;
import org.springframework.data.annotation.TypeAlias;

/**
 *
 * @author antonio
 */
@TypeAlias("com.orchestra.portale.persistence.mongo.documents.TileSliderComponent")
public class TileSliderComponent extends AbstractPoiComponent{
    private ArrayList<TileSlider> tileList;

    /**
     * @return the tileList
     */
    public ArrayList<TileSlider> getTileList() {
        return tileList;
    }

    /**
     * @param tileList the tileList to set
     */
    public void setTileList(ArrayList<TileSlider> tileList) {
        this.tileList = tileList;
    }
}
