/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import colellaparser.Produttore;
import colellaparser.XMLParser;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import java.io.File;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.ContactsComponent;
import com.orchestra.portale.persistence.mongo.documents.CoverImgComponent;
import com.orchestra.portale.persistence.mongo.documents.DescriptionComponent;
import com.orchestra.portale.persistence.mongo.documents.EmailContact;
import com.orchestra.portale.persistence.mongo.documents.GenericSocial;
import com.orchestra.portale.persistence.mongo.documents.PhoneContact;
import com.orchestra.portale.persistence.mongo.documents.Section;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author antonio
 */
@Controller
public class ColellaController {
    @Autowired
    PersistenceManager pm;
    
    
    @RequestMapping(value = "/colellaImport")
    public @ResponseBody
    String colellaImport(HttpServletRequest request) {
        
        
        HttpSession session = request.getSession();
        ServletContext sc = session.getServletContext();
        String folder = sc.getRealPath("/")+ "dist" + File.separator + "colella";
                            
        
        
        List<Produttore> pList = XMLParser.parseCartellaProduttori(folder);
        
        for(Produttore prod : pList){
            System.out.println("*******************************");
            
            //Dati obbligatori
            CompletePOI p = new CompletePOI();
            String den = prod.getDenominazione().replace("'", "&#039;");
            den = den.replace("\"", "&quot;").replace("\n", "").replace("\r", "");
            p.setName(den);
            
            System.out.println("Name: " + p.getName());
            
            p.setVisibility("1");
            p.setLocation(new double [] {Double.parseDouble(prod.getSedeLat()), Double.parseDouble(prod.getSedeLon())});
            p.setAddress(prod.getSedeVia());
            if(prod.getDescrizione() != null && !prod.getDescrizione().trim().equals("")){
                if(prod.getDescrizione().length() <= 100){
                    String desc = prod.getDescrizione().replace("'", "&#039;");
                    desc = desc.replace("\"", "&quot;").replace("\n", "").replace("\r", "");
                    p.setShortDescription(desc.substring(0, desc.length()));
                }
                else{
                    String desc = prod.getDescrizione().replace("'", "&#039;");
                    desc = desc.replace("\"", "&quot;").replace("\n", "").replace("\r", "");
                    p.setShortDescription(desc.substring(0, 100));
                }
                
                 System.out.println(p.getShortDescription());
            }
            
            ArrayList<String> l = new ArrayList <String>();
            l.add("food");
            p.setCategories(l);
            
            //componenti
            ArrayList<AbstractPoiComponent> listComponent = new ArrayList<AbstractPoiComponent>();
            
            //componente cover
            if(true) {
            CoverImgComponent coverimg=new CoverImgComponent();
                coverimg.setLink("cover.jpg");
                listComponent.add(coverimg);
            }
            
            //componente contatti
            ContactsComponent contacts_component = new ContactsComponent();
            boolean contacts=false;
            
            ArrayList<PhoneContact> phoneList= new ArrayList<PhoneContact>();
            
            //Recapiti telefonici
            if(prod.getTelFissoList()!=null && prod.getTelFissoList().size()>0){
                 
                 
                 
                 for(String t : prod.getTelFissoList()){
                    
                    if(!t.trim().equals("")){ 
                     
                        PhoneContact phone=new PhoneContact();
                        phone.setLabel("Fisso");

                        phone.setNumber(t);
                        phoneList.add(phone);

                        System.out.println("Tel: " + phone.getLabel() + " " + phone.getNumber());
                    }
                    
                 }
                 contacts=true;
                 contacts_component.setPhoneList(phoneList);
                 for(PhoneContact ph : contacts_component.getPhoneList()){
                     System.out.println("Tel****: " + ph.getLabel() + " " + ph.getNumber());
                 }
            }
            if(prod.getTelMobList()!=null && prod.getTelMobList().size()>0){
                 
                 
                 
                 for(String t : prod.getTelMobList()){
                     
                    if(!t.trim().equals("")){ 
                        PhoneContact phone=new PhoneContact();
                        phone.setLabel("Mobile");

                        phone.setNumber(t);
                        phoneList.add(phone);

                        System.out.println("Tel M: " + phone.getLabel() + " " + phone.getNumber());
                    }
                 }
                 contacts=true;
                 contacts_component.setPhoneList(phoneList);
             }            
            if(prod.getEmailList()!=null && prod.getEmailList().size()>0){
                 
                ArrayList<EmailContact> emailList= new ArrayList<EmailContact>();
                 
                for(String t : prod.getEmailList()){
                    
                   if(!t.trim().equals("")){
                    EmailContact email=new EmailContact();
                    email.setEmail(t);
                    emailList.add(email);

                    System.out.println("Email: " + email.getLabel() + " " + email.getEmail());
                   }
                }
                 contacts=true;
                 contacts_component.setEmailsList(emailList);
             }  
            
            if(prod.getFacebook() != null && !prod.getFacebook().trim().equals("")){
                 
                contacts_component.setFacebook(prod.getFacebook());
                System.out.println("Face: " + prod.getFacebook());
            }  
            
            if(prod.getTwitter() != null && !prod.getTwitter().trim().equals("")){
                 
                contacts_component.setTwitter(prod.getTwitter());
                System.out.println("Twit: " + prod.getTwitter());
            }            
            
            
            if(prod.getWebSite() != null && !prod.getWebSite().trim().equals("")){
                ArrayList<GenericSocial> customsocial= new ArrayList<GenericSocial>();
                GenericSocial social= new GenericSocial();
                contacts=true;
                social.setLabel("Website");
                social.setSocial(prod.getWebSite());
                customsocial.add(social);
                contacts_component.setSocialList(customsocial);
                
                System.out.println("Social: " + social.getLabel() + " " + social.getSocial());
            }            
            if(contacts){
                System.out.println("### INSERT CONTACTS ###### SIZE: "+contacts_component.getPhoneList().size());
               
                
                listComponent.add(contacts_component);            
            }
            
            
            //DESCRIPTION COMPONENT
            if(prod.getDescrizione() != null && !prod.getDescrizione().trim().equals("")) {
                ArrayList<Section> list = new ArrayList<Section>();
                
                //descrizione
                Section section = new Section();
                section.setDescription(prod.getDescrizione());
                list.add(section);
                System.out.println("Desc: " + section.getDescription());
                
                //dati storici
                if(prod.getDatiStorici() != null && !prod.getDatiStorici().trim().equals("")){
                    Section section_d = new Section();
                    section_d.setTitle("Dati Storici");
                    section_d.setDescription(prod.getDatiStorici());
                    list.add(section_d);
                    System.out.println("Dati Sto: " + section_d.getDescription());
                }

                //curiosità
                if(prod.getCuriosita() != null && !prod.getCuriosita().trim().equals("")){
                    Section section_c = new Section();
                    section_c.setTitle("Curiosità");
                    section_c.setDescription(prod.getCuriosita());
                    list.add(section_c); 
                    System.out.println("Curiosita: " + section_c.getDescription());
                }
                
                
                //note
                if(prod.getNoteList() != null && prod.getNoteList().size() > 0 ){
                    
                    for(String n : prod.getNoteList()){
                        
                        if(!n.trim().equals("")){

                            Section section_n = new Section();
                            section_n.setTitle("Note");
                            section_n.setDescription(n);
                            list.add(section_n);  

                            System.out.println("Note: " + section_n.getDescription());
                        }
                    }
                    
                
                }
                 
                DescriptionComponent description_component = new DescriptionComponent();
                description_component.setSectionsList(list);
                listComponent.add(description_component);
             }            
            
            p.setComponents(listComponent);
            
            pm.savePoi(p);
            
            System.out.println(prod.getDenominazione());
	}
        
        return "ok";
        
    }
}
