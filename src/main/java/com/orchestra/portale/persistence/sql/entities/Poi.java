/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orchestra.portale.persistence.sql.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mekko
 */
@Entity
@Table(name = "poi", catalog = "Orchestra_Schema", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Poi.findAll", query = "SELECT p FROM Poi p"),
    @NamedQuery(name = "Poi.findById", query = "SELECT p FROM Poi p WHERE p.id = :id"),
    @NamedQuery(name = "Poi.findByName", query = "SELECT p FROM Poi p WHERE p.name = :name"),
    @NamedQuery(name = "Poi.findByLatitude", query = "SELECT p FROM Poi p WHERE p.latitude = :latitude"),
    @NamedQuery(name = "Poi.findByLongitude", query = "SELECT p FROM Poi p WHERE p.longitude = :longitude"),
    @NamedQuery(name = "Poi.findByIdmongo", query = "SELECT p FROM Poi p WHERE p.idmongo = :idmongo"),
    @NamedQuery(name = "Poi.findByShortDescription", query = "SELECT p FROM Poi p WHERE p.shortDescription = :shortDescription")})
public class Poi implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "address")
    private String address;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id")
    private Integer id;
    @Basic
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "name")
    private String name;
    @Basic
    @NotNull
    @Column(name = "latitude")
    private double latitude;
    @Basic
    @NotNull
    @Column(name = "longitude")
    private double longitude;
    @Basic
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "idmongo")
    private String idmongo;
    @Basic
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "shortDescription")
    private String shortDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpoi")
    private List<CompPoiCategory> compPoiCategoryList;

    public Poi() {
    }

    public Poi(Integer id) {
        this.id = id;
    }

    public Poi(Integer id, String name, double latitude, double longitude, String idmongo, String shortDescription) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.idmongo = idmongo;
        this.shortDescription = shortDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getIdmongo() {
        return idmongo;
    }

    public void setIdmongo(String idmongo) {
        this.idmongo = idmongo;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @XmlTransient
    public List<CompPoiCategory> getCompPoiCategoryList() {
        return compPoiCategoryList;
    }

    public void setCompPoiCategoryList(List<CompPoiCategory> compPoiCategoryList) {
        this.compPoiCategoryList = compPoiCategoryList;
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
        if (!(object instanceof Poi)) {
            return false;
        }
        Poi other = (Poi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.Poi[ id=" + id + " ]";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
