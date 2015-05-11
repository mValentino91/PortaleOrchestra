/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.profiler;

import com.google.gson.Gson;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author antonio
 */
@Component
@Scope("session")
public class FbProfiler{

    static private String serverURL = "http://www.smile.unina.it:5000";
    
    private String access_token;
    
    public String testServer(){
    
         try {
          
          HttpURLConnection connection = openConnection("/", "GET");
          String json_response = streamToString(connection.getInputStream());
          JsonParser parser = new JsonParser();
	  JsonElement element = parser.parse(json_response);
          
          JsonObject j_object = (JsonObject)element;
          String msg = j_object.get("msg").getAsString();
          
          return msg;
          
        } catch(MalformedURLException ex) {
                ex.printStackTrace();
        } catch(IOException ioex) {
                ioex.printStackTrace();
        }   
         
       return null;
    }
    
    
    
    public String basicProfile(){
        
        if(access_token==null){
            return null;
        }
    
        try {
          
          HttpURLConnection connection = openConnection("/mom_profile", "GET");
          
          Map<String,String> params = new HashMap<String,String>();
          params.put("access_token", access_token);
          connection = addParameter(connection, params);
          
          String json_response = streamToString(connection.getInputStream());
          JsonParser parser = new JsonParser();
	  JsonElement element = parser.parse(json_response);
          JsonObject j_object = (JsonObject)element;
          JsonObject msg = (JsonObject) j_object.get("msg");
          
          return msg.toString();
          
        } catch(MalformedURLException ex) {
                ex.printStackTrace();
        } catch(IOException ioex) {
                ioex.printStackTrace();
        }
        
        return null;
    }
 
    
    
    /**
     * @return the access_token
     */
    public String getAccess_token() {
        return access_token;
    }

    
    
    /**
     * @param access_token the access_token to set
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }    
    
    /**
     * Create an new user on profiler system
     * @param access_token
     * @return true if operation succeed, false otherwise
     */
    public Boolean newUser(){
        
        if(access_token==null){
            return false;
        }
    
        try {
          
          HttpURLConnection connection = openConnection("/new_user", "GET");
          
          Map<String,String> params = new HashMap<String,String>();
          params.put("access_token", access_token);
          connection = addParameter(connection, params);
          
          String json_response = streamToString(connection.getInputStream());
          JsonParser parser = new JsonParser();
	  JsonElement element = parser.parse(json_response);
          JsonObject j_object = (JsonObject)element;
          String msg = j_object.get("msg").getAsString();
          
          if(msg==null){
              return false;
          }
          
          return true;
          
        } catch(MalformedURLException ex) {
            //ex.printStackTrace();
            return false;
        } catch(IOException ioex) {
            //ioex.printStackTrace();
            return false;
        } 
        
        
    }
    
    
    /* MOM */
    public List<String> getPoiStereotype(){
        if(access_token==null){
            return null;
        }
    
        try {
          
          HttpURLConnection connection = openConnection("/poi_stereotype", "GET");
          
          Map<String,String> params = new HashMap<String,String>();
          params.put("access_token", access_token);
          connection = addParameter(connection, params);
          
          String json_response = streamToString(connection.getInputStream());
          JsonParser parser = new JsonParser();
	  JsonElement element = parser.parse(json_response);
          JsonObject j_object = (JsonObject)element;
          JsonArray j_poi_list = (JsonArray) j_object.get("poi_list");
          
          List<String> poi_list = new ArrayList<String>();
          
          for(JsonElement p : j_poi_list){
              poi_list.add(p.toString().replace("\"", ""));
          }
          
          return poi_list;
          
        } catch(MalformedURLException ex) {
                ex.printStackTrace();
        } catch(IOException ioex) {
                ioex.printStackTrace();
        }
        
        return null;   
    }
    
    /*MOM*/
    public Map<String, List<String[]> > getFBCategories(){
         if(access_token==null){
            return null;
        }
    
        try {
          
          HttpURLConnection connection = openConnection("/mom", "GET");
          
          Map<String,String> params = new HashMap<String,String>();
          params.put("access_token", access_token);
          connection = addParameter(connection, params);
          
          String json_response = streamToString(connection.getInputStream());
          JsonParser parser = new JsonParser();
	  JsonElement element = parser.parse(json_response);
          JsonObject j_object = (JsonObject)element;
          JsonObject cat_list = (JsonObject) j_object.get("msg");
          
          Map<String, List<String[]> > cat_ret = new HashMap <String, List<String[]> >();
          JsonArray likes = cat_list.getAsJsonArray("likes");
          JsonArray photos = cat_list.getAsJsonArray("photos");
          JsonArray places = cat_list.getAsJsonArray("places");
          
          List<String[]> likes_array = new ArrayList<String[]>();
          for(JsonElement like : likes){
              String [] l = {like.getAsJsonObject().get("name").getAsString(), like.getAsJsonObject().get("weight").getAsString()};
              likes_array.add(l);
          }
          cat_ret.put("likes", likes_array);
          
          List<String[]> photos_array = new ArrayList<String[]>();
          for(JsonElement photo : photos){
              String [] p = {photo.getAsJsonObject().get("name").getAsString(), photo.getAsJsonObject().get("weight").getAsString()};
              photos_array.add(p);
          }
          cat_ret.put("photos", photos_array);
          
          List<String[]> places_array = new ArrayList<String[]>();
          for(JsonElement place : places){
              String [] pl = {place.getAsJsonObject().get("name").getAsString(), place.getAsJsonObject().get("weight").getAsString()};
              places_array.add(pl);
          }
          cat_ret.put("places", places_array);
          
          return cat_ret;
          
        } catch(MalformedURLException ex) {
                //ex.printStackTrace();
                return null;
        } catch(IOException ioex) {
                //ioex.printStackTrace();
                return null;
        }
        
    
    }
        
    /**
     * Return a connection to serverURL+serverPath URL with indicated request method
     * @param serverPath
     * @param method
     * @return
     * @throws MalformedURLException
     * @throws IOException 
     */
    private HttpURLConnection openConnection(String serverPath, String method) throws MalformedURLException, IOException {
        URL url = new URL(serverURL+serverPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
        connection.setRequestProperty("charset", "utf-8"); 
        
        return connection;
    }
    
    
    private HttpURLConnection addParameter(HttpURLConnection connection, Map<String, String> params) throws IOException{
        
        if(params != null){
            String param_string = "";
            for(String k : params.keySet()){
                param_string += k + "=" + params.get(k) + "&";
            }
            
            param_string = param_string.substring(0, param_string.length()-1);

            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(param_string);
            wr.flush();
            wr.close();              
        }
        return connection;
    }
    
    
    /**
     * Get an InputStream, iterate on all lines and concatenate them in one 
     * unique string
     * @param stream
     * @return
     * @throws IOException 
     */
    private String streamToString(InputStream stream) throws IOException{
        String line;
        String full_stream="";
        BufferedReader read = new BufferedReader(new InputStreamReader(stream));
        
        while((line=read.readLine()) != null){
            full_stream += line;
        }
        
        return full_stream;
    }


    
    
    
}
