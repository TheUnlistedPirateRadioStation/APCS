    /* M.L. Billington, 10/02/2006.
    Uses the helper classes Selection and Insertion. 
	 Students are to write the Selection and Insertion classes.
    */
import java.util.*;
import java.io.*;
public class Sorts
{
   public static void main(String[] args) throws Exception
   {
        //Part 1, for doubles
      int n = (int)(Math.random()*100);
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random();	
      print(array);
      System.out.println("*************  *************");
      //Selection.sort(array);
      Insertion.sort(array);
      print(array);
         
      	//Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      String[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
      print(arrayStr);
      System.out.println("*************  *************");
      //Selection.sort(arrayStr);
      Insertion.sort(arrayStr);
      print(arrayStr);
   }
   public static void print(double[] a)
   {
         // for(int k = 0; k < a.length; k++)
            // System.out.println(a[k]);
      for(double d : a)
         System.out.println(d);
      System.out.println();
   }
   public static void print(Object[] papaya)
   {
      for(Object item : papaya)     //for-each
         System.out.println( item );
   }
}
   //*******************************************************************
  //Name:              Date:
  //The Selection class will have methods sort(), findMax() and swap().
  //Three versions of each method will have to be written, to work 
  //for doubles, Strings, and Comparables.
  
class Selection
{
   public static void sort(double[] array, int low, int high)
   { 
      int upper = array.length;
      for(int n=0; n<array.length; n++){
         int max = findMax(array, upper);
         swap(array, max, upper-1);
         upper--;
      }
   }
   private static int findMax(double[] array, int n)
   {
      double max = array[0];
      int pos=0;
      for(int k=0; k<n; k++){
         if(array[k]>max){
            max = array[k];
            pos=k;
         }
      }
      return pos;
   }
   private static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
   	/***************************************************
   	  for Strings
   	  ***********************************************/
   public static void sort(String[] array)
   {
      int upper = array.length;
      for(int n=0; n<array.length; n++){
         int max = findMax(array, upper);
         swap(array, max, upper-1);
         upper--;
      }
   }
   public static int findMax(String[] array, int upper)
   {
      String max = array[0];
      int pos=0;
      for(int k=0; k<upper; k++){
         if(array[k].compareTo(max)>0){
            max = array[k];
            pos=k;
         }
      }
      return pos;
   }
   public static void swap(String[] array, int a, int b)
   {
      String temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   
   }
   	/***************************************************
   	 for Comparables,
   	      Swap() is for Objects.
   	      make sure that print() is for Objects, too.
   	  ***********************************************/
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
       public static void sort(Comparable[] array)
   {
      int upper = array.length;
      for(int n=0; n<array.length; n++){
         int max = findMax(array, upper);
         swap(array, max, upper-1);
         upper--;
      }
   }
   @SuppressWarnings("unchecked")
       public static int findMax(Comparable[] array, int upper)
   {
      Comparable max = array[0];
      int pos=0;
      for(int k=0; k<upper; k++){
         if(array[k].compareTo(max)>0){
            max = array[k];
            pos=k;
         }
      }
      return pos;
   }
   public static void swap(Object[] array, int a, int b)
   {
      Object temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
}   

//**********************************************************
  //Name:              Date:
  //The Insertion class 
  //write enough methods (sort() and shift()) to handle doubles and Comparables.
class Insertion
{
   public static void sort(double[] array){
      int n = 0;
      while(n<array.length)
      {
         shift(array, n, array[n]);
         n++;
      }      
   }
   public static void shift(double[] array, int index, double value){
      while(index>0 && array[index-1]>value){
            array[index] = array[index-1];
            index--;
      }
      array[index] = value;
   }
   public static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
    public static void sort(Comparable[] array){
      int n = 0;
      while(n<array.length)
      {
         shift(array, n, array[n]);
         n++;
      }      
   }
   public static void shift(Comparable[] array, int index, Comparable value){
      while(index>0 && (array[index-1].compareTo(value)>0)){
            array[index] = array[index-1];
            index--;
      }
      array[index] = value;
   }
   public static void swap(Comparable[] array, int a, int b)
   {
      Comparable temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }


}
