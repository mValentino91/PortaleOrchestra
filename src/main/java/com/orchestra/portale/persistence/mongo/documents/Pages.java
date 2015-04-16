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
@Document(collection = "pages")
public class Pages {
    @Id
    private String id;
    private String slug;
    private String title;
    private String description;
    private ArrayList<Tile> tilesList;
    private SubMenu submenu;

    public void setSubmenu(SubMenu submenu) {
        this.submenu = submenu;
    }

    public SubMenu getSubmenu() {
        return submenu;
    }
    private List<AbstractPoiComponent> components;
    private ArrayList<CarouselPages> imgList;
    private ArrayList<String> categorySlugList;
    private ArrayList<String> idPoiList;

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setCategorySlugList(ArrayList<String> categorySlugList) {
        this.categorySlugList = categorySlugList;
    }

    public void setIdPoiList(ArrayList<String> idPoiList) {
        this.idPoiList = idPoiList;
    }

    public String getSlug() {
        return slug;
    }

    public ArrayList<String> getCategorySlugList() {
        return categorySlugList;
    }

    public ArrayList<String> getIdPoiList() {
        return idPoiList;
    }

   

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTilesList(ArrayList<Tile> tilesList) {
        this.tilesList = tilesList;
    }

   

    public void setComponents(List<AbstractPoiComponent> components) {
        this.components = components;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Tile> getTilesList() {
        return tilesList;
    }

    public List<AbstractPoiComponent> getComponents() {
        return components;
    }

    public ArrayList<CarouselPages> getImgList() {
        return imgList;
    }

    public void setImgList(ArrayList<CarouselPages> imgList) {
        this.imgList = imgList;
    }
    
}
