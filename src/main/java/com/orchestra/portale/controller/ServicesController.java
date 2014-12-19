/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Marco Valentino
 */
@Controller
@RequestMapping("/Services")
public class ServicesController {

    @RequestMapping(value = "/Anm")
    public @ResponseBody
    String getPrevision(@RequestParam String idStop) {

        return anmadvancedservices.AnmJsonServicesStub.getJsonPrevisioniPalina(idStop);
    }

    @RequestMapping(value = "/Ibm/Alberghi")
    public @ResponseBody
    String getHotels() {

        return ibmServices.IBM_Requests.getAlberghi();
    }

    @RequestMapping(value = "/Ibm/Albergo")
    public @ResponseBody
    String getHotel(@RequestParam String idHotel) {

        return ibmServices.IBM_Requests.getAlbergo(idHotel);
    }
}
