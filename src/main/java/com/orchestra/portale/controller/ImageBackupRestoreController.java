/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.orchestra.portale.utils.BackupRestoreUtils;
import static com.orchestra.portale.utils.BackupRestoreUtils.copy;

/**
 *
 * @author Alex
 */
@Controller
public class ImageBackupRestoreController {
    @RequestMapping(value= "/backupimg")
    public ModelAndView backup() throws IOException {
        ModelAndView model = new ModelAndView("showResult");
        String rootPath = System.getProperty("catalina.home");
               File dir = new File(rootPath + File.separator + "webapps" + File.separator + "orchestra"+ File.separator+"dist"+File.separator+"poi");
               File dir2 = new File(rootPath + File.separator + "webapps" + File.separator + "orchestra"+ File.separator+"dist"+File.separator+"user");
               File dir3= new File(rootPath + File.separator + "webapps" + File.separator + "orchestra"+ File.separator+"dist"+File.separator+"page");
               File dir4= new File(rootPath + File.separator + "webapps" + File.separator + "orchestra"+ File.separator+"dist"+File.separator+"dpage");
             copy(dir.getCanonicalPath(),rootPath);
             copy(dir2.getCanonicalPath(),rootPath);
             copy(dir3.getCanonicalPath(),rootPath);
             copy(dir4.getCanonicalPath(),rootPath);
             model.addObject("mess", "Tutte le immagini sono state copiate");
        return model;
    }
    @RequestMapping(value= "/restoreimg")
    public ModelAndView restore() throws IOException {
        ModelAndView model = new ModelAndView("showResult");
        String rootPath = System.getProperty("catalina.home");
               File dir = new File(rootPath + File.separator + "webapps" + File.separator + "orchestra"+ File.separator+"dist");
             copy(rootPath+File.separator+"poi",dir.getCanonicalPath());
             copy(rootPath+File.separator+"user",dir.getCanonicalPath());
             copy(rootPath+File.separator+"page",dir.getCanonicalPath());
             copy(rootPath+File.separator+"dpage",dir.getCanonicalPath());
             model.addObject("mess", "Tutte le immagini sono state ripristinate");
        return model;
    }
}
