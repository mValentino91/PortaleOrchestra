/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
public class AnmViewController {
    
        @RequestMapping(value = "/stopInfo")
        public ModelAndView getAnmStopInfo() {
        //Creo la view che sarà mostrata all'utente
        ModelAndView model = new ModelAndView("anmStopInfo");

        return model;
    }
    
}
