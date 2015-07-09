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
    @NamedQuery(name = "ItineraryDetail.findAll", query = "SELECT i FROM ItineraryDetail i"),
    @NamedQuery(name = "ItineraryDetail.findByIdItineraryDetail", query = "SELECT i FROM ItineraryDetail i WHERE i.idItineraryDetail = :idItineraryDetail"),
    @NamedQuery(name = "ItineraryDetail.findByIdItinerary", query = "SELECT i FROM ItineraryDetail i WHERE i.idItinerary = :idItinerary"),
    @NamedQuery(name = "ItineraryDetail.findByIdPoi", query = "SELECT i FROM ItineraryDetail i WHERE i.idPoi = :idPoi"),
    @NamedQuery(name = "ItineraryDetail.findByIdOffer", query = "SELECT i FROM ItineraryDetail i WHERE i.idOffer = :idOffer"),
    @NamedQuery(name = "ItineraryDetail.findByQta", query = "SELECT i FROM ItineraryDetail i WHERE i.qta = :qta"),
    @NamedQuery(name = "ItineraryDetail.findByStatusOffer", query = "SELECT i FROM ItineraryDetail i WHERE i.statusOffer = :statusOffer")})
public class ItineraryDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idItineraryDetail")
    private Integer idItineraryDetail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idItinerary")
    private int idItinerary;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "idPoi")
    private String idPoi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idOffer")
    private int idOffer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qta")
    private int qta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "statusOffer")
    private int statusOffer;

    public ItineraryDetail() {
    }

    public ItineraryDetail(Integer idItineraryDetail) {
        this.idItineraryDetail = idItineraryDetail;
    }

    public ItineraryDetail(Integer idItineraryDetail, int idItinerary, String idPoi, int idOffer, int qta, int statusOffer) {
        this.idItineraryDetail = idItineraryDetail;
        this.idItinerary = idItinerary;
        this.idPoi = idPoi;
        this.idOffer = idOffer;
        this.qta = qta;
        this.statusOffer = statusOffer;
    }

    public Integer getIdItineraryDetail() {
        return idItineraryDetail;
    }

    public void setIdItineraryDetail(Integer idItineraryDetail) {
        this.idItineraryDetail = idItineraryDetail;
    }

    public int getIdItinerary() {
        return idItinerary;
    }

    public void setIdItinerary(int idItinerary) {
        this.idItinerary = idItinerary;
    }

    public String getIdPoi() {
        return idPoi;
    }

    public void setIdPoi(String idPoi) {
        this.idPoi = idPoi;
    }

    public int getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(int idOffer) {
        this.idOffer = idOffer;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public int getStatusOffer() {
        return statusOffer;
    }

    public void setStatusOffer(int statusOffer) {
        this.statusOffer = statusOffer;
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
