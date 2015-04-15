/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author antonio
 */

@Controller
@RequestMapping
public class MapComponentsController {
    @RequestMapping("/filterOnPoiList")
    public String filterOnPoiList() {
        System.out.println("Sono nel controller****************************");
	return "components/filterOnPoiList";
    }
}
