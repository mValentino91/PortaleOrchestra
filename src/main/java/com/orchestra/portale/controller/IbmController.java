/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import ibmServices.IBM_Requests;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Alex
 */
@Controller
public class IbmController {

    @RequestMapping("/ibm")
    public @ResponseBody
    String ibm() {
        System.out.println(IBM_Requests.getToken());
        return IBM_Requests.getToken();
    }

    @RequestMapping("/ibmAlberghi")
    public @ResponseBody
    String ibmAlberghi() {
        return IBM_Requests.getAlberghi();
    }

}
