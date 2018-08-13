//your name, date
import java.util.*;
public class LeftRight
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("\nHow many digits? ");
      String s = sc.next();
      int n = Integer.parseInt(s);
      //recursive("", n);
      //oddDigits("", n);
   	superprime(n);
   }
   public static void recursive(String s, int n)
   {
      if(n==0)
         System.out.println(s);
      else{
         recursive(s+"L", n-1);
         recursive(s+"R", n-1);
      }
   }
   public static void oddDigits(String s, int n)
   {
      if(n==0)
         System.out.println(s);
      else{
         oddDigits(s+"1", n-1);
         oddDigits(s+"3", n-1);  
         oddDigits(s+"5", n-1);
         oddDigits(s+"7", n-1);
         oddDigits(s+"9", n-1);    
      }
   
   }
   
	//the SuperPrime methods 
   public static void superprime(int n)
   {
      recur(2, n); //try leading 2, etc.
      recur(3, n); //all the primes < 10
      recur(5, n);
      recur(7, n);
   }
   private static void recur(int k, int n)
   {
   String num = ""+k;
   int numdigits = num.length();
   if(n == numdigits)
      {
         if(isPrime(k))
            System.out.println(k);
      } 
      else
      {
         for(int x = 1; x<10; x++)
         {
            if(isPrime(k*10+x))
            {
               recur(k*10+x, n);
            }
         }
      }
   }
   private static boolean isPrime(int n)
   {
   boolean prime = true;
   for(int k=2; k<n; k++)
   if(n%k == 0)
   prime = false;
      return prime;
   }   
}
