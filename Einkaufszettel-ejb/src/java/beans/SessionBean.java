/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import model.Person;
import model.Produkt;

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
    public List<Produkt> sucheProdukt(String keyword) {
        TypedQuery<Produkt> p = em.createNamedQuery("Produkt.sucheProdukt", Produkt.class);
        if (keyword != null) {
            p.setParameter("querykeyword", keyword);
            return p.getResultList();
        }
        else {
            return new ArrayList<Produkt>();
        }
    }
    
       
    
    
    @Override
    public List<Produkt> sucheAnbieter(String keyword) {
        
      TypedQuery<Produkt> p = em.createNamedQuery("Produkt.sucheAnbieter", Produkt.class);
        if (keyword != null) {
            p.setParameter("querykeyword", keyword);
            return p.getResultList();
        }
        else {
            return new ArrayList<Produkt>();
        }
        
        
    }
    
    
    
    
    @Override
    public List<Produkt> sucheKategorie(String keyword) {
        TypedQuery<Produkt> p = em.createNamedQuery("Produkt.sucheKategorie", Produkt.class);
        if (keyword != null) {
            p.setParameter("querykeyword", keyword);
            return p.getResultList();
        }
        else {
            return new ArrayList<Produkt>();
        }
    }
    
    @Override
    public Person createPerson(String name, String login, String passwort)
    {
        em.setFlushMode(FlushModeType.AUTO);
        Person person = new Person(name, login, passwort);
        em.persist(person);
        person = em.merge(person);
        em.flush();
        return person;
    }
    
}
