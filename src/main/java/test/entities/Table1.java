/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.entities;

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
 * @author mekko
 */
@Entity
@Table(name = "table_1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Table1.findAll", query = "SELECT t FROM Table1 t"),
    @NamedQuery(name = "Table1.findByIdtable1", query = "SELECT t FROM Table1 t WHERE t.id = :id"),
    @NamedQuery(name = "Table1.findByCol1", query = "SELECT t FROM Table1 t WHERE t.col1 = :col1"),
    @NamedQuery(name = "Table1.findByCol2", query = "SELECT t FROM Table1 t WHERE t.col2 = :col2")})
public class Table1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "col1")
    private String col1;
    @Size(max = 45)
    @Column(name = "col2")
    private String col2;

    public Table1() {
    }

    public Table1(Integer idtable1) {
        this.id = idtable1;
    }

    public Table1(Integer idtable1, String col1) {
        this.id = idtable1;
        this.col1 = col1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idtable1) {
        this.id = idtable1;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
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
        if (!(object instanceof Table1)) {
            return false;
        }
        Table1 other = (Table1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "test.entities.Table1[ idtable1=" + id + " ]";
    }
    
}
