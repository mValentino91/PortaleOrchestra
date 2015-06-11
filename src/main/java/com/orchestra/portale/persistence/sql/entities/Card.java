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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andzaccaro
 */
@Entity
@Table(name = "card")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Card.findAll", query = "SELECT c FROM Card c"),
    @NamedQuery(name = "Card.findByIdCard", query = "SELECT c FROM Card c WHERE c.idCard = :idCard"),
    @NamedQuery(name = "Card.findByIdUser", query = "SELECT c FROM Card c WHERE c.idUser = :idUser"),
    @NamedQuery(name = "Card.findByCreationDate", query = "SELECT c FROM Card c WHERE c.creationDate = :creationDate"),
    @NamedQuery(name = "Card.findByActivationDate", query = "SELECT c FROM Card c WHERE c.activationDate = :activationDate"),
    @NamedQuery(name = "Card.findByStatus", query = "SELECT c FROM Card c WHERE c.status = :status")})
public class Card implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCard")
    private Integer idCard;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUser")
    private int idUser;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "activation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;

    public Card() {
    }

    public Card(Integer idCard) {
        this.idCard = idCard;
    }

    public Card(Integer idCard, int idUser, int status) {
        this.idCard = idCard;
        this.idUser = idUser;
        this.status = status;
    }

    public Integer getIdCard() {
        return idCard;
    }

    public void setIdCard(Integer idCard) {
        this.idCard = idCard;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
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
        hash += (idCard != null ? idCard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Card)) {
            return false;
        }
        Card other = (Card) object;
        if ((this.idCard == null && other.idCard != null) || (this.idCard != null && !this.idCard.equals(other.idCard))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orchestra.portale.persistence.sql.entities.Card[ idCard=" + idCard + " ]";
    }
    
}
