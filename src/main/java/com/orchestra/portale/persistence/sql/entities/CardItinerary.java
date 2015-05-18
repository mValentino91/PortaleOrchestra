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
@Table(name = "cardItinerary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CardItinerary.findAll", query = "SELECT c FROM CardItinerary c"),
    @NamedQuery(name = "CardItinerary.findByIdItinerary", query = "SELECT c FROM CardItinerary c WHERE c.idItinerary = :idItinerary"),
    @NamedQuery(name = "CardItinerary.findByIdCard", query = "SELECT c FROM CardItinerary c WHERE c.idCard = :idCard"),
    @NamedQuery(name = "CardItinerary.findByIdCartItinerary", query = "SELECT c FROM CardItinerary c WHERE c.idCartItinerary = :idCartItinerary"),
    @NamedQuery(name = "CardItinerary.findByKeyString", query = "SELECT c FROM CardItinerary c WHERE c.keyString = :keyString")})
public class CardItinerary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idItinerary")
    private Integer idItinerary;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCard")
    private int idCard;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCartItinerary")
    private int idCartItinerary;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "keyString")
    private String keyString;

    public CardItinerary() {
    }

    public CardItinerary(Integer idItinerary) {
        this.idItinerary = idItinerary;
    }

    public CardItinerary(Integer idItinerary, int idCard, int idCartItinerary, String keyString) {
        this.idItinerary = idItinerary;
        this.idCard = idCard;
        this.idCartItinerary = idCartItinerary;
        this.keyString = keyString;
    }

    public Integer getIdItinerary() {
        return idItinerary;
    }

    public void setIdItinerary(Integer idItinerary) {
        this.idItinerary = idItinerary;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public int getIdCartItinerary() {
        return idCartItinerary;
    }

    public void setIdCartItinerary(int idCartItinerary) {
        this.idCartItinerary = idCartItinerary;
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItinerary != null ? idItinerary.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CardItinerary)) {
            return false;
        }
        CardItinerary other = (CardItinerary) object;
        if ((this.idItinerary == null && other.idItinerary != null) || (this.idItinerary != null && !this.idItinerary.equals(other.idItinerary))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.CardItinerary[ idItinerary=" + idItinerary + " ]";
    }
    
}
