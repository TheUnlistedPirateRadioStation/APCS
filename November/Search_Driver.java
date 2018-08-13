	//name:    date:
import java.io.*;      //the File 
import java.util.*;    //the Scanner 
import javax.swing.*;  //the JOptionPane
public class Search_Driver  {
   public static void main(String[] args) throws Exception
   {
      String[] file = input("declaration");
      Selection.sort(file);
      String in = JOptionPane.showInputDialog("Enter a word: ");
      Searches s = new Searches();
      System.out.println("Linear Search found it at location "+s.linear(file, in)+" in "+s.linearCount+" comparisons.");
      System.out.println("Binary Search found it at location "+s.binary(file, in)+" in "+s.binaryCount()+" comparisons.");
   }
   public static String[] input(String filename) throws Exception
   {
      int size = 1325;
      Scanner sc = new Scanner(new File(filename+".txt"));
      String[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();
      return arrayStr;	
   }
}
	/////////////////////////////////////////////////////////
class Searches
{
   public static int linearCount = 0;
   private static int binaryCount = 0;      
   public static int binaryCount()
   {
      return binaryCount;
   }
   public static int linear(Comparable[] array, Comparable target)
   { 
      linearCount = 0;
      int n = -1;
      for(int k=0; k<array.length; k++){
         if(array[k].compareTo(target)==0)
            n = k;
         linearCount++;
      }
      return n;
   }
   public static int binary(Comparable[] array, Comparable target)
   {
            return binaryhelper(array, target, 0, array.length-1);
   }
   private static int binaryhelper(Comparable[] array, Comparable target, int start, int end)
   {
      int mid = ((end-start)/2)+start;
      if(start<0 || end>1325)
      return -1;
      if(mid<0)
         return -1;
      else if(array[mid].compareTo(target)==0){
         binaryCount++;
         return mid;
      }
      else{
      if(array[mid].compareTo(target)<0){
         binaryCount++;
         return binaryhelper(array, target, mid, end);
      }
      else if(array[mid].compareTo(target)>0){
         binaryCount++;
         return binaryhelper(array, target, start, mid);
      }
      else return -1;
     }
   }
}