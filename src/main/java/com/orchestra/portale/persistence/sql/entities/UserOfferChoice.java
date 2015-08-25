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
@Table(name = "userOffer_choice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserOfferChoice.findAll", query = "SELECT u FROM UserOfferChoice u"),
    @NamedQuery(name = "UserOfferChoice.findByIdUserOfferChoice", query = "SELECT u FROM UserOfferChoice u WHERE u.idUserOfferChoice = :idUserOfferChoice"),
    @NamedQuery(name = "UserOfferChoice.findByIdItineraryDetail", query = "SELECT u FROM UserOfferChoice u WHERE u.idItineraryDetail = :idItineraryDetail"),
    @NamedQuery(name = "UserOfferChoice.findByQta", query = "SELECT u FROM UserOfferChoice u WHERE u.qta = :qta"),
    @NamedQuery(name = "UserOfferChoice.findByStatus", query = "SELECT u FROM UserOfferChoice u WHERE u.status = :status"),
    @NamedQuery(name = "UserOfferChoice.findBySum", query = "SELECT u FROM UserOfferChoice u WHERE u.sum = :sum"),
    @NamedQuery(name = "UserOfferChoice.findByIdOffer", query = "SELECT u FROM UserOfferChoice u WHERE u.idOffer = :idOffer"),
    @NamedQuery(name = "UserOfferChoice.findByStockType", query = "SELECT u FROM UserOfferChoice u WHERE u.stockType = :stockType"),
    @NamedQuery(name = "UserOfferChoice.findByType", query = "SELECT u FROM UserOfferChoice u WHERE u.type = :type"),
    @NamedQuery(name = "UserOfferChoice.findByDescription", query = "SELECT u FROM UserOfferChoice u WHERE u.description = :description"),
    @NamedQuery(name = "UserOfferChoice.findByPrice", query = "SELECT u FROM UserOfferChoice u WHERE u.price = :price")})
public class UserOfferChoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUserOfferChoice")
    private Integer idUserOfferChoice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idItineraryDetail")
    private int idItineraryDetail;
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
    @Size(max = 100)
    @Column(name = "stockType")
    private String stockType;
    @Size(max = 100)
    @Column(name = "type")
    private String type;
    @Size(max = 100)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Float price;

    public UserOfferChoice() {
    }

    public UserOfferChoice(Integer idUserOfferChoice) {
        this.idUserOfferChoice = idUserOfferChoice;
    }

    public UserOfferChoice(Integer idUserOfferChoice, int idItineraryDetail, int qta, int status, float sum) {
        this.idUserOfferChoice = idUserOfferChoice;
        this.idItineraryDetail = idItineraryDetail;
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

    public int getIdItineraryDetail() {
        return idItineraryDetail;
    }

    public void setIdItineraryDetail(int idItineraryDetail) {
        this.idItineraryDetail = idItineraryDetail;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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
        if (!(object instanceof UserOfferChoice)) {
            return false;
        }
        UserOfferChoice other = (UserOfferChoice) object;
        if ((this.idUserOfferChoice == null && other.idUserOfferChoice != null) || (this.idUserOfferChoice != null && !this.idUserOfferChoice.equals(other.idUserOfferChoice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.UserOfferChoice[ idUserOfferChoice=" + idUserOfferChoice + " ]";
    }
    
}
