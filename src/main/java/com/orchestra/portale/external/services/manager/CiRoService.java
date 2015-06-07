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

/**
 *
 * @author Marco Valentino
 */
public class CiRoService implements ExternalServiceManager {

    private PersistenceManager pm;
    private static final String loadUrl = "http://ciro.techmobile.eu:8080/CiRo/prenotazioneService/getPuntiCiro";
    private static final String getUrl = "http://ciro.techmobile.eu:8080/CiRo/administrator/PrenotazioneService/checkDispoVetture";
        private static final String innerUrl = "./externalService/ciro/get";
    private static final String[] categoriesName = {"ciro", "mobility"};
    private static final String[] categoriesDelete = {"ciro"};
    private static Gson gson = new Gson();

    public CiRoService(PersistenceManager manager) {
        pm = manager;
    }

    @Override
    public String load() {
        try {
            deletePois();
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(loadUrl).openConnection();
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(30000);
            String result = IOUtils.toString(urlConnection.getInputStream());
            urlConnection.disconnect();
            JsonParser parser = new JsonParser();
            JsonElement json = parser.parse(result);
            JsonArray puntiCiro = json.getAsJsonObject().get("puntiCiro").getAsJsonArray();
            StringBuilder insertedPoi = new StringBuilder();
            for (int i = 0; i < puntiCiro.size(); i++) {
                insertedPoi.append(createPoi(i, puntiCiro));
            }
            return insertedPoi.toString();
        } catch (Exception e) {
            return "response{code:1,error:" + e.toString() + "}";
        }
    }

    @Override
    public String getResponse(Map<String, String[]> mapParams) {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(getUrl).openConnection();
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(30000);
            urlConnection.addRequestProperty("Accept-Language", Locale.getDefault().toString().replace('_', '-'));
            String result = IOUtils.toString(urlConnection.getInputStream());
            urlConnection.disconnect();
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

    private String createPoi(int item, JsonArray puntiCiro) {
        CompletePOI newPoi = new CompletePOI();
        newPoi.setName(puntiCiro.get(item).getAsJsonObject().get("nome").getAsString());
        newPoi.setAddress(puntiCiro.get(item).getAsJsonObject().get("indirizzo").getAsString());
        double[] location = {
            puntiCiro.get(item).getAsJsonObject().get("latitudine").getAsDouble(),
            puntiCiro.get(item).getAsJsonObject().get("longitudine").getAsDouble()
        };
        newPoi.setLocation(location);
        ArrayList<AbstractPoiComponent> newlistComponent = new ArrayList<AbstractPoiComponent>();
        ExternalServiceComponent externalServiceComponent = new ExternalServiceComponent();
        String id = puntiCiro.get(item).getAsJsonObject().get("id").getAsString();
        externalServiceComponent.setURL(getUrl);
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
