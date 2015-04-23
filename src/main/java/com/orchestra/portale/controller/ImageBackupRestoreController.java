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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alex
 */
@Controller
public class ImageBackupRestoreController {

    @RequestMapping(value = "/backupimg")
    public ModelAndView backup(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("showResult");
        HttpSession session = request.getSession();

        ServletContext sc = session.getServletContext();
        File root = new File(sc.getRealPath("/"));

        File dir = new File(sc.getRealPath("/") + "dist" + File.separator + "poi");
        File dir2 = new File(sc.getRealPath("/") + "dist" + File.separator + "user");
        File dir3 = new File(sc.getRealPath("/") + "dist" + File.separator + "page");
        File dir4 = new File(sc.getRealPath("/") + "dist" + File.separator + "dpage");
        copy(dir.getCanonicalPath(), root.getParentFile().getParentFile().getParentFile().getPath() + File.separator + "BackupImg");
        copy(dir2.getCanonicalPath(), root.getParentFile().getParentFile().getParentFile().getPath() + File.separator + "BackupImg");
        copy(dir3.getCanonicalPath(), root.getParentFile().getParentFile().getParentFile().getPath() + File.separator + "BackupImg");
        copy(dir4.getCanonicalPath(), root.getParentFile().getParentFile().getParentFile().getPath() + File.separator + "BackupImg");
        model.addObject("mess", "Tutte le immagini sono state copiate");
        return model;
    }

    @RequestMapping(value = "/restoreimg")
    public ModelAndView restore(HttpServletRequest request) throws IOException {

        ModelAndView model = new ModelAndView("showResult");
        HttpSession session = request.getSession();
        ServletContext sc = session.getServletContext();
        File root = new File(sc.getRealPath("/"));
        File dir = new File(sc.getRealPath("/") + "dist");
        copy(root.getParentFile().getParentFile().getParentFile().getPath() + File.separator + "BackupImg" + File.separator + "poi", dir.getCanonicalPath());
        copy(root.getParentFile().getParentFile().getParentFile().getPath() + File.separator + "BackupImg" + File.separator + "page", dir.getCanonicalPath());
        copy(root.getParentFile().getParentFile().getParentFile().getPath() + File.separator + "BackupImg" + File.separator + "dpage", dir.getCanonicalPath());
        copy(root.getParentFile().getParentFile().getParentFile().getPath() + File.separator + "BackupImg" + File.separator + "user", dir.getCanonicalPath());

        model.addObject("mess", "Tutte le immagini sono state ripristinate");
        return model;
    }
}
