/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.DeepeningPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
public class DummyController {
    @Autowired
    PersistenceManager pm;

    @RequestMapping("/newdp")
   public @ResponseBody String newdp() {
        DeepeningPage dp= new DeepeningPage();
        dp.setName("AAAAAA");
        pm.saveDeepeningPage(dp);
        return "ok";
    }
    
}
