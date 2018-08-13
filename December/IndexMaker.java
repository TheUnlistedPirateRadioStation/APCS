//  Name:      date:
//  This program takes a text file, creates an index (by line numbers)
//  for all the words in the file and writes the index
//  into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.io.*;

public class IndexMaker
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String fileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(fileName));
      System.out.print("\nEnter output file name: ");
      fileName = keyboard.nextLine().trim();
      PrintWriter outputFile = new PrintWriter(new FileWriter(fileName));
      DocumentIndex index = new DocumentIndex(); 	
      String line = null;
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      for(IndexEntry entry : index)
         outputFile.println(entry);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
}
class DocumentIndex extends ArrayList<IndexEntry>
{

   public DocumentIndex()
   {
      super();
   }
   public DocumentIndex(int size)
   {
      super(size);
   }
    //constructors
   
   
   /** calls foundOrInserted, which returns an index.  At that position,  
   updates that IndexEntry's list of line numbers with num. */
   public void addWord(String word, int num)
   {
      int ind = foundOrInserted(word);
      get(ind).add(num);
   }
      
    /** extracts all the words from str, skipping punctuation and whitespace 
    and for each word calls addWord(word, num).  A good way to skip punctuation 
    and whitespace is to use String's split method, e.g., split("[., \"!?]") */
   public void addAllWords(String str, int num) 
   {
      String[] words = str.split("[., \"!?]");
      if(words.length > 0){
         for(int k = 0; k < words.length; k++)
         {
         if(words[k].length()>0)
            addWord(words[k], num);
         }
      }
   }    
    /** traverses this DocumentIndex and compares word to the words in the 
    IndexEntry objects in this list, looking for the correct position of 
    word. If an IndexEntry with word is not already in that position, the 
    method creates and inserts a new IndexEntry at that position. The 
    method returns the position of either the found or the inserted 
    IndexEntry.*/
   private int foundOrInserted(String word)
   {
      int pos = 0;
      IndexEntry entry = new IndexEntry(word);
      for(IndexEntry ind : this) {
         if(ind.compareTo(entry) == 0) 
            return pos;
         if(ind.compareTo(entry) > 0) {
            add(pos, entry);
            return pos;
         }
         pos++;
      }
      add(pos, entry);
      return pos;
   }
}

   
class IndexEntry implements Comparable<IndexEntry>
{
     //fields
   private String word = "";
   private ArrayList<Integer> index;
     //constructors
   public IndexEntry(String s){
      index = new ArrayList<Integer>();
      word = s.toUpperCase();
   }
   
     /**  appends num to numsList, but only if it is not already in that list.    
          */
   public void add(int num)
   {
      if(!index.contains(num))
         index.add(num);
   }
      
   	/** this is a standard accessor method  */
   public String getWord()
   {
      return word;
   }
      
     /**  returns a string representation of this Index Entry.  */
   public String toString()
   {
      String res = word + " ";
      int len = index.size()-1;
      for(int k = 0; k < len; k++){
         res+=(index.get(k) + ", ");
      }
      res+=index.get(len);
      return res;
   }
   public int compareTo(IndexEntry s)
   {
      return word.compareTo(s.getWord());
   }
}

