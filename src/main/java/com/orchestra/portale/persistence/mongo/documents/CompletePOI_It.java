/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author mekko
 */
@Document(collection = "poi")
public class CompletePOI_It implements CompletePOI {
    @Id
    private String id;
    private String name;
    private String visibility;
    private double[] location;
    private String shortDescription;
    private String address;
    private List<AbstractPoiComponent> components;
    private List<String> categories;
    private String start_date;
    private String end_date;
    private String externalUrl;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @param shortDescription the shortDescription to set
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the components
     */
    public List<AbstractPoiComponent> getComponents() {
        return components;
    }

    /**
     * @param components the components to set
     */
    public void setComponents(List<AbstractPoiComponent> components) {
        this.components = components;
    }

    /**
     * @return the categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * @return the location
     */
    public double[] getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(double[] location) {
        this.location = location;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    /**
     * @return the externalUrl
     */
    public String getExternalUrl() {
        return externalUrl;
    }

    /**
     * @param externalUrl the externalUrl to set
     */
    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    
    
    
    
}
