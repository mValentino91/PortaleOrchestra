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
 * @author Alex
 */
@TypeAlias("com.orchestra.portale.persistence.mongo.documents.LinkedPoiComponent")
public class LinkedPoiComponent extends AbstractPoiComponent {
    private ArrayList<LinkedPoi> linked;

    public ArrayList<LinkedPoi> getLinked() {
        return linked;
    }

    public void setLinked(ArrayList<LinkedPoi> linked) {
        this.linked = linked;
    }
    
}
