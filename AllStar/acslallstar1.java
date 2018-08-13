import java.util.*;
public class acslallstar1{
   public static void main(String[] args){
      Scanner in = new Scanner(System.in);
      for(int k=0; k<10; k++){
         System.out.println("Insert two numbers seperated by spaces:");
         int fir= in.nextInt();
         int sec = in.nextInt();
         ArrayList<Integer> fina = big(Math.max(fir, sec));
         ArrayList<Integer> sice = small(fina,Math.min(fir,sec));
         String str = sice+"";
         System.out.println(str.substring(1, str.length()-1));
      
      }
   }
   private static ArrayList<Integer> big(int input){
      ArrayList<Integer> sums = new ArrayList<Integer>();
      int temp = input;
      while(temp != 0){
         sums.add((int)(Math.pow(2, maxtwo(temp, 0))));
         temp -= Math.pow(2, maxtwo(temp, 0));
      }
      return sums;
   }
   private static ArrayList<Integer> small(ArrayList<Integer> one,int input){
      ArrayList<Integer> two = new ArrayList<Integer>();
      for(int i=0;i<one.size();i++)
         two.add((int)(one.get(i)*input));
      return two;   
   }
   private static int maxtwo(int input, int max){
      if(Math.pow(2, max) > input)
         return max-1;
      else if(Math.pow(2, max) == input)
         return max;
      else
         return maxtwo(input, max+1);
   }
}