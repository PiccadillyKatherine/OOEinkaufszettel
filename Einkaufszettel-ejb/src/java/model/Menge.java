/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Marco
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Menge.sucheMengeById", query = "SELECT m FROM Menge m WHERE m.id = :querymengenid"),
})

public class Menge implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int anzahl;
    private Produkt p;

    public Menge() {
    }
    
    

    public Menge(int anzahl, Produkt p) {
        this.anzahl = anzahl;
        this.p = p;
    }
    
    

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public Produkt getP() {
        return p;
    }

    public void setP(Produkt p) {
        this.p = p;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Menge)) {
            return false;
        }
        Menge other = (Menge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Menge[ id=" + id + " ]";
    }
    
}
