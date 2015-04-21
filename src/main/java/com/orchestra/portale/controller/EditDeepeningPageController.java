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
import static com.orchestra.portale.utils.InsertUtils.delimg;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EditDeepeningPageController {
     @Autowired
    PersistenceManager pm;

    @RequestMapping(value= "/editdpage")
    public ModelAndView editDpage() {
        ModelAndView model = new ModelAndView("editdpage");
        return model;
    }
    @RequestMapping(value= "/editdpage", params="name")
    public ModelAndView editPoi(@RequestParam(value = "name") String name) {
        ModelAndView model = new ModelAndView("editdpageform");
        try {
        DeepeningPage poi= pm.findDeepeningPageByName(name);
       model.addObject("nome", poi.getName());
        model.addObject("cat", poi.getCategories());
        model.addObject("id", poi.getId());
        
        
       
         for (AbstractPoiComponent comp : poi.getComponents()) {

            //associazione delle componenti al model tramite lo slug
            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            
            try {
                Class c = Class.forName(slug);
                model.addObject(cname, c.cast(comp));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PoiViewController.class.getName()).log(Level.SEVERE, null, ex);
            
            }
                
               
         }
               
        return model;
    }
    catch(RuntimeException e){
            ModelAndView model2= new ModelAndView("errorViewPoi");
            model2.addObject("err", "Errore impossibile trovare la paginda d'approfondimento: "+ e.toString());
            return model2;
        }
    }
    
    @RequestMapping(value= "/editdpage", params="id")
    public ModelAndView editPoibyID(@RequestParam(value = "id") String id) {
        ModelAndView model = new ModelAndView("editdpageform");
        try {
        DeepeningPage poi= pm.findDeepeningPage(id);
        model.addObject("nome", poi.getName());
        model.addObject("cat", poi.getCategories());
        model.addObject("id", poi.getId());
        
        
        
         for (AbstractPoiComponent comp : poi.getComponents()) {

            //associazione delle componenti al model tramite lo slug
            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            
            try {
                Class c = Class.forName(slug);
                model.addObject(cname, c.cast(comp));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PoiViewController.class.getName()).log(Level.SEVERE, null, ex);
            
            }
         }
        return model;
    }
    catch(RuntimeException e){
            ModelAndView model2= new ModelAndView("errorViewPoi");
            model2.addObject("err", "Errore impossibile trovare la pagina d'approfondimento con id: "+id);
            return model2;
        }
    }
     @RequestMapping(value= "/updatedpage", method = RequestMethod.POST)
    public ModelAndView updatePoi(@RequestParam Map<String,String> params, @RequestParam(value="file", required=false) MultipartFile[] files, @RequestParam(value="cover", required=false) MultipartFile cover, @RequestParam(value="fileprec", required=false) String[] fileprec, @RequestParam(value="imgdel", required=false) String[] imgdel ) throws InterruptedException {
        
         DeepeningPage poi= pm.findDeepeningPage(params.get("id"));
         CoverImgComponent coverimg=new CoverImgComponent();
         ArrayList<AbstractPoiComponent> listComponent = new ArrayList<AbstractPoiComponent>();
         for (AbstractPoiComponent comp : poi.getComponents()) {

            //associazione delle componenti al model tramite lo slug
            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            if(cname.equals("coverimg")){
                coverimg=(CoverImgComponent) comp;
            }
         }
         ModelAndView model = new ModelAndView("editedpoi");
        
        poi.setId(params.get("id"));
        
        ModelAndView model2 = new ModelAndView("errorViewPoi");
        
        
        poi.setName(params.get("name"));
       
        
        
        int i=1;
        ArrayList<String> categories=new ArrayList<String>();
        while(params.containsKey("category"+i)){
          
            categories.add(params.get("category"+i));
  
            model.addObject("nome", categories.get(i-1));
            i=i+1;
        }
        poi.setCategories(categories);
        
        
        
        //componente cover
            if(!cover.isEmpty()) {
           
            coverimg.setLink("cover.jpg");
            
            }
            listComponent.add(coverimg);
           //componente galleria immagini
            ImgGalleryComponent img_gallery=new ImgGalleryComponent();
            ArrayList<ImgGallery> links=new ArrayList<ImgGallery>();
            i=0;
           
            if(files != null && files.length>0){
              while(i < files.length){
                  ImgGallery img=new ImgGallery();
               Thread.sleep(100);
                Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhmmssSSa");
            String currentTimestamp = sdf.format(date);
               img.setLink("img_"+currentTimestamp+".jpg");
               if(params.containsKey("newcredit"+(i+1)))
                    img.setCredit(params.get("newcredit"+(i+1)));
               i=i+1;
               links.add(img);
           }
            }
             int iximg=0;
            if (fileprec != null && fileprec.length>0){
                while(iximg < fileprec.length){
                   ImgGallery img=new ImgGallery(); 
               img.setLink(fileprec[iximg]);
               if(params.containsKey("credit"+(iximg+1)))
                    img.setCredit(params.get("credit"+(iximg+1)));
               iximg=iximg+1;
               links.add(img);
           }
            }
            if( (fileprec!= null && fileprec.length > 0 )|| (files!=null && files.length > 0)) {
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
             }
              poi.setComponents(listComponent);
              
             pm.saveDeepeningPage(poi);
             
            
            DeepeningPage poi2= pm.findDeepeningPage(poi.getName());
             
 
       
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
        if(!cover.isEmpty()){
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
                return  model;
            }
        }    
       
        // DELETE IMMAGINI DA CANCELLARE
        
        if(imgdel != null && imgdel.length > 0){
            for(int kdel=0; kdel<imgdel.length; kdel++){
            delimg(poi.getId(),imgdel[kdel]);
            }
        }
                
        return model;
    }
}
