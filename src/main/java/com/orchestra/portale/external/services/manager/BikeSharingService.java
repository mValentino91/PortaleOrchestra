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
                     return "<b>Postazioni libere:</b> "+puntiBike.get(i).getAsJsonObject().get("available_locks").getAsString()
                             +"<br><b>Biciclette disponibili:</b> "+puntiBike.get(i).getAsJsonObject().get("available_bikes").getAsString()
                             +"<br><b>Capacità :</b>"+puntiBike.get(i).getAsJsonObject().get("capacity").getAsString();
                 }
            }
            
            return "Servizio Momentaneamente Non Disponibile!";
            
        } catch (IOException e) {
            return "Servizio Momentaneamente Non Disponibile!";
        }
    }

    private void deletePois() {
        Iterator<CompletePOI> pois = pm.getCompletePoiByCategories(categoriesDelete).iterator();
        while (pois.hasNext()) {
            CompletePOI poi = pois.next();
            pm.deletePoi(poi);
        }
    }

    private String createPoi(int item, JsonArray puntiBike) {
        CompletePOI newPoi = new CompletePOI();
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

        pm.savePoi(newPoi);

        return gson.toJson(newPoi);
    }
}
