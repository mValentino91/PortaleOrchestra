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
public class CompactWorkingDays {
    
     private String days;
     private ArrayList<WorkingHours> workinghours;

    public void setDays(String days) {
        this.days = days;
    }

    public void setWorkinghours(ArrayList<WorkingHours> workinghours) {
        this.workinghours = workinghours;
    }

    public String getDays() {
        return days;
    }

    public ArrayList<WorkingHours> getWorkinghours() {
        return workinghours;
    }
}
