/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andzaccaro
 */
@Entity
@Table(name = "itinerary_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itinerary_detail.findAll", query = "SELECT i FROM Itinerary_detail i"),
    @NamedQuery(name = "Itinerary_detail.findByIdItinerarydetail", query = "SELECT i FROM Itinerary_detail i WHERE i.idItinerarydetail = :idItinerarydetail"),
    @NamedQuery(name = "Itinerary_detail.findByIdPoi", query = "SELECT i FROM Itinerary_detail i WHERE i.idPoi = :idPoi"),
    @NamedQuery(name = "Itinerary_detail.findByIdItinerary", query = "SELECT i FROM Itinerary_detail i WHERE i.idItinerary = :idItinerary"),
    @NamedQuery(name = "Itinerary_detail.findByIdOfferChoice", query = "SELECT i FROM Itinerary_detail i WHERE i.idOfferChoice = :idOfferChoice")})
public class Itinerary_detail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idItinerary_detail")
    private Integer idItinerarydetail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "idPoi")
    private String idPoi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idItinerary")
    private int idItinerary;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idOfferChoice")
    private int idOfferChoice;

    public Itinerary_detail() {
    }

    public Itinerary_detail(Integer idItinerarydetail) {
        this.idItinerarydetail = idItinerarydetail;
    }

    public Itinerary_detail(Integer idItinerarydetail, String idPoi, int idItinerary, int idOfferChoice) {
        this.idItinerarydetail = idItinerarydetail;
        this.idPoi = idPoi;
        this.idItinerary = idItinerary;
        this.idOfferChoice = idOfferChoice;
    }

    public Integer getIdItinerarydetail() {
        return idItinerarydetail;
    }

    public void setIdItinerarydetail(Integer idItinerarydetail) {
        this.idItinerarydetail = idItinerarydetail;
    }

    public String getIdPoi() {
        return idPoi;
    }

    public void setIdPoi(String idPoi) {
        this.idPoi = idPoi;
    }

    public int getIdItinerary() {
        return idItinerary;
    }

    public void setIdItinerary(int idItinerary) {
        this.idItinerary = idItinerary;
    }

    public int getIdOfferChoice() {
        return idOfferChoice;
    }

    public void setIdOfferChoice(int idOfferChoice) {
        this.idOfferChoice = idOfferChoice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItinerarydetail != null ? idItinerarydetail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itinerary_detail)) {
            return false;
        }
        Itinerary_detail other = (Itinerary_detail) object;
        if ((this.idItinerarydetail == null && other.idItinerarydetail != null) || (this.idItinerarydetail != null && !this.idItinerarydetail.equals(other.idItinerarydetail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.Itinerary_detail[ idItinerarydetail=" + idItinerarydetail + " ]";
    }
    
}
