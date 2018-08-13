import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PigLatinTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test
   public void myTests(){
   Assert.assertEquals("pig was piglatinezed", "igpay", pig("pig"));
   Assert.assertEquals("Hello. was piglatinezed", "Ellohay.", pig("Hello."));
   Assert.assertEquals("Arnav! was piglatinezed", "Arnavway!", pig("Arnav!"));
   Assert.assertEquals("\"java\" was piglatinezed", "\"avajay\"", pig("\"java\""));
   Assert.assertEquals("Question was piglatinezed", "Estionquay", pig("Question"));
   Assert.assertEquals("Yolo was piglatinezed", "Oloyay", pig("Yolo"));
   Assert.assertEquals("jyden was piglatinezed", "ydenjay", pig("jyden"));
   } 
   public void defaultTest() {
      Assert.assertEquals("Default test added by jGRASP. Delete "
            + "this test once you have added your own.", 0, 1);
   }
   public static String pig(String s)
   {
      String part1 = "";
      String part2 = "";
      String vow = "aeiouyAEIOUY";
      String ystring = "Yy";
            
      boolean quotes = false; //special case of quotes since two are always required 
      String punctuation = ".,?!:;\'()";
      String punct = "";
      
      for(int k = 0; k<s.length(); k++)
      {
         if(punctuation.indexOf(s.charAt(k))!=-1)
         {
            punct += s.charAt(k);//stores any punctuation in the string for later use
         }
      }
      s = s.replace(punct, "");//removes punctuation
      boolean noyfirst = true;//checks if first letter is a y or Y
      if(ystring.indexOf(s.charAt(0)) != -1)
         noyfirst = false;
      if((vow.indexOf(s.charAt(0)) != -1) && noyfirst)
         return s + "way" + punct;//if the first letter is a vowel, not including y, returns string with "way
   
      boolean upper = false;//keeps track of upper case letter
      if(Character.isUpperCase(s.charAt(0)))
      {
         upper = true;
         s = s.toLowerCase();  
      }
      for(int n=0; n<s.length(); n++)
      {
         if(s.substring(n,n+1).equals("\""))
         {
            quotes = true;
            s = s.substring(n+1,s.length() -1);
            if(upper && (vow.indexOf(s.charAt(0)) != -1) && noyfirst)
            {
               return "\"" + s + "way" + punct + "\""; //takes care of quotes
            
            }
         
            if(Character.isUpperCase(s.charAt(0)))
            {
               upper = true;
               s = s.toLowerCase();  
            }
            if((vow.indexOf(s.charAt(0)) != -1) && noyfirst)
               return "\"" + s + "way" + punct + "\"";
         }
         if(Character.isUpperCase(s.charAt(1)))
         {
            if(vow.indexOf(s.charAt(n)) == -1)
            {
               part1 += s.substring(n, n+1);
            }
            else
            { 
               part2 = s.substring(n, s.length());
               if(quotes == true)
               {
                  (part2.substring(1,2)).toUpperCase();
               }
               n= s.length();
            }
         }
      
         if(s.indexOf("u") != -1 && (s.substring(s.indexOf("u") - 1, s.indexOf("u")).equals("q") || s.substring(s.indexOf("u") - 1, s.indexOf("u" )).equals("Q")))
         {
            if(s.indexOf("Q") == 0 || s.indexOf("q") == 0)
            {
               part1 += s.substring(0,2);
               s = s.substring(2);
            }
            else
            {
               part1 += s.substring(0,(s.indexOf("u") + 1));
               s =s.substring(s.indexOf("u")+ 1);
            }
         }
         
         if(noyfirst == false)
         {
            part1 += s.charAt(0);
            s = s.substring(1);
         }
         if(vow.indexOf(s.charAt(n)) == -1)
         {
          
            part1 += s.substring(n, n+1);
         }
         else
         { 
            part2 = s.substring(n, s.length());
            n= s.length();
         }
      }
      if(part2 == "")
         return "INVALID";
      if(upper && quotes)
      {
         return "\"" + Character.toUpperCase(part2.charAt(0)) + part2.substring(1) + part1 + "ay" + punct + '\"';
      }   
      if(upper)
      {
         return Character.toUpperCase(part2.charAt(0)) + part2.substring(1) + part1 + "ay" + punct;
      }   
      if(quotes)
      {
         return '\"' +part2 + part1 + "ay"+ punct + '\"';
      }
   
      return part2 + part1 + "ay"+ punct;
   }



}
