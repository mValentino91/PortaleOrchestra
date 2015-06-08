/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.external.services.manager.BikeSharingService;
import com.orchestra.portale.external.services.manager.CiRoService;
import com.orchestra.portale.external.services.manager.IbmService;
import com.orchestra.portale.external.services.manager.ServiceManagerDispacher;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
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
    private PersistenceManager pm;

    @RequestMapping(value = "/ciro/get")
    public @ResponseBody
    String getCiRo(WebRequest request) {
        pm = new ConcretePersistenceManager(LocaleContextHolder.getLocale().getDisplayLanguage());
        Map<String, String[]> params = request.getParameterMap();
        serviceDispacher.setService(new CiRoService(pm));
        return serviceDispacher.getExternalServiceResponse(params);
    }

    @RequestMapping(value = "/ciro/load")
    public @ResponseBody
    String loadCiRo() {
        pm = new ConcretePersistenceManager(LocaleContextHolder.getLocale().getDisplayLanguage());
        serviceDispacher.setService(new CiRoService(pm));
        return serviceDispacher.loadExternalService();
    }

    @RequestMapping(value = "/bikeSharing/get")
    public @ResponseBody
    String getBikeSharing(WebRequest request) {
        pm = new ConcretePersistenceManager(LocaleContextHolder.getLocale().getDisplayLanguage());
        Map<String, String[]> params = request.getParameterMap();
        serviceDispacher.setService(new BikeSharingService(pm));
        return serviceDispacher.getExternalServiceResponse(params);
    }

    @RequestMapping(value = "/bikeSharing/load")
    public @ResponseBody
    String loadBikeSharing() {
        pm = new ConcretePersistenceManager(LocaleContextHolder.getLocale().getDisplayLanguage());
        serviceDispacher.setService(new BikeSharingService(pm));
        return serviceDispacher.loadExternalService();
    }

    @RequestMapping(value = "/ibm/get")
    public @ResponseBody
    String getIbm(WebRequest request) {
        pm = new ConcretePersistenceManager(LocaleContextHolder.getLocale().getDisplayLanguage());
        Map<String, String[]> params = request.getParameterMap();
        serviceDispacher.setService(new IbmService(pm));
        return serviceDispacher.getExternalServiceResponse(params);
    }

    @RequestMapping(value = "/ibm/load")
    public @ResponseBody
    String loadIbm() {
        pm = new ConcretePersistenceManager(LocaleContextHolder.getLocale().getDisplayLanguage());
        serviceDispacher.setService(new IbmService(pm));
        return serviceDispacher.loadExternalService();
    }
    
    @RequestMapping(value = "/reset/lang")
    public @ResponseBody
    String resetLang() {
        
        pm = new ConcretePersistenceManager(LocaleContextHolder.getLocale().getDisplayLanguage());
        Iterable<CompletePOI> pois = pm.getAll();
        for (CompletePOI completePOI : pois) {
            completePOI.setLang("it");
            pm.savePoi(completePOI);
        }
        return "OK";
    }
}
