
package beans;

import static java.lang.System.out;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.ejb.Local;
import static javax.ejb.embeddable.EJBContainer.createEJBContainer;
import javax.naming.NamingException;
import model.Menge;
import model.Person;
import model.Produkt;
import model.Warenkorb;
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
public class SessionBeanTest {
    EJBContainer container;
    SessionBeanLocal instance;
    
    Person tester;
    ArrayList<Menge> produktanzahlen;
    Warenkorb meinwarenkorb;
    
   
    public SessionBeanTest() throws NamingException {
        container = createEJBContainer();
        instance = (SessionBeanLocal) container.getContext().lookup("java:global/classes/SessionBean");
        this.tester = instance.createPerson("Test", "Test");
        
        this.meinwarenkorb = new Warenkorb(produktanzahlen);
        instance.addItem( 2, 1, tester);
        out.println("hier");
        instance.addItem( 3, 2, tester);
        
        instance.warenkorbAnzeigen(tester);
        out.println(meinwarenkorb.calculateAldiPrice());
        instance.calculatePrices(tester);
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
        container.close();
    }


   
    @Test
    public void testCalculatePrices() throws Exception {
        System.out.println("calculatePrices");
        List<Double> expResult = null;
        List<Double> result = this.instance.calculatePrices(tester);
        assertEquals(expResult, result);
       
    }

   
}
