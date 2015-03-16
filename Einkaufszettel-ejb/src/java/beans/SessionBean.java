
package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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

/**
 *
 * @author Marco
 */
@Stateless

public class SessionBean implements SessionBeanLocal {
    @Resource(name = "einkaufszettel")
    private DataSource einkaufszettel;
    @PersistenceContext (unitName = "Einkaufszettel-ejbPU")
    EntityManager em;
    
@Override
public List<Menge> warenkorbAnzeigen(Person user) {
        TypedQuery<Warenkorb> ergebnis = em.createNamedQuery("Warenkorb.warenkorbAnzeigen", Warenkorb.class);
        ergebnis.setParameter("queryid", user.getMeinwarenkorb().getId());
        if (ergebnis.getResultList().isEmpty()) {
             return new ArrayList<Menge>();
        }else {
            ergebnis.getResultList().get(0).calculateRewePrice();
            return ergebnis.getResultList().get(0).getProduktanzahlen();
        }
          


}
    
    @Override
    public void deleteItem(long mengenid, Person user) {
       // Produkt-Objekt besorgen, da JSP nur Bezeichnung übermitteln kann
       
       
       List<Menge> wkinhalt = user.getMeinwarenkorb().getProduktanzahlen();
       Menge m = sucheMengeById(mengenid);
       wkinhalt.remove(m);
       user.getMeinwarenkorb().setProduktanzahlen(wkinhalt);
       em.merge(user.meinwarenkorb);
       //produktanzahlen.remove(0);
       
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
            p.setParameter("querykeyword", keyword+"%");
            return p.getResultList();
        }
        else {
            return new ArrayList<Produkt>();
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
       // Mengenarray auslesen
       List <Menge> produktanzahlen = user.getMeinwarenkorb().getProduktanzahlen();
       
       
       //  LÖSCHEN VORM ADDEN 
        int index = 0;
        int deleteindex = 999;
        for (Menge aktuellemenge : produktanzahlen){
            if (aktuellemenge.getP().getId() == produktid){
                deleteindex = index;
            }
            index++;
        }
        if (deleteindex!=999){
            produktanzahlen.remove(deleteindex);
        }
        
       //Produkt durch id auslesen
       Produkt p  = sucheId(produktid);
       
       //Mengenobjekt erzeugen
       Menge m = new Menge (anzahl, p);
       
       //Zu Mengenarray hinzufügen
       produktanzahlen.add(m);
       user.getMeinwarenkorb().setProduktanzahlen(produktanzahlen);
       em.merge(user.meinwarenkorb);
    }
    
    
    
    @Override
    public List<Produkt> sucheKategorie(String keyword) {
        TypedQuery<Produkt> p = em.createNamedQuery("Produkt.sucheKategorie", Produkt.class);
        if (keyword != null) {
            p.setParameter("querykeyword", keyword+"%");
            return p.getResultList();
        }
        else {
            return new ArrayList<Produkt>();
        }
    }
    
    @Override
    public Person createPerson(String name, String passwort)
    {
        
     TypedQuery<Person> p = em.createNamedQuery("Person.findByName", Person.class);
     p.setParameter("name", name);
     if(p.getResultList().isEmpty()){
        
         
        em.setFlushMode(FlushModeType.AUTO);
        ArrayList<Menge> produktanzahlen = new ArrayList();
        Warenkorb meinwarenkorb = new Warenkorb(produktanzahlen);
        Person person = new Person(name, passwort, meinwarenkorb);
        em.persist(person);
        person = em.merge(person);
        em.flush();
        return person;
    } else {
         //"ausgeloggter User"  hier redundant
         Warenkorb keinwarenkorb = new Warenkorb();
         return new Person("notloggedin", "lol", keinwarenkorb);
     }
    }
    
    @Override 
    public Person login(String name,String passwort){
        TypedQuery<Person> p = em.createNamedQuery("Person.sucheName", Person.class); 
        p.setParameter("name", name);
        p.setParameter("passwort", passwort);
         
        if (p.getResultList().isEmpty())
        {
            //"ausgeloggter User"  hier redundant
         Warenkorb keinwarenkorb = new Warenkorb();
         return new Person("notloggedin", "lol", keinwarenkorb);
         
        }else{
            
            
            return p.getSingleResult();
            
        }
        
    }
    
    @Override
    public List<Double> calculatePrices (Person user) {
        List<Double> preise = new ArrayList();
        preise.add(user.getMeinwarenkorb().calculateRewePrice());
        preise.add(user.getMeinwarenkorb().calculateAldiPrice());
        preise.add(user.getMeinwarenkorb().calculateLidlPrice());
        preise.add(user.getMeinwarenkorb().calculateRealPrice());
        preise.add(user.getMeinwarenkorb().calculateEdekaPrice());
        return preise;
    }
}
    
