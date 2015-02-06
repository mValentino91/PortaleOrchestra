/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.external.services.manager;

import java.util.Map;

/**
 *
 * @author Marco Valentino
 */
//Strategy pattern
public class ServiceManagerDispacher {

    private ExternalServiceManager service;

    public void setService(ExternalServiceManager service) {
        this.service = service;
    }

    public ExternalServiceManager getService() {
        return service;
    }

    public String loadExternalService() {
        return service.load();
    }

    public String getExternalServiceResponse(Map<String,String[]> mapParams) {
        return service.getResponse(mapParams);
    }
}
