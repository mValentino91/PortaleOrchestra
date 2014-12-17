/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompactWorkingDays;
import com.orchestra.portale.persistence.mongo.documents.CoverImgComponent;
import com.orchestra.portale.persistence.mongo.documents.DescriptionComponent;
import com.orchestra.portale.persistence.mongo.documents.ImageGalleryComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.ContactsComponent;
import com.orchestra.portale.persistence.mongo.documents.EmailContact;
import com.orchestra.portale.persistence.mongo.documents.PhoneContact;
import com.orchestra.portale.persistence.mongo.documents.PricesComponent;
import com.orchestra.portale.persistence.mongo.documents.Section;
import com.orchestra.portale.persistence.mongo.documents.TicketPrice;
import com.orchestra.portale.persistence.mongo.documents.TitleComponent;
import com.orchestra.portale.persistence.mongo.documents.WorkingHours;
import com.orchestra.portale.persistence.mongo.documents.WorkingTimeComponent;
import com.orchestra.portale.persistence.mongo.repositories.PoiMongoRepository;
import com.orchestra.portale.persistence.sql.repositories.CategoryRepository;
import com.orchestra.portale.persistence.sql.repositories.CompCategoryComponentRepository;
import com.orchestra.portale.persistence.sql.repositories.CompPoiCategoryRepository;
import com.orchestra.portale.persistence.sql.repositories.ComponentRepository;
import com.orchestra.portale.persistence.sql.repositories.PoiRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller per effettuare test. Le chiamate vanno effettuate in sequenza
 * test1, test2, test3
 *
 * @author mekko
 */
