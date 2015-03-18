/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import model.Person;
import model.Produkt;

/**
 *
 * @author Manuel
 */
@Local
public interface SessionBeanLocal {
    
   List<Produkt> sucheAnbieter(String keyword);
   List<Produkt> sucheProdukt(String keyword);
   List<Produkt> sucheKategorie(String keyword);
   Person createPerson(String name, String login, String passwort);
   
    
}
