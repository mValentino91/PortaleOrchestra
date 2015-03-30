/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class LinkedPoi {
    private String description;
    private ArrayList<String> idlist;

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getIdlist() {
        return idlist;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdlist(ArrayList<String> idlist) {
        this.idlist = idlist;
    }
    
            
}
