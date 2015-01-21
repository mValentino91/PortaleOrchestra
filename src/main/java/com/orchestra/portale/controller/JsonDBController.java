/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Marco Valentino
 */
@Controller
@RequestMapping("/jsonDB")
public class JsonDBController {

    private final String categoriesTree = "/dist/jsonDB/categoriesTree_IT.json";

    @RequestMapping(value = "/categoriesTree")
    public String getCategoriesTree() {

        return "forward:" + categoriesTree;
    }

}
