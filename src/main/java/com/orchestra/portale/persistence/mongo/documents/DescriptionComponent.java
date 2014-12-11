/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

import org.springframework.data.annotation.TypeAlias;
import java.util.*;

/**
 *
 * @author Marco Valentino
 */
@TypeAlias("com.orchestra.portale.persistence.mongo.documents.DescriptionComponent")
public class DescriptionComponent extends AbstractPoiComponent {
    
    private ArrayList<Section> sectionsList;
    /**
     * @return the sectionsList
     */
    public ArrayList<Section> getSectionsList() {
        return sectionsList;
    }

    /**
     * @param sectionsList the sectionsList to set
     */
    public void setSectionsList(ArrayList<Section> sectionsList) {
        this.sectionsList = sectionsList;
    }
}
