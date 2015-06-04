/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

import org.springframework.data.annotation.TypeAlias;

/**
 *
 * @author mekko
 */
@TypeAlias("com.orchestra.portale.persistence.mongo.documents.CoverImgComponent")
public class CoverImgComponent extends AbstractPoiComponent {

    private String link;
    private String top;
    private String left;
    
    

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the top
     */
    public String getTop() {
        return top;
    }

    /**
     * @param top the top to set
     */
    public void setTop(String top) {
        this.top = top;
    }

    /**
     * @return the left
     */
    public String getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(String left) {
        this.left = left;
    }

}
