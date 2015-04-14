/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.persistence.mongo.documents.Pages;
import com.orchestra.portale.persistence.mongo.documents.Tile;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
public class NewPageController {
    
    public ModelAndView newPage() {
        ModelAndView model = new ModelAndView("bho");
        
        Pages page= new Pages();
        page.setTitle("TEST");
        page.setDescription("DESCRIZIONE TEST PAGINA INTERMEDIA");
        Tile tile1 = new Tile();
        tile1.setColor("BLUE");
        tile1.setIcon("bho");
        tile1.setLink("http://www.pollo.com");
        tile1.setText("gnegne");
        ArrayList<Tile> tilelist= new ArrayList<Tile>();
        tilelist.add(tile1);
        page.setTilesList(tilelist);
        
        return model;
    }
}
