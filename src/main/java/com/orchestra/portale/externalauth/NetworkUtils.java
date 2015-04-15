/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.externalauth;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
    
    
    public static void saveImageFromURL(String imageUrl, String destinationPath, String destinationFile) throws IOException {
            
            System.out.println("***********************");
            System.out.println("imgUrl: " + imageUrl);
            System.out.println("destinatioPath: " + destinationPath);
            System.out.println("destinatioFile: " + destinationFile);
            System.out.println("***********************");
        
        
            new File(destinationPath).mkdirs();
            
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(destinationPath+"/"+destinationFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
            }

            is.close();
            os.close();
    }    
}
