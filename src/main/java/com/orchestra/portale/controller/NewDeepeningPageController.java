/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.CoverImgComponent;
import com.orchestra.portale.persistence.mongo.documents.DeepeningPage;
import com.orchestra.portale.persistence.mongo.documents.DescriptionComponent;
import com.orchestra.portale.persistence.mongo.documents.ImgGallery;
import com.orchestra.portale.persistence.mongo.documents.ImgGalleryComponent;
import com.orchestra.portale.persistence.mongo.documents.LinkedPoi;
import com.orchestra.portale.persistence.mongo.documents.LinkedPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.Section;
import com.orchestra.portale.utils.CouplePOI;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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
@RequestMapping(value = "/admin")
public class NewDeepeningPageController {

    
    @RequestMapping("/newdpage")
    public ModelAndView newdpage() {
        PersistenceManager pm = new ConcretePersistenceManager( LocaleContextHolder.getLocale().getDisplayLanguage() );
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
        
       ModelAndView model= new ModelAndView("dpageform");
       model.addObject("lista", lista);
       model.addObject("lista2", lista2);
       return model;
        

    }

    @RequestMapping(value = "/savedpage", method = RequestMethod.POST)
    public ModelAndView savedpage(HttpServletRequest request, @RequestParam Map<String, String> params, @RequestParam("cover") MultipartFile cover, @RequestParam("file") MultipartFile[] files) throws InterruptedException {
        ModelAndView model = new ModelAndView("okpageadmin");
        PersistenceManager pm = new ConcretePersistenceManager( LocaleContextHolder.getLocale().getDisplayLanguage() );
        DeepeningPage dp = new DeepeningPage();
        dp.setName(params.get("name"));
                System.out.println(params.get("name"));
                
        int i = 1;
        ArrayList<String> categories = new ArrayList<String>();
        while (params.containsKey("category" + i)) {

            categories.add(params.get("category" + i));

            i = i + 1;
        }
        dp.setCategories(categories);

        ArrayList<AbstractPoiComponent> listComponent = new ArrayList<AbstractPoiComponent>();

        //componente cover
        if (!cover.isEmpty()) {
            CoverImgComponent coverimg = new CoverImgComponent();
            coverimg.setLink("cover.jpg");
            listComponent.add(coverimg);
        }
        //componente galleria immagini
        ArrayList<ImgGallery> links = new ArrayList<ImgGallery>();
        if (files.length > 0) {
            ImgGalleryComponent img_gallery = new ImgGalleryComponent();

            i = 0;
            while (i < files.length) {
                ImgGallery img = new ImgGallery();

                Thread.sleep(100);
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhmmssSSa");
                String currentTimestamp = sdf.format(date);
                img.setLink("img_" + currentTimestamp + ".jpg");
                if (params.containsKey("credit" + (i + 1))) {
                    img.setCredit(params.get("credit" + (i + 1)));
                }

                i = i + 1;
                links.add(img);
            }
            img_gallery.setLinks(links);
            listComponent.add(img_gallery);
        }
        LinkedPoiComponent lpc = new LinkedPoiComponent();
            ArrayList<LinkedPoi> alp = new ArrayList<LinkedPoi>();
            i = 1;
            int k = 0;
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
            dp.setComponents(listComponent);

            pm.saveDeepeningPage(dp);

            DeepeningPage poi2 = pm.findDeepeningPageByName(dp.getName());

            for (int z = 0; z < files.length; z++) {
                MultipartFile file = files[z];

                try {
                    byte[] bytes = file.getBytes();

                    // Creating the directory to store file
                    HttpSession session = request.getSession();
                    ServletContext sc = session.getServletContext();

                    File dir = new File(sc.getRealPath("/") + "dist" + File.separator + "dpage" + File.separator + "img" + File.separator + poi2.getId());
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    // Create the file on server
                    File serverFile = new File(dir.getAbsolutePath() + File.separator + links.get(z).getLink());
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();

                } catch (Exception e) {

                    model.addObject("mess", "ERRORE!");
                    return model;
                }
            }
            MultipartFile file = cover;

            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                HttpSession session = request.getSession();
                    ServletContext sc = session.getServletContext();

                File dir = new File(sc.getRealPath("/") + "dist" + File.separator + "dpage" + File.separator + "img" + File.separator + poi2.getId());
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
            model.addObject("mess", "PAGINA INSERITA CORRETTAMENTE!");
        return model;
        }
        
    }

