/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import model.Menge;
import model.Person;
import model.Produkt;
import model.Warenkorb;

/**
 *
 @author Manuel
 */
@Local
public interface SessionBeanLocal {
   /*String anbieterBerechnen(List<Warenkorb> productlist); BAUSTELLE MARCO */ 
   

   List<Menge> warenkorbAnzeigen(Person user);
   List<Produkt> sucheProdukt(String keyword);
   Produkt sucheId(long id);
   List<Produkt> sucheKategorie(String keyword);
   Person createPerson(String name, String passwort);
   Person login(String name, String passwort);
   void addItem(int menge, long produktid, Person user);
   void deleteItem(long mengenid, Person user);
   Menge sucheMengeById(long mengenid);
   List<Double> calculatePrices (Person user);
    
}
