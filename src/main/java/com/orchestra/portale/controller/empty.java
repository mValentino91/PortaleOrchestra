/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author andrea
 */
@Controller
public class empty {
    @RequestMapping(value = "/empty", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView empty(){
        ModelAndView model = new ModelAndView("newjsp");
        return model;
    }
    
}
