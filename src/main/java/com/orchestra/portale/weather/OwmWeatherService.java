/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.weather;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author antonio
 */
public class OwmWeatherService implements WeatherService{
    
    private String serviceURL = "http://api.openweathermap.org/data/2.5/weather?id=3172394&lang=it&units=metric&APPID=0255d4749e4af436bf7974687fe98773";
    private String appKey = "0255d4749e4af436bf7974687fe98773";
    private String cityCode = "3172394";
    
    public Weather getCurrentWeather(){
        Weather weather = new OwmWeather();
        
        try {
          
          HttpURLConnection connection = openConnection(serviceURL, "GET");
          
          /*
          Map<String,String> params = new HashMap<String,String>();
          params.put("id", cityCode);
          params.put("lang", "it");
          params.put("APPID", appKey);
          connection = addParameter(connection, params);
          */
          
          String json_response = streamToString(connection.getInputStream());
          JsonParser parser = new JsonParser();
	  JsonElement element = parser.parse(json_response);
          JsonObject j_object = (JsonObject)element;
          JsonArray w_array = j_object.get("weather").getAsJsonArray();
          JsonObject j_weather = w_array.get(0).getAsJsonObject();
          String code = j_weather.get("icon").getAsString();
          String description = j_weather.get("description").getAsString().toLowerCase();
          
          JsonObject j_temp = j_object.get("main").getAsJsonObject();
          Double temp = j_temp.get("temp").getAsDouble();
          Double temp_max = j_temp.get("temp_max").getAsDouble();
          Double temp_min = j_temp.get("temp_min").getAsDouble();
          
          weather.setWeatherCode(code);
          weather.setWeatherDescription(description);
          weather.setTemp(temp.intValue());
          weather.setTempMin(temp_min.intValue());
          weather.setTempMax(temp_max.intValue());
          
        } catch(MalformedURLException ex) {
                ex.printStackTrace();
        } catch(IOException ioex) {
                ioex.printStackTrace();
        }        
        
        
        return weather;
    }
    
    
        
    /**
     * Return a connection to serverURL+serverPath URL with indicated request method
     * @param serverPath
     * @param method
     * @return
     * @throws MalformedURLException
     * @throws IOException 
     */
    private HttpURLConnection openConnection(String serverURL, String method) throws MalformedURLException, IOException {
        URL url = new URL(serverURL);
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
