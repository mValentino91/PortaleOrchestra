/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrea
 */
@Entity
@Table(name = "dealerOffer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DealerOffer.findAll", query = "SELECT d FROM DealerOffer d"),
    @NamedQuery(name = "DealerOffer.findByIdOffer", query = "SELECT d FROM DealerOffer d WHERE d.idOffer = :idOffer"),
    @NamedQuery(name = "DealerOffer.findByIdPoi", query = "SELECT d FROM DealerOffer d WHERE d.idPoi = :idPoi"),
    @NamedQuery(name = "DealerOffer.findByDesc", query = "SELECT d FROM DealerOffer d WHERE d.desc = :desc"),
    @NamedQuery(name = "DealerOffer.findByFullPrice", query = "SELECT d FROM DealerOffer d WHERE d.fullPrice = :fullPrice"),
    @NamedQuery(name = "DealerOffer.findByDiscountedPrice", query = "SELECT d FROM DealerOffer d WHERE d.discountedPrice = :discountedPrice"),
    @NamedQuery(name = "DealerOffer.findByRateDiscount", query = "SELECT d FROM DealerOffer d WHERE d.rateDiscount = :rateDiscount"),
    @NamedQuery(name = "DealerOffer.findByDateStart", query = "SELECT d FROM DealerOffer d WHERE d.dateStart = :dateStart"),
    @NamedQuery(name = "DealerOffer.findByDateEnd", query = "SELECT d FROM DealerOffer d WHERE d.dateEnd = :dateEnd")})
public class DealerOffer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOffer")
    private Integer idOffer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "idPoi")
    private String idPoi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "desc")
    private String desc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fullPrice")
    private float fullPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discountedPrice")
    private float discountedPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rateDiscount")
    private int rateDiscount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;

    public DealerOffer() {
    }

    public DealerOffer(Integer idOffer) {
        this.idOffer = idOffer;
    }

    public DealerOffer(Integer idOffer, String idPoi, String desc, float fullPrice, float discountedPrice, int rateDiscount, Date dateStart, Date dateEnd) {
        this.idOffer = idOffer;
        this.idPoi = idPoi;
        this.desc = desc;
        this.fullPrice = fullPrice;
        this.discountedPrice = discountedPrice;
        this.rateDiscount = rateDiscount;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Integer getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(Integer idOffer) {
        this.idOffer = idOffer;
    }

    public String getIdPoi() {
        return idPoi;
    }

    public void setIdPoi(String idPoi) {
        this.idPoi = idPoi;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(float fullPrice) {
        this.fullPrice = fullPrice;
    }

    public float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(float discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public int getRateDiscount() {
        return rateDiscount;
    }

    public void setRateDiscount(int rateDiscount) {
        this.rateDiscount = rateDiscount;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOffer != null ? idOffer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DealerOffer)) {
            return false;
        }
        DealerOffer other = (DealerOffer) object;
        if ((this.idOffer == null && other.idOffer != null) || (this.idOffer != null && !this.idOffer.equals(other.idOffer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.DealerOffer[ idOffer=" + idOffer + " ]";
    }
    
}
