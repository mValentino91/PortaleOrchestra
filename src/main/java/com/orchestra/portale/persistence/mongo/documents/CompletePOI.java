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

public interface CompletePOI {

    public abstract String getId();

    public abstract String getVisibility();

    public abstract void setVisibility(String visibility);

    

    /**
     * @param id the id to set
     */
    public abstract void setId(String id);

    /**
     * @return the name
     */
    public abstract String getName();

    /**
     * @param name the name to set
     */
    public abstract void setName(String name);
    
    /**
     * @return the shortDescription
     */
    public abstract String getShortDescription();

    /**
     * @param shortDescription the shortDescription to set
     */
    public abstract void setShortDescription(String shortDescription);

    /**
     * @return the address
     */
    public abstract String getAddress();

    /**
     * @param address the address to set
     */
    public abstract void setAddress(String address);

    /**
     * @return the components
     */
    public abstract List<AbstractPoiComponent> getComponents();

    /**
     * @param components the components to set
     */
    public abstract void setComponents(List<AbstractPoiComponent> components);

    /**
     * @return the categories
     */
    public abstract List<String> getCategories();

    /**
     * @param categories the categories to set
     */
    public abstract void setCategories(List<String> categories);

    /**
     * @return the location
     */
    public abstract double[] getLocation();

    /**
     * @param location the location to set
     */
    public abstract void setLocation(double[] location);

    public abstract String getStart_date();

    public abstract String getEnd_date();

    public abstract void setStart_date(String start_date);

    public abstract void setEnd_date(String end_date);

    /**
     * @return the externalUrl
     */
    public abstract String getExternalUrl();

    /**
     * @param externalUrl the externalUrl to set
     */
    public abstract void setExternalUrl(String externalUrl);

    /**
     * @return the lang
     */
  

    /**
     * @param lang the lang to set
     */
   
    
    
    
}

