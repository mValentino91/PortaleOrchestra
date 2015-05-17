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
@Table(name = "cart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c"),
    @NamedQuery(name = "Cart.findByIdCart", query = "SELECT c FROM Cart c WHERE c.idCart = :idCart"),
    @NamedQuery(name = "Cart.findByIdPoi", query = "SELECT c FROM Cart c WHERE c.idPoi = :idPoi"),
    @NamedQuery(name = "Cart.findByIdUser", query = "SELECT c FROM Cart c WHERE c.idUser = :idUser"),
    @NamedQuery(name = "Cart.findByIdCartItinerary", query = "SELECT c FROM Cart c WHERE c.idCartItinerary = :idCartItinerary")})
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCart")
    private Integer idCart;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "idPoi")
    private String idPoi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser")
    private int idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCartItinerary")
    private int idCartItinerary;

    public Cart() {
    }

    public Cart(Integer idCart) {
        this.idCart = idCart;
    }

    public Cart(Integer idCart, String idPoi, int idUser, int idCartItinerary) {
        this.idCart = idCart;
        this.idPoi = idPoi;
        this.idUser = idUser;
        this.idCartItinerary = idCartItinerary;
    }

    public Integer getIdCart() {
        return idCart;
    }

    public void setIdCart(Integer idCart) {
        this.idCart = idCart;
    }

    public String getIdPoi() {
        return idPoi;
    }

    public void setIdPoi(String idPoi) {
        this.idPoi = idPoi;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCartItinerary() {
        return idCartItinerary;
    }

    public void setIdCartItinerary(int idCartItinerary) {
        this.idCartItinerary = idCartItinerary;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCart != null ? idCart.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
        if ((this.idCart == null && other.idCart != null) || (this.idCart != null && !this.idCart.equals(other.idCart))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.Cart[ idCart=" + idCart + " ]";
    }
    
}
