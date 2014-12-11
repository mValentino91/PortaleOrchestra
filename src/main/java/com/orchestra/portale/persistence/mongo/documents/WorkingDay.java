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
public class WorkingDay {
    private String day;
    private ArrayList<WorkingHours> workinghours;

    public void setDay(String day) {
        this.day = day;
    }

    public void setWorkinghours(ArrayList<WorkingHours> workinghours) {
        this.workinghours = workinghours;
    }

    public String getDay() {
        return day;
    }

    public ArrayList<WorkingHours> getWorkinghours() {
        return workinghours;
    }
}
