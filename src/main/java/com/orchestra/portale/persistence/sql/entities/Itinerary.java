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
@Table(name = "itinerary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itinerary.findAll", query = "SELECT i FROM Itinerary i"),
    @NamedQuery(name = "Itinerary.findByIdItinerary", query = "SELECT i FROM Itinerary i WHERE i.idItinerary = :idItinerary"),
    @NamedQuery(name = "Itinerary.findByIdUser", query = "SELECT i FROM Itinerary i WHERE i.idUser = :idUser"),
    @NamedQuery(name = "Itinerary.findByName", query = "SELECT i FROM Itinerary i WHERE i.name = :name"),
    @NamedQuery(name = "Itinerary.findByStaus", query = "SELECT i FROM Itinerary i WHERE i.staus = :staus"),
    @NamedQuery(name = "Itinerary.findByKeyString", query = "SELECT i FROM Itinerary i WHERE i.keyString = :keyString"),
    @NamedQuery(name = "Itinerary.findByStatus", query = "SELECT i FROM Itinerary i WHERE i.status = :status")})
public class Itinerary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idItinerary")
    private Integer idItinerary;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "staus")
    private int staus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "keyString")
    private String keyString;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;

    public Itinerary() {
    }

    public Itinerary(Integer idItinerary) {
        this.idItinerary = idItinerary;
    }

    public Itinerary(Integer idItinerary, int idUser, String name, int staus, String keyString, int status) {
        this.idItinerary = idItinerary;
        this.idUser = idUser;
        this.name = name;
        this.staus = staus;
        this.keyString = keyString;
        this.status = status;
    }

    public Integer getIdItinerary() {
        return idItinerary;
    }

    public void setIdItinerary(Integer idItinerary) {
        this.idItinerary = idItinerary;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStaus() {
        return staus;
    }

    public void setStaus(int staus) {
        this.staus = staus;
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        if (!(object instanceof Itinerary)) {
            return false;
        }
        Itinerary other = (Itinerary) object;
        if ((this.idItinerary == null && other.idItinerary != null) || (this.idItinerary != null && !this.idItinerary.equals(other.idItinerary))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.Itinerary[ idItinerary=" + idItinerary + " ]";
    }
    
}
