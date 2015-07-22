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
@Table(name = "userOffer_choice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserOffer_choice.findAll", query = "SELECT u FROM UserOffer_choice u"),
    @NamedQuery(name = "UserOffer_choice.findByIdUserOfferChoice", query = "SELECT u FROM UserOffer_choice u WHERE u.idUserOfferChoice = :idUserOfferChoice"),
    @NamedQuery(name = "UserOffer_choice.findByIdItinerarydetail", query = "SELECT u FROM UserOffer_choice u WHERE u.idItinerarydetail = :idItinerarydetail"),
    @NamedQuery(name = "UserOffer_choice.findByQta", query = "SELECT u FROM UserOffer_choice u WHERE u.qta = :qta"),
    @NamedQuery(name = "UserOffer_choice.findByStatus", query = "SELECT u FROM UserOffer_choice u WHERE u.status = :status"),
    @NamedQuery(name = "UserOffer_choice.findBySum", query = "SELECT u FROM UserOffer_choice u WHERE u.sum = :sum"),
    @NamedQuery(name = "UserOffer_choice.findByIdOffer", query = "SELECT u FROM UserOffer_choice u WHERE u.idOffer = :idOffer"),
    @NamedQuery(name = "UserOffer_choice.findByStockType", query = "SELECT u FROM UserOffer_choice u WHERE u.stockType = :stockType")})
public class UserOffer_choice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUserOfferChoice")
    private Integer idUserOfferChoice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idItinerary_detail")
    private int idItinerarydetail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qta")
    private int qta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sum")
    private float sum;
    @Column(name = "idOffer")
    private Integer idOffer;
    @Size(max = 200)
    @Column(name = "StockType")
    private String stockType;

    public UserOffer_choice() {
    }

    public UserOffer_choice(Integer idUserOfferChoice) {
        this.idUserOfferChoice = idUserOfferChoice;
    }

    public UserOffer_choice(Integer idUserOfferChoice, int idItinerarydetail, int qta, int status, float sum) {
        this.idUserOfferChoice = idUserOfferChoice;
        this.idItinerarydetail = idItinerarydetail;
        this.qta = qta;
        this.status = status;
        this.sum = sum;
    }

    public Integer getIdUserOfferChoice() {
        return idUserOfferChoice;
    }

    public void setIdUserOfferChoice(Integer idUserOfferChoice) {
        this.idUserOfferChoice = idUserOfferChoice;
    }

    public int getIdItinerarydetail() {
        return idItinerarydetail;
    }

    public void setIdItinerarydetail(int idItinerarydetail) {
        this.idItinerarydetail = idItinerarydetail;
    }

    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserOfferChoice != null ? idUserOfferChoice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserOffer_choice)) {
            return false;
        }
        UserOffer_choice other = (UserOffer_choice) object;
        if ((this.idUserOfferChoice == null && other.idUserOfferChoice != null) || (this.idUserOfferChoice != null && !this.idUserOfferChoice.equals(other.idUserOfferChoice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.UserOffer_choice[ idUserOfferChoice=" + idUserOfferChoice + " ]";
    }
    
}
