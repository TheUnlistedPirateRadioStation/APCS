//  Name:      date:
//  This program takes a text file, creates an index (by line numbers)
//  for all the words in the file and writes the index
//  into the output file.  The program prompts the user for the file names.

import java.io.*;
import java.util.*;

public class IndexMakerMap
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String infileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(infileName));
      String outfileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outfileName));
      indexDocument(inputFile, outputFile);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }

   public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
   {
      DocumentIndex index = new DocumentIndex(); 	
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      //outputFile.println(index);
      //print to the outputFile
      for(String entry : index.keySet()){
         String s = ""+index.get(entry);
         outputFile.println(entry+" "+s.substring(1, s.length()-1));
      
      }
   }
}

class DocumentIndex extends TreeMap<String, ArrayList<Integer>>
{
   public DocumentIndex()
   {
      super();
   }
   public void addWord(String word, int lineNum)
   {
      ArrayList temp = new ArrayList();
      if(containsKey(word)){
         get(word).add(lineNum);
      }
      else{
         put(word, temp);
         get(word).add(lineNum);
      }
   }
   
 /** extracts all the words from str, skipping punctuation and whitespace 
 and for each word calls addWord(word, num).  A good way to skip punctuation 
 and whitespace is to use String's split method, e.g., split("[., \"!?]") */
   public void addAllWords(String str, int lineNum) 
   {
      str.trim();
      String[] words = str.split("[., \"!?]");
      for(int k=0; k<words.length; k++){
         if(words[k].length() >= 1)
            addWord(words[k].toUpperCase(), lineNum);
      }
   }
}


