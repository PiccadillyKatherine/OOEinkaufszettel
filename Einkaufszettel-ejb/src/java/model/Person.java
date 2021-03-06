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
@Table(name = "PERSON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name"),
    @NamedQuery(name = "Person.sucheName", query = "SELECT p FROM Person p WHERE p.name = :name AND p.passwort = :passwort"),})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String passwort;
    public Warenkorb meinwarenkorb;

    public Person() {
    }

    public Person(String name, String passwort, Warenkorb meinwarenkorb) {
        this.name = name;
        this.passwort = passwort;
        this.meinwarenkorb = meinwarenkorb;
    }

    // Konstruktor für "ausgeloggt"-User benötigt  Name
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public Warenkorb getMeinwarenkorb() {
        return meinwarenkorb;
    }

    public void setMeinwarenkorb(Warenkorb meinwarenkorb) {
        this.meinwarenkorb = meinwarenkorb;
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
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.Person[ id=" + id + " ]";
    }
}
