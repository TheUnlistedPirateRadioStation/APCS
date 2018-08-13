//Author:  
//Date:       
import java.util.Scanner;
public class Fibonacci
{
   public static void main(String[] args)
   {
      long start, end, fib;
      int[] fibNumber = {1, 5, 10, 20, 30, 40, 41, 42};
      System.out.println("\tFibonacci\tBy Iteration\tTime\tby Recursion\t Time");
      for(int n = fibNumber[0]; n <= fibNumber[fibNumber.length - 1]; n++)
      { 
         start = System.nanoTime();
         fib = fibIterate(n);
         end = System.nanoTime();
         System.out.print("\t\t" + n + "\t\t" + fib + "\t" + (end-start)/1000.);
         start = System.nanoTime();   	
         fib = fibRecur(n);      
         end = System.nanoTime();
         System.out.println("\t" + fib + "\t\t" + (end-start)/1000.);
      }
   }
	/***********************
	Calculates the nth Fibonacci number by iteration
	***********************/
   public static long fibIterate(int n)
   {
      long fib1 = 0;
      long fib2 = 1;
      long fib3 = 0;
      for(int k=0; k<n; k++){
         fib3 = fib1 + fib2;
         fib1 = fib2;
         fib2 = fib3;
      }
      return fib3;
       
   }
	/***********************
	Calculates the nth Fibonacci number by recursion
	***********************/
   public static long fibRecur(int n)
   {
      if(n<=1)
         return 1;
      else
         return fibRecur(n-1) + fibRecur(n-2);
      
   }
}