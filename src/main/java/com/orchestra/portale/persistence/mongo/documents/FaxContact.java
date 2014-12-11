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
public class FaxContact {
      private String label;
    private String fax;

    public String getLabel() {
        return label;
    }

    public String getEmail() {
        return fax;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setEmail(String fax) {
        this.fax = fax;
    }
}
