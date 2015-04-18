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
public class CarouselPages {
    private String link;
    private String titolo;
    private String testo;

    public String getLink() {
        return link;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}
