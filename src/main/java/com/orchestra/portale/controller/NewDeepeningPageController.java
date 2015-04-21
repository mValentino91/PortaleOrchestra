/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.CoverImgComponent;
import com.orchestra.portale.persistence.mongo.documents.DeepeningPage;
import com.orchestra.portale.persistence.mongo.documents.DescriptionComponent;
import com.orchestra.portale.persistence.mongo.documents.ImgGallery;
import com.orchestra.portale.persistence.mongo.documents.ImgGalleryComponent;
import com.orchestra.portale.persistence.mongo.documents.Section;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
@RequestMapping(value= "/admin")
public class NewDeepeningPageController {
    @Autowired
    PersistenceManager pm;
    
    @RequestMapping("/newdpage")
    public ModelAndView newdpage() {
        return new ModelAndView("dpageform");
        
    }
    
    @RequestMapping(value= "/savedpage", method = RequestMethod.POST)
    public ModelAndView savedpage(@RequestParam Map<String,String> params, @RequestParam("cover") MultipartFile cover, @RequestParam("file") MultipartFile[] files) throws InterruptedException {
        ModelAndView model= new ModelAndView("okpage");
        DeepeningPage dp=new DeepeningPage();
        dp.setName(params.get("name"));
        
        int i=1;
        ArrayList<String> categories=new ArrayList<String>();
        while(params.containsKey("category"+i)){
          
            categories.add(params.get("category"+i));
  
            i=i+1;
        }
        dp.setCategories(categories);
      
        ArrayList<AbstractPoiComponent> listComponent = new ArrayList<AbstractPoiComponent>();
        
        //componente cover
            if(!cover.isEmpty()) {
            CoverImgComponent coverimg=new CoverImgComponent();
            coverimg.setLink("cover.jpg");
            listComponent.add(coverimg);
            }
           //componente galleria immagini
            ArrayList<ImgGallery> links=new ArrayList<ImgGallery>();
            if(files.length>0){
           ImgGalleryComponent img_gallery=new ImgGalleryComponent();
           
           i=0;
           while(i < files.length){
               ImgGallery img=new ImgGallery();
               
               Thread.sleep(100);
                Date date = new Date();
             SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhmmssSSa");
            String currentTimestamp = sdf.format(date);
               img.setLink("img_"+currentTimestamp+".jpg");
               if(params.containsKey("credit"+(i+1)))
                    img.setCredit(params.get("credit"+(i+1)));
               
               i=i+1;
               links.add(img);
           }
           img_gallery.setLinks(links);
           listComponent.add(img_gallery); 
            }
            //DESCRIPTION COMPONENT
             i=1;
             if(params.containsKey("par"+i)) {
                 ArrayList<Section> list = new ArrayList<Section>();
               
                 while(params.containsKey("par"+i)){
                     Section section = new Section();
                     if(params.containsKey("titolo"+i)) {
                         section.setTitle(params.get("titolo"+i));
                     }
                     section.setDescription(params.get("par"+i));
                     list.add(section);
                     i=i+1;
                     
                 }
             DescriptionComponent description_component = new DescriptionComponent();
            description_component.setSectionsList(list);
            listComponent.add(description_component);
             dp.setComponents(listComponent);
              
             pm.saveDeepeningPage(dp);
             
            
            DeepeningPage poi2= pm.findDeepeningPageByName(dp.getName());
             
 
       
        for (int z = 0; z < files.length; z++) {
            MultipartFile file = files[z];
            
            try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                
                File dir = new File(rootPath + File.separator + "webapps" + File.separator + "orchestra"+ File.separator+"dist"+File.separator+"dpage"+File.separator+"img"+File.separator+poi2.getId());
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()+ File.separator + links.get(z).getLink());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
            } catch (Exception e) {
                return  model;
            }
        }
         MultipartFile file = cover;
            
            try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                
                File dir = new File(rootPath + File.separator + "webapps" + File.separator + "orchestra"+ File.separator+"dist"+File.separator+"dpage"+File.separator+"img"+File.separator+poi2.getId());
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()+ File.separator + "cover.jpg");
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
            } catch (Exception e) {
               
            }
                 
       
            
            
       
    }
     return model;
}
}
