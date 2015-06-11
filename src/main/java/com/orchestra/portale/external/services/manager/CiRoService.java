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
import com.orchestra.portale.persistence.mongo.documents.CoverImgComponent;
import com.orchestra.portale.persistence.mongo.documents.ExternalServiceComponent;
import com.orchestra.portale.utils.BackupRestoreUtils;
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
import org.springframework.context.i18n.LocaleContextHolder;

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
    public String load(HttpSession session) {
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
                insertedPoi.append(createPoi(i, puntiCiro, session));
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
            if (LocaleContextHolder.getLocale().toString().equals("en")) {
                return "Service temporarily unavailable";
            }
            return "Servizio Momentaneamente Non Disponibile!";
        } catch (IOException e) {
            if (LocaleContextHolder.getLocale().toString().equals("en")) {
                return "Service temporarily unavailable";
            }
            return "Servizio Momentaneamente Non Disponibile!";
        }
    }

    private void deletePois() {
        pm.setLang("it");
        Iterator<? extends CompletePOI> pois = pm.getCompletePoiByCategories(categoriesDelete).iterator();
        while (pois.hasNext()) {
            CompletePOI poi = pois.next();
            pm.deletePoi((CompletePOI_It) poi);

        }

        pm.setLang("en");
        Iterator<? extends CompletePOI> enpois = pm.getCompletePoiByCategories(categoriesDelete).iterator();
        while (enpois.hasNext()) {
            CompletePOI poi = enpois.next();
            pm.deleteEnPoi((CompletePOI_En) poi);

        }
    }

    private String createPoi(int item, JsonArray puntiCiro, HttpSession session) {
        CompletePOI_It newPoi = new CompletePOI_It();
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
        externalServiceComponent.setParameters("id=" + id);
        newPoi.setExternalUrl(innerUrl + "?id=" + id);
        CoverImgComponent cover = new CoverImgComponent();
        cover.setLink("cover.jpg");
        newlistComponent.add(cover);
        newlistComponent.add(externalServiceComponent);
        ArrayList<String> categories = new ArrayList<String>();
        categories.addAll(Arrays.asList(categoriesName));
        newPoi.setCategories(categories);
        newPoi.setComponents(newlistComponent);

        pm.savePoi((CompletePOI_It) newPoi);

        ServletContext sc = session.getServletContext();
        File dir = new File(sc.getRealPath("/") + "dist" + File.separator + "img" + File.separator + "webservice" + File.separator + "ciro" + File.separator + "cover.jpg");
        File dir2 = new File(sc.getRealPath("/") + "dist" + File.separator + "poi" + File.separator + "img" + File.separator + newPoi.getId());
        if (!dir2.exists()) {
            dir2.mkdirs();
        }
        try {
            copy(dir.getCanonicalPath(), dir2.getCanonicalPath());
        } catch (IOException ex) {
            Logger.getLogger(CiRoService.class.getName()).log(Level.SEVERE, null, ex);
        }

        CompletePOI_En newEnPoi = new CompletePOI_En();
        newEnPoi.setName(puntiCiro.get(item).getAsJsonObject().get("nome").getAsString());
        newEnPoi.setAddress(puntiCiro.get(item).getAsJsonObject().get("indirizzo").getAsString());
        newEnPoi.setId(newPoi.getId());
        double[] enlocation = {
            puntiCiro.get(item).getAsJsonObject().get("latitudine").getAsDouble(),
            puntiCiro.get(item).getAsJsonObject().get("longitudine").getAsDouble()
        };
        newEnPoi.setLocation(enlocation);
        ArrayList<AbstractPoiComponent> ennewlistComponent = new ArrayList<AbstractPoiComponent>();
        ExternalServiceComponent enexternalServiceComponent = new ExternalServiceComponent();
        String enid = puntiCiro.get(item).getAsJsonObject().get("id").getAsString();
        enexternalServiceComponent.setURL(getUrl);
        enexternalServiceComponent.setParameters("id=" + enid);
        newEnPoi.setExternalUrl(innerUrl + "?id=" + enid);

        ennewlistComponent.add(enexternalServiceComponent);
        ArrayList<String> encategories = new ArrayList<String>();
        encategories.addAll(Arrays.asList(categoriesName));
        newEnPoi.setCategories(encategories);
        newEnPoi.setComponents(ennewlistComponent);

        pm.saveEnPoi(newEnPoi);

        return gson.toJson(newPoi);
    }
}
