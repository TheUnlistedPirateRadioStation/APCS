   //name:     date:
   
import java.util.*;
public class Postfix
{
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postExp = new ArrayList<String>();
      postExp.add("345*+");
      postExp.add("34*5+");
      postExp.add("45+53*-");
      postExp.add("34+56+*");
      postExp.add("345+*2-5/");
      postExp.add("812*+93/-");
      
      
      for( String pf : postExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   public static int eval(String postfix)
   {
      char[] exp = postfix.toCharArray();
      Stack<Integer> nums = new Stack<Integer>();
      for(char c: exp){
         if(isOperator(c)){
            int b = (nums.pop());
            int a = (nums.pop());
            int ans = eval(a, b, c);
            nums.push(ans);
         }
         else{
            int i = Integer.parseInt(""+c);
            nums.push(i);
         }
      }
      return nums.pop();
   }
   public static int eval(int a, int b, char ch)
   {
      int ans;
      switch(ch){
         case '+':
            ans = a+b;
            break;
         case '-':
            ans = a-b;
            break;
         case '*':
            ans = a*b;
            break;
         case '/':
            ans = a/b;
            break;
         default: 
            ans =0;
      }
      return ans;
   }
   public static boolean isOperator(char ch)
   {
      if(ch=='+' || ch=='-' || ch=='*' || ch=='/')
         return true;
      else
         return false;
   }
}