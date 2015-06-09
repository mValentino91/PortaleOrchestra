/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Alex
 *
 **/

public interface Pages {
   
  

    public abstract void setSubmenu(SubMenu submenu);

    public abstract SubMenu getSubmenu();
 

    public abstract void setSlug(String slug);

    

    public abstract void setIdPoiList(ArrayList<String> idPoiList);

    public abstract String getSlug();

    public abstract String[] getCategorySlugList();

    public abstract void setCategorySlugList(String[] categorySlugList);

    

    public abstract ArrayList<String> getIdPoiList();

   

    public abstract void setId(String id);

    public abstract void setTitle(String title);

    public abstract void setDescription(String description);

    public abstract void setTilesList(ArrayList<Tile> tilesList);

   

    public abstract void setComponents(List<AbstractPoiComponent> components);

    public abstract String getId();

    public abstract String getTitle();

    public abstract String getDescription();

    public abstract ArrayList<Tile> getTilesList();

    public abstract List<AbstractPoiComponent> getComponents();

    public abstract ArrayList<CarouselPages> getImgList();

    public abstract void setImgList(ArrayList<CarouselPages> imgList);

    /**
     * @return the colorSchemaList
     */
    public abstract ArrayList<PageColorSchema> getColorSchemaList();

    /**
     * @param colorSchemaList the colorSchemaList to set
     */
    public abstract void setColorSchemaList(ArrayList<PageColorSchema> colorSchemaList);
    
}
