/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

/**
 *
 * @author Alex
 */
public class GenericSocial {
      private String label;
    private String social;

    public String getLabel() {
        return label;
    }

    public String getEmail() {
        return social;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setEmail(String social) {
        this.social = social;
    }
}
