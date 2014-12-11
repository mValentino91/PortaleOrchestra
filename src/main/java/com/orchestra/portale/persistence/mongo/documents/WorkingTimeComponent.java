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
@TypeAlias("com.orchestra.portale.persistence.mongo.documents.WorkingTimeComponent")
public class WorkingTimeComponent extends AbstractPoiComponent{
    
    private ArrayList<CompactWorkingDays> workingdays;
    private String weekly_day_of_rest;
    private ArrayList<String> days_of_rest;

    public ArrayList<CompactWorkingDays> getWorkingdays() {
        return workingdays;
    }

    public String getWeekly_day_of_rest() {
        return weekly_day_of_rest;
    }

    public ArrayList<String> getDays_of_rest() {
        return days_of_rest;
    }

    public void setWorkingdays(ArrayList<CompactWorkingDays> workingdays) {
        this.workingdays = workingdays;
    }

    public void setWeekly_day_of_rest(String weekly_day_of_rest) {
        this.weekly_day_of_rest = weekly_day_of_rest;
    }

    public void setDays_of_rest(ArrayList<String> days_of_rest) {
        this.days_of_rest = days_of_rest;
    }
    
    
}
