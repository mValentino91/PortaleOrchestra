/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompactWorkingDays;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_It;
import com.orchestra.portale.persistence.mongo.documents.ContactsComponent;
import com.orchestra.portale.persistence.mongo.documents.CoverImgComponent;
import com.orchestra.portale.persistence.mongo.documents.DeepeningPage;
import com.orchestra.portale.persistence.mongo.documents.DescriptionComponent;
import com.orchestra.portale.persistence.mongo.documents.EmailContact;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_En;
import com.orchestra.portale.persistence.mongo.documents.EventsDateComponent;
import com.orchestra.portale.persistence.mongo.documents.EventsDates;
import com.orchestra.portale.persistence.mongo.documents.EventsHours;
import com.orchestra.portale.persistence.mongo.documents.FaxContact;
import com.orchestra.portale.persistence.mongo.documents.GenericSocial;
import com.orchestra.portale.persistence.mongo.documents.ImgGalleryComponent;
import com.orchestra.portale.persistence.mongo.documents.ImgGallery;
import com.orchestra.portale.persistence.mongo.documents.LinkedPoi;
import com.orchestra.portale.persistence.mongo.documents.LinkedPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.PhoneContact;
import com.orchestra.portale.persistence.mongo.documents.PricesComponent;
import com.orchestra.portale.persistence.mongo.documents.Section;
import com.orchestra.portale.persistence.mongo.documents.ServicesComponent;
import com.orchestra.portale.persistence.mongo.documents.TicketPrice;
import com.orchestra.portale.persistence.mongo.documents.WorkingHours;
import com.orchestra.portale.persistence.mongo.documents.WorkingTimeComponent;
import com.orchestra.portale.utils.CouplePOI;
import com.orchestra.portale.utils.InsertUtils;
import static com.orchestra.portale.utils.InsertUtils.delimg;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
@RequestMapping(value = "/admin")
public class EditEventController {

        @Autowired
    PersistenceManager pm ;
    @RequestMapping(value = "/editevent")
    public ModelAndView editPoi() {
        ModelAndView model = new ModelAndView("editevent");
        return model;
    }

    @RequestMapping(value = "/editevent", params = "name")
    public ModelAndView editPoi(@RequestParam(value = "name") String name) {
        ModelAndView model = new ModelAndView("editeventform");
        try {
            CompletePOI poi = pm.findOneCompletePoiByName(name);
            model.addObject("nome", poi.getName());
            model.addObject("loc", poi.getLocation());
            model.addObject("cat", poi.getCategories());
            model.addObject("id", poi.getId());
            model.addObject("shortD", poi.getShortDescription());
            model.addObject("addr", poi.getAddress());
            model.addObject("start", poi.getStart_date());
            model.addObject("end", poi.getEnd_date());

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
        } catch (RuntimeException e) {
            ModelAndView model2 = new ModelAndView("errorViewPoi");
            model2.addObject("err", "Errore impossibile trovare il poi: " + e.toString());
            return model2;
        }
    }

