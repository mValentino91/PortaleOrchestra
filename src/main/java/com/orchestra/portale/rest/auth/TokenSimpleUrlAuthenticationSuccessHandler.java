/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.rest.auth;
  
import java.io.IOException;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.springframework.security.core.Authentication;  
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;  
  
public class TokenSimpleUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {  
  
    @Override  
    protected String determineTargetUrl(HttpServletRequest request,  
            HttpServletResponse response) {  
        String context = request.getContextPath();  
        String fullURL = request.getRequestURI();  
        String url = fullURL.substring(fullURL.indexOf(context)+context.length());  
        return url;  
    }  
   
    @Override  
    public void onAuthenticationSuccess(HttpServletRequest request,  
            HttpServletResponse response, Authentication authentication)  
            throws IOException, ServletException {  
        String url = determineTargetUrl(request,response);  
        request.getRequestDispatcher(url).forward(request, response);  
    }  
  
}  