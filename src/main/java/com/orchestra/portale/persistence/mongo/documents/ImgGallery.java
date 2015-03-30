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
public class ImgGallery {
    private String link;
    private String credit;

    public String getLink() {
        return link;
    }

    public String getCredit() {
        return credit;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
    
    
}
