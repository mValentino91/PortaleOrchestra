/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.utils;

import java.io.File;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Alex
 */
public class InsertUtils {
   public static String delimg(String id, String nameimg){
        String esito= "";
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "webapps" + File.separator + "orchestra"+ File.separator+"dist"+File.separator+"poi"+File.separator+"img"+File.separator+id);
        
        File img=new File(dir.getAbsolutePath()+ File.separator + nameimg);
        if(img.delete())
            esito="OK";
        else
            esito="ERRORE";
        
        return esito;
    }
}
