/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CarouselPages;
import com.orchestra.portale.persistence.mongo.documents.CategorySubMenu;
import com.orchestra.portale.persistence.mongo.documents.Home;
import com.orchestra.portale.persistence.mongo.documents.SubMenu;
import com.orchestra.portale.persistence.mongo.documents.Tile;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
public class NewHomeController {
    @Autowired 
    PersistenceManager pm;
    
    @RequestMapping("/newhome")
    public ModelAndView newHome() {
        ModelAndView model = new ModelAndView("insertpoi");
        Home home= new Home();
        home.setDescription("Questa è la home");
        home.setTitle("Home");
        home.setSlug("home");
        
        ArrayList<Tile> tilelist= new ArrayList<Tile>();
        
        Tile tile1 = new Tile();
        tile1.setColor("#F69B7C");
        tile1.setIcon("./dist/img/temple.png");
        tile1.setLink("http://www.pollo.com");
        tilelist.add(tile1);
        
        Tile tile3 = new Tile();
        tile3.setColor("#F7DA65");
        tile3.setIcon("./dist/img/posate.png");
        tile3.setLink("http://www.pollo.com");
        tilelist.add(tile3);
       
        Tile tile5 = new Tile();
        tile5.setColor("#89C8F4");
        tile5.setIcon("./dist/img/sleeping.png");
        tile5.setLink("http://www.pollo.com");
        tilelist.add(tile5);
        
        Tile tile7 = new Tile();
        tile7.setColor("#CDABDE");
        tile7.setIcon("./dist/img/hand.png");
        tile7.setLink("http://www.pollo.com");
        tilelist.add(tile7);
        
        Tile tile8 = new Tile();
        tile8.setColor("#9FDC96");
        tile8.setIcon("./dist/img/bicycle.png");
        tile8.setLink("http://www.pollo.com");
        tilelist.add(tile8);
        
        Tile tile9 = new Tile();
        tile9.setColor("#CDABDE");
        tile9.setText("EXPO");
        tile9.setLink("http://www.pollo.com");
        tilelist.add(tile9);
        
        Tile tile10 = new Tile();
        tile10.setColor("#B9B9B9");
        tile10.setText("VIDEO");
        tile10.setLink("http://www.pollo.com");
        tilelist.add(tile10);
        
        Tile tile11 = new Tile();
        tile11.setColor("#B9B9B9");
        tile11.setIcon("./dist/img/meteo.png");
        tile11.setLink("http://www.pollo.com");
        tilelist.add(tile11);
        
        Tile tile12 = new Tile();
        tile12.setColor("#B9B9B9");
        tile12.setText("TOP TEN");
        tile12.setLink("http://www.pollo.com");
        tilelist.add(tile12);
        
        Tile tile13 = new Tile();
        tile13.setColor("#B9B9B9");
        tile13.setIcon("./dist/img/calendar.png");
        tile13.setLink("http://www.pollo.com");
        tilelist.add(tile13);
        
        home.setTilesList(tilelist);
        
         CategorySubMenu csm = new CategorySubMenu();
        
        csm.setLink("http://www.pippo.com");
        csm.setText("CULTURA");
        
        CategorySubMenu csm2 = new CategorySubMenu();
        
        csm2.setLink("http://www.pippo.com");
        csm2.setText("FOOD");
        
        
        ArrayList<CategorySubMenu> submenu = new ArrayList<CategorySubMenu>();
        submenu.add(csm);
        submenu.add(csm2);
        
        SubMenu sub = new SubMenu();
        sub.setCategories(submenu);
        sub.setColor("#E74C3C");
        home.setSubmenu(sub);
        
        CarouselPages cps= new CarouselPages();
       cps.setLink("./dist/img/c_pizza.jpg");
      
       ArrayList<CarouselPages> cpsl= new ArrayList<CarouselPages>();
       cpsl.add(cps);
       home.setImgList(cpsl);
       
       ArrayList<String> slugs = new ArrayList<String>();
        slugs.add("museum");
        home.setCategorySlugList(slugs);
        
        pm.saveHome(home);
        
        return model;
        
    } 
}