    @RequestMapping(value = "/editevent", params = "id")
    public ModelAndView editEventbyID(@RequestParam(value = "id") String id) {
        ModelAndView model = new ModelAndView("editeventform");
        ArrayList<CompletePOI> poilist = (ArrayList<CompletePOI>) pm.getAllCompletePoi();
        ArrayList<CouplePOI> lista = new ArrayList<CouplePOI>();
        ArrayList<CouplePOI> lista2 = new ArrayList<CouplePOI>();
        for (CompletePOI p : poilist) {
            CouplePOI temp = new CouplePOI();
            temp.setIdpoi(p.getId());
            temp.setNome(p.getName());
            temp.setType("Poi");
            lista.add(temp);
        }
        ArrayList<DeepeningPage> dpagelist = (ArrayList<DeepeningPage>) pm.findAllDeepeningPages();
        for (DeepeningPage dp : dpagelist) {
            CouplePOI temp = new CouplePOI();

            temp.setIdpoi(dp.getId());
            temp.setNome(dp.getName());
            temp.setType("DP");
            lista2.add(temp);
        }
        model.addObject("lista", lista);
        model.addObject("lista2", lista2);
        try {
            CompletePOI_It poi = (CompletePOI_It) pm.getCompletePoiById(id);
            model.addObject("nome", poi.getName());
            model.addObject("loc", poi.getLocation());
            model.addObject("cat", poi.getCategories());
            model.addObject("id", poi.getId());
            model.addObject("shortD", poi.getShortDescription());
            model.addObject("addr", poi.getAddress());
            model.addObject("start", poi.getStart_date());
            model.addObject("end", poi.getEnd_date());

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

            //RECUPERO POI INGLESE
            CompletePOI_En enpoi = (CompletePOI_En) pm.getCompletePoiById(id);
            model.addObject("ennome", enpoi.getName());
            model.addObject("enloc", enpoi.getLocation());
            model.addObject("encat", enpoi.getCategories());
            model.addObject("enshortD", enpoi.getShortDescription());
            model.addObject("enaddr", enpoi.getAddress());
            model.addObject("enstart", enpoi.getStart_date());
            model.addObject("enend", enpoi.getEnd_date());

            for (AbstractPoiComponent comp : enpoi.getComponents()) {

                //associazione delle componenti al model tramite lo slug
                String slug = comp.slug();
                int index = slug.lastIndexOf(".");
                String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
                cname = "en" + cname;
                try {
                    Class c = Class.forName(slug);
                    model.addObject(cname, c.cast(comp));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PoiViewController.class.getName()).log(Level.SEVERE, null, ex);

                }
            }

            return model;
        } catch (RuntimeException e) {
            ModelAndView model2 = new ModelAndView("errorViewPoi");
            model2.addObject("err", "Errore impossibile trovare il poi con id: " + id);
            return model2;
        }
    }

    @RequestMapping(value = "/updateevent", method = RequestMethod.POST)
    public ModelAndView updateEvent(HttpServletRequest request, @RequestParam Map<String, String> params, @RequestParam(value = "file", required = false) MultipartFile[] files, @RequestParam(value = "cover", required = false) MultipartFile cover, @RequestParam(value = "fileprec", required = false) String[] fileprec, @RequestParam(value = "imgdel", required = false) String[] imgdel) throws InterruptedException {
        CompletePOI_It poi = (CompletePOI_It) pm.getCompletePoiById(params.get("id"));
        CoverImgComponent coverimg = new CoverImgComponent();
        ArrayList<AbstractPoiComponent> listComponent = new ArrayList<AbstractPoiComponent>();
        for (AbstractPoiComponent comp : poi.getComponents()) {

            //associazione delle componenti al model tramite lo slug
            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            if (cname.equals("coverimg")) {
                coverimg = (CoverImgComponent) comp;
            }
        }
        ModelAndView model = new ModelAndView("editedpoi");

        poi.setId(params.get("id"));

        ModelAndView model2 = new ModelAndView("errorViewPoi");

        poi.setName(params.get("name"));
        poi.setVisibility("1");
        poi.setAddress(params.get("address"));
        double lat = Double.parseDouble(params.get("latitude"));
        double longi = Double.parseDouble(params.get("longitude"));
        poi.setLocation(new double[]{lat, longi});
        poi.setStart_date(params.get("datai"));
        poi.setEnd_date(params.get("dataf"));
        poi.setShortDescription(params.get("shortd"));
        int i = 1;
        ArrayList<String> categories = new ArrayList<String>();
        while (params.containsKey("category" + i)) {

            categories.add(params.get("category" + i));

            model.addObject("nome", categories.get(i - 1));
            i = i + 1;
        }
        poi.setCategories(categories);

        //componente cover
        if (!cover.isEmpty()) {

            coverimg.setLink("cover.jpg");

        }
        listComponent.add(coverimg);
        //componente galleria immagini
        ImgGalleryComponent img_gallery = new ImgGalleryComponent();
        ArrayList<ImgGallery> links = new ArrayList<ImgGallery>();
        i = 0;

        if (files != null && files.length > 0) {
            while (i < files.length) {
                ImgGallery img = new ImgGallery();
                Thread.sleep(100);
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhmmssSSa");
                String currentTimestamp = sdf.format(date);
                img.setLink("img_" + currentTimestamp + ".jpg");
                if (params.containsKey("newcredit" + (i + 1))) {
                    img.setCredit(params.get("newcredit" + (i + 1)));
                }
                i = i + 1;
                links.add(img);
            }
        }
        int iximg = 0;
        if (fileprec != null && fileprec.length > 0) {
            while (iximg < fileprec.length) {
                ImgGallery img = new ImgGallery();
                img.setLink(fileprec[iximg]);
                if (params.containsKey("credit" + (iximg + 1))) {
                    img.setCredit(params.get("credit" + (iximg + 1)));
                }
                iximg = iximg + 1;
                links.add(img);
            }
        }
        if ((fileprec != null && fileprec.length > 0) || (files != null && files.length > 0)) {
            img_gallery.setLinks(links);
            listComponent.add(img_gallery);
        }
        //componente contatti
        ContactsComponent contacts_component = new ContactsComponent();
        //Recapiti telefonici
        i = 1;
        boolean contacts = false;
        if (params.containsKey("tel" + i)) {
            ArrayList<PhoneContact> phoneList = new ArrayList<PhoneContact>();

            while (params.containsKey("tel" + i)) {
                PhoneContact phone = new PhoneContact();
                if (params.containsKey("tel" + i)) {
                    phone.setLabel(params.get("desctel" + i));
                }
                phone.setNumber(params.get("tel" + i));
                phoneList.add(phone);
                i = i + 1;
            }
            contacts = true;
            contacts_component.setPhoneList(phoneList);
        }
        //Recapiti mail
        i = 1;
        if (params.containsKey("email" + i)) {
            ArrayList<EmailContact> emailList = new ArrayList<EmailContact>();

            while (params.containsKey("email" + i)) {
                EmailContact email = new EmailContact();
                if (params.containsKey("email" + i)) {
                    email.setLabel(params.get("descemail" + i));
                }
                email.setEmail(params.get("email" + i));
                emailList.add(email);
                i = i + 1;
            }
            contacts = true;
            contacts_component.setEmailsList(emailList);
        }
        //Recapiti fax
        i = 1;
        if (params.containsKey("fax" + i)) {
            ArrayList<FaxContact> faxList = new ArrayList<FaxContact>();

            while (params.containsKey("fax" + i)) {
                FaxContact fax = new FaxContact();
                if (params.containsKey("fax" + i)) {
                    fax.setLabel(params.get("descfax" + i));
                }
                fax.setFax(params.get("fax" + i));
                faxList.add(fax);
                i = i + 1;
            }
            contacts = true;
            contacts_component.setFaxList(faxList);
        }
        //Social predefiniti
        i = 1;
        if (params.containsKey("SN" + i)) {

            while (params.containsKey("SN" + i)) {
                if (params.get("SN" + i).equals("facebook")) {
                    contacts = true;
                    contacts_component.setFacebook(params.get("LSN" + i));
                }
                if (params.get("SN" + i).equals("twitter")) {
                    contacts = true;
                    contacts_component.setTwitter(params.get("LSN" + i));
                }
                if (params.get("SN" + i).equals("google")) {
                    contacts = true;
                    contacts_component.setGoogle(params.get("LSN" + i));
                }
                if (params.get("SN" + i).equals("skype")) {
                    contacts = true;
                    contacts_component.setSkype(params.get("LSN" + i));
                }
                i = i + 1;
            }
        }
        //Social personalizzati
        i = 1;
        if (params.containsKey("CSN" + i)) {
            ArrayList<GenericSocial> customsocial = new ArrayList<GenericSocial>();

            while (params.containsKey("CSN" + i)) {
                GenericSocial social = new GenericSocial();
                contacts = true;
                social.setLabel(params.get("CSN" + i));
                social.setSocial(params.get("LCSN" + i));
                customsocial.add(social);
                i = i + 1;
            }
            contacts_component.setSocialList(customsocial);
        }
        if (contacts == true) {
            listComponent.add(contacts_component);
        }
        //DESCRIPTION COMPONENT
        i = 1;
        if (params.containsKey("par" + i)) {
            ArrayList<Section> list = new ArrayList<Section>();

            while (params.containsKey("par" + i)) {
                Section section = new Section();
                if (params.containsKey("titolo" + i)) {
                    section.setTitle(params.get("titolo" + i));
                }
                section.setDescription(params.get("par" + i));
                list.add(section);
                i = i + 1;

            }
            DescriptionComponent description_component = new DescriptionComponent();
            description_component.setSectionsList(list);
            listComponent.add(description_component);
        }
        //Orari
        i = 1;
        int k = 1;
        boolean ok = false;
        String gg = "";
        boolean[] aperto = new boolean[8];

        EventsDateComponent workingtime = new EventsDateComponent();
        if (params.containsKey("WD" + i + "start" + k + "H")) {
            ArrayList<EventsDates> workingdays = new ArrayList<EventsDates>();

            while (params.containsKey("WD" + i)) {
                ArrayList<EventsHours> Listwh = new ArrayList<EventsHours>();
                k = 1;
                while (params.containsKey("WD" + i + "start" + k + "H")) {
                    EventsHours wh = new EventsHours();
                    wh.setStart(params.get("WD" + i + "start" + k + "H") + ":" + params.get("WD" + i + "start" + k + "M"));
                    wh.setEnd(params.get("WD" + i + "end" + k + "H") + ":" + params.get("WD" + i + "end" + k + "M"));
                    Listwh.add(wh);
                    k = k + 1;
                }
                EventsDates cwd = new EventsDates();
                cwd.setDate(params.get("WD" + i));
                if (params.containsKey("WDT" + i)) {
                    cwd.setText(params.get("WDT" + i));
                }
                cwd.setHours(Listwh);
                workingdays.add(cwd);
                i = i + 1;
            }

            workingtime.setDates(workingdays);
            listComponent.add(workingtime);
        }
        LinkedPoiComponent lpc = new LinkedPoiComponent();
        ArrayList<LinkedPoi> alp = new ArrayList<LinkedPoi>();
        i = 1;
        while (params.containsKey("mot" + i)) {
            k = 1;
            ArrayList<CouplePOI> apoi = new ArrayList<CouplePOI>();
            while (params.containsKey("COL" + i + "-" + k)) {

                CouplePOI cpoi = new CouplePOI();
                String temp = params.get("COL" + i + "-" + k);
                cpoi.setIdpoi(temp.substring(0, temp.indexOf("|")));
                temp = temp.substring(temp.indexOf("|") + 1, temp.length());
                cpoi.setType(temp.substring(0, temp.indexOf("|")));
                apoi.add(cpoi);
                k++;

            }
            LinkedPoi lp = new LinkedPoi();
            lp.setDescription(params.get("mot" + i));
            lp.setPoilist(apoi);
            alp.add(lp);
            i++;
        }
        lpc.setLinked(alp);
        if (params.containsKey("mot1")) {
            listComponent.add(lpc);
        }
        
        i = 1;
        if (params.containsKey("type" + i)) {

            PricesComponent pc = new PricesComponent();
            ArrayList<TicketPrice> tpList = new ArrayList<TicketPrice>();
            while (params.containsKey("type" + i)) {
                TicketPrice tp = new TicketPrice();
                tp.setType(params.get("type" + i));
                double dp = Double.parseDouble(params.get("price" + i));
                tp.setPrice(dp);
                tp.setType_description(params.get("typedesc" + i));
                tpList.add(tp);
                i = i + 1;
            }
            pc.setPrices(tpList);
            listComponent.add(pc);
        }

        i = 1;
        if (params.containsKey("SERV" + i)) {
            ArrayList<String> servList = new ArrayList<String>();
            while (params.containsKey("SERV" + i)) {
                servList.add(params.get("SERV" + i));
                i = i + 1;
            }
            ServicesComponent servicescomponent = new ServicesComponent();
            servicescomponent.setServicesList(servList);
            listComponent.add(servicescomponent);
        }
        poi.setComponents(listComponent);

        pm.savePoi(poi);

        CompletePOI poi2 = (CompletePOI_It) pm.findOneCompletePoiByName(poi.getName());

        // POI INGLESE 
        if (params.get("inglese").equals("true")) {
            addeng(params, poi2.getId(), coverimg, img_gallery);
        } else {
            CompletePOI_En enpoi = new CompletePOI_En();
            enpoi.setAddress(poi.getAddress());
            enpoi.setCategories(poi.getCategories());
            enpoi.setId(poi.getId());
            enpoi.setName(poi.getName());
            enpoi.setShortDescription(poi.getShortDescription());
            enpoi.setStart_date(poi.getStart_date());
            enpoi.setEnd_date(poi.getEnd_date());
            enpoi.setVisibility("1");
            double enlat = Double.parseDouble(params.get("latitude"));
            double enlongi = Double.parseDouble(params.get("longitude"));
            enpoi.setLocation(new double[]{enlat, enlongi});
            enpoi.setComponents(listComponent);

            pm.saveEnPoi(enpoi);
        }
        for (int z = 0; z < files.length; z++) {
            MultipartFile file = files[z];

            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                HttpSession session = request.getSession();
                ServletContext sc = session.getServletContext();

                File dir = new File(sc.getRealPath("/") + "dist" + File.separator + "poi" + File.separator + "img" + File.separator + poi2.getId());
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + links.get(z).getLink());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            } catch (Exception e) {
                return model;
            }
        }
        if (!cover.isEmpty()) {
            MultipartFile file = cover;

            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                HttpSession session = request.getSession();
                ServletContext sc = session.getServletContext();

                File dir = new File(sc.getRealPath("/") + "dist" + File.separator + "poi" + File.separator + "img" + File.separator + poi2.getId());
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + "cover.jpg");
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            } catch (Exception e) {
                return model;
            }
        }

        // DELETE IMMAGINI DA CANCELLARE
        if (imgdel != null && imgdel.length > 0) {
            for (int kdel = 0; kdel < imgdel.length; kdel++) {
                delimg(request, poi.getId(), imgdel[kdel]);
            }
        }
        return model;
    }

    public void addeng(Map<String, String> params, String id, CoverImgComponent cover, ImgGalleryComponent gallery) {
        CompletePOI_En enpoi = new CompletePOI_En();
        enpoi.setId(id);
        enpoi.setName(params.get("enname"));
        enpoi.setAddress(params.get("enaddress"));
        double lat = Double.parseDouble(params.get("enlatitude"));
        double longi = Double.parseDouble(params.get("enlongitude"));
        enpoi.setLocation(new double[]{lat, longi});
        enpoi.setShortDescription(params.get("enshortd"));
        enpoi.setStart_date(params.get("endatai"));
        enpoi.setEnd_date(params.get("endataf"));
        enpoi.setVisibility("1");
        int i = 1;
        ArrayList<String> categories = new ArrayList<String>();
        while (params.containsKey("encategory" + i)) {

            categories.add(params.get("encategory" + i));

            i = i + 1;
        }
        enpoi.setCategories(categories);

        ArrayList<AbstractPoiComponent> listComponent = new ArrayList<AbstractPoiComponent>();

        //componente contatti
        ContactsComponent contacts_component = new ContactsComponent();
        //Recapiti telefonici
        i = 1;
        boolean contacts = false;
        if (params.containsKey("entel" + i)) {
            ArrayList<PhoneContact> phoneList = new ArrayList<PhoneContact>();

            while (params.containsKey("entel" + i)) {
                PhoneContact phone = new PhoneContact();
                if (params.containsKey("entel" + i)) {
                    phone.setLabel(params.get("endesctel" + i));
                }
                phone.setNumber(params.get("entel" + i));
                phoneList.add(phone);
                i = i + 1;
            }
            contacts = true;
            contacts_component.setPhoneList(phoneList);
        }
        //Recapiti mail
        i = 1;
        if (params.containsKey("enemail" + i)) {
            ArrayList<EmailContact> emailList = new ArrayList<EmailContact>();

            while (params.containsKey("enemail" + i)) {
                EmailContact email = new EmailContact();
                if (params.containsKey("enemail" + i)) {
                    email.setLabel(params.get("endescemail" + i));
                }
                email.setEmail(params.get("enemail" + i));
                emailList.add(email);
                i = i + 1;
            }
            contacts = true;
            contacts_component.setEmailsList(emailList);
        }
        //Recapiti fax
        i = 1;
        if (params.containsKey("enfax" + i)) {
            ArrayList<FaxContact> faxList = new ArrayList<FaxContact>();

            while (params.containsKey("enfax" + i)) {
                FaxContact fax = new FaxContact();
                if (params.containsKey("enfax" + i)) {
                    fax.setLabel(params.get("endescfax" + i));
                }
                fax.setFax(params.get("enfax" + i));
                faxList.add(fax);
                i = i + 1;
            }
            contacts = true;
            contacts_component.setFaxList(faxList);
        }
        //Social predefiniti
        i = 1;
        if (params.containsKey("enSN" + i)) {

            while (params.containsKey("enSN" + i)) {
                if (params.get("enSN" + i).equals("facebook")) {
                    contacts = true;
                    contacts_component.setFacebook(params.get("enLSN" + i));
                }
                if (params.get("enSN" + i).equals("twitter")) {
                    contacts = true;
                    contacts_component.setTwitter(params.get("enLSN" + i));
                }
                if (params.get("enSN" + i).equals("google")) {
                    contacts = true;
                    contacts_component.setGoogle(params.get("enLSN" + i));
                }
                if (params.get("enSN" + i).equals("skype")) {
                    contacts = true;
                    contacts_component.setSkype(params.get("enLSN" + i));
                }
                i = i + 1;
            }
        }
        //Social personalizzati
        i = 1;
        if (params.containsKey("enCSN" + i)) {
            ArrayList<GenericSocial> customsocial = new ArrayList<GenericSocial>();

            while (params.containsKey("enCSN" + i)) {
                GenericSocial social = new GenericSocial();
                contacts = true;
                social.setLabel(params.get("enCSN" + i));
                social.setSocial(params.get("enLCSN" + i));
                customsocial.add(social);
                i = i + 1;
            }
            contacts_component.setSocialList(customsocial);
        }
        if (contacts == true) {
            listComponent.add(contacts_component);
        }
        //DESCRIPTION COMPONENT
        i = 1;
        if (params.containsKey("enpar" + i)) {
            ArrayList<Section> list = new ArrayList<Section>();

            while (params.containsKey("enpar" + i)) {
                Section section = new Section();
                if (params.containsKey("entitolo" + i)) {
                    section.setTitle(params.get("entitolo" + i));
                }
                section.setDescription(params.get("enpar" + i));
                list.add(section);
                i = i + 1;

            }
            DescriptionComponent description_component = new DescriptionComponent();
            description_component.setSectionsList(list);
            listComponent.add(description_component);
        }
        //Orari
        i = 1;
        int k = 1;

        EventsDateComponent workingtime = new EventsDateComponent();
        if (params.containsKey("enWD" + i + "start" + k + "H")) {
            ArrayList<EventsDates> workingdays = new ArrayList<EventsDates>();

            while (params.containsKey("enWD" + i)) {
                ArrayList<EventsHours> Listwh = new ArrayList<EventsHours>();
                k = 1;
                while (params.containsKey("enWD" + i + "start" + k + "H")) {
                    EventsHours wh = new EventsHours();
                    wh.setStart(params.get("enWD" + i + "start" + k + "H") + ":" + params.get("enWD" + i + "start" + k + "M"));
                    wh.setEnd(params.get("enWD" + i + "end" + k + "H") + ":" + params.get("enWD" + i + "end" + k + "M"));
                    Listwh.add(wh);
                    k = k + 1;
                }
                EventsDates cwd = new EventsDates();
                cwd.setDate(params.get("enWD" + i));
                if (params.containsKey("enWDT" + i)) {
                    cwd.setText(params.get("enWDT" + i));
                }
                cwd.setHours(Listwh);
                workingdays.add(cwd);
                i = i + 1;
            }

            workingtime.setDates(workingdays);
            listComponent.add(workingtime);
        }
        /*
         LinkedPoiComponent lpc = new LinkedPoiComponent();
         ArrayList<LinkedPoi> alp = new ArrayList<LinkedPoi>();
         i = 1;
         while (params.containsKey("enmot" + i)) {
         k = 1;
         ArrayList<String> aenpoi = new ArrayList<String>();
         while (params.containsKey("enCOL" + i + "-" + k)) {
         aenpoi.add(params.get("enCOL" + i + "-" + k));
         k++;
         }
         LinkedPoi lp = new LinkedPoi();
         lp.setDescription(params.get("enmot" + i));
         lp.setIdlist(aenpoi);
         alp.add(lp);
         i++;
         }
         lpc.setLinked(alp);
         if (params.containsKey("enmot1")) {
         listComponent.add(lpc);
         }
         */
        i = 1;
        if (params.containsKey("entype" + i)) {

            PricesComponent pc = new PricesComponent();
            ArrayList<TicketPrice> tpList = new ArrayList<TicketPrice>();
            while (params.containsKey("entype" + i)) {
                TicketPrice tp = new TicketPrice();
                tp.setType(params.get("entype" + i));
                double dp = Double.parseDouble(params.get("enprice" + i));
                tp.setPrice(dp);
                tp.setType_description(params.get("entypedesc" + i));
                tpList.add(tp);
                i = i + 1;
            }
            pc.setPrices(tpList);
            listComponent.add(pc);
        }

        i = 1;
        if (params.containsKey("enSERV" + i)) {
            ArrayList<String> servList = new ArrayList<String>();
            while (params.containsKey("enSERV" + i)) {
                servList.add(params.get("enSERV" + i));
                i = i + 1;
            }
            ServicesComponent servicescomponent = new ServicesComponent();
            servicescomponent.setServicesList(servList);
            listComponent.add(servicescomponent);
        }

        listComponent.add(cover);
        if(gallery != null && gallery.getLinks() != null)
        listComponent.add(gallery);
        enpoi.setComponents(listComponent);
         pm.saveEnPoi(enpoi);
    }
}
