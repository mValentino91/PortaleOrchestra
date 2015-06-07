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
import com.orchestra.portale.persistence.mongo.documents.DeepeningPage;
import com.orchestra.portale.persistence.mongo.documents.DescriptionComponent;
import com.orchestra.portale.persistence.mongo.documents.EmailContact;
import com.orchestra.portale.persistence.mongo.documents.EnCompletePOI;
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
@RequestMapping(value= "/admin")
public class EditPoiController {
    @Autowired
    PersistenceManager pm;

    @RequestMapping(value= "/editpoi")
    public ModelAndView editPoi() {
        ModelAndView model = new ModelAndView("editpoi");
        return model;
    }
    
    @RequestMapping(value= "/editpoi", params="name")
    public ModelAndView editPoi(@RequestParam(value = "name") String name) {
        ModelAndView model = new ModelAndView("editform");
        try {
        CompletePOI poi= pm.findOneCompletePoiByName(name);
       model.addObject("nome", poi.getName());
        model.addObject("loc", poi.getLocation());
        model.addObject("cat", poi.getCategories());
        model.addObject("id", poi.getId());
        model.addObject("shortD", poi.getShortDescription());
        model.addObject("addr", poi.getAddress());
        model.addObject("visibility", poi.getVisibility());
       
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
            model2.addObject("err", "Errore impossibile trovare il poi: "+ e.toString());
            return model2;
        }
    }
    
