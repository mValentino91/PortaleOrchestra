/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

import java.util.ArrayList;
import org.springframework.data.annotation.TypeAlias;

/**
 *
 * @author Alex
 */
@TypeAlias("com.orchestra.portale.persistence.mongo.documents.EventsDateComponent")
public class EventsDateComponent extends AbstractPoiComponent {
    private ArrayList<EventsDates> dates;

    public ArrayList<EventsDates> getDates() {
        return dates;
    }

    public void setDates(ArrayList<EventsDates> dates) {
        this.dates = dates;
    }
    
}
