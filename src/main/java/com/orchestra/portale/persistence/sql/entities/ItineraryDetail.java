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
 * @author andrea
 */
@Entity
@Table(name = "itinerary_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItineraryDetail.findAll", query = "SELECT i FROM ItineraryDetail i"),
    @NamedQuery(name = "ItineraryDetail.findByIdItineraryDetail", query = "SELECT i FROM ItineraryDetail i WHERE i.idItineraryDetail = :idItineraryDetail"),
    @NamedQuery(name = "ItineraryDetail.findByIdPoi", query = "SELECT i FROM ItineraryDetail i WHERE i.idPoi = :idPoi"),
    @NamedQuery(name = "ItineraryDetail.findByIdItinerary", query = "SELECT i FROM ItineraryDetail i WHERE i.idItinerary = :idItinerary"),
    @NamedQuery(name = "ItineraryDetail.findByIdOfferChoice", query = "SELECT i FROM ItineraryDetail i WHERE i.idOfferChoice = :idOfferChoice")})
public class ItineraryDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idItineraryDetail")
    private Integer idItineraryDetail;
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

    public ItineraryDetail() {
    }

    public ItineraryDetail(Integer idItineraryDetail) {
        this.idItineraryDetail = idItineraryDetail;
    }

    public ItineraryDetail(Integer idItineraryDetail, String idPoi, int idItinerary, int idOfferChoice) {
        this.idItineraryDetail = idItineraryDetail;
        this.idPoi = idPoi;
        this.idItinerary = idItinerary;
        this.idOfferChoice = idOfferChoice;
    }

    public Integer getIdItineraryDetail() {
        return idItineraryDetail;
    }

    public void setIdItineraryDetail(Integer idItineraryDetail) {
        this.idItineraryDetail = idItineraryDetail;
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
        hash += (idItineraryDetail != null ? idItineraryDetail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItineraryDetail)) {
            return false;
        }
        ItineraryDetail other = (ItineraryDetail) object;
        if ((this.idItineraryDetail == null && other.idItineraryDetail != null) || (this.idItineraryDetail != null && !this.idItineraryDetail.equals(other.idItineraryDetail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.ItineraryDetail[ idItineraryDetail=" + idItineraryDetail + " ]";
    }
    
}
