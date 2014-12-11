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
public class TicketPrice {
    private double price;
    private String type;
    private String type_description;

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getType_description() {
        return type_description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType_description(String type_description) {
        this.type_description = type_description;
    }
    
}
