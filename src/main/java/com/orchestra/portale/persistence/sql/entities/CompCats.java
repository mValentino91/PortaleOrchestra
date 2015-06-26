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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "comp_cats")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompCats.findAll", query = "SELECT c FROM CompCats c"),
    @NamedQuery(name = "CompCats.findById", query = "SELECT c FROM CompCats c WHERE c.id = :id"),
    @NamedQuery(name = "CompCats.findByCategory", query = "SELECT c FROM CompCats c WHERE c.category = :category"),
    @NamedQuery(name = "CompCats.findByComponent", query = "SELECT c FROM CompCats c WHERE c.component = :component"),
    @NamedQuery(name = "CompCats.findByDescription", query = "SELECT c FROM CompCats c WHERE c.description = :description")})
public class CompCats implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2000)
    @Column(name = "category")
    private String category;
    @Size(max = 2000)
    @Column(name = "component")
    private String component;
    @Size(max = 2000)
    @Column(name = "description")
    private String description;

    public CompCats() {
    }

    public CompCats(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof CompCats)) {
            return false;
        }
        CompCats other = (CompCats) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.CompCats[ id=" + id + " ]";
    }
    
}
