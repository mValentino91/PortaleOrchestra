
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CarouselPages;
import com.orchestra.portale.persistence.mongo.documents.CategorySubMenu;
import com.orchestra.portale.persistence.mongo.documents.ContactsComponent;
import com.orchestra.portale.persistence.mongo.documents.EmailContact;
import com.orchestra.portale.persistence.mongo.documents.Pages;
import com.orchestra.portale.persistence.mongo.documents.PhoneContact;
import com.orchestra.portale.persistence.mongo.documents.SubMenu;
import com.orchestra.portale.persistence.mongo.documents.Tile;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
public class NewPageController {


    @RequestMapping("/newpage")
    public ModelAndView newPage() {
        PersistenceManager pm = new ConcretePersistenceManager( LocaleContextHolder.getLocale().getDisplayLanguage() );
        ModelAndView model = new ModelAndView("insertpoi");

        Pages page = new Pages();

        page.setSlug("expo");
        ArrayList<CarouselPages> cpsl = new ArrayList<CarouselPages>();
        ArrayList<Tile> tilelist = new ArrayList<Tile>();
        page.setTitle("EXPO NAPOLI");
        page.setDescription("EXPO2015 rappresenta un evento fondamentale per il mondo.Il Comune di Napoli propone un calendario di iniziative ed eventi capaci di esaltare il protagonismo della città perché Expo è Milano ma è, contemporaneamente, tutta l'Italia.Per noi non poteva essere altrimenti, considerata la tradizione secolare che la città vanta nel campo dell'alimentazione e della sicurezza alimentare: paesaggi gastronomici celebri sin dall'antichità, filiere produttive di eccellenza note in tutto il mondo, prodotti tipici che sono i pilastri di quella “dieta mediterranea” proclamata nel 2010 Patrimonio Immateriale dell'Umanità dall'Unesco, pizza prossimo patrimonio del mondo, caffè sospeso, street food, aria e gioa. A Napoli il cibo è cultura, più che altrove. Ma a Napoli il cibo è anche storia, intelligenza, voglia di scoprire.");

        CarouselPages cps1= new CarouselPages();
         cps1.setLink("expo1.jpg");
         cpsl.add(cps1);
         page.setImgList(cpsl);
         
        Tile tile1 = new Tile();
        tile1.setColor("#D31E76");
        tile1.setLink("#");
        tilelist.add(tile1);

        Tile tile2 = new Tile();
        tile2.setColor("#A2DED0");
        tile2.setLink("#");
        tilelist.add(tile2);

        Tile tile3 = new Tile();
        tile3.setColor("#F9D800");
        tile3.setLink("#");
        tilelist.add(tile3);

        Tile tile4 = new Tile();
        tile4.setColor("#A2DED0");
        tile4.setLink("#");
        tilelist.add(tile4);

        Tile tile5 = new Tile();
        tile5.setColor("#009DE0");
        tile5.setLink("#");
        tilelist.add(tile5);

        Tile tile6 = new Tile();
        tile6.setColor("#F69B7C");
        tile6.setIcon("expo_tile.svg");
        tile6.setLink("#");
        
        tilelist.add(tile6);

        Tile tile7 = new Tile();
        tile7.setColor("#A2DED0");
        tile7.setLink("#");
        tilelist.add(tile7);

        Tile tile8 = new Tile();
        tile8.setColor("#A2DED0");
        tile8.setLink("#");
        tilelist.add(tile8);

        page.setTilesList(tilelist);

        CategorySubMenu csm = new CategorySubMenu();
        csm.setLink("#");
        csm.setText("Conoscere Napoli");

        CategorySubMenu csm2 = new CategorySubMenu();
        csm2.setLink("#");
        csm2.setText("Vivere Napoli");
        
        CategorySubMenu csm3 = new CategorySubMenu();
        csm3.setLink("#");
        csm3.setText("Gustare Napoli");     
        
        CategorySubMenu csm4 = new CategorySubMenu();
        csm4.setLink("#");
        csm4.setText("Visitare Napoli");     
        
        CategorySubMenu csm5 = new CategorySubMenu();
        csm5.setLink("#");
        csm5.setText("Progetti Speciali NAPOLIperEXPO");     

        ArrayList<CategorySubMenu> submenu = new ArrayList<CategorySubMenu>();
        submenu.add(csm);
        submenu.add(csm2);
        submenu.add(csm3);
        submenu.add(csm4);
        submenu.add(csm5);

        
        SubMenu sub = new SubMenu();
        sub.setCategories(submenu);
        sub.setColor("#e74c3c");
        page.setSubmenu(sub);
/*
        ContactsComponent cc = new ContactsComponent();
        PhoneContact pc = new PhoneContact();
        pc.setLabel("Ufficio");
        pc.setNumber("081081081");
        ArrayList<PhoneContact> pcl = new ArrayList<PhoneContact>();
        pcl.add(pc);
        cc.setPhoneList(pcl);
        EmailContact ec = new EmailContact();
        ec.setEmail("pippo@pluto.com");
        ec.setLabel("pippo");
        ArrayList<EmailContact> ecl = new ArrayList<EmailContact>();
        ecl.add(ec);
        cc.setEmailsList(ecl);
        cc.setFacebook("http://www.facebook.it");
        ArrayList<AbstractPoiComponent> abc = new ArrayList<AbstractPoiComponent>();
        abc.add(cc);
        page.setComponents(abc);
        *
        String[] slugs = new String[100];
        slugs[1] = "food";
        page.setCategorySlugList(slugs);

        CarouselPages cps1 = new CarouselPages();
        cps1.setLink("./dist/img/foto1.jpg");
        cps1.setTesto("Quanno mor'io, chiagnìteme nu quarto d'ora e basta. Che m'atterrate all'ùnnice?"
                + "Salute e bbene!  'e  ddoie menàte  'a pasta.(Eduardo Nicolardi)");
        
        CarouselPages cps2 = new CarouselPages();
        cps2.setLink("./dist/img/26 pasta riposata.jpg");

        CarouselPages cps3 = new CarouselPages();
        cps3.setLink("./dist/img/sorbillo.JPG");

        CarouselPages cps4 = new CarouselPages();
        cps4.setLink("./dist/img/la padella.JPG");

        CarouselPages cps5 = new CarouselPages();
        cps5.setLink("./dist/img/leopoldo.JPG");

        CarouselPages cps6 = new CarouselPages();
        cps6.setLink("./dist/img/baba.JPG");

        cpsl.add(cps1);
        cpsl.add(cps2);
        cpsl.add(cps3);
        cpsl.add(cps4);
        cpsl.add(cps5);
        cpsl.add(cps6);

        /*
         page.setSlug("culture");
         ArrayList<CarouselPages> cpsl= new ArrayList<CarouselPages>();
         ArrayList<Tile> tilelist= new ArrayList<Tile>();
         page.setTitle("Cultura");
         page.setDescription("La lunghissima storia della città ha fatto sì che nel corso dei secoli si stratificasse un immenso patrimonio di testimonianze storiche e artistiche. In particolare nel Centro Antico, ma non solo, dalla fondazione di Neapolis, avvenuta intorno al V secolo a.C., ai giorni nostri la continuità di vita ha prodotto un fitto tessuto di monumenti che spesso custodiscono al loro interno importanti opere d’arte. Di tanto in tanto fa capolino anche l’archeologia, offrendo esempi di siti urbani talvolta sorprendentemente ben conservati mentre la città di oggi, la Napoli contemporanea continua a destinare spazi all’arte. I musei non mancano di testimoniare la ricchezza di una lunga storia e la vivacità culturale di una città che ha a lungo svolto il ruolo di capitale.");
        
         Tile tile1 = new Tile();
         tile1.setColor("#F9D800");
         tile1.setText("Musei");
         tile1.setLink("#");
         tilelist.add(tile1);
        
         Tile tile2 = new Tile();
         tile2.setColor("#F5926F");
         tile2.setText("Monumenti");
         tile2.setLink("#");
         tilelist.add(tile2);
        
         Tile tile3 = new Tile();
         tile3.setColor("#d31e76");
         tile3.setText("Napoli contemporanea");
         tile3.setLink("#");
         tilelist.add(tile3);
        
         Tile tile4 = new Tile();
         tile4.setColor("#F5926F");
         tile4.setText("Archeologia");
         tile4.setLink("#");
         tilelist.add(tile4);
        
         Tile tile5 = new Tile();
         tile5.setColor("#009DE0");
         tile5.setLink("#");
         tilelist.add(tile5);
        
         Tile tile6 = new Tile();
         tile6.setColor("#F5926F");
         tile6.setLink("#");
         tilelist.add(tile6);
        
         Tile tile7 = new Tile();
         tile7.setColor("#27AE60");
         tile7.setText("TOP 10");
         tile7.setLink("#");
         tilelist.add(tile7);
        
         Tile tile8 = new Tile();
         tile8.setColor("#F5926F");
         tile8.setLink("#");
         tilelist.add(tile8);
        
         page.setTilesList(tilelist);
        
         CategorySubMenu csm = new CategorySubMenu();
        
         csm.setLink("#");
         csm.setText("Stazioni dell'arte");
        
         CategorySubMenu csm2 = new CategorySubMenu();
        
         csm2.setLink("#");
         csm2.setText("Le piazze");
        
         ArrayList<CategorySubMenu> submenu = new ArrayList<CategorySubMenu>();
         submenu.add(csm);
         submenu.add(csm2);
        
         SubMenu sub = new SubMenu();
         sub.setCategories(submenu);
         sub.setColor("#F5926F");
         page.setSubmenu(sub);
        
         ContactsComponent cc = new ContactsComponent();
         PhoneContact pc=new PhoneContact();
         pc.setLabel("Ufficio");
         pc.setNumber("081081081");
         ArrayList<PhoneContact> pcl= new ArrayList<PhoneContact>();
         pcl.add(pc);
         cc.setPhoneList(pcl);
         EmailContact ec= new EmailContact();
         ec.setEmail("pippo@pluto.com");
         ec.setLabel("pippo");
         ArrayList<EmailContact> ecl= new ArrayList<EmailContact>();
         ecl.add(ec);
         cc.setEmailsList(ecl);
         cc.setFacebook("http://www.facebook.it");
         ArrayList<AbstractPoiComponent> abc = new ArrayList<AbstractPoiComponent>();
         abc.add(cc);
         page.setComponents(abc);
         String [] slugs= new String [100];
         slugs[1]="museum";
         page.setCategorySlugList(slugs);
        
         CarouselPages cps1= new CarouselPages();
         cps1.setLink("./dist/img/Castel_Sant'_Elmo_e_Certosa_di_San_Martino_da_piazza_del_Plebiscito.jpg");
       
         CarouselPages cps2= new CarouselPages();
         cps2.setLink("./dist/img/02.jpg");
       
         CarouselPages cps3= new CarouselPages();
         cps3.setLink("./dist/img/053.jpg");
       
         CarouselPages cps4= new CarouselPages();
         cps4.setLink("./dist/img/1280px-Issus_-_Alexander.jpg");
      
       
         CarouselPages cps5= new CarouselPages();
         cps5.setLink("./dist/img/UNIVERSITA' 1.jpg");
       
         CarouselPages cps6= new CarouselPages();
         cps6.setLink("./dist/img/1024px-Boscoreale_cubiculum_H_left_side_painting_MMA.jpg");
       
         CarouselPages cps7= new CarouselPages();
         cps7.setLink("./dist/img/Immagine30.jpg");
        
         CarouselPages cps8= new CarouselPages();
         cps8.setLink("./dist/img/049.jpg");
        
         CarouselPages cps9= new CarouselPages();
         cps9.setLink("./dist/img/Immagine24.jpg");
        
        
         CarouselPages cps10= new CarouselPages();
         cps10.setLink("./dist/img/800px-ScavidiSanLorenzo2.jpg");
        
         CarouselPages cps11= new CarouselPages();
         cps11.setLink("./dist/img/cristo-velato-cappella-sansevero.jpg");
       
       
       
       
         cpsl.add(cps1);
         cpsl.add(cps2);
         cpsl.add(cps3);
         cpsl.add(cps4);
         cpsl.add(cps5);
         cpsl.add(cps6);
         cpsl.add(cps7);
         cpsl.add(cps8);
         cpsl.add(cps9);
         cpsl.add(cps10);
         cpsl.add(cps11);
        */ 
        String [] slugs= new String [100];
         slugs[1]="expo";
         page.setCategorySlugList(slugs);
         
        page.setImgList(cpsl);
        
        pm.savePage(page);

        return model;
    }
}
