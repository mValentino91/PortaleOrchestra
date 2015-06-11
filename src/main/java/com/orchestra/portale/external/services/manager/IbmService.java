/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.orchestra.portale.external.services.manager;

import com.google.gson.Gson;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_En;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_It;
import com.orchestra.portale.persistence.mongo.documents.CoverImgComponent;
import com.orchestra.portale.persistence.mongo.documents.ExternalServiceComponent;
import static com.orchestra.portale.utils.BackupRestoreUtils.copy;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Marco Valentino
 */
public class IbmService implements ExternalServiceManager {

    private PersistenceManager pm;
    private static final String loadUrl = "http://www.orchestra.unina.it/orchestra/Services/Ibm/Alberghi";
    private static final String getUrl = "http://www.orchestra.unina.it/orchestra/Services/Ibm/Albergo";
    private static final String[] categoriesName = {"accomodation"};
    //private static final String[] categoriesDelete  = {"accomodation"};
    private static Gson gson = new Gson();

    public IbmService(PersistenceManager manager) {
        pm = manager;
    }

    @Override
    public String load(HttpSession session) {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(loadUrl).openConnection();
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(30000);
            String result = IOUtils.toString(urlConnection.getInputStream());
            urlConnection.disconnect();
            JsonParser parser = new JsonParser();
            JsonElement json = parser.parse(result);
            JsonArray alberghi = json.getAsJsonArray();
            StringBuilder insertedPoi = new StringBuilder();
            for (int i = 0; i < alberghi.size(); i++) {
                insertedPoi.append(createPoi(i, alberghi, session));
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
            return result;
        } catch (IOException e) {
            return "response{code:1,error:" + e.getMessage() + "}";
        }
    }

    /*private void deletePois() {
        for (String categorie : categoriesDelete) {
            Iterator<CompletePOI> pois = pm.getCompletePoiByCategories(categorie).iterator();
            while (pois.hasNext()) {
                CompletePOI_It poi = pois.next();
                pm.deletePoi(poi);
            }
        }
    }*/

    private String createPoi(int item, JsonArray alberghi, HttpSession session) {
        CompletePOI_It newPoi = new CompletePOI_It();
        newPoi.setName(alberghi.get(item).getAsJsonObject().get("nome").getAsString());
        newPoi.setAddress(alberghi.get(item).getAsJsonObject().get("indirizzo").getAsString().replace("\"", ""));
        double[] location = {
            alberghi.get(item).getAsJsonObject().get("location").getAsJsonArray().get(0).getAsDouble(),
            alberghi.get(item).getAsJsonObject().get("location").getAsJsonArray().get(1).getAsDouble()
        };
        
        newPoi.setLocation(location);
        newPoi.setShortDescription(alberghi.get(item).getAsJsonObject().get("classificazione").getAsString().replace("\"", ""));
        ArrayList<String> categories = new ArrayList<String>();
        categories.addAll(Arrays.asList(categoriesName));
        newPoi.setCategories(categories);
        ArrayList<AbstractPoiComponent> newlistComponent = new ArrayList<AbstractPoiComponent>();
        CoverImgComponent cover = new CoverImgComponent();
        cover.setLink("cover.jpg");
        newlistComponent.add(cover);
        newPoi.setComponents(newlistComponent);

        pm.savePoi((CompletePOI_It) newPoi);

        ServletContext sc = session.getServletContext();
        File dir = new File(sc.getRealPath("/") + "dist" + File.separator + "img" + File.separator + "webservice" + File.separator + "alberghi" + File.separator + "cover.jpg");
        File dir2 = new File(sc.getRealPath("/") + "dist" + File.separator + "poi" + File.separator + "img" + File.separator + newPoi.getId());
        if (!dir2.exists()) {
            dir2.mkdirs();
        }
        try {
            copy(dir.getCanonicalPath(), dir2.getCanonicalPath());
        } catch (IOException ex) {
            Logger.getLogger(CiRoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        pm.savePoi( newPoi);
        
        CompletePOI_En newEnPoi = new CompletePOI_En();
        newEnPoi.setName(alberghi.get(item).getAsJsonObject().get("nome").getAsString());
        newEnPoi.setAddress(alberghi.get(item).getAsJsonObject().get("indirizzo").getAsString().replace("\"", ""));
        double[] enlocation = {
            alberghi.get(item).getAsJsonObject().get("location").getAsJsonArray().get(0).getAsDouble(),
            alberghi.get(item).getAsJsonObject().get("location").getAsJsonArray().get(1).getAsDouble()
        };
        newEnPoi.setLocation(enlocation);
        newEnPoi.setId(newPoi.getId());
        newEnPoi.setShortDescription(alberghi.get(item).getAsJsonObject().get("classificazione").getAsString().replace("\"", ""));
        ArrayList<String> encategories = new ArrayList<String>();
        encategories.addAll(Arrays.asList(categoriesName));
        newEnPoi.setCategories(encategories);
        pm.saveEnPoi(newEnPoi);

        return gson.toJson(newPoi);
    }
}