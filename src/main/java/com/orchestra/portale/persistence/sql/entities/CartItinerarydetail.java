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
@Table(name = "cartItinerary_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CartItinerarydetail.findAll", query = "SELECT c FROM CartItinerarydetail c"),
    @NamedQuery(name = "CartItinerarydetail.findByIdCartItinerary", query = "SELECT c FROM CartItinerarydetail c WHERE c.idCartItinerary = :idCartItinerary"),
    @NamedQuery(name = "CartItinerarydetail.findByIdCard", query = "SELECT c FROM CartItinerarydetail c WHERE c.idCard = :idCard"),
    @NamedQuery(name = "CartItinerarydetail.findByIdItinerary", query = "SELECT c FROM CartItinerarydetail c WHERE c.idItinerary = :idItinerary"),
    //@NamedQuery(name = "CartItinerarydetail.findByIdUser", query = "SELECT c FROM CartItinerarydetail c WHERE c.idUser = :idUser"),
    @NamedQuery(name = "CartItinerarydetail.findByIdPoi", query = "SELECT c FROM CartItinerarydetail c WHERE c.idPoi = :idPoi"),
    @NamedQuery(name = "CartItinerarydetail.findByQta", query = "SELECT c FROM CartItinerarydetail c WHERE c.qta = :qta"),
    @NamedQuery(name = "CartItinerarydetail.findBySum", query = "SELECT c FROM CartItinerarydetail c WHERE c.sum = :sum"),
    @NamedQuery(name = "CartItinerarydetail.findByIdOffer", query = "SELECT c FROM CartItinerarydetail c WHERE c.idOffer = :idOffer"),
    @NamedQuery(name = "CartItinerarydetail.findByStatus", query = "SELECT c FROM CartItinerarydetail c WHERE c.status = :status"),
    @NamedQuery(name = "CartItinerarydetail.findByTipoStock", query = "SELECT c FROM CartItinerarydetail c WHERE c.tipoStock = :tipoStock")})
public class CartItinerarydetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCartItinerary")
    private Integer idCartItinerary;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCard")
    private int idCard;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idItinerary")
    private int idItinerary;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "idPoi")
    private String idPoi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qta")
    private int qta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sum")
    private float sum;
    @Column(name = "idOffer")
    private Integer idOffer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Size(max = 200)
    @Column(name = "tipoStock")
    private String tipoStock;

    public CartItinerarydetail() {
    }

    public CartItinerarydetail(Integer idCartItinerary) {
        this.idCartItinerary = idCartItinerary;
    }

    public CartItinerarydetail(Integer idCartItinerary, int idCard, int idItinerary, int idUser, String idPoi, int qta, float sum, int status) {
        this.idCartItinerary = idCartItinerary;
        this.idCard = idCard;
        this.idItinerary = idItinerary;
        this.idUser = idUser;
        this.idPoi = idPoi;
        this.qta = qta;
        this.sum = sum;
        this.status = status;
    }

    public Integer getIdCartItinerary() {
        return idCartItinerary;
    }

    public void setIdCartItinerary(Integer idCartItinerary) {
        this.idCartItinerary = idCartItinerary;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public int getIdItinerary() {
        return idItinerary;
    }

    public void setIdItinerary(int idItinerary) {
        this.idItinerary = idItinerary;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getIdPoi() {
        return idPoi;
    }

    public void setIdPoi(String idPoi) {
        this.idPoi = idPoi;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public Integer getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(Integer idOffer) {
        this.idOffer = idOffer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTipoStock() {
        return tipoStock;
    }

    public void setTipoStock(String tipoStock) {
        this.tipoStock = tipoStock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCartItinerary != null ? idCartItinerary.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CartItinerarydetail)) {
            return false;
        }
        CartItinerarydetail other = (CartItinerarydetail) object;
        if ((this.idCartItinerary == null && other.idCartItinerary != null) || (this.idCartItinerary != null && !this.idCartItinerary.equals(other.idCartItinerary))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.CartItinerarydetail[ idCartItinerary=" + idCartItinerary + " ]";
    }
    
}
