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
public class PhoneContact {
    private String label;
    private String number;

    public String getLabel() {
        return label;
    }

    public String getNumber() {
        return number;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
}
