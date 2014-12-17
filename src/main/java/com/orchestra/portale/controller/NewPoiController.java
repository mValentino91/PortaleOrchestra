/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompactWorkingDays;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.ContactsComponent;
import com.orchestra.portale.persistence.mongo.documents.CoverImgComponent;
import com.orchestra.portale.persistence.mongo.documents.DescriptionComponent;
import com.orchestra.portale.persistence.mongo.documents.EmailContact;
import com.orchestra.portale.persistence.mongo.documents.FaxContact;
import com.orchestra.portale.persistence.mongo.documents.GenericSocial;
import com.orchestra.portale.persistence.mongo.documents.ImageGalleryComponent;
import com.orchestra.portale.persistence.mongo.documents.PhoneContact;
import com.orchestra.portale.persistence.mongo.documents.PricesComponent;
import com.orchestra.portale.persistence.mongo.documents.Section;
import com.orchestra.portale.persistence.mongo.documents.TicketPrice;
import com.orchestra.portale.persistence.mongo.documents.WorkingHours;
import com.orchestra.portale.persistence.mongo.documents.WorkingTimeComponent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.*;

/**
 *
 * @author Alex
 */
@Controller
public class NewPoiController {

    //Manager della persistenza
    @Autowired
    PersistenceManager pm;

