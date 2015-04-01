/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.externalauth;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;

import com.orchestra.portale.externalauth.NetworkUtils;
import com.orchestra.portale.externalauth.exception.FacebookException;
import com.orchestra.portale.externalauth.exception.UserNotFoundException;
import com.orchestra.portale.persistence.sql.entities.Role;
import com.orchestra.portale.persistence.sql.repositories.UserRepository;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
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
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author barnap
 */
public class FbAuthenticationManager {

    public static void fbLogin(HttpServletRequest request, HttpServletResponse response) {

        //Create Facebook login url
        String fb_url = FacebookUtils.getLoginURL();

        try {
            //Call Facebook login dialog
            response.sendRedirect(fb_url);

        } catch (IOException e) {
            //Call error manager
            e.printStackTrace();
        }

    }

    public static User fbLoginJs(HttpServletRequest request, HttpServletResponse response, UserRepository userRepository) {

        //Get access_token from request
        String access_token = request.getParameter("access_token");
        User user = null;

        if (StringUtils.isNotEmpty(access_token)) {

            try {

                Boolean validity = FacebookUtils.ifTokenValid(access_token);

                //if token is valid, retrieve userid and email from Facebook
                if (validity) {
                    Map<String, String> userId_mail = FacebookUtils.getUserIDMail(access_token);
                    String id = userId_mail.get("id");
                    String email = userId_mail.get("email");

                    try {
                        user = fbUserCheck(id, email, userRepository);
                    } catch (UserNotFoundException ioex) {
                        /*Retrieve User Data to Registration*/
                        Map<String, String> userData = FacebookUtils.getUserData(access_token);

                        /*Create User*/
                        com.orchestra.portale.persistence.sql.entities.User new_user = new com.orchestra.portale.persistence.sql.entities.User();
                        new_user.setFbEmail(userData.get("email"));
                        new_user.setFbUser(userData.get("id"));
                        new_user.setUsername(userData.get("email"));
                        new_user.setFirstName(userData.get("firstName"));
                        new_user.setLastName(userData.get("lastName"));
                        new_user.setPassword(new BigInteger(130, new SecureRandom()).toString(32));

                        /*Create Role*/
                        com.orchestra.portale.persistence.sql.entities.Role new_user_role = new com.orchestra.portale.persistence.sql.entities.Role();
                        new_user_role.setRole("ROLE_USER");
                        new_user_role.setUser(new_user);
                        ArrayList<com.orchestra.portale.persistence.sql.entities.Role> new_user_roles = new ArrayList<com.orchestra.portale.persistence.sql.entities.Role>();
                        new_user_roles.add(new_user_role);
                        new_user.setRoles(new_user_roles);

                        /*Save User*/
                        userRepository.save(new_user);
                        String img_url = userData.get("img");
                        System.out.println("IMG*********************************************");
                        System.out.println(img_url);

                        //Save user image
                        try {
                            String destination = userRepository.findByUsername(new_user.getUsername()).getId().toString() + ".jpg";
                            saveImage(img_url, destination);
                        } catch (MalformedURLException ex) {
                            throw new FacebookException();
                        } catch (IOException ioexc) {
                            ioexc.getMessage();
                        }
                        /*Create Spring User*/
                        boolean enabled = true;
                        boolean accountNonExpired = true;
                        boolean credentialsNonExpired = true;
                        boolean accountNonLocked = true;

                        user = new User(new_user.getUsername(),
                                new_user.getPassword().toLowerCase(),
                                enabled,
                                accountNonExpired,
                                credentialsNonExpired,
                                accountNonLocked,
                                getAuthorities(new_user.getRoles()));

                    }

                }

            } catch (FacebookException ioex) {
                ioex.printStackTrace();
            }

        }

        return user;
    }

    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        System.out.println("IMGURL:" + imageUrl + "DESTINATION:" + destinationFile);
        // Creating the directory to store file
        String rootPath = System.getProperty("catalina.home");
        String destName = rootPath + File.separator + "webapps" + File.separator + "orchestra" + File.separator + "dist" + File.separator + "user" + File.separator + "img" + File.separator + destinationFile;
        System.out.println(destName);

        URL url = new URL(imageUrl);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();

        FileOutputStream fos = new FileOutputStream(destName);
        fos.write(response);
        fos.close();

    }

    private static User fbUserCheck(String userFbId, String userFbMail, UserRepository userRepository) throws UserNotFoundException {
        // Recupera i dati dell'utente userServiceId dal repository 
        com.orchestra.portale.persistence.sql.entities.User domainUser = userRepository.findByFbEmailOrFbUser(userFbMail, userFbId);

        if (domainUser == null || domainUser.getUsername() == null || "".equals(domainUser.getUsername())) {
            throw new UserNotFoundException();
        }

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
    private static Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles_obj) {

        Iterator<Role> roles_iter = roles_obj.iterator();

        List<String> roles = new ArrayList<String>();
        while (roles_iter.hasNext()) {
            Role r = roles_iter.next();
            roles.add(r.getRole());
        }

        roles.add("ROLE_FB");
        List<GrantedAuthority> authList = getGrantedAuthorities(roles);
        return authList;
    }

    /**
     * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
     *
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
