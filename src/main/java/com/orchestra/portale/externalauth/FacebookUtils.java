/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.externalauth;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;    
import java.util.HashMap;
import java.util.Map;
    
import com.orchestra.portale.externalauth.NetworkUtils;
import com.orchestra.portale.externalauth.exception.FacebookException;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author antonio
 */
public class FacebookUtils {
    
    private static final String FACEBOOK_APP_ID = "1507821126169380";
    private static final String FACEBOOK_CLIENT_SECRET = "e1fd6ae79648fe69efabae4f6562efb0";
    private static final String FACEBOOK_EXCHANGE_KEY = "";
    private static final String FACEBOOK_APP_TOKEN = "1507821126169380|NDiYkHlIAtds-bBOvQDzjGzBzQs";
    private static final String FACEBOOK_REDIRECT_URL = "";
    private static final String FACEBOOK_GRAPH_URL = "https://graph.facebook.com/v2.2/";    


    public static String getLoginURL(){
        String fb_url="https://www.facebook.com/dialog/oauth/?"
            + "client_id=" + FACEBOOK_APP_ID
            + "&redirect_uri=" + FACEBOOK_REDIRECT_URL
            + "&scope=email"
            + "&display=page"
            + "&response_type=code";
        
        return fb_url;
    } 


    public static String codeToToken(String code) throws FacebookException{
        
        String token = null;
        String access_token = null;
        
        try{
            String fb_code_url="https://graph.facebook.com/oauth/access_token?"
                + "client_id=" + FACEBOOK_APP_ID
                + "&redirect_uri=" + FACEBOOK_REDIRECT_URL
                + "&client_secret=" + FACEBOOK_CLIENT_SECRET
                + "&code=" + code;

            BufferedReader tk_read = NetworkUtils.doConnection(fb_code_url);
            token = tk_read.readLine();
            
            
            //extract token from response
            if(token != null && token.contains("access_token")){
                access_token=token.replace("access_token=", "").trim().replaceAll("&expire.*", "");
            }            
            
            
        } catch(MalformedURLException ex) {
                throw new FacebookException();
        } catch(IOException ioex) {
                throw new FacebookException();
        }  
        
        return access_token;
    }         
    

    public static Boolean ifTokenValid(String access_token) throws FacebookException{
        
        Boolean is_valid = false;
        
        String fb_token_url="https://graph.facebook.com/debug_token?"
            + "input_token=" + access_token
            + "&access_token=" + FACEBOOK_APP_TOKEN;

        try{
            
            BufferedReader ck_token_in = NetworkUtils.doConnection(fb_token_url);
            String ck_token =  ck_token_in.readLine();
            
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(ck_token);
            JsonObject j_object = (JsonObject)element;
            JsonObject data = (JsonObject)j_object.get("data");
            JsonElement validity = data.get("is_valid");    
            is_valid = validity.getAsBoolean();
            
        } catch(MalformedURLException ex) {
                throw new FacebookException();
        } catch(IOException ioex) {
                throw new FacebookException();
        }  
        
     
        
        return is_valid;
    }    
   

    public static  Map<String, String> getUserIDMail(String access_token) throws FacebookException{
        
        Map<String, String> userId_mail = new HashMap<String, String>();
        
        String url="https://graph.facebook.com/v2.2/"
            + "me?fields=id,email" 
            + "&access_token=" + access_token;

        try{
            
            BufferedReader fb_userIdMail_in = NetworkUtils.doConnection(url);
            String fb_userIdMail =  fb_userIdMail_in.readLine(); 
            
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(fb_userIdMail);
            JsonObject j_object = (JsonObject)element;            
            
            String id = j_object.get("id").getAsString();
            String email = j_object.get("email").getAsString();
            
            userId_mail.put("id", id);
            userId_mail.put("email", email);
            
        } catch(MalformedURLException ex) {
                throw new FacebookException();
        } catch(IOException ioex) {
                throw new FacebookException();
        }  
        
     
        
        return userId_mail;
    }      

    public static  Map<String, String> getUserData(String access_token) throws FacebookException{
        
        Map<String, String> user_data = new HashMap<String, String>();
        
        String url="https://graph.facebook.com/v2.2/"
            + "me?fields=id,email,first_name,last_name" 
            + "&access_token=" + access_token;

        try{
            
            BufferedReader fb_user_data_in = NetworkUtils.doConnection(url);
            String fb_user_data =  fb_user_data_in.readLine(); 
            
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(fb_user_data);
            JsonObject j_object = (JsonObject)element;            
            
            user_data.put("id", j_object.get("id").getAsString());
            user_data.put("email", j_object.get("email").getAsString());
            user_data.put("firstName", j_object.get("first_name").getAsString());
            user_data.put("lastName", j_object.get("last_name").getAsString());
            
        } catch(MalformedURLException ex) {
                throw new FacebookException();
        } catch(IOException ioex) {
                throw new FacebookException();
        }  
        
     
        
        return user_data;
    }       
        
}
