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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mekko
 */
@Entity
@Table(name = "comp_category_component", catalog = "Orchestra_Schema", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompCategoryComponent.findAll", query = "SELECT c FROM CompCategoryComponent c"),
    @NamedQuery(name = "CompCategoryComponent.findById", query = "SELECT c FROM CompCategoryComponent c WHERE c.id = :id"),
    @NamedQuery(name = "CompCategoryComponent.findByRequired", query = "SELECT c FROM CompCategoryComponent c WHERE c.required = :required")})
public class CompCategoryComponent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "id")
    private Integer id;
    @Basic
    @NotNull
    @Column(name = "required")
    private boolean required;
    @JoinColumn(name = "idcomponent", referencedColumnName = "id")
    @ManyToOne
    private Component idcomponent;
    @JoinColumn(name = "idcategory", referencedColumnName = "id")
    @ManyToOne
    private Category idcategory;

    public CompCategoryComponent() {
    }

    public CompCategoryComponent(Integer id) {
        this.id = id;
    }

    public CompCategoryComponent(Integer id, boolean required) {
        this.id = id;
        this.required = required;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Component getIdcomponent() {
        return idcomponent;
    }

    public void setIdcomponent(Component idcomponent) {
        this.idcomponent = idcomponent;
    }

    public Category getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Category idcategory) {
        this.idcategory = idcategory;
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
        if (!(object instanceof CompCategoryComponent)) {
            return false;
        }
        CompCategoryComponent other = (CompCategoryComponent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.CompCategoryComponent[ id=" + id + " ]";
    }
    
}
