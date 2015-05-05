/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.external.services.manager;

import com.orchestra.portale.dbManager.PersistenceManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private static final String baseImgUrl = "http://www.bikesharingnapoli.it/wp-content/themes/bikesharing/images/maps/stazioni/";

    public BikeSharingService(PersistenceManager manager) {
        pm = manager;
    }

    @Override
    public String load() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

            return result;

        } catch (IOException e) {
            return "response:{code:1,error:" + e.getMessage() + "}";
        }
    }
}
