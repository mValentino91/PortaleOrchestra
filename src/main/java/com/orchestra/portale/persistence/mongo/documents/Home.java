/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Alex
 */
@Document(collection = "pages")
public class Home {
    @Id
    private String id;
    private String slug;
    private String title;
    private String description;
    private ArrayList<Tile> tilesList;
    private SubMenu submenu;
    private ArrayList<CarouselPages> imgList;
    private ArrayList<String> categorySlugList;
    private ArrayList<String> idPoiList;
    private ArrayList<TileSlider> tileslider;

    public ArrayList<TileSlider> getTileslider() {
        return tileslider;
    }

    public void setTileslider(ArrayList<TileSlider> tileslider) {
        this.tileslider = tileslider;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * @param slug the slug to set
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the tilesList
     */
    public ArrayList<Tile> getTilesList() {
        return tilesList;
    }

    /**
     * @param tilesList the tilesList to set
     */
    public void setTilesList(ArrayList<Tile> tilesList) {
        this.tilesList = tilesList;
    }

    /**
     * @return the submenu
     */
    public SubMenu getSubmenu() {
        return submenu;
    }

    /**
     * @param submenu the submenu to set
     */
    public void setSubmenu(SubMenu submenu) {
        this.submenu = submenu;
    }

    /**
     * @return the imgList
     */
    public ArrayList<CarouselPages> getImgList() {
        return imgList;
    }

    /**
     * @param imgList the imgList to set
     */
    public void setImgList(ArrayList<CarouselPages> imgList) {
        this.imgList = imgList;
    }

    /**
     * @return the categorySlugList
     */
    public ArrayList<String> getCategorySlugList() {
        return categorySlugList;
    }

    /**
     * @param categorySlugList the categorySlugList to set
     */
    public void setCategorySlugList(ArrayList<String> categorySlugList) {
        this.categorySlugList = categorySlugList;
    }

    /**
     * @return the idPoiList
     */
    public ArrayList<String> getIdPoiList() {
        return idPoiList;
    }

    /**
     * @param idPoiList the idPoiList to set
     */
    public void setIdPoiList(ArrayList<String> idPoiList) {
        this.idPoiList = idPoiList;
    }
}
