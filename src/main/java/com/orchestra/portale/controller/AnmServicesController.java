/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Marco Valentino
 */
@Controller
@RequestMapping("/anmServices")
public class AnmServicesController {

    @RequestMapping(value = "/stops/allInfo/{idStop}")
    public @ResponseBody
    String getStopAllInfo(@PathVariable String idStop) {

        return anmadvancedservices.AnmJsonIBMServices.getJsonAllInfoPalina(idStop);
    }

    @RequestMapping(value = "/stops/basicInfo/{idStop}")
    public @ResponseBody
    String getStopBasicInfo(@PathVariable String idStop) {

        return anmadvancedservices.AnmJsonIBMServices.getJsonBasicInfoPalina(idStop);
    }

    @RequestMapping(value = "/lines/route/{idLine}")
    public @ResponseBody
    String getLineRoute(@PathVariable String idLine) {

        return anmadvancedservices.AnmJsonIBMServices.getJsonPercorsoLinea(idLine);
    }

    @RequestMapping(value = "/stops/prevision/{idStop}")
    public @ResponseBody
    String getStopPrevisions(@PathVariable String idStop) {

        return anmadvancedservices.AnmJsonIBMServices.getJsonPrevisioniPalina(idStop);
    }

    @RequestMapping(value = "/stops/transit/{idStop}")
    public @ResponseBody
    String getStopTransits(@PathVariable String idStop) {

        return anmadvancedservices.AnmJsonIBMServices.getJsonTransitiPalina(idStop);
    }

}
