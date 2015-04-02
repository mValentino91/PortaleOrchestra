/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.Role;
import com.orchestra.portale.persistence.sql.entities.User;
import static com.orchestra.portale.utils.MD5.crypt;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alex
 */
@Controller
public class UserEditController {
    @Autowired
   PersistenceManager pm;
    
    
    @RequestMapping("userEditProfile")
    @Secured("ROLE_USER")
    public ModelAndView editUser() {
        ModelAndView model = new ModelAndView("userEditProfile","command", new User());
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       User user= pm.findUserByUsername(auth.getName());
       String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "webapps" + File.separator + "orchestra"+ File.separator+"dist"+File.separator+"user"+File.separator+"img"+File.separator+user.getId()+File.separator+"avatar.jpg");
       if (dir.exists()) {
          model.addObject("avatar", "./dist/user/img/"+user.getId()+"/avatar.jpg");
       }
       else {
           
           model.addObject("avatar", "./dist/img/default_avatar.png");
       }
       
        model.addObject("user", user);
       
       
       return model;
    
    }
    
    @RequestMapping(value = "userEditProfile", method = RequestMethod.POST)
    @Secured("ROLE_USER")
   public ModelAndView updateUser(@ModelAttribute("SpringWeb")User user, 
    MultipartFile avatar) {
       ModelAndView model = new ModelAndView("userInfo");
       User olduser = pm.findUserByUsername(user.getUsername());
       user.setId(olduser.getId());
       
       if(user.getPassword() == null || user.getPassword().equals("")) {
           user.setPassword(olduser.getPassword());
       }
       else{
        user.setPassword(crypt(user.getPassword()));
       }
       
        pm.saveUser(user);
        
      if(avatar.getSize() > 0) {
          
        User user2= pm.findUserByUsername(user.getUsername());
         MultipartFile file = avatar;
            
            try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                
                File dir = new File(rootPath + File.separator + "webapps" + File.separator + "orchestra"+ File.separator+"dist"+File.separator+"user"+File.separator+"img"+File.separator+user2.getId());
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()+ File.separator + "avatar.jpg");
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                } catch (Exception e) {
                
            }
            
      
   }
   model.addObject("user", user);
   String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "webapps" + File.separator + "orchestra"+ File.separator+"dist"+File.separator+"user"+File.separator+"img"+File.separator+user.getId()+File.separator+"avatar.jpg");
       if (dir.exists()) {
          model.addObject("avatar", "./dist/user/img/"+user.getId()+"/avatar.jpg");
       }
       else {
           model.addObject("avatar", "./dist/img/default_avatar.png");
       }
return model;
}
}
