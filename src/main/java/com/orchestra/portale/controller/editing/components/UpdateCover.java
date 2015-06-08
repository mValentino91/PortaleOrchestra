/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller.editing.components;

import com.orchestra.portale.controller.PoiViewController;
import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.CoverImgComponent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Alex
 */
@Controller
public class UpdateCover {
    
    @Autowired
    PersistenceManager pm ;
    @ResponseBody
    @RequestMapping(value = "/UpdateCover")
    public void UpdateCover(HttpServletRequest request, @RequestParam(value = "cover", required = false) MultipartFile cover, @RequestParam("top") String top, @RequestParam("left") String left, @RequestParam("id") String id) {
        
        CompletePOI poi = pm.getCompletePoiById(id);
        ArrayList<AbstractPoiComponent> complist = new ArrayList<AbstractPoiComponent>();

        for (AbstractPoiComponent comp : poi.getComponents()) {

            //associazione delle componenti al model tramite lo slug
            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            if (cname.equals("coverimg")) {
                CoverImgComponent coverimg = new CoverImgComponent();
                coverimg.setLink("cover.jpg");
                coverimg.setLeft(left);
                System.out.println("LEFT: " + coverimg.getLeft());
                coverimg.setTop(top);
                System.out.println("TOP: " + coverimg.getTop());
                complist.add(coverimg);
            } else {
                complist.add(comp);
            }

        }
        poi.setComponents(complist);
        pm.savePoi(poi);

        if (cover != null && !cover.isEmpty()) {
            MultipartFile file = cover;

            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                HttpSession session = request.getSession();
                ServletContext sc = session.getServletContext();

                File dir = new File(sc.getRealPath("/") + "dist" + File.separator + "poi" + File.separator + "img" + File.separator + poi.getId());
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + "cover.jpg");
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            } catch (Exception e) {

            }
        }
    }
}
