/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.rest.auth;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.sql.entities.Role;
import com.orchestra.portale.persistence.sql.entities.Token;
import java.io.IOException;  
import java.text.MessageFormat;  
import java.util.ArrayList;  
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;  

import javax.servlet.FilterChain;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.apache.commons.lang.StringUtils;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;  
import org.springframework.security.authentication.AuthenticationServiceException;  
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;  
import org.springframework.security.core.Authentication;  
import org.springframework.security.core.AuthenticationException;  
import org.springframework.security.core.GrantedAuthority;  
import org.springframework.security.core.authority.SimpleGrantedAuthority;  
import org.springframework.security.core.userdetails.User;  
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;  
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;  

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {  
    @Autowired
    PersistenceManager pm;
    
    protected TokenAuthenticationFilter(String defaultFilterProcessesUrl) {  
        super(defaultFilterProcessesUrl); //defaultFilterProcessesUrl - specified in applicationContext.xml.    
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(defaultFilterProcessesUrl)); //Authentication will only be initiated for the request url matching this pattern  
        setAuthenticationManager(new NoOpAuthenticationManager());  
        setAuthenticationSuccessHandler(new TokenSimpleUrlAuthenticationSuccessHandler());  
    }  

    /** 
     * Attempt to authenticate request  
     */  
    @Override  
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {  
        String token = request.getHeader("Authorization");  
        
        AbstractAuthenticationToken userAuthenticationToken = authUserByToken(token);  
        if(userAuthenticationToken == null) throw new AuthenticationServiceException("Invalid Token");
        return userAuthenticationToken;  
    }  

    /** 
     * authenticate the user based on token 
     * @return 
     */  
    private AbstractAuthenticationToken authUserByToken(String token) {  
        if(token==null) return null;  

        /* Verifica Esistenza/Validità Token */
        Token tokenObj = pm.getTokenByToken(token);
     
        //se il token non è presente nel DB
        if(tokenObj==null){ 
            return null;
        }
   
        //se il token è scaduto
        if(new Date().after(tokenObj.getValidity())){ 
            return null;
        }
        
        com.orchestra.portale.persistence.sql.entities.User domainUser = pm.findUserById(tokenObj.getIdUser().longValue());
        
        if(domainUser==null){
            return null;
        }
        
        User principal = new User(domainUser.getUsername(), "", getAuthorities(domainUser.getRoles()));        
        
        AbstractAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());  

        return authToken;  
    }  


    @Override  
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {  
        super.doFilter(req, res, chain);  
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
