
package com.orchestra.portale.utils;

import com.orchestra.portale.persistence.mongo.documents.CompletePOI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andrea
 */
public class MapPoiCat {
    private Map<String,List<CompletePOI>> map;

    /**
     * @return the map
     */
    public Map<String,List<CompletePOI>> getMap() {
        return map;
    }

    public MapPoiCat(Map<String,List<CompletePOI>> map){
        this.map = map;
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
    public void insertPoi(String cat, CompletePOI poi){
        List<CompletePOI> elem;
        elem=map.get(cat);
        if(elem==null){
            System.out.println("nuova lista");
            List<CompletePOI> newele = new ArrayList<CompletePOI>();
            newele.add(poi);
            map.put(cat,newele);
            
            System.out.println("agg ele nuova lista"+newele+""+cat);
            
        } else {
            elem.add(poi);
            map.put(cat, elem);
            
            System.out.println("agg elem lista");
            
        }
    }
    
}
