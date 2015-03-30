/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.mongo.documents;

import java.util.List;
import org.springframework.data.annotation.TypeAlias;

/**
 *
 * @author mekko
 */
@TypeAlias("com.orchestra.portale.persistence.mongo.documents.ImgGalleryComponent")
public class ImgGalleryComponent extends AbstractPoiComponent {

    private List<ImgGallery> links;

    public List<ImgGallery> getLinks() {
        return links;
    }

    public void setLinks(List<ImgGallery> links) {
        this.links = links;
    }

}
