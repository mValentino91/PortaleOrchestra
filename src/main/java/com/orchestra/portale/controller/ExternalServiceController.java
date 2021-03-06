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
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_It;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    PersistenceManager pm;
    private ServiceManagerDispacher serviceDispacher = new ServiceManagerDispacher();

    @RequestMapping(value = "/ciro/get")
    public @ResponseBody
    String getCiRo(WebRequest request) {
        pm.setLang(LocaleContextHolder.getLocale().getDisplayLanguage());
        Map<String, String[]> params = request.getParameterMap();
        serviceDispacher.setService(new CiRoService(pm));
        return serviceDispacher.getExternalServiceResponse(params);
    }

    @RequestMapping(value = "/ciro/load")
    public @ResponseBody
    String loadCiRo(HttpSession session) {
        pm.setLang(LocaleContextHolder.getLocale().getDisplayLanguage());
        serviceDispacher.setService(new CiRoService(pm));
        return serviceDispacher.loadExternalService(session);
    }

    @RequestMapping(value = "/bikeSharing/get")
    public @ResponseBody
    String getBikeSharing(WebRequest request) {
        pm.setLang(LocaleContextHolder.getLocale().getDisplayLanguage());
        Map<String, String[]> params = request.getParameterMap();
        serviceDispacher.setService(new BikeSharingService(pm));
        return serviceDispacher.getExternalServiceResponse(params);
    }

    @RequestMapping(value = "/bikeSharing/load")
    public @ResponseBody
    String loadBikeSharing(HttpSession session) {
        pm.setLang(LocaleContextHolder.getLocale().getDisplayLanguage());
        serviceDispacher.setService(new BikeSharingService(pm));
        return serviceDispacher.loadExternalService(session);
    }

    @RequestMapping(value = "/ibm/get")
    public @ResponseBody
    String getIbm(WebRequest request) {
        pm.setLang(LocaleContextHolder.getLocale().getDisplayLanguage());
        Map<String, String[]> params = request.getParameterMap();
        serviceDispacher.setService(new IbmService(pm));
        return serviceDispacher.getExternalServiceResponse(params);
    }

    @RequestMapping(value = "/ibm/load")
    public @ResponseBody
    String loadIbm(HttpSession session) {
        pm.setLang(LocaleContextHolder.getLocale().getDisplayLanguage());
        serviceDispacher.setService(new IbmService(pm));
        return serviceDispacher.loadExternalService(session);
    }

    
}
