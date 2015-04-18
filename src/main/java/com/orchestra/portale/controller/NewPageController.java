/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
public class NewPageController {
    @Autowired
    PersistenceManager pm;
    
    @RequestMapping("/newpage")
    public ModelAndView newPage() {
        ModelAndView model = new ModelAndView("insertpoi");
        
        Pages page= new Pages();
        page.setSlug("provapag");
        ArrayList<Tile> tilelist= new ArrayList<Tile>();
        page.setTitle("TEST8");
        page.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut mollis malesuada vehicula. Maecenas ex ipsum, facilisis sit amet magna et, sollicitudin mattis sem. Aenean placerat velit maximus, sodales tortor quis, ornare nulla. Curabitur varius consequat finibus. Donec vulputate auctor laoreet. Sed convallis erat sed ipsum imperdiet luctus. Fusce aliquet tempor erat sit amet convallis. Sed convallis auctor erat id commodo. Sed ac purus tempor, pharetra elit sit amet, pellentesque justo. Proin at lectus nulla. Suspendisse iaculis orci ut ante semper pulvinar. Praesent ullamcorper, nisl a vulputate tincidunt, mi est imperdiet mi, eu molestie ex sapien sed mi. Sed sit amet finibus arcu. Quisque aliquam risus urna, ut semper ante accumsan vel. Duis eget porttitor lectus. Aenean laoreet, elit vel efficitur ullamcorper, risus elit sodales neque, quis hendrerit nunc ipsum ac libero. Nam a iaculis justo. Vivamus interdum euismod lacus id vulputate. Nam a semper ipsum, at ultricies arcu. Ut sagittis purus nec condimentum suscipit. Etiam vel interdum ante. Praesent non orci nisl. Integer tortor enim, vulputate vitae erat eu, efficitur pretium est. Aliquam congue id velit id tincidunt. In gravida condimentum purus, in maximus arcu aliquet in.");
        
        Tile tile1 = new Tile();
        tile1.setColor("#F9D800");
        tile1.setIcon("../dist/img/pizza_.png");
        tile1.setLink("http://www.pollo.com");
        tilelist.add(tile1);
        
        Tile tile2 = new Tile();
        tile2.setColor("#F5926F");
        tile2.setText("TEST1");
        tile2.setLink("http://www.pollo.com");
        tilelist.add(tile2);
        
        Tile tile3 = new Tile();
        tile3.setColor("#d31e76");
        tile3.setIcon("../dist/img/coffee.png");
        tile3.setLink("http://www.pollo.com");
        tilelist.add(tile3);
        
        Tile tile4 = new Tile();
        tile4.setColor("#F5926F");
        tile4.setText("TEST2");
        tile4.setLink("http://www.pollo.com");
        tilelist.add(tile4);
        
        Tile tile5 = new Tile();
        tile5.setColor("#009DE0");
        tile5.setIcon("../dist/img/spaghetti.png");
        tile5.setLink("http://www.pollo.com");
        tilelist.add(tile5);
        
        Tile tile6 = new Tile();
        tile6.setColor("#F5926F");
        tile6.setText("TEST3");
        tile6.setLink("http://www.pollo.com");
        tilelist.add(tile6);
        
        Tile tile7 = new Tile();
        tile7.setColor("#27AE60");
        tile7.setIcon("../dist/img/calendar.png");
        tile7.setLink("http://www.pollo.com");
        tilelist.add(tile7);
        
        Tile tile8 = new Tile();
        tile8.setColor("#F5926F");
        tile8.setText("TEST4");
        tile8.setLink("http://www.pollo.com");
        tilelist.add(tile8);
        
        page.setTilesList(tilelist);
        
        CategorySubMenu csm = new CategorySubMenu();
        
        csm.setLink("http://www.pippo.com");
        csm.setText("MUSEI");
        
        CategorySubMenu csm2 = new CategorySubMenu();
        
        csm2.setLink("http://www.pippo.com");
        csm2.setText("MUSEI");
        
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
        
       CarouselPages cps= new CarouselPages();
       cps.setLink("./dist/img/c_pizza.jpg");
       cps.setTesto("Ciao andrea magnt a pizz");
       cps.setTitolo("Pizza pazza");
       ArrayList<CarouselPages> cpsl= new ArrayList<CarouselPages>();
       cpsl.add(cps);
       page.setImgList(cpsl);
       pm.savePage(page);
        
        return model;
    }
}
