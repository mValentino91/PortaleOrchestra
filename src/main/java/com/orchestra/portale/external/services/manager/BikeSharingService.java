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

    @Autowired
    PersistenceManager pm;
    private final String baseUrl = "http://www.movinap.it/_CI/api_v1/station/getAllStationsInfo";
    private final String baseImgUrl = "http://www.bikesharingnapoli.it/wp-content/themes/bikesharing/images/maps/stazioni/";

    @Override
    public String load() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getResponse(Map<String, String[]> mapParams) {
        try {
            /*URL url = new URL(baseUrl);
             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
             connection.setRequestMethod("GET");
             //connection.addRequestProperty("token", token);
             connection.setRequestProperty("Content-Type", "application/json");
             //connection.setRequestProperty("charset", "utf-8");
             BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             //System.out.println(read.readLine());
             return read.readLine();*/

            HttpURLConnection urlConnection = (HttpURLConnection) new URL(baseUrl).openConnection();
            urlConnection.setConnectTimeout(15000); // set timeout to 10 seconds
            urlConnection.setReadTimeout(30000); // set timeout to 10 seconds

            urlConnection.addRequestProperty("Accept-Language", Locale.getDefault().toString().replace('_', '-'));

            String result = IOUtils.toString(urlConnection.getInputStream());
            urlConnection.disconnect();

            return result;
            
        } catch (IOException e) {
            return "response{code:1,error:" + e.getMessage() + "}";
        }
    }
}
