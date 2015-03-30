/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.utils;
  import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

    
/**
 *
 * @author Alex
 */
public class BackupRestoreUtils {
 static FileInputStream fis = null;
  static  FileOutputStream fos = null;
    public static void copy(String origine, String destinaz) throws IOException
    {
        File orig = new File(origine);
        File dest = new File(destinaz);
        //controllo che dest sia una cartella altrimenti chiudo
        
        if (orig.isFile())
        {
            copioFile(orig,new File(dest+File.separator+orig.getName()));
        }
        else
        {
            String dir_path = dest + File.separator;
            // copio i file
            leggo_i_file(orig,dest,dir_path);
        }
    }
    static File dir = null; // mi serve nel caso in cui ci sono cartelle in altre cartelle
    public static void leggo_i_file(File orig,File dest,String dir_path) throws IOException
    {
        // creo la cartella
        dir_path +=  orig.getName()+File.separator;
        dir = new File(dir_path);
        if (!dir.exists())
        {
            
            dir.mkdirs();
        }
        File [] lista = orig.listFiles();
        for(int y = 0; y < lista.length; y++)
        {
            if (lista[y].isFile())
            {
                //invia File
                
                copioFile( lista[y] ,new File(dir+File.separator+lista[y].getName()));
            }
            else if (lista[y].isDirectory())
            {
                //ricorsione
                leggo_i_file(lista[y],dest,dir_path);
            }
        }
    }
    public static void copioFile(File orig, File dest) throws IOException
    {
        int letti = 0;
        long tot = 0;
        // inizializzo uno buffer di 4Kb
        byte[] buffer = new byte[4096];
        // inizializzo stream per la copia del file
        try {
            fis = new FileInputStream(orig);
            fos = new FileOutputStream(dest);
            } catch (FileNotFoundException e) {
            
           
        }
        
        while((letti = fis.read(buffer)) != -1){
            fos.write(buffer, 0, letti);
            tot += letti;
        }
        
        // chiudo il file d´origine
        fis.close();
        //chiudo il file di destinazione
        fos.close();
    }
    
}

