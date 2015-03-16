package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import model.Person;

/**
 *
 * @author Katherine
 */
public class PersonTest {
    
    public PersonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Person.
     * Leerer Konstruktor -> leerer Name
     * Test erfolgreich
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Person instance = new Person();
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
     
    }
    /**
     * Test of getName method, of class Person.
     * Nutzen der bereits gegetesteten Methode setName.
     * gesetzter name: Tanja.
     * Erfolgreich.
     */
    @Test
    public void testGetName2() {
        System.out.println("getName");
        Person instance = new Person();
        instance.setName("Tanja");
        String expResult = "Tanja";
        String result = instance.getName();
        assertEquals(expResult, result);
     
    }

    /**
     * Test of setName method, of class Person.
     * Test erfolgreich.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Ina";
        Person instance = new Person();
        instance.setName(name);
        String expResult = "Ina";
        String result = instance.getName();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPasswort method, of class Person.
     */
    @Test
    public void testGetPasswort() {
        System.out.println("getPasswort");
        Person instance = new Person();
        String expResult = "";
        String result = instance.getPasswort();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPasswort method, of class Person.
     */
    @Test
    public void testSetPasswort() {
        System.out.println("setPasswort");
        String passwort = "";
        Person instance = new Person();
        instance.setPasswort(passwort);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMeinwarenkorb method, of class Person.
     */
    @Test
    public void testGetMeinwarenkorb() {
        System.out.println("getMeinwarenkorb");
        Person instance = new Person();
        Warenkorb expResult = null;
        Warenkorb result = instance.getMeinwarenkorb();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMeinwarenkorb method, of class Person.
     */
    @Test
    public void testSetMeinwarenkorb() {
        System.out.println("setMeinwarenkorb");
        Warenkorb meinwarenkorb = null;
        Person instance = new Person();
        instance.setMeinwarenkorb(meinwarenkorb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Person.
     * Leere Person. ID = Null. Erwarteter Wert Null.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Person instance = new Person();
        Long expResult = null;             
        Long result = instance.getId();
        assertEquals(expResult, result);
    }
    /**
     * Test of getId method, of class Person.
     * Setzen der ID mit SetId.
     * Erfolgreich.
     */
    @Test
    public void testGetId2() {
        System.out.println("getId");
        Person instance = new Person();
        instance.setId(20L);
        Long expResult =20L;             
        Long result = instance.getId();
        assertEquals(expResult, result);
       // System.out.println(instance.getId());
    }

    /**
     * Test of setId method, of class Person.
     * Eingabe Null, ergebnis null.
     * Test Erfolgreich.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Person instance = new Person();
        instance.setId(id);
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setId method, of class Person.
     */
    @Test
    public void testSetId2() {
        System.out.println("setId");
        Long id = 10L;
        Person instance = new Person();
        instance.setId(id);
        Long expResult = 10L;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Person.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Person instance = new Person();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Person.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Person instance = new Person();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Person.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Person instance = new Person();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
