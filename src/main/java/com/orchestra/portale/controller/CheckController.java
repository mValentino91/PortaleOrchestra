/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public ModelAndView check(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext sc = session.getServletContext();
        File f = new File(sc.getRealPath("/"));
        ModelAndView model = new ModelAndView("check");
        model.addObject("mess", f.getParentFile().getParentFile().getParentFile().getPath() + File.separator + "BackupImg" );
        return model;
    }
}
