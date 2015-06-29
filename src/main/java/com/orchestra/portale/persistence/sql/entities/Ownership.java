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
 * @author antonio
 */
@Entity
@Table(name = "ownership")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ownership.findAll", query = "SELECT o FROM Ownership o"),
    @NamedQuery(name = "Ownership.findById", query = "SELECT o FROM Ownership o WHERE o.id = :id"),
    @NamedQuery(name = "Ownership.findByIdUser", query = "SELECT o FROM Ownership o WHERE o.idUser = :idUser"),
    @NamedQuery(name = "Ownership.findByIdPoi", query = "SELECT o FROM Ownership o WHERE o.idPoi = :idPoi")})
public class Ownership implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_user")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "id_poi")
    private String idPoi;

    public Ownership() {
    }

    public Ownership(Integer id) {
        this.id = id;
    }

    public Ownership(Integer id, int idUser, String idPoi) {
        this.id = id;
        this.idUser = idUser;
        this.idPoi = idPoi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ownership)) {
            return false;
        }
        Ownership other = (Ownership) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.Ownership[ id=" + id + " ]";
    }
    
}
