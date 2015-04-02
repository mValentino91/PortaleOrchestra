/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.User;
import com.orchestra.portale.profiler.FbProfiler;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
@Scope("request")
public class UserInfoController {
    @Autowired
    PersistenceManager pm;
    
    @Autowired
    FbProfiler fbprofiler;
    
    @RequestMapping(value="/userInfo")
    @Secured("ROLE_USER")
    public ModelAndView getUserInfo() {
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         ModelAndView model = new ModelAndView("userInfo");
         ModelAndView model2 = new ModelAndView("index");
         if (auth != null ){
        User user= pm.findUserByUsername(auth.getName());
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "webapps" + File.separator + "orchestra"+ File.separator+"dist"+File.separator+"user"+File.separator+"img"+File.separator+user.getId()+File.separator+"avatar.jpg");
       if (dir.exists()) {
          model.addObject("avatar", "./dist/user/img/"+user.getId()+"/avatar.jpg");
       }
       else {
           model.addObject("avatar", "./dist/img/default_avatar.png");
       }
       
        model.addObject("user", user);
        model.addObject("categories", fbprofiler.getFBCategories());
         }
         else {
             return model2 ;
         }
        return model;
    }
    
    
}
