/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ibmServices;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 *
 * @author antonio
 */
public class IBM_Requests {

    static private String ip = "143.225.131.31";
    
    
    public static String getToken(){
        String token = "";
        
        try {
          URL url = new URL("http://"+ip+"/orchestra/api/token");
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          connection.setRequestMethod("POST");
          connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
          //connection.setRequestProperty("charset", "utf-8");
	  
          String param = "credentials=" + URLEncoder.encode("d3BzYWRtaW46cGFzc3cwcmQ=", "UTF-8");
          connection.setDoOutput(true);
          DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(param);
            wr.flush();
            wr.close();
                    
          BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          
          String json_token = read.readLine();
          
          JsonParser parser = new JsonParser();
		
	  JsonElement element = parser.parse(json_token);
          
          JsonObject j_object = (JsonObject)element;
          
          JsonObject payload = (JsonObject)j_object.get("payload");
          
          JsonElement token_obj = payload.get("token");
          
          token = token_obj.getAsString();
          
        } catch(MalformedURLException ex) {
                ex.printStackTrace();
        } catch(IOException ioex) {
                ioex.printStackTrace();
        }
        
        return token;

    }
    
    public static String getAlberghi(){
        
        String alberghi_str="";
 
        try {
          //obtain token
          String token = getToken();
            
          URL url = new URL("http://143.225.131.31/orchestra/api/info/alberghi");
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          connection.setRequestMethod("GET");
          connection.addRequestProperty("token", token);
          connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
          //connection.setRequestProperty("charset", "utf-8");
	  
          BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          
          String json_alberghi = read.readLine();
           
          JsonParser parser = new JsonParser();
		
	  JsonElement element = parser.parse(json_alberghi);
          
          JsonObject j_object = (JsonObject)element;
          
          JsonArray payload = (JsonArray)j_object.get("payload");
          
          
          JsonArray alberghi = new JsonArray();
          for(JsonElement a : payload){
              JsonObject alb = (JsonObject) a;
             
              //System.out.println(alb);
              
              JsonElement name = alb.get("name");
              JsonElement geometry = alb.get("geometry");
              JsonElement id_j = alb.get("id");
             
              String coord=geometry.getAsString();
              coord=coord.replace("POINT(", "");
              coord=coord.replace(")", "");
              String[] coords = coord.split("\\s+");
              JsonArray c = new JsonArray();
              c.add(new JsonPrimitive(coords[1]));
              c.add(new JsonPrimitive(coords[0]));

              JsonObject properties = (JsonObject) alb.get("properties");
             
              String indirizzo = ( (JsonPrimitive) properties.get("Indirizzo") ).toString();
              String classificazione = ( (JsonPrimitive) properties.get("Classificazione") ).toString();
              String nome = name.getAsString();
              String id = id_j.getAsString();
              
              JsonObject albergo = new JsonObject();
              albergo.addProperty("id", id);
              albergo.addProperty("nome", nome);
              albergo.addProperty("indirizzo", indirizzo);
              albergo.addProperty("classificazione", classificazione);
              albergo.add("location", c);
              alberghi.add(albergo);
          }
          
          alberghi_str=alberghi.toString();
          
         
        } catch(MalformedURLException ex) {
                ex.printStackTrace();
        } catch(IOException ioex) {
                ioex.printStackTrace();
        }        
        
        
        return alberghi_str;
    }


    
    public static String getAlbergo(String id){
        
        String albergo_str="";
 
        try {
          //obtain token
          String token = getToken();
            
          URL url = new URL("http://143.225.131.31/orchestra/api/info/alberghi/"+id);
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          connection.setRequestMethod("GET");
          connection.addRequestProperty("token", token);
          connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
          //connection.setRequestProperty("charset", "utf-8");
	  
          BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          
          String json_alberghi = read.readLine();
          
          //System.out.println(json_alberghi);
          
          
          JsonParser parser = new JsonParser();
		
	  JsonElement element = parser.parse(json_alberghi);
          
          JsonObject j_object = (JsonObject)element;
                  
          JsonObject payload = (JsonObject)j_object.get("payload");
               
          JsonObject properties = (JsonObject) payload.get("properties");
          
          JsonElement email_j = properties.get("Email");
          String email=email_j.getAsString();
          JsonElement web_j = properties.get("Web");
          String web=web_j.getAsString();
          
          JsonObject alb_det = new JsonObject();
              alb_det.addProperty("web", web);
              alb_det.addProperty("email", email);
          
              
          albergo_str = alb_det.toString();
          //System.out.println(albergo_str);
         
          
        } catch(MalformedURLException ex) {
                ex.printStackTrace();
        } catch(IOException ioex) {
                ioex.printStackTrace();
        }  
        
        return albergo_str;
    }
    
    
}
