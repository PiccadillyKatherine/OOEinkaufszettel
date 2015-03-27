package beans;

import java.util.List;
import javax.ejb.Local;
import model.Menge;
import model.Person;
import model.Produkt;

// Authoren:  Katherine Feil, Marco Preuss und Manuel Speck
@Local
public interface SessionBeanLocal {

    List<Menge> warenkorbAnzeigen(Person user);

    List<Produkt> sucheProdukt(String keyword);

    Produkt sucheId(long id);

    List<Produkt> sucheKategorie(String keyword);

    Person createPerson(String name, String passwort);

    Person login(String name, String passwort);

    void addItem(int menge, long produktid, Person user);

    void deleteItem(long mengenid, Person user);

    Menge sucheMengeById(long mengenid);

    List<Double> calculatePrices(Person user);

    Boolean registerCheck(String name, String passwort1, String passwort2);
}
