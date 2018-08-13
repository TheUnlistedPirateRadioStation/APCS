    /* 
    Calls methods in the classes Merge and QuickSort. 
	 Students are to write the Merge and QuickSort classes.
    */
import java.util.*;
import java.io.*;
public class Merge_Quick
{
   public static void main(String[] args) throws Exception
   {
      int n = (int)(Math.random()*100);
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random();	
      print(array);
      MergeSort.sort(array);
      //QuickSort.sort(array);
      print(array);
      if(check(array))
         System.out.println("In order!");
      else
         System.out.println("oops!");
   }
   public static void print(double[] array)
   {
      for(double theNumber : array )     //doing something to each
         System.out.println(theNumber);
      System.out.println();
   }
   public static boolean check(double[] a)
   {
      return true;
   }
}
/////////////////////////////////////////////////
// from Lambert & Osborne, p. 482 - 485
class MergeSort
{
   private static final int CUTOFF = 10;  // for small lists, recursion isn't worth it
   public static void sort(double[] array)
   { 
      double[] copyBuffer = new double[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);
   }
   
   private static void mergeSortHelper(double[] array, double[] copyBuffer,
                                                             int low, int high)
   {  
      if ( high - low  < CUTOFF )              //switch to selection sort  when
         Selection.sort(array, low, high);     //each list gets small enough 
      else  
         if (low < high)
         {
            int middle = (low + high) / 2;
            mergeSortHelper(array, copyBuffer, low, middle);
            mergeSortHelper(array, copyBuffer, middle + 1, high);
            merge(array, copyBuffer, low, middle, high);
         }
   }
   	
   public static void merge(double[] array, double[] copyBuffer,
                                      int low, int middle, int high)
      // array				array that is being sorted
      // copyBuffer		temp space needed during the merge process
      // low				beginning of first sorted subarray
      // middle			end of first sorted subarray
      // middle + 1		beginning of second sorted subarray
      // high				end of second sorted subarray
   {
      int lo = low;
      int mid= middle + 1;
      int hi = high;
      for(int i = low; i <= hi; i++){
         if(lo > middle)
            copyBuffer[i] = array[mid++];
         else if(mid > hi)
           copyBuffer[i] = array[lo++];
         else if(array[lo] < array[mid])
            copyBuffer[i] = array[lo++];
         else
            copyBuffer[i] = array[mid++];
      }
      for(int i = low; i <= high; i++)
         array[i] = copyBuffer[i];    
   }	

}

////////////////////////////////////////////////////
class QuickSort
{
   public static void sort(double[] array)
   {
      if(array.length == 0)
         return;
      else{
         quickSort(array, 0, array.length-1);
      }
   }
   private static void quickSort(double[] array, int first, int last)
   {
      int splitPt;            
      if (first < last)   				
      // General case      
      {            
         splitPt = split (array, first, last);         
         quickSort (array, first, splitPt - 1);	
      // sort left side         
         quickSort (array, splitPt + 1, last);	
      // sort right side      
      }     
   }
   private static int split(double[] info, int first, int last)
   {
      // choose pivot and rearrange data so that 
      // info[first] ...info[splitPt - 1] <= pivot and 
      // info[splitPt + 1] ... info[last] >= pivot
      int splitPt = first;       
      double pivot = info[first];
      while (first <= last)   
      {     
         if (info[first] <= pivot)       
         //if on correct side,         
            first++;                      
         //   update		
         // increment first until elemetn > pivot    
         else if (info [last] >= pivot)  
         //if on correct side,	        
            last--;                        
         //   update		
         // decrement last until elemtn < pivot   
         else                               
         //if on the wrong side,       
         {          
            swap (info, first, last);      
         //    then swap them,       
            first++;                       
         //    update both        
            last--;     
         }   
      }
      swap (info, last, splitPt); 	
         // swap pivot with element at splitPt
      splitPt = last;			
         // set splitPt to place where the halves meet
      return splitPt;
   }
   
   private static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
}









































