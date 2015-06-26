/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.controller.editing.components;

import com.orchestra.portale.dbManager.PersistenceManager;
import com.orchestra.portale.persistence.mongo.documents.AbstractPoiComponent;
import com.orchestra.portale.persistence.mongo.documents.CompletePOI_It;
import com.orchestra.portale.persistence.mongo.documents.DescriptionComponent;
import com.orchestra.portale.persistence.mongo.documents.Section;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Alex
 */
@Controller
public class UpdateSection {

    @Autowired
    PersistenceManager pm;

    @ResponseBody
    @RequestMapping(value = "/updatesect")
    public void updsec(@RequestParam Map<String, String> params, @RequestParam("id") String id) {
        pm.setLang("it");
        CompletePOI_It poi = (CompletePOI_It) pm.getCompletePoiById(id);
        ArrayList<AbstractPoiComponent> complist = new ArrayList<AbstractPoiComponent>();
        for (AbstractPoiComponent comp : poi.getComponents()) {

            //associazione delle componenti al model tramite lo slug
            String slug = comp.slug();
            int index = slug.lastIndexOf(".");
            String cname = slug.substring(index + 1).replace("Component", "").toLowerCase();
            if (cname.equals("description")) {
                int i = 1;
                if (params.containsKey("par" + i)) {
                    ArrayList<Section> list = new ArrayList<Section>();

                    while (params.containsKey("par" + i)) {
                        Section section = new Section();
                        if (params.containsKey("titolo" + i)) {
                            section.setTitle(params.get("titolo" + i));
                        }
                        section.setDescription(params.get("par" + i));
                        list.add(section);
                        i = i + 1;

                    }
                    DescriptionComponent description_component = new DescriptionComponent();
                    description_component.setSectionsList(list);
                    complist.add(description_component);
                }
            } else {
                complist.add(comp);
            }
        }
        poi.setComponents(complist);
        pm.savePoi(poi);
    }
}
