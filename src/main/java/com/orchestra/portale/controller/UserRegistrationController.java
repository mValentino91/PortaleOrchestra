/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.Role;
import com.orchestra.portale.persistence.sql.entities.User;
import static com.orchestra.portale.utils.MD5.crypt;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserRegistrationController {
    //Manager della persistenza
  

   @RequestMapping(value = "/userSignIn", method = RequestMethod.GET)
   public ModelAndView signin() {
      return new ModelAndView("userSignIn", "command", new User());
   }
   
   @RequestMapping(value = "/userSignIn", method = RequestMethod.POST)
   public ModelAndView addUser(HttpServletRequest request, @ModelAttribute("SpringWeb")User user, @RequestParam(value="avatar", required=false)
    MultipartFile avatar) {
       PersistenceManager pm = new ConcretePersistenceManager( LocaleContextHolder.getLocale().getDisplayLanguage() );
       ModelAndView model2 = new ModelAndView("okpage");
        User usertest=pm.findUserByUsername(user.getUsername());
        if(usertest!= null && usertest.getUsername().toLowerCase().equals(user.getUsername().toLowerCase())){
            model2.addObject("err", "Esiste giÃ  un utente con username: "+user.getUsername());
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
        
        if (avatar.getSize() > 0  ){
            
        User user2= pm.findUserByUsername(user.getUsername());
      
         MultipartFile file = avatar;
            
            try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                HttpSession session = request.getSession();
                ServletContext sc = session.getServletContext();
                
                File dir = new File(sc.getRealPath("/")+"dist"+File.separator+"user"+File.separator+"img"+File.separator+user2.getId());
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
            model2.addObject("mess", "Registrazione completata con successo!<br><br><center> <a href='page?sec=home' class='btn btn-primary'>Torna alla Home</a></center> ");
      return model2;
   }
}
