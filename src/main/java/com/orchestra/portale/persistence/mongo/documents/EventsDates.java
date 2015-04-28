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
public class EventsDates {
    private String text;
    private String date;
    private ArrayList<EventsHours> hours;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    

    
    public String getDate() {
        return date;
    }

    public ArrayList<EventsHours> getHours() {
        return hours;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHours(ArrayList<EventsHours> hours) {
        this.hours = hours;
    }
  
}
