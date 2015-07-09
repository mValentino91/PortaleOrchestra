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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andzaccaro
 */
@Entity
@Table(name = "userItinerary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserItinerary.findAll", query = "SELECT u FROM UserItinerary u"),
    @NamedQuery(name = "UserItinerary.findByIdUserItinerary", query = "SELECT u FROM UserItinerary u WHERE u.idUserItinerary = :idUserItinerary"),
    @NamedQuery(name = "UserItinerary.findByIdUser", query = "SELECT u FROM UserItinerary u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "UserItinerary.findByIdItinerary", query = "SELECT u FROM UserItinerary u WHERE u.idItinerary = :idItinerary")})
public class UserItinerary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUserItinerary")
    private Integer idUserItinerary;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idItinerary")
    private int idItinerary;

    public UserItinerary() {
    }

    public UserItinerary(Integer idUserItinerary) {
        this.idUserItinerary = idUserItinerary;
    }

    public UserItinerary(Integer idUserItinerary, int idUser, int idItinerary) {
        this.idUserItinerary = idUserItinerary;
        this.idUser = idUser;
        this.idItinerary = idItinerary;
    }

    public Integer getIdUserItinerary() {
        return idUserItinerary;
    }

    public void setIdUserItinerary(Integer idUserItinerary) {
        this.idUserItinerary = idUserItinerary;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdItinerary() {
        return idItinerary;
    }

    public void setIdItinerary(int idItinerary) {
        this.idItinerary = idItinerary;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserItinerary != null ? idUserItinerary.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserItinerary)) {
            return false;
        }
        UserItinerary other = (UserItinerary) object;
        if ((this.idUserItinerary == null && other.idUserItinerary != null) || (this.idUserItinerary != null && !this.idUserItinerary.equals(other.idUserItinerary))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.UserItinerary[ idUserItinerary=" + idUserItinerary + " ]";
    }
    
}
