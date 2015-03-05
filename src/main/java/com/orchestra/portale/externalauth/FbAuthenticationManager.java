/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.externalauth;

import com.orchestra.portale.persistence.sql.entities.Role;
import org.springframework.security.core.userdetails.User;
import com.orchestra.portale.externalauth.exception.UserNotFoundException;
import com.orchestra.portale.persistence.sql.repositories.UserRepository;
import com.orchestra.portale.externalauth.exception.FacebookException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author barnap
 */
public class FbAuthenticationManager  {
    
    
    public static void fbLogin(HttpServletRequest request, HttpServletResponse response) {
        
        //Create Facebook login url
        String fb_url= FacebookUtils.getLoginURL();

        try {
            //Call Facebook login dialog
            response.sendRedirect(fb_url);

        } catch (IOException e) {
            //Call error manager
            e.printStackTrace();
        }            

    }
    
    public static User fbLoginJs(HttpServletRequest request, HttpServletResponse response, UserRepository userRepository)  {
        
        //Get access_token from request
        String access_token = request.getParameter("access_token");
        User user = null;
        
        if(StringUtils.isNotEmpty(access_token)){
        
            try {
                    
                Boolean validity = FacebookUtils.ifTokenValid(access_token);
                        
                //if token is valid, retrieve userid and email from Facebook
                if(validity){
                    Map<String, String> userId_mail = FacebookUtils.getUserIDMail(access_token);
                    String id = userId_mail.get("id");
                    String email = userId_mail.get("email");
  
                    try{
                        user = fbUserCheck(id, email, userRepository);
                    }
                    catch(UserNotFoundException ioex) {
                        /*Retrieve User Data to Registration
                        Map<String, String> userData = FacebookUtils.getUserData(access_token);
                        System.out.println(userData.get("id"));
                        System.out.println(userData.get("email"));
                        System.out.println(userData.get("firstName"));
                        System.out.println(userData.get("lastName"));
                        */
                    }  
                    
                }

            } catch(FacebookException ioex) {
                    ioex.printStackTrace();
            }           
             
        }
     
        return user;
    }    
    
    
    
    
    private static User fbUserCheck(String userFbId, String userFbMail, UserRepository userRepository) throws UserNotFoundException {
        // Recupera i dati dell'utente userServiceId dal repository 
        com.orchestra.portale.persistence.sql.entities.User domainUser = userRepository.findByFbEmailOrFbUser(userFbMail, userFbId);
        
        if (domainUser == null || domainUser.getUsername() == null || "".equals(domainUser.getUsername()))
            throw new UserNotFoundException();
        
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        User user = new User(domainUser.getUsername(), 
                        domainUser.getPassword().toLowerCase(),
                        enabled,
                        accountNonExpired,
                        credentialsNonExpired,
                        accountNonLocked,
                        getAuthorities(domainUser.getRoles()));
        
        return user;
    }
    
    
    /**
     * Retrieves a collection of {@link GrantedAuthority}
     */
    private static Collection<? extends GrantedAuthority> getAuthorities( List <Role> roles_obj) {

            Iterator <Role> roles_iter = roles_obj.iterator();

            List<String> roles = new ArrayList<String>();
            while(roles_iter.hasNext()){
                Role r = roles_iter.next();
                roles.add(r.getRole());
            }

            List<GrantedAuthority> authList = getGrantedAuthorities(roles);
            return authList;
    }



    /**
     * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
     * @param roles {@link String} of roles
     * @return list of granted authorities
     */
    private static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for (String role : roles) {
                    authorities.add(new SimpleGrantedAuthority(role));
            }
            return authorities;
    }

}
