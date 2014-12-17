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
@TypeAlias("com.orchestra.portale.persistence.mongo.documents.ServicesComponent")
public class ServicesComponent extends AbstractPoiComponent {
    private ArrayList<String> servicesList;

    public void setServicesList(ArrayList<String> servicesList) {
        this.servicesList = servicesList;
    }

    public ArrayList<String> getServicesList() {
        return servicesList;
    }
}
