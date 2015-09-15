/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.rest.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author antonio
 */
@Controller
public class RestAuthController {

    @RequestMapping(value = "/tokenrest")
    public @ResponseBody
    String tokenRest(HttpServletRequest request) {    
        String msg = "ti do il token";
        return msg;
    }
}
