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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "top10")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Top10.findAll", query = "SELECT t FROM Top10 t"),
    @NamedQuery(name = "Top10.findByIdtop", query = "SELECT t FROM Top10 t WHERE t.idtop = :idtop"),
    @NamedQuery(name = "Top10.findByIdpoi", query = "SELECT t FROM Top10 t WHERE t.idpoi = :idpoi"),
   // @NamedQuery(name = "Top10.findByTipo", query = "SELECT t FROM Top10 t WHERE t.tipo = :tipo")
    @NamedQuery(name = "Top10.findByDescr", query = "SELECT t FROM Top10 t WHERE t.descr = :descr")})
public class Top10 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtop")
    private Integer idtop;
    @Size(max = 45)
    @Column(name = "idpoi")
    private String idpoi;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 45)
    @Column(name = "descr")
    private String descr;

    public Top10() {
    }

    public Top10(Integer idtop) {
        this.idtop = idtop;
    }

    public Integer getIdtop() {
        return idtop;
    }

    public void setIdtop(Integer idtop) {
        this.idtop = idtop;
    }

    public String getIdpoi() {
        return idpoi;
    }

    public void setIdpoi(String idpoi) {
        this.idpoi = idpoi;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtop != null ? idtop.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Top10)) {
            return false;
        }
        Top10 other = (Top10) object;
        if ((this.idtop == null && other.idtop != null) || (this.idtop != null && !this.idtop.equals(other.idtop))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.Top10[ idtop=" + idtop + " ]";
    }
    
}