@Controller
public class TestController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ComponentRepository componentRepository;

    @Autowired
    CompCategoryComponentRepository compCategoryComponentRepository;

    @Autowired
    PoiRepository poiRepository;

    @Autowired
    CompPoiCategoryRepository compPoiCategoryRepository;

    @Autowired
    MongoOperations mongoOps;

    @Autowired
    PoiMongoRepository poiMongoRepo;

    @RequestMapping("/elimina")
    public ModelAndView elimina() {
        ModelAndView model = new ModelAndView("testview");
        String msg = "ok";

        try {
        
            poiMongoRepo.deleteAll();

        } catch (RuntimeException e) {
            msg = "error";
            e.printStackTrace();
        }

        model.addObject("msg", msg);

        return model;
    }

    @RequestMapping("/insertMongo")
    public ModelAndView insertSectionsList() {

        ModelAndView model = new ModelAndView("testview");
        String msg = "ok";

        try {
            
            CompletePOI poi = new CompletePOI();
            
            poi.setName("Pio Monte Della Misericordia");
            ArrayList<String> categories=new ArrayList<String>();
            categories.add("Museo");
            poi.setCategories(categories);
            poi.setLocation(new double[] { 40.85123, 14.258117 });
            poi.setAddress("Via dei Tribunali 253, 80138 Napoli");
            poi.setShortDescription("Bellissimo posto!");
            
            ArrayList<AbstractPoiComponent> listComponent = new ArrayList<AbstractPoiComponent>();
            
            //componente DescriptionComponent
            ArrayList<Section> list = new ArrayList<Section>();
            Section section0 = new Section();
            section0.setTitle("Titolo di prova");
            section0.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus laoreet vestibulum pellentesque. Nulla dictum quam eu tellus malesuada vestibulum. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nulla facilisi. Mauris congue felis placerat sapien aliquam, id vulputate sapien lacinia. Maecenas tempus felis justo. Pellentesque magna tellus, bibendum in elit ut, porttitor pharetra elit. Morbi dictum, est id tincidunt vehicula, lorem nunc aliquam nisi, at vulputate erat quam ut quam. Suspendisse tempus ipsum nunc, ac luctus eros posuere eget. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras eu nulla sit amet mauris sagittis tristique non a urna. Aliquam in massa sed nunc iaculis faucibus in vitae diam.");
            
            Section section = new Section();
            section.setTitle("Titolo1");
            section.setDescription("Descrizion1");
            Section section1 = new Section();
            section1.setTitle("Titolo2");
            section1.setDescription("Descrizion2");
            list.add(section0);
            list.add(section);
            list.add(section1);
            DescriptionComponent description_component = new DescriptionComponent();
            description_component.setSectionsList(list);
            listComponent.add(description_component);
            //componente contatti
            ContactsComponent contacts_component = new ContactsComponent();
             //Recapiti telefonici
            ArrayList<PhoneContact> phoneList= new ArrayList<PhoneContact>();
            PhoneContact phone=new PhoneContact();
            phone.setLabel("Telefono");
            phone.setNumber("081123456789");
            PhoneContact phone1=new PhoneContact();
            phone1.setLabel("Telefono1");
            phone1.setNumber("081555666777");
            phoneList.add(phone);
            phoneList.add(phone1);
            contacts_component.setPhoneList(phoneList);
             //Indirizzi email
            ArrayList<EmailContact> emailList= new ArrayList<EmailContact>();
            EmailContact email=new EmailContact();
            email.setLabel("Email");
            email.setEmail("email@email.com");
            EmailContact email1=new EmailContact();
            email1.setLabel("Email1");
            email1.setEmail("email1@email.com");
            emailList.add(email);
            emailList.add(email1);
            contacts_component.setEmailsList(emailList);
            //facebook & twitter
            contacts_component.setFacebook("www.facebook.com/pagina");
            contacts_component.setTwitter("www.twitter.com/pagina");
            
            listComponent.add(contacts_component);
            //ORARI E PREZZI
            
            //Orari Di lavoro compattati
            WorkingHours wh = new WorkingHours();
            wh.setStart("9:00");
            wh.setEnd("13:00");
             WorkingHours wh2 = new WorkingHours();
            wh2.setStart("16:00");
            wh2.setEnd("19:00");
            CompactWorkingDays cwd = new CompactWorkingDays();
            cwd.setDays("Lun-Mer");
            ArrayList<WorkingHours> Listwh = new ArrayList<WorkingHours>();
            Listwh.add(wh);
            Listwh.add(wh2);
            cwd.setWorkinghours(Listwh);
            
            WorkingHours wh3 = new WorkingHours();
            wh3.setStart("15:00");
            wh3.setEnd("18:00");
            CompactWorkingDays cwd2 = new CompactWorkingDays();
            cwd2.setDays("Giovedì");
            ArrayList<WorkingHours> Listwh2 = new ArrayList<WorkingHours>();
            Listwh2.add(wh3);
            cwd2.setWorkinghours(Listwh2);
            
            WorkingHours wh4 = new WorkingHours();
            wh4.setStart("10:00");
            wh4.setEnd("19:00");
            
            CompactWorkingDays cwd3 = new CompactWorkingDays();
            cwd3.setDays("Ven-Sab");
            ArrayList<WorkingHours> Listwh3 = new ArrayList<WorkingHours>();
            Listwh3.add(wh4);
            cwd3.setWorkinghours(Listwh3);
            
            ArrayList<CompactWorkingDays> workingdays = new ArrayList<CompactWorkingDays>();
            workingdays.add(cwd);
            workingdays.add(cwd2);
            workingdays.add(cwd3);
            WorkingTimeComponent workingtime = new WorkingTimeComponent();
            workingtime.setWorkingdays(workingdays);
            workingtime.setWeekly_day_of_rest("Domenica");
            ArrayList<String> days_of_rest= new ArrayList<String>();
            days_of_rest.add("25/12");
            days_of_rest.add("26/12");
            days_of_rest.add("1/1");
            //workingtime.setDays_of_rest(days_of_rest);
            listComponent.add(workingtime);
            
            //ORARI ESTESI(DA FARE)
            
            //PREZZI
            TicketPrice tp=new TicketPrice();
            tp.setPrice(8.50);
            tp.setType("Intero");
            tp.setType_description("Prezzo biglietto intero");
            
            TicketPrice tp2=new TicketPrice();
            tp2.setPrice(4);
            tp2.setType("Ridotto");
            tp2.setType_description("Biglietto per ragazzi fino ai 12 anni");
            
            PricesComponent pc= new PricesComponent();
            ArrayList<TicketPrice> tpList = new ArrayList<TicketPrice>();
            tpList.add(tp);
            tpList.add(tp2);
            pc.setPrices(tpList);
            listComponent.add(pc);
           
            
            
            //componente titolo
            TitleComponent title=new TitleComponent();
            title.setTitle("Pio Monte Della Misericordia");
            listComponent.add(title);
            
            //componente cover
            CoverImgComponent cover=new CoverImgComponent();
            cover.setLink("pm1.jpg");
            listComponent.add(cover);
            
            //componente galleria immagini
           ImageGalleryComponent img_gallery=new ImageGalleryComponent();
           ArrayList<String> links=new ArrayList<String>();
           links.add("pm1.jpg");
           links.add("pm2.jpg");
           links.add("pm3.jpg");
           img_gallery.setLinks(links);
           listComponent.add(img_gallery);

           poi.setComponents(listComponent);

            poiMongoRepo.save(poi);
        } catch (RuntimeException e) {
            msg = "error";
            e.printStackTrace();
        }

        model.addObject("msg", msg);

        return model;
    }
}
