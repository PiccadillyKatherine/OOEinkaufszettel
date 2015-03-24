package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

// Authoren:  Katherine Feil, Marco Preuss und Manuel Speck
@Entity
@Table(name = "PRODUKT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produkt.sucheProdukt", query = "SELECT  p FROM Produkt p WHERE p.bezeichnung LIKE :querykeyword"),
    @NamedQuery(name = "Produkt.sucheId", query = "SELECT  p FROM Produkt p WHERE p.id = :queryid"),
    @NamedQuery(name = "Produkt.sucheKategorie", query = "SELECT  p FROM Produkt p WHERE p.kategorie LIKE :querykeyword")})
public class Produkt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bezeichnung;
    private String kategorie;
    private double Preisrewe;
    private double Preisaldi;
    private double Preislidl;
    private double Preisreal;
    private double Preisedeka;

    public Produkt() {
    }

    public Produkt(String bezeichnung, String kategorie, double Preisrewe, double Preisaldi, double Preislidl, double Preisreal, double Preisedeka) {
        this.bezeichnung = bezeichnung;
        this.kategorie = kategorie;
        this.Preisrewe = Preisrewe;
        this.Preisaldi = Preisaldi;
        this.Preislidl = Preislidl;
        this.Preisreal = Preisreal;
        this.Preisedeka = Preisedeka;
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

    public double getPreisrewe() {
        return Preisrewe;
    }

    public void setPreisrewe(double Preisrewe) {
        this.Preisrewe = Preisrewe;
    }

    public double getPreisaldi() {
        return Preisaldi;
    }

    public void setPreisaldi(double Preisaldi) {
        this.Preisaldi = Preisaldi;
    }

    public double getPreislidl() {
        return Preislidl;
    }

    public void setPreislidl(double Preislidl) {
        this.Preislidl = Preislidl;
    }

    public double getPreisreal() {
        return Preisreal;
    }

    public void setPreisreal(double Preisreal) {
        this.Preisreal = Preisreal;
    }

    public double getPreisedeka() {
        return Preisedeka;
    }

    public void setPreisedeka(double Preisedeka) {
        this.Preisedeka = Preisedeka;
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
        if (!(object instanceof Produkt)) {
            return false;
        }
        Produkt other = (Produkt) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.Produkt[ id=" + id + " ]";
    }
}
