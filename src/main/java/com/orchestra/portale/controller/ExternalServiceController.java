/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.external.services.manager.BikeSharingService;
import com.orchestra.portale.external.services.manager.CiRoService;
import com.orchestra.portale.external.services.manager.ServiceManagerDispacher;
import java.util.Map;
import javax.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Marco Valentino
 */
@Controller
@Scope("session")
@RequestMapping(value = "/externalService")
public class ExternalServiceController {

    private ServiceManagerDispacher serviceDispacher = new ServiceManagerDispacher();
    
    @Autowired
    PersistenceManager pm;

    @RequestMapping(value = "/ciro/get")
    public @ResponseBody
    String getCiRo(WebRequest request) {

        Map<String, String[]> params = request.getParameterMap();
        serviceDispacher.setService(new BikeSharingService(pm));
        return serviceDispacher.getExternalServiceResponse(params);
    }
    
    @RequestMapping(value = "/ciro/load")
    public @ResponseBody
    String loadCiRo() {
        
        serviceDispacher.setService(new CiRoService(pm));
        return serviceDispacher.loadExternalService();
    }
    
    @RequestMapping(value = "/bikeSharing/get")
    public @ResponseBody
    String getBikeSharing(WebRequest request) {

        Map<String, String[]> params = request.getParameterMap();
        serviceDispacher.setService(new BikeSharingService(pm));
        return serviceDispacher.getExternalServiceResponse(params);
    }

    @RequestMapping(value = "/bikeSharing/load")
    public @ResponseBody
    String loadBikeSharing() {
        serviceDispacher.setService(new BikeSharingService(pm));
        return serviceDispacher.loadExternalService();
    }
}
