/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import java.io.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
public class CheckController {

    @RequestMapping("/check")
    public ModelAndView check() {
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "webapps" + File.separator + "orchestra" + File.separator + "dist" + File.separator + "poi");
        ModelAndView model = new ModelAndView("check");
        model.addObject("mess", dir.toString());
        return model;
    }
}
