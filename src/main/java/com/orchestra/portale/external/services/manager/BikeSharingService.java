/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.external.services.manager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_En;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_It;
import com.orchestra.portale.persistence.mongo.documents.ExternalServiceComponent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 *
 * @author Marco Valentino
 */
public class BikeSharingService implements ExternalServiceManager {

    private PersistenceManager pm;
    private static final String baseUrl = "http://www.movinap.it/_CI/api_v1/station/getAllStationsInfo";
    private static final String getUrl = "http://www.movinap.it/_CI/api_v1/station/getAllStationsInfo";
    private static final String baseImgUrl = "http://www.bikesharingnapoli.it/wp-content/themes/bikesharing/images/maps/stazioni/";
    private static final String innerUrl = "./externalService/bikeSharing/get";
    private static final String[] categoriesName = {"bikesharing", "mobility"};
    private static final String[] categoriesDelete = {"bikesharing"};
    private static Gson gson = new Gson();

    public BikeSharingService(PersistenceManager manager) {
        pm = manager;
    }

    @Override
    public String load() {
        try {
            deletePois();
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(baseUrl).openConnection();
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(30000);
            urlConnection.addRequestProperty("Accept-Language", "it-IT");
            urlConnection.addRequestProperty("Accept", "application/json");
            String result = IOUtils.toString(urlConnection.getInputStream());
            urlConnection.disconnect();
            JsonParser parser = new JsonParser();
            JsonElement json = parser.parse(result);
            JsonArray puntiBike = json.getAsJsonObject().get("response_data").getAsJsonArray();
            StringBuilder insertedPoi = new StringBuilder();
            for (int i = 0; i < puntiBike.size(); i++) {
                insertedPoi.append(createPoi(i, puntiBike));
            }
            return insertedPoi.toString();
        } catch (IOException e) {
            return "response:{code:1,error:" + e.getMessage() + "}";
        }
    }

    @Override
    public String getResponse(Map<String, String[]> mapParams) {
        try {

            HttpURLConnection urlConnection = (HttpURLConnection) new URL(baseUrl).openConnection();
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(30000);
            urlConnection.addRequestProperty("Accept-Language", "it-IT");
            urlConnection.addRequestProperty("Accept", "application/json");
            String result = IOUtils.toString(urlConnection.getInputStream());
            urlConnection.disconnect();
            JsonParser parser = new JsonParser();
            JsonElement json = parser.parse(result);
            JsonArray puntiBike = json.getAsJsonObject().get("response_data").getAsJsonArray();
            for (int i = 0; i < puntiBike.size(); i++) {
                 if(puntiBike.get(i).getAsJsonObject().get("station_id").getAsString().equals(mapParams.get("id")[0])){
                      if (LocaleContextHolder.getLocale().toString().equals("en")){
                          return "<b>Available Locks:</b> "+puntiBike.get(i).getAsJsonObject().get("available_locks").getAsString()
                             +"<br><b>Available Bikes:</b> "+puntiBike.get(i).getAsJsonObject().get("available_bikes").getAsString()
                             +"<br><b>Capacity :</b>"+puntiBike.get(i).getAsJsonObject().get("capacity").getAsString();
                      }
                      else {
                     return "<b>Postazioni libere:</b> "+puntiBike.get(i).getAsJsonObject().get("available_locks").getAsString()
                             +"<br><b>Biciclette disponibili:</b> "+puntiBike.get(i).getAsJsonObject().get("available_bikes").getAsString()
                             +"<br><b>Capacità :</b>"+puntiBike.get(i).getAsJsonObject().get("capacity").getAsString();
                      }
                      }
            }
             if (LocaleContextHolder.getLocale().toString().equals("en")){
                 return "Service temporarily unavailable";
             }
            return "Servizio Momentaneamente Non Disponibile!";
            
        } catch (IOException e) {
            if (LocaleContextHolder.getLocale().toString().equals("en")){
                 return "Service temporarily unavailable";
             }
            return "Servizio Momentaneamente Non Disponibile!";
        }
    }

    private void deletePois() {
        pm.setLang("it");
        Iterator<? extends CompletePOI> pois = pm.getCompletePoiByCategories(categoriesDelete).iterator();
        while (pois.hasNext()) {
            CompletePOI poi = (CompletePOI) pois.next();
            
            pm.deletePoi((CompletePOI_It) poi);
          
        }
        
        pm.setLang("en");
        Iterator<? extends CompletePOI> enpois = pm.getCompletePoiByCategories(categoriesDelete).iterator();
        while (enpois.hasNext()) {
            CompletePOI poi = (CompletePOI) enpois.next();
            
            pm.deleteEnPoi((CompletePOI_En) poi);
        }
    }

    private String createPoi(int item, JsonArray puntiBike) {
        CompletePOI_It newPoi = new CompletePOI_It();
        newPoi.setName("Bike Sharing - "+puntiBike.get(item).getAsJsonObject().get("title").getAsString());
        newPoi.setAddress(puntiBike.get(item).getAsJsonObject().get("address").getAsString());
        double[] location = {
            puntiBike.get(item).getAsJsonObject().get("latitude").getAsDouble(),
            puntiBike.get(item).getAsJsonObject().get("longitude").getAsDouble()
        };
        newPoi.setLocation(location);
        ArrayList<AbstractPoiComponent> newlistComponent = new ArrayList<AbstractPoiComponent>();
        ExternalServiceComponent externalServiceComponent = new ExternalServiceComponent();
        String id = puntiBike.get(item).getAsJsonObject().get("station_id").getAsString();
        externalServiceComponent.setURL(innerUrl);
        externalServiceComponent.setParameters("id="+id);
        newPoi.setExternalUrl(innerUrl+"?id="+id);
        
        newlistComponent.add(externalServiceComponent);
        ArrayList<String> categories = new ArrayList<String>();
        categories.addAll(Arrays.asList(categoriesName));
        newPoi.setCategories(categories);
        newPoi.setComponents(newlistComponent);

        pm.savePoi((CompletePOI_It) newPoi);
        CompletePOI_En newEnPoi = new CompletePOI_En();
        newEnPoi.setName("Bike Sharing - "+puntiBike.get(item).getAsJsonObject().get("title").getAsString());
        newEnPoi.setId(newPoi.getId());
        newEnPoi.setAddress(puntiBike.get(item).getAsJsonObject().get("address").getAsString());
        double[] enlocation = {
            puntiBike.get(item).getAsJsonObject().get("latitude").getAsDouble(),
            puntiBike.get(item).getAsJsonObject().get("longitude").getAsDouble()
        };
        newEnPoi.setLocation(enlocation);
        ArrayList<AbstractPoiComponent> ennewlistComponent = new ArrayList<AbstractPoiComponent>();
        ExternalServiceComponent enexternalServiceComponent = new ExternalServiceComponent();
        String enid = puntiBike.get(item).getAsJsonObject().get("station_id").getAsString();
        enexternalServiceComponent.setURL(innerUrl);
        enexternalServiceComponent.setParameters("id="+enid);
        newEnPoi.setExternalUrl(innerUrl+"?id="+enid);
        
        ennewlistComponent.add(enexternalServiceComponent);
        ArrayList<String> encategories = new ArrayList<String>();
        encategories.addAll(Arrays.asList(categoriesName));
        newEnPoi.setCategories(encategories);
        newEnPoi.setComponents(ennewlistComponent);
        pm.saveEnPoi(newEnPoi);
        
        return gson.toJson(newPoi);
    }
}
