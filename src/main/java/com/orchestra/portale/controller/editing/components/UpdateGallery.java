/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller.editing.components;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.CoverImgComponent;
import com.orchestra.portale.persistence.mongo.documents.ImgGallery;
import com.orchestra.portale.persistence.mongo.documents.ImgGalleryComponent;
import static com.orchestra.portale.utils.InsertUtils.delimg;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UpdateGallery {

    @Autowired
    PersistenceManager pm;

    @ResponseBody
    @RequestMapping(value = "/UpdateGallery")
    public void UpdateGallery(HttpServletRequest request, @RequestParam("files") MultipartFile[] files, @RequestParam("id") String id) throws InterruptedException {
        CompletePOI poi = pm.getCompletePoiById(id);
        ArrayList<AbstractPoiComponent> complist = new ArrayList<AbstractPoiComponent>();
        ArrayList<ImgGallery> imglist = new ArrayList<ImgGallery>();
        int fd = 0;
        int ok=0;
        for (AbstractPoiComponent comp : poi.getComponents()) {

            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            if (cname.equals("imggallery")) {
                ok=1;
                ImgGalleryComponent imggallery = (ImgGalleryComponent) comp;
                imglist = (ArrayList<ImgGallery>) imggallery.getLinks();
                fd = imggallery.getLinks().size();
                int i = 0;
                while (i < files.length) {
                    ImgGallery imgG = new ImgGallery();

                    Thread.sleep(100);
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhmmssSSa");
                    String currentTimestamp = sdf.format(date);
                    imgG.setLink("img_" + currentTimestamp + ".jpg");

                    i = i + 1;
                    imglist.add(imgG);
                    
                }
                imggallery.setLinks(imglist);
                complist.add(imggallery);
            } else {
                complist.add(comp);
            }

        }
        if (ok==0) {
            ImgGalleryComponent imggallery = new ImgGalleryComponent();
                
                
                int i = 0;
                while (i < files.length) {
                    ImgGallery imgG = new ImgGallery();

                    Thread.sleep(100);
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhmmssSSa");
                    String currentTimestamp = sdf.format(date);
                    imgG.setLink("img_" + currentTimestamp + ".jpg");

                    i = i + 1;
                    imglist.add(imgG);
                    
                }
                imggallery.setLinks(imglist);
                complist.add(imggallery);
        }
        poi.setComponents(complist);
        pm.savePoi(poi);
        int k=0;
        for (int z = fd; z < imglist.size(); z++) {
            MultipartFile file = files[k];

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
                File serverFile = new File(dir.getAbsolutePath() + File.separator + imglist.get(z).getLink());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            } catch (Exception e) {
                System.out.println(""+e.getMessage());
            }
            k++;
        }
    }
    @ResponseBody
    @RequestMapping(value = "/DeleteImg")
    public void Delete(HttpServletRequest request, @RequestParam("del") int del, @RequestParam("id") String id) {
        CompletePOI poi = pm.getCompletePoiById(id);
        ArrayList<AbstractPoiComponent> complist = new ArrayList<AbstractPoiComponent>();
        ArrayList<ImgGallery> imglist = new ArrayList<ImgGallery>();
        ArrayList<ImgGallery> delimglist = new ArrayList<ImgGallery>();
        String delete="";
        
        for (AbstractPoiComponent comp : poi.getComponents()) {

            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            if (cname.equals("imggallery")) {
                ImgGalleryComponent imggallery = (ImgGalleryComponent) comp;
                imglist = (ArrayList<ImgGallery>) imggallery.getLinks();
                int i = 0;
                for(ImgGallery img : imglist) {
                    if(i!=del-1){
                        delimglist.add(img);
                    }
                    else {
                       delete= img.getLink();
                    }
                    i++;
                    
                }
                imggallery.setLinks(delimglist);
                complist.add(imggallery);
            } else {
                complist.add(comp);
            }

        }
        poi.setComponents(complist);
        pm.savePoi(poi);
        delimg(request,poi.getId(),delete);
        
    }
}
