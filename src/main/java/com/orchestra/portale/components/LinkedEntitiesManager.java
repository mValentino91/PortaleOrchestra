/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.components;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_It;
import com.orchestra.portale.persistence.mongo.documents.DeepeningPage;
import com.orchestra.portale.persistence.mongo.documents.LinkedPoi;
import com.orchestra.portale.persistence.mongo.documents.LinkedPoiComponent;
import com.orchestra.portale.utils.CouplePOI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;

/**
 *
 * @author Alex
 */
public class LinkedEntitiesManager {

    

    public static ArrayList<LinkedEntities> linkedmanager(LinkedPoiComponent lp, ServletContext sc, PersistenceManager pm) throws FileNotFoundException {
        ArrayList<LinkedEntities> entitieslist = new ArrayList<LinkedEntities>();
        ArrayList<LinkedPoi> lplist = lp.getLinked();
        ArrayList<String> pois = new ArrayList<String>();
        ArrayList<String> dpages = new ArrayList<String>();
        ArrayList<CompletePOI_It> completepois = new ArrayList<CompletePOI_It>();
        ArrayList<DeepeningPage> deepeningpages = new ArrayList<DeepeningPage>();
        Map<String, CompletePOI_It> poiMap = new HashMap<String, CompletePOI_It>();
        Map<String, DeepeningPage> dpageMap = new HashMap<String, DeepeningPage>();
        Map<String, String> catMap = new HashMap<String, String>();
        BufferedReader br = new BufferedReader(
                new FileReader(sc.getRealPath("/" + "dist" + File.separator + "jsonDB" + File.separator + "categoriesTree_IT.json")));

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(br);
        JsonArray jarray = element.getAsJsonArray();
        JsonObject jobj;
        for (JsonElement el : jarray) {
            jobj = el.getAsJsonObject();
            catMap.put(jobj.get("slug").getAsString(), jobj.get("color").getAsString());
        }

        for (LinkedPoi l : lplist) {
            ArrayList<CouplePOI> lpoi = l.getPoilist();
            for (CouplePOI cp : lpoi) {
                if (cp.getType().equals("Poi")) {
                    pois.add(cp.getIdpoi());
                }
                if (cp.getType().equals("DP")) {
                    dpages.add(cp.getIdpoi());
                }

            }
        }
        completepois = (ArrayList<CompletePOI_It>) pm.getCompletePoisById(pois);
        deepeningpages = (ArrayList<DeepeningPage>) pm.getDeepeningPagesById(dpages);

        for (CompletePOI_It p : completepois) {
            poiMap.put(p.getId(), p);
        }
        for (DeepeningPage dp : deepeningpages) {
            dpageMap.put(dp.getId(), dp);
        }

        for (LinkedPoi l : lplist) {
            LinkedEntities le = new LinkedEntities();
            ArrayList<LinkedEntity> lelist = new ArrayList<LinkedEntity>();
            le.setText(l.getDescription());
            ArrayList<CouplePOI> lpoi = l.getPoilist();
            for (CouplePOI cp : lpoi) {
                LinkedEntity lne = new LinkedEntity();
                if (cp.getType().equals("Poi")) {
                    lne.setType("Poi");
                    lne.setId(cp.getIdpoi());
                    lne.setName(poiMap.get(lne.getId()).getName());
                    lne.setColor(catMap.get(poiMap.get(lne.getId()).getCategories().get(0)));

                }
                if (cp.getType().equals("DP")) {
                    lne.setType("DP");
                    lne.setId(cp.getIdpoi());
                    lne.setName(dpageMap.get(lne.getId()).getName());
                    lne.setColor("#3498db");

                }
                lelist.add(lne);
            }
            le.setEntities(lelist);
            entitieslist.add(le);
        }

        return entitieslist;

    }

}
