//Miss Galanos
//version 12.8.2015

import twitter4j.*;       //set the classpath to lib\twitter4j-core-4.0.2.jar
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Twitter_Driver
{
   private static PrintStream consolePrint;
   
   public static void main (String []args) throws TwitterException, IOException
   {
      consolePrint = System.out;
      
      // PART 1
      // set up classpath and properties file
             
      TJTwitter bigBird = new TJTwitter(consolePrint);
         
      //create message to tweet, then call the tweetOut method
      String message = "JAVA Test";
      bigBird.tweetOut(message);
     
      // PART 2
      // Choose a public Twitter user's handle 
         
       Scanner scan = new Scanner(System.in);
         consolePrint.println("Please enter a Twitter handle, do not include the @symbol --> ");
          String twitter_handle = scan.next();
             
          while (!twitter_handle.equals("done"))
         {
             // Print the most popular word they tweet
             bigBird.makeSortedListOfWordsFromTweets(twitter_handle);
             consolePrint.println("The most common word from @" + twitter_handle + " is: " + bigBird.mostPopularWord());
             consolePrint.println();
             consolePrint.print("Please enter a Twitter handle, do not include the @ symbol --> ");
             twitter_handle = scan.next();
          }
         
      // PART 3
      //bigBird.investigate();
         
         
   }//end main         
         
}//end driver        
         
class TJTwitter 
{
   private Twitter twitter;
   private PrintStream consolePrint;
   private List<Status> statuses;
   private List<String> sortedTerms;
   
   public TJTwitter(PrintStream console)
   {
      // Makes an instance of Twitter - this is re-useable and thread safe.
      twitter = TwitterFactory.getSingleton(); //connects to Twitter and performs authorizations
      consolePrint = console;
      statuses = new ArrayList<Status>();
      sortedTerms = new ArrayList<String>();   
   }
   
   /******************  Part 1 *******************/
   public void tweetOut(String message) throws TwitterException, IOException
   {
      twitter.updateStatus(message);
 
   }
   @SuppressWarnings("unchecked")
   /******************  Part 2 *******************/
   public void makeSortedListOfWordsFromTweets(String handle) throws TwitterException, IOException
   {
      statuses.clear();
      sortedTerms.clear();
      PrintStream fileout = new PrintStream(new FileOutputStream("tweets.txt")); // Creates file for dedebugging purposes
      Paging page = new Paging (1,200);
      int p = 1;
      while (p <= 10)
      {
         page.setPage(p);
         statuses.addAll(twitter.getUserTimeline(handle,page)); 
         p++;        
      }
      int numberTweets = statuses.size();
      fileout.println("Number of tweets = " + numberTweets);
      
      fileout = new PrintStream(new FileOutputStream("garbageOutput.txt"));
   
      int count=1;
      for (Status j: statuses)
      {
         fileout.println(count+".  "+j.getText());
         count++;
      }		
         	
     	//Makes a list of words from user timeline
      for (Status status : statuses)
      {			
         String[]array = status.getText().split(" ");
         for (String word : array)
            sortedTerms.add(removePunctuation(word).toLowerCase());
      }	
   					
      // Remove common English words, which are stored in commonWords.txt
      sortedTerms = removeCommonEnglishWords(sortedTerms);
      sortAndRemoveEmpties();
      
   }
   
   // Sort words in alpha order. You should use your old code from the Sort/Search unit.
   // Remove all empty strings ""
   @SuppressWarnings("unchecked")
   private void sortAndRemoveEmpties()
   {
      String empty = "";
      for(int i=0; i<sortedTerms.size(); i++){
         if(sortedTerms.get(i).equals(empty)){
            sortedTerms.remove(i);
            i=0;
            }
      }
      sort(sortedTerms);
   }
   public static void sort(List array)
   {
      int upper = array.size();
      for(int n=0; n<array.size(); n++){
         int max = findMax(array, upper);
         swap(array, max, upper-1);
         upper--;
      }
   }
   @SuppressWarnings("unchecked")
       public static int findMax(List array, int upper)
   {
      String max = array.get(0).toString();
      int pos=0;
      for(int k=0; k<upper; k++){
         if(array.get(k).toString().compareTo(max)>0){
            max = array.get(k).toString();
            pos=k;
         }
      }
      return pos;
   }
   public static void swap(List array, int a, int b)
   {
      Object temp = array.get(a);
      array.set(a, array.get(b));
      array.set(b, temp);
   }

   // Removes common English words from list
   // Remove all words found in commonWords.txt  from the argument list.
   // The count will not be given in commonWords.txt. You must count the number of words in this method.
   // This method should NOT throw an exception. Use try/catch.
   @SuppressWarnings("unchecked")
   private List removeCommonEnglishWords (List<String> list)
   {	
      try{
         ArrayList words = new ArrayList();
         Scanner infile = new Scanner(new File("commonWords.txt"));
         while(infile.hasNext()){
         String curr = infile.next();
            words.add(curr.toUpperCase());
            words.add(curr.toLowerCase());
         }
         boolean removed = list.removeAll(words);
      }
      catch(FileNotFoundException e){
         System.out.println("File not Found");
      }
      return list;
   }
   
   //Remove punctuation - borrowed from prevoius lab
   //Consider if you want to remove the # or @ from your words. They could be interesting to keep (or remove).
   private String removePunctuation( String s )
   {
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
      return s;
   }
   //Should return the most common word from sortedTerms. 
   //Consider case. Should it be case sensitive? The choice is yours.
   @SuppressWarnings("unchecked")
   public String mostPopularWord()
   {
      //for(int j=0; j<sortedTerms.size(); j++){
         //sortedTerms.set(j, sortedTerms.get(j).toLowerCase());
     // }
      int maxcnt = 0;
      int pos = 0;
      for(int m=0; m<sortedTerms.size(); m++){
         if(occur(sortedTerms.get(m))>maxcnt){
            maxcnt = occur(sortedTerms.get(m));
            pos = m;
         }
      }
      return sortedTerms.get(pos);
   }
   private int occur(String w){
      int cnt = 0;
      for(int n=0; n<sortedTerms.size(); n++){
         if(sortedTerms.get(n).equalsIgnoreCase(w))
            cnt++;
      }
      return cnt;
   }

   /******************  Part 3 *******************/
   public void investigate ()
   {
      Query query = new Query("Super Bowl");
      query.setCount(100);
      query.setGeoCode(new GeoLocation(40.7903, 73.9597), 2500, Query.MILES);
      query.setSince("2015-12-1");
      try {
         QueryResult result = twitter.search(query);
         System.out.println("Count : " + result.getTweets().size()) ;
         for (Status tweet : result.getTweets()) {
            System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText());  
         }
      } 
      catch (TwitterException e) {
         e.printStackTrace();
      } 
      System.out.println(); 

   }
   // A sample query to determine how many people in Manhattan tweet about the Super Bowl
   public void sampleInvestigate ()
   {
      Query query = new Query("Miami Dolphins");
      query.setCount(100);
      query.setGeoCode(new GeoLocation(38.8372839,-77.1082443), 5, Query.MILES);
      query.setSince("2015-12-1");
      try {
         QueryResult result = twitter.search(query);
         System.out.println("Count : " + result.getTweets().size()) ;
         for (Status tweet : result.getTweets()) {
            System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText());  
         }
      } 
      catch (TwitterException e) {
         e.printStackTrace();
      } 
      System.out.println(); 
   }  
   
}  

// Consider adding a sorter class here.