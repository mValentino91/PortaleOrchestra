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
public class SubMenu {
    private String color;
    private ArrayList<CategorySubMenu> categories;

    public String getColor() {
        return color;
    }

    public ArrayList<CategorySubMenu> getCategories() {
        return categories;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCategories(ArrayList<CategorySubMenu> categories) {
        this.categories = categories;
    }
}
