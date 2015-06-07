/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author andrea
 */

@Controller
public class preview_cart {
    @RequestMapping(value = "/prova")
    public ModelAndView show(){
        ModelAndView model = new ModelAndView("carrelloPreview");
        return model;
    }
}
