/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
 * @author Manuel
 */
@Entity
@Table(name = "PRODUKT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produkt.findAll", query = "SELECT p FROM Produkt p"),
    @NamedQuery(name = "Produkt.findById", query = "SELECT p FROM Produkt p WHERE p.id = :id"),
    
    
    @NamedQuery(name = "Produkt.sucheAnbieter", query = "select p FROM Produkt p WHERE p.anbieter LIKE :querykeyword"),
    @NamedQuery(name = "Produkt.sucheProdukt", query = "select p FROM Produkt p WHERE p.bezeichnung LIKE :querykeyword"),
    @NamedQuery(name = "Produkt.sucheKategorie", query = "select p FROM Produkt p WHERE p.kategorie LIKE :querykeyword"),

    @NamedQuery(name = "Produkt.findByBezeichnung", query = "SELECT p FROM Produkt p WHERE p.bezeichnung = :bezeichnung"),
    @NamedQuery(name = "Produkt.findByKategorie", query = "SELECT p FROM Produkt p WHERE p.kategorie = :kategorie"),
    @NamedQuery(name = "Produkt.findByPreis", query = "SELECT p FROM Produkt p WHERE p.preis = :preis"),
    @NamedQuery(name = "Produkt.findByAnbieter", query = "SELECT p FROM Produkt p WHERE p.anbieter = :anbieter")})
public class Produkt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "BEZEICHNUNG")
    private String bezeichnung;
    @Size(max = 100)
    @Column(name = "KATEGORIE")
    private String kategorie;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PREIS")
    private Double preis;
    @Size(max = 100)
    @Column(name = "ANBIETER")
    private String anbieter;

    public Produkt() {
    }

    public Produkt(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public Double getPreis() {
        return preis;
    }

    public void setPreis(Double preis) {
        this.preis = preis;
    }

    public String getAnbieter() {
        return anbieter;
    }

    public void setAnbieter(String anbieter) {
        this.anbieter = anbieter;
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
        if (!(object instanceof Produkt)) {
            return false;
        }
        Produkt other = (Produkt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Produkt[ id=" + id + " ]";
    }
    
}
