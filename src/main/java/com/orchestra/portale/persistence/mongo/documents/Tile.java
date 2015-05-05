/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

/**
 *
 * @author Alex
 */
public class Tile {
    private String text;
    private String icon;
    private String link;
    private String color;
    private Boolean animated;
    private String start;

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public String getIcon() {
        return icon;
    }

    public String getLink() {
        return link;
    }

    public String getColor() {
        return color;
    }

    /**
     * @return the animation
     */
    public Boolean getAnimated() {
        return animated;
    }

    /**
     * @param animated the animation to set
     */
    public void setAnimated(Boolean animated) {
        this.animated = animated;
    }

    /**
     * @return the start
     */
    public String getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(String start) {
        this.start = start;
    }
}
