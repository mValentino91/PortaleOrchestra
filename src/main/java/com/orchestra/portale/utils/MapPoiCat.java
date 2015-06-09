
package com.orchestra.portale.utils;

import com.orchestra.portale.persistence.mongo.documents.CompletePOI_It;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andrea
 */
public class MapPoiCat {
    private Map<String,List<CompletePOI_It>> map;
    
    private Map<String, Integer> ratings;

    /**
     * @return the map
     */
    public Map<String,List<CompletePOI_It>> getMap() {
        return map;
    }

    public MapPoiCat(){
        this.map = new HashMap<String,List<CompletePOI_It>>();
        this.ratings = new HashMap<String,Integer>();
        
    }
    
    public static String checkCategory(List<String> mc, List<String> pc){
        String ok = null;
        for(String s: pc){
            for(String t : mc){
                if(s.equals(t)){
                    ok=s;
                    break;
                }
            }
        }
        return ok;
    }
    public void insertPoi(String cat, CompletePOI_It poi){
        List<CompletePOI_It> elem;
        elem=map.get(cat);
        if(elem==null){
            System.out.println("nuova lista");
            List<CompletePOI_It> newele = new ArrayList<CompletePOI_It>();
            newele.add(poi);
            map.put(cat,newele);
            
            System.out.println("agg ele nuova lista"+newele+""+cat);
            
        } else {
            elem.add(poi);
            map.put(cat, elem);
            
            System.out.println("agg elem lista");
            
        }
    }
    
    public void insertRate(String poi, Integer rate){
        this.ratings.put(poi, rate);
    }
    
    public Integer getRate(String poi){
        return this.ratings.get(poi);
    }

    /**
     * @return the ratings
     */
    public Map<String, Integer> getRatings() {
        return ratings;
    }

    /**
     * @param ratings the ratings to set
     */
    public void setRating(Map<String, Integer> ratings) {
        this.ratings = ratings;
    }
    
}
