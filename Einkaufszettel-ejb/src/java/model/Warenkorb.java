package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

// Authoren:  Katherine Feil, Marco Preuss und Manuel Speck
@Entity
@NamedQueries({
    @NamedQuery(name = "Warenkorb.warenkorbAnzeigen", query = "SELECT w FROM Warenkorb w WHERE w.id = :queryid"),})
public class Warenkorb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private List<Menge> produktanzahlen;

    public Warenkorb() {
    }

    public Warenkorb(List<Menge> produktanzahlen) {
        this.produktanzahlen = produktanzahlen;
    }

    public List<Menge> getProduktanzahlen() {
        return produktanzahlen;
    }

    public void setProduktanzahlen(List<Menge> produktanzahlen) {
        this.produktanzahlen = produktanzahlen;
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
        if (!(object instanceof Warenkorb)) {
            return false;
        }
        Warenkorb other = (Warenkorb) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.Warenkorb[ id=" + id + " ]";
    }

    public double calculateRewePrice() {
        double summe = 0;
        if (produktanzahlen.size() > 0) {
            for (Menge mengenelement : produktanzahlen) {
                summe = summe + (mengenelement.getAnzahl() * mengenelement.getP().getPreisrewe());
            }
        }
        return summe;
    }

    public double calculateAldiPrice() {
        double summe = 0;
        if (produktanzahlen.size() > 0) {
            for (Menge mengenelement : produktanzahlen) {
                summe = summe + (mengenelement.getAnzahl() * mengenelement.getP().getPreisaldi());
            }
        }
        return summe;
    }

    public double calculateLidlPrice() {
        double summe = 0;
        if (produktanzahlen.size() > 0) {
            for (Menge mengenelement : produktanzahlen) {
                summe = summe + (mengenelement.getAnzahl() * mengenelement.getP().getPreislidl());
            }
        }
        return summe;
    }

    public double calculateRealPrice() {
        double summe = 0;
        if (produktanzahlen.size() > 0) {
            for (Menge mengenelement : produktanzahlen) {
                summe = summe + (mengenelement.getAnzahl() * mengenelement.getP().getPreisreal());
            }
        }
        return summe;
    }

    public double calculateEdekaPrice() {
        double summe = 0;
        if (produktanzahlen.size() > 0) {
            for (Menge mengenelement : produktanzahlen) {
                summe = summe + (mengenelement.getAnzahl() * mengenelement.getP().getPreisedeka());
            }
        }
        return summe;
    }
}
