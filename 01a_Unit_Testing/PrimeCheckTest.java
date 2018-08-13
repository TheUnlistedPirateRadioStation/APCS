import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PrimeCheckTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test 
  /* public void prime13(){
   Assert.assertEquals("13 is prime.", true, PrimeCheck.isPrime(13));
   }*/
   public void prime1(){
   Assert.assertEquals("1 is prime.", true, PrimeCheck.isPrime(1));
   }
   public void prime2(){
   Assert.assertEquals("2 is prime.", true, PrimeCheck.isPrime(2));
   }
   public void prime7(){
   Assert.assertEquals("7 is prime.", true, PrimeCheck.isPrime(7));
   }
   public void defaultTest() {
      Assert.assertEquals("Default test added by jGRASP. Delete "
            + "this test once you have added your own.", 0, 1);
   }
}
