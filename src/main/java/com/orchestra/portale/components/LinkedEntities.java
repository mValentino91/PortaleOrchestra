/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.components;

import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class LinkedEntities {
    private String text;
    private ArrayList<LinkedEntity> entities;

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the entities
     */
    public ArrayList<LinkedEntity> getEntities() {
        return entities;
    }

    /**
     * @param entities the entities to set
     */
    public void setEntities(ArrayList<LinkedEntity> entities) {
        this.entities = entities;
    }
    
    
}
