
package beans;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.ejb.Local;
import javax.naming.NamingException;
import model.Menge;
import model.Person;
import model.Produkt;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Katherine
 */
public class SessionBeanTest1 {
    EJBContainer container;
    SessionBeanLocal sessionBean;
    Person tester;
    
   
    public SessionBeanTest1() {
    }
       
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws NamingException { 
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SessionBeanLocal instance = (SessionBeanLocal)container.getContext().lookup("java:global/classes/SessionBean");
        Person tester = instance.createPerson("Test", "Test");
        
    }
    
    @After
    public void tearDown() {
        container.close();
    }

    
    @Test
    public void testWarenkorbAnzeigen() throws Exception {
        System.out.println("warenkorbAnzeigen");
        Person user = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SessionBeanLocal instance = (SessionBeanLocal)container.getContext().lookup("java:global/classes/SessionBean");
        List<Menge> expResult = null;
        List<Menge> result = instance.warenkorbAnzeigen(user);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    @Test
    public void testDeleteItem() throws Exception {
        System.out.println("deleteItem");
        long mengenid = 0L;
        Person user = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SessionBeanLocal instance = (SessionBeanLocal)container.getContext().lookup("java:global/classes/SessionBean");
        instance.deleteItem(mengenid, user);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testSucheMengeById() throws Exception {
        System.out.println("sucheMengeById");
        long mengenid = 0L;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SessionBeanLocal instance = (SessionBeanLocal)container.getContext().lookup("java:global/classes/SessionBean");
        Menge expResult = null;
        Menge result = instance.sucheMengeById(mengenid);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testSucheProdukt() throws Exception {
        System.out.println("sucheProdukt");
        String keyword = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SessionBeanLocal instance = (SessionBeanLocal)container.getContext().lookup("java:global/classes/SessionBean");
        List<Produkt> expResult = null;
        List<Produkt> result = instance.sucheProdukt(keyword);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testSucheId() throws Exception {
        System.out.println("sucheId");
        long produktid = 0L;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SessionBeanLocal instance = (SessionBeanLocal)container.getContext().lookup("java:global/classes/SessionBean");
        Produkt expResult = null;
        Produkt result = instance.sucheId(produktid);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

  
    @Test
    public void testAddItem() throws Exception {
        System.out.println("addItem");
        int anzahl = 0;
        long produktid = 0L;
        Person user = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SessionBeanLocal instance = (SessionBeanLocal)container.getContext().lookup("java:global/classes/SessionBean");
        instance.addItem(anzahl, produktid, user);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testSucheKategorie() throws Exception {
        System.out.println("sucheKategorie");
        String keyword = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SessionBeanLocal instance = (SessionBeanLocal)container.getContext().lookup("java:global/classes/SessionBean");
        List<Produkt> expResult = null;
        List<Produkt> result = instance.sucheKategorie(keyword);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testCreatePerson() throws Exception {
        System.out.println("createPerson");
        String name = "Test";
        String passwort = "Test";
        try (EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer()) {
            SessionBeanLocal instance = (SessionBeanLocal)container.getContext().lookup("java:global/classes/SessionBean");
            Person expResult = null;
            Person result = instance.createPerson(name, passwort);
            assertEquals(expResult, result);
        }
        
    }

    /**
     * @throws java.lang.Exception     
     **/
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        String name = "";
        String passwort = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SessionBeanLocal instance = (SessionBeanLocal)container.getContext().lookup("java:global/classes/SessionBean");
        Person expResult = null;
        Person result = instance.login(name, passwort);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
   
    @Test
    public void testCalculatePrices() throws Exception {
        System.out.println("calculatePrices");
        Person user = null;
        try (EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer()) {
            SessionBean instance = (SessionBean)container.getContext().lookup("java:global/classes/SessionBean");
            List<Double> expResult = null;
            List<Double> result = instance.calculatePrices(user);
            assertEquals(expResult, result);
        }
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    
    @Test
    public void testRegisterCheck() throws Exception {
        System.out.println("registerCheck");
        String name = "";
        String passwort1 = "";
        String passwort2 = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        SessionBeanLocal instance = (SessionBeanLocal)container.getContext().lookup("java:global/classes/SessionBean");
        Boolean expResult = null;
        Boolean result = instance.registerCheck(name, passwort1, passwort2);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
