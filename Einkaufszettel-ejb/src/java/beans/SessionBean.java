package beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import model.Menge;
import model.Person;
import model.Produkt;
import model.Warenkorb;

// Authoren:  Katherine Feil, Marco Preuss und Manuel Speck
@Stateless
public class SessionBean implements SessionBeanLocal {

    @Resource(name = "einkaufszettel")
    private DataSource einkaufszettel;
    @PersistenceContext(unitName = "Einkaufszettel-ejbPU")
    EntityManager em;

    @Override
    public List<Menge> warenkorbAnzeigen(Person user) {
        TypedQuery<Warenkorb> ergebnis = em.createNamedQuery("Warenkorb.warenkorbAnzeigen", Warenkorb.class);
        ergebnis.setParameter("queryid", user.getMeinwarenkorb().getId());
        if (ergebnis.getResultList().isEmpty()) {
            return new ArrayList<>();
        } else {
           
            return ergebnis.getResultList().get(0).getProduktanzahlen();
        }
    }

    @Override
    public void deleteItem(long mengenid, Person user) {
        List<Menge> wkinhalt = user.getMeinwarenkorb().getProduktanzahlen();
        for (Menge item : wkinhalt) {
            System.out.println(item);
            if (item.getId() == mengenid) {
                wkinhalt.remove(item);
                break;
            }
        }
        user.getMeinwarenkorb().setProduktanzahlen(wkinhalt);
        em.merge(user.meinwarenkorb);
    }

    @Override
    public Menge sucheMengeById(long mengenid) {
        TypedQuery<Menge> m = em.createNamedQuery("Menge.sucheMengeById", Menge.class);
        m.setParameter("querymengenid", mengenid);
        return m.getResultList().get(0);
    }

    @Override
    public List<Produkt> sucheProdukt(String keyword) {
        TypedQuery<Produkt> p = em.createNamedQuery("Produkt.sucheProdukt", Produkt.class);
        if (keyword != null) {
            p.setParameter("querykeyword", keyword + "%");
            return p.getResultList();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Produkt sucheId(long produktid) {
        TypedQuery<Produkt> p = em.createNamedQuery("Produkt.sucheId", Produkt.class);
        p.setParameter("queryid", produktid);
        return p.getResultList().get(0);
    }

    @Override
    public void addItem(int anzahl, long produktid, Person user) {
        List<Menge> produktanzahlen = user.getMeinwarenkorb().getProduktanzahlen();

        //  LÖSCHEN VORM ADDEN 
        for (Menge aktuellemenge : produktanzahlen) {
            if (aktuellemenge.getP().getId() == produktid) {
                produktanzahlen.remove(aktuellemenge);
                break;
            }
        }

        Produkt p = sucheId(produktid);
        Menge m = new Menge(anzahl, p);
        em.persist(m);
        produktanzahlen.add(m);
        user.getMeinwarenkorb().setProduktanzahlen(produktanzahlen);
        em.merge(user.meinwarenkorb);
    }

    @Override
    public List<Produkt> sucheKategorie(String keyword) {
        TypedQuery<Produkt> p = em.createNamedQuery("Produkt.sucheKategorie", Produkt.class);
        if (keyword != null) {
            p.setParameter("querykeyword", keyword + "%");
            return p.getResultList();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Person createPerson(String name, String passwort) {
        TypedQuery<Person> p = em.createNamedQuery("Person.findByName", Person.class);
        p.setParameter("name", name);
        if (p.getResultList().isEmpty()) {
            em.setFlushMode(FlushModeType.AUTO);
            ArrayList<Menge> produktanzahlen = new ArrayList();
            Warenkorb meinwarenkorb = new Warenkorb(produktanzahlen);
            Person person = new Person(name, passwort, meinwarenkorb);
            em.persist(person);
            person = em.merge(person);
            em.flush();
            return person;
        } else {
            return new Person("notloggedin");
        }
    }

    @Override
    public Person login(String name, String passwort) {
        TypedQuery<Person> p = em.createNamedQuery("Person.sucheName", Person.class);
        p.setParameter("name", name);
        p.setParameter("passwort", passwort);
        if (p.getResultList().isEmpty()) {
            //"ausgeloggter User"  hier redundant
            Warenkorb keinwarenkorb = new Warenkorb();
            return new Person("notloggedin", "lol", keinwarenkorb);
        } else {
            return p.getSingleResult();
        }
    }

    @Override
    public List<Double> calculatePrices(Person user) {
        List<Double> preise = new ArrayList();
        preise.add(user.getMeinwarenkorb().calculateRewePrice());
        preise.add(user.getMeinwarenkorb().calculateAldiPrice());
        preise.add(user.getMeinwarenkorb().calculateLidlPrice());
        preise.add(user.getMeinwarenkorb().calculateRealPrice());
        preise.add(user.getMeinwarenkorb().calculateEdekaPrice());
        return preise;
    }
    
    @Override
    public Boolean registerCheck(String name, String passwort1, String passwort2){
        return (!"".equals(passwort1) && passwort1.equals(passwort2) && !"".equals(name));
    }
}