    @RequestMapping(value= "/editpoi", params="id")
    public ModelAndView editPoibyID(@RequestParam(value = "id") String id) {
       ModelAndView model = new ModelAndView("editform");
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
            CompletePOI poi = pm.getCompletePoiById(id);
            model.addObject("nome", poi.getName());
            model.addObject("loc", poi.getLocation());
            model.addObject("cat", poi.getCategories());
            model.addObject("id", poi.getId());
            model.addObject("shortD", poi.getShortDescription());
            model.addObject("addr", poi.getAddress());
            

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
            EnCompletePOI enpoi = pm.findEnCompletePoiById(id);
            if(enpoi != null) {
            model.addObject("ennome", enpoi.getName());
            model.addObject("enloc", enpoi.getLocation());
            model.addObject("encat", enpoi.getCategories());
            model.addObject("enshortD", enpoi.getShortDescription());
            model.addObject("enaddr", enpoi.getAddress());
           

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
            }

            return model;
        } catch (RuntimeException e) {
            ModelAndView model2 = new ModelAndView("errorViewPoi");
            model2.addObject("err", "Errore impossibile trovare il poi con id: " + id);
            return model2;
        }
    }
    
    @RequestMapping(value= "/updatepoi", method = RequestMethod.POST)
    public ModelAndView updatePoi(@RequestParam Map<String,String> params, @RequestParam(value="file", required=false) MultipartFile[] files, @RequestParam(value="cover", required=false) MultipartFile cover, @RequestParam(value="fileprec", required=false) String[] fileprec, @RequestParam(value="imgdel", required=false) String[] imgdel, HttpServletRequest request ) throws InterruptedException {
        
         CompletePOI poi = pm.getCompletePoiById(params.get("id"));
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
             i=1;
            int k=1;
             boolean ok=false;
              String gg="";
              boolean[] aperto= new boolean[8];
              for (int z=1; z<=7; z++){
                     aperto[z]=false;
                 }
             WorkingTimeComponent workingtime = new WorkingTimeComponent();
             if(params.containsKey("WD"+i+"start"+k+"H")){
                
                
                ok=true;
                ArrayList<CompactWorkingDays> workingdays = new ArrayList<CompactWorkingDays>();
               
                 while(params.containsKey("WD"+i)){
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
                        cwd.setDays(params.get("WD"+i));
                        cwd.setWorkinghours(Listwh);
                        workingdays.add(cwd);
                        i=i+1;
                 }
                 int grn=1;
                 ArrayList<CompactWorkingDays> wdef = new ArrayList<CompactWorkingDays>();
                 
                 for (int z=1; z<=7; z++){
                     aperto[z]=false;
                 }
                 while(grn<=7){
                     
                     for(CompactWorkingDays g : workingdays) {
                         
                         if (grn==1 && g.getDays().equals("Lunedì")){
                             aperto[1]=true;
                             wdef.add(g);
                         }
                         if (grn==2 && g.getDays().equals("Martedì")){
                             aperto[2]=true;
                             wdef.add(g);
                         }
                         if (grn==3 && g.getDays().equals("Mercoledì")){
                             aperto[3]=true;
                             wdef.add(g);
                         }
                         if (grn==4 && g.getDays().equals("Giovedì")){
                             aperto[4]=true;
                             wdef.add(g);
                         }
                         if (grn==5 && g.getDays().equals("Venerdì")){
                             aperto[5]=true;
                             wdef.add(g);
                         }
                         if (grn==6 && g.getDays().equals("Sabato")){
                             aperto[6]=true;
                             wdef.add(g);
                         }
                         if (grn==7 && g.getDays().equals("Domenica")){
                             aperto[7]=true;
                             wdef.add(g);
                         }
                         
                             
                     }
                     grn++;
                 }
                 workingtime.setWorkingdays(wdef);
                 
                 for(int z=1; z<=7; z++) {
                         if(aperto[z]==false && z==1 )
                             gg=gg+" "+"Lunedì";
                         if(aperto[z]==false && z==2 )
                             gg=gg+" "+"Martedì";
                         if(aperto[z]==false && z==3 )
                             gg=gg+" "+"Mercoledì";
                         if(aperto[z]==false && z==4 )
                             gg=gg+" "+"Giovedì";
                         if(aperto[z]==false && z==5 )
                             gg=gg+" "+"Venerdì";
                         if(aperto[z]==false && z==6 )
                             gg=gg+" "+"Sabato";
                         if(aperto[z]==false && z==7 )
                             gg=gg+" "+"Domenica";
                 }
                                 
                 if(!gg.equals("")){
                     ok=true;
                     workingtime.setWeekly_day_of_rest(gg);
                 }
             }
                 
                 i=1;
                 String ggs="";
                 while(params.containsKey("RDA"+i)){
                     ggs=ggs+" "+params.get("RDA"+i);
                     i=i+1;
                 }
                 if(!ggs.equals("")){
                     ok=true;
                     workingtime.setDays_of_rest(ggs);
                 }
                 if(ok){
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

        CompletePOI poi2 = (CompletePOI) pm.findOneCompletePoiByName(poi.getName());

        // POI INGLESE 
        if (params.containsKey("enname") && !params.get("enname").equals("")) {
            addeng(params, poi2.getId(), coverimg, img_gallery);
        } else {
            EnCompletePOI enpoi = new EnCompletePOI();
            enpoi.setAddress(poi.getAddress());
            enpoi.setCategories(poi.getCategories());
            enpoi.setId(poi.getId());
            enpoi.setName(poi.getName());
            enpoi.setShortDescription(poi.getShortDescription());
            
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
        
        EnCompletePOI enpoi = new EnCompletePOI();
        enpoi.setId(id);
        enpoi.setName(params.get("enname"));
        enpoi.setAddress(params.get("enaddress"));
        double lat = Double.parseDouble(params.get("enlatitude"));
        double longi = Double.parseDouble(params.get("enlongitude"));
        enpoi.setLocation(new double[]{lat, longi});
        enpoi.setShortDescription(params.get("enshortd"));
        
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
             i=1;
            int k=1;
             boolean ok=false;
              String gg="";
              boolean[] aperto= new boolean[8];
              for (int z=1; z<=7; z++){
                     aperto[z]=false;
                 }
             WorkingTimeComponent workingtime = new WorkingTimeComponent();
             if(params.containsKey("enWD"+i+"start"+k+"H")){
                
                
                ok=true;
                ArrayList<CompactWorkingDays> workingdays = new ArrayList<CompactWorkingDays>();
               
                 while(params.containsKey("enWD"+i)){
                      ArrayList<WorkingHours> Listwh = new ArrayList<WorkingHours>();
                     k=1;
                       while(params.containsKey("enWD"+i+"start"+k+"H")){
                           WorkingHours wh = new WorkingHours();
                        wh.setStart(params.get("enWD"+i+"start"+k+"H")+":"+params.get("enWD"+i+"start"+k+"M"));
                        wh.setEnd(params.get("enWD"+i+"end"+k+"H")+":"+params.get("enWD"+i+"end"+k+"M"));
                        Listwh.add(wh);
                        k=k+1;
                       }
                       CompactWorkingDays cwd = new CompactWorkingDays();
                        cwd.setDays(params.get("enWD"+i));
                        cwd.setWorkinghours(Listwh);
                        workingdays.add(cwd);
                        i=i+1;
                 }
                 int grn=1;
                 ArrayList<CompactWorkingDays> wdef = new ArrayList<CompactWorkingDays>();
                 
                 for (int z=1; z<=7; z++){
                     aperto[z]=false;
                 }
                 while(grn<=7){
                     
                     for(CompactWorkingDays g : workingdays) {
                         
                         if (grn==1 && g.getDays().equals("Lunedì")){
                             aperto[1]=true;
                             wdef.add(g);
                         }
                         if (grn==2 && g.getDays().equals("Martedì")){
                             aperto[2]=true;
                             wdef.add(g);
                         }
                         if (grn==3 && g.getDays().equals("Mercoledì")){
                             aperto[3]=true;
                             wdef.add(g);
                         }
                         if (grn==4 && g.getDays().equals("Giovedì")){
                             aperto[4]=true;
                             wdef.add(g);
                         }
                         if (grn==5 && g.getDays().equals("Venerdì")){
                             aperto[5]=true;
                             wdef.add(g);
                         }
                         if (grn==6 && g.getDays().equals("Sabato")){
                             aperto[6]=true;
                             wdef.add(g);
                         }
                         if (grn==7 && g.getDays().equals("Domenica")){
                             aperto[7]=true;
                             wdef.add(g);
                         }
                         
                             
                     }
                     grn++;
                 }
                 workingtime.setWorkingdays(wdef);
                 
                 for(int z=1; z<=7; z++) {
                         if(aperto[z]==false && z==1 )
                             gg=gg+" "+"Lunedì";
                         if(aperto[z]==false && z==2 )
                             gg=gg+" "+"Martedì";
                         if(aperto[z]==false && z==3 )
                             gg=gg+" "+"Mercoledì";
                         if(aperto[z]==false && z==4 )
                             gg=gg+" "+"Giovedì";
                         if(aperto[z]==false && z==5 )
                             gg=gg+" "+"Venerdì";
                         if(aperto[z]==false && z==6 )
                             gg=gg+" "+"Sabato";
                         if(aperto[z]==false && z==7 )
                             gg=gg+" "+"Domenica";
                 }
                                 
                 if(!gg.equals("")){
                     ok=true;
                     workingtime.setWeekly_day_of_rest(gg);
                 }
             }
                 
                 i=1;
                 String ggs="";
                 while(params.containsKey("enRDA"+i)){
                     ggs=ggs+" "+params.get("enRDA"+i);
                     i=i+1;
                 }
                 if(!ggs.equals("")){
                     ok=true;
                     workingtime.setDays_of_rest(ggs);
                 }
                 if(ok){
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
        listComponent.add(gallery);
        enpoi.setComponents(listComponent);
        pm.saveEnPoi(enpoi);
    }
}
