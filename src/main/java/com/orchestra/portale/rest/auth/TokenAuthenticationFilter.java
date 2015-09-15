/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.rest.auth;

import com.orchestra.portale.dbManager.ConcretePersistenceManager;
import com.orchestra.portale.dbManager.PersistenceManager;
import java.io.IOException;  
import java.text.MessageFormat;  
import java.util.ArrayList;  
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

        /* Verifica Token */
        String username = "ciccio"; //logic to extract username from token  
        String role = "ROLE_ADMIN"; //extract role information from token  

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();  
        authorities.add(new SimpleGrantedAuthority(role));  

        User principal = new User(username, "", authorities);   
        AbstractAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());  

        return authToken;  
    }  


    @Override  
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {  
        super.doFilter(req, res, chain);  
    }  
}  