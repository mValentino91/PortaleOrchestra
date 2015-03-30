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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserRegistrationController {
    //Manager della persistenza
    @Autowired
    PersistenceManager pm;

   @RequestMapping(value = "/userSignIn", method = RequestMethod.GET)
   public ModelAndView signin() {
      return new ModelAndView("userSignIn", "command", new User());
   }
   
   @RequestMapping(value = "/userSignIn", method = RequestMethod.POST)
   public ModelAndView addUser(@ModelAttribute("SpringWeb")User user, 
    MultipartFile avatar) {
       ModelAndView model2 = new ModelAndView("errorViewPoi");
        User usertest=pm.findUserByUsername(user.getUsername());
        if(usertest!= null && usertest.getUsername().toLowerCase().equals(user.getUsername().toLowerCase())){
            model2.addObject("err", "Esiste già un utente con username: "+user.getUsername());
            return model2;
        }
       //HASH PASSWORD
        user.setPassword(crypt(user.getPassword()));
      /*Create Role*/
        Role new_user_role = new Role();
        new_user_role.setRole("ROLE_USER");
        new_user_role.setUser(user);
        ArrayList<Role> new_user_roles = new ArrayList<Role>();
        new_user_roles.add(new_user_role);
        user.setRoles(new_user_roles);
        pm.saveUser(user);
        
        User user2= pm.findUserByUsername(user.getUsername());
      
         MultipartFile file = avatar;
            
            try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                
                File dir = new File(rootPath + File.separator + "webapps" + File.separator + "PortaleOrchestraMVC2-1.0"+ File.separator+"dist"+File.separator+"user"+File.separator+"img"+File.separator+user2.getId());
                if (!dir.exists())
                    dir.mkdirs();
 
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()+ File.separator + "avatar.jpg");
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                } catch (Exception e) {
                
            }
            model2.addObject("err", "funziona");
      return model2;
   }
}