    //Richiesta per la visualizzazione di un singolo poi
    @RequestMapping(value = "/newpoi")
    public ModelAndView newPoi() {

        //Creo la view che sarà mostrata all'utente
        ModelAndView model = new ModelAndView("poiform");
        return model;
}
    @RequestMapping(value= "/insertpoi", method = RequestMethod.POST)
    public ModelAndView insertPoi(@RequestParam Map<String,String> params, @RequestParam("file") MultipartFile[] files, @RequestParam("cover") MultipartFile cover) {
        
         CompletePOI poi = new CompletePOI();
        
        ModelAndView model = new ModelAndView("insertpoi");
        
        poi.setName(params.get("name"));
        poi.setAddress(params.get("address"));
        double lat= Double.parseDouble(params.get("latitude"));
        double longi= Double.parseDouble(params.get("longitude"));
        poi.setLocation(new double[] { lat, longi });
        poi.setShortDescription(params.get("shortd"));
        int i=1;
        ArrayList<String> categories=new ArrayList<String>();
        while(params.containsKey("category"+i)){
          
            categories.add(params.get("category"+i));
  
            model.addObject("nome", categories.get(i-1));
            i=i+1;
        }
        poi.setCategories(categories);
        
        ArrayList<AbstractPoiComponent> listComponent = new ArrayList<AbstractPoiComponent>();
        
        //componente cover
            if(!cover.isEmpty()) {
            CoverImgComponent coverimg=new CoverImgComponent();
            coverimg.setLink(cover.getOriginalFilename());
            listComponent.add(coverimg);
            }
           //componente galleria immagini
            if(files.length>0){
           ImageGalleryComponent img_gallery=new ImageGalleryComponent();
           ArrayList<String> links=new ArrayList<String>();
           i=0;
           while(i < files.length){
               links.add(files[i].getOriginalFilename());
               i=i+1;
           }
           img_gallery.setLinks(links);
           listComponent.add(img_gallery); 
            }
           //componente contatti
            ContactsComponent contacts_component = new ContactsComponent();
             //Recapiti telefonici
            i=1;
            boolean contacts=false;
             if(params.containsKey("tel"+i)){
                 ArrayList<PhoneContact> phoneList= new ArrayList<PhoneContact>();
                 
                 while(params.containsKey("tel"+i)){
                     PhoneContact phone=new PhoneContact();
                    if(params.containsKey("tel"+i)){
                     phone.setLabel(params.get("desctel"+i));
                    }
                    phone.setNumber(params.get("tel"+i));
                    phoneList.add(phone);
                    i=i+1;
                 }
                 contacts=true;
                 contacts_component.setPhoneList(phoneList);
             }
             //Recapiti mail
              i=1;
             if(params.containsKey("email"+i)){
                 ArrayList<EmailContact> emailList= new ArrayList<EmailContact>();
                 
                 while(params.containsKey("email"+i)){
                     EmailContact email=new EmailContact();
                    if(params.containsKey("email"+i)){
                     email.setLabel(params.get("descemail"+i));
                    }
                    email.setEmail(params.get("email"+i));
                    emailList.add(email);
                    i=i+1;
                 }
                 contacts=true;
                 contacts_component.setEmailsList(emailList);
             }
             //Recapiti fax
              i=1;
             if(params.containsKey("fax"+i)){
                 ArrayList<FaxContact> faxList= new ArrayList<FaxContact>();
                
                 while(params.containsKey("fax"+i)){
                      FaxContact fax=new FaxContact();
                    if(params.containsKey("fax"+i)){
                     fax.setLabel(params.get("descfax"+i));
                    }
                    fax.setFax(params.get("fax"+i));
                    faxList.add(fax);
                    i=i+1;
                 }
                 contacts=true;
                 contacts_component.setFaxList(faxList);
             }
             //Social predefiniti
              i=1;
             if(params.containsKey("SN"+i)){
                
                 while(params.containsKey("SN"+i)){
                     if(params.get("SN"+i).equals("facebook")){
                         contacts=true;
                         contacts_component.setFacebook(params.get("LSN"+i));
                     }
                     if(params.get("SN"+i).equals("twitter")){
                         contacts=true;
                         contacts_component.setTwitter(params.get("LSN"+i));
                     }
                     if(params.get("SN"+i).equals("google")){
                         contacts=true;
                         contacts_component.setGoogle(params.get("LSN"+i));
                     }
                     if(params.get("SN"+i).equals("skype")){
                         contacts=true;
                         contacts_component.setSkype(params.get("LSN"+i));
                     }
                     i=i+1;
                    }
                  }
               //Social personalizzati
              i=1;
             if(params.containsKey("CSN"+i)){
               ArrayList<GenericSocial> customsocial= new ArrayList<GenericSocial>();
               
                 while(params.containsKey("CSN"+i)){
                     GenericSocial social= new GenericSocial();
                     contacts=true;
                     social.setLabel(params.get("CSN"+i));
                     social.setSocial(params.get("LCSN"+i));
                     customsocial.add(social);
                     i=i+1;
                    }
                 contacts_component.setSocialList(customsocial);
                  }
             if(contacts==true){
             listComponent.add(contacts_component);
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
             //Orari
             i=1;
             int k=1;
             if(params.containsKey("WD"+i+"start"+k+"H")){
                
                
                
                ArrayList<CompactWorkingDays> workingdays = new ArrayList<CompactWorkingDays>();
                WorkingTimeComponent workingtime = new WorkingTimeComponent();
                 while(params.containsKey("WD"+i+"day")){
                      ArrayList<WorkingHours> Listwh = new ArrayList<WorkingHours>();
                     k=1;
                       while(params.containsKey("WD"+i+"start"+k+"H")){
                           WorkingHours wh = new WorkingHours();
                        wh.setStart(params.get("WD"+i+"start"+k+"H")+":"+params.get("WD"+i+"start"+k+"M"));
                        wh.setEnd(params.get("WD"+i+"end"+k+"H")+":"+params.get("WD"+i+"end"+k+"M"));
                        Listwh.add(wh);
                        k=k+1;
                       }
                       CompactWorkingDays cwd = new CompactWorkingDays();
                        cwd.setDays(params.get("WD"+i+"day"));
                        cwd.setWorkinghours(Listwh);
                        workingdays.add(cwd);
                        i=i+1;
                 }
                 workingtime.setWorkingdays(workingdays);
                 i=1;
                 String gg="";
                 while(params.containsKey("RD"+i)){
                     gg=gg+" "+params.get("RD"+i);
                     i=i+1;
                 }
                 if(!gg.equals("")){
                     workingtime.setWeekly_day_of_rest(gg);
                 }
                 i=1;
                 if(params.containsKey("RDA"+i)){
                     ArrayList<String> days_of_rest= new ArrayList<String>();
                 while(params.containsKey("RDA"+i)){
                     days_of_rest.add(params.get("RDA"+i));
                     i=i+1;
                    }
                 workingtime.setDays_of_rest(days_of_rest);
                 }
                 listComponent.add(workingtime);
             }
             
             
             
             i=1;
             if(params.containsKey("type"+i)){
                 
                 PricesComponent pc= new PricesComponent();
            ArrayList<TicketPrice> tpList = new ArrayList<TicketPrice>();
                 while(params.containsKey("type"+i)){
                     TicketPrice tp=new TicketPrice();
                     tp.setType(params.get("type"+i));
                     double dp=Double.parseDouble(params.get("price"+i));
                     tp.setPrice(dp);
                     tp.setType_description(params.get("typedesc"+i));
                     tpList.add(tp);
                     i=i+1;
                 }
                 pc.setPrices(tpList);
                 listComponent.add(pc);
             }
              poi.setComponents(listComponent);
              
             pm.savePoi(poi);
         /*    CompletePOI poi2=(CompletePOI) pm.findCompletePoiByName(poi.getName());
             
 
       
        for (int z = 0; z < files.length; z++) {
            MultipartFile file = files[z];
            
            try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("user.dir");
                
                File dir = new File(rootPath + File.separator + "dist" + File.separator + poi2.getId());
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + files[z].getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
            } catch (Exception e) {
                return  model;
            }
        }   
                 */
        return model;
    }
}
