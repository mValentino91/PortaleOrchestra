/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

import java.util.Map;
import org.springframework.data.annotation.TypeAlias;

/**
 *
 * @author Marco Valentino
 */
@TypeAlias("com.orchestra.portale.persistence.mongo.documents.ExternalServiceComponent")
public class ExternalServiceComponent extends AbstractPoiComponent {

    private String URL;
    private Map<String, String[]> mapParams;

    /**
     * @return the URL
     */
    public String getURL() {
        return URL;
    }

    /**
     * @param URL the URL to set
     */
    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     * @return the mapParams
     */
    public Map<String, String[]> getMapParams() {
        return mapParams;
    }

    /**
     * @param mapParams the mapParams to set
     */
    public void setMapParams(Map<String, String[]> mapParams) {
        this.mapParams = mapParams;
    }

}
