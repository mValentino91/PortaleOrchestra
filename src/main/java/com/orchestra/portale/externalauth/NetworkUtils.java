/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.externalauth;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author antonio
 */
public class NetworkUtils {

    public static BufferedReader doConnection(String url_in) throws MalformedURLException, IOException{
            
            URL url = new URL(url_in);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            return read;
            
    }
}
