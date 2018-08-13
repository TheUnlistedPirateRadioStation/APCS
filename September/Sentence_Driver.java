import java.util.StringTokenizer; 
public class Sentence_Driver
{
   public static void main(String[] args)
   {
      System.out.println("PALINDROME TESTER");
      Sentence s = new Sentence( "\"Hello there!\" she said." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
         
      s = new Sentence( "A Santa lived as a devil at NASA." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
        
      
      s = new Sentence( "Flo, gin is a sin! I golf." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
         
      
      s = new Sentence( "Eva, can I stab bats in a cave?" );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      s = new Sentence( "Madam, I'm Adam." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
   
         
      // Lots more test cases.  Test every line of code.  Test
      // the extremes, test the boundaries.  How many test cases do you need?
      
   }
}
class Sentence
{
   private String mySentence;
   private int myNumWords;
      
      //Constructor.  Creates sentence from String str.
      //						Finds the number of words in sentence.
      //Precondition:  Words in str separated by exactly one blank.
   public Sentence(String str)
   { 
      mySentence = str;
      String temp = mySentence;
      StringTokenizer counter = new StringTokenizer(str);
      myNumWords = counter.countTokens();
   }
      
   public int getNumWords()
   {  
      return myNumWords;  
   }
      
   public String getSentence()
   {
      return mySentence; 
   }
      
      //Returns true if mySentence is a palindrome, false otherwise.
   public boolean isPalindrome()
   {
      String s = this.getSentence();
      String temp1 = lowerCase(s);
      String temp2 = removeBlanks(temp1);
      String temp3 = removePunctuation(temp2);
      if(temp3.length() == 1 || temp3.length() == 0)
         return true;
      else
         return isPalindrome(temp3,0,temp3.length()-1);             
   }
      //Precondition: s has no blanks, no punctuation, and is in lower case.
      //Returns true if s is a palindrome, false otherwise.
   private static boolean isPalindrome( String s, int start, int end )
   {
   
      if((end-start)<2)
         return true;
      if(s.charAt(start) != s.charAt(end))
         return false;
      else
         return isPalindrome(s, start+1, end-1);
         
   }
      //Returns copy of String s with all blanks removed.
      //Postcondition:  Returned string contains just one word.
   private static String removeBlanks( String s )
   {    
      String noblank = s.replace(" ", "");
      return noblank; 
   }
      
      //Returns copy of String s with all letters in lowercase.
      //Postcondition:  Number of words in returned string equals
      //						number of words in s.
   private static String lowerCase( String s )
   {  
      return s.toLowerCase();
   }
      
      //Returns copy of String s with all punctuation removed.
      //Postcondition:  Number of words in returned string equals
      //						number of words in s.
   private static String removePunctuation( String s )
   { 
      String nopunct = s;
   
      nopunct = nopunct.toLowerCase();
      nopunct = nopunct.replace(",", "");
      nopunct = nopunct.replace(".", "");
      nopunct = nopunct.replace(";", "");
      nopunct = nopunct.replace("!", "");
      nopunct = nopunct.replace("?", "");
      nopunct = nopunct.replace("\'", "");
      nopunct = nopunct.replace("\"", "");
   
      return nopunct;
   }
}
