/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.external.services.manager.BikeSharingService;
import com.orchestra.portale.external.services.manager.ServiceManagerDispacher;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/bikeSharing/get")
    public @ResponseBody
    String getBikeSharing(WebRequest request) {

        Map<String, String[]> params = request.getParameterMap();
        serviceDispacher.setService(new BikeSharingService());
        return serviceDispacher.getExternalServiceResponse(params);
    }

    @RequestMapping(value = "/bikeSharing/load")
    public @ResponseBody
    String loadBikeSharing() {
        serviceDispacher.setService(new BikeSharingService());
        return serviceDispacher.loadExternalService();
    }
}
