  //name:   date: 
import java.util.*;
public class Infix
{
   public static void main(String[] args)
   {
      System.out.println("Infix  -->  Postfix  -->  Evaluate");
      ArrayList<String> infixExp = new ArrayList<String>();
      infixExp.add("3+4*5");
      infixExp.add("3*4+5");
      infixExp.add("(4+5)-5*3"); 
      infixExp.add("(3+4)*(5+6)");
      infixExp.add("(3*(4+5)-2)/5");
      infixExp.add("8+1*2-9/3"); 
   
      for( String s : infixExp )
      {
         String pf = infixToPostfix(s);
         System.out.println(s + "       " + pf + "       " + Postfix.eval(pf));
      }
   }
   public static String infixToPostfix(String infix)
   {
      String postfix = "";
      char[] exp = infix.toCharArray();
      Stack<Character> post = new Stack<Character>();
      for(int k=0; k<exp.length; k++){
         if(!isOperator(exp[k]) && exp[k] != '(' && exp[k] != ')')
            postfix+=exp[k];
         else if(exp[k] == '(')
            post.push('(');
         else if(exp[k] == ')'){
            while(post.peek() != '(')
               postfix += post.pop();
            post.pop();
         }
         else if(isOperator(exp[k])){
            while(!(post.isEmpty()) && post.peek() != '(' && !isLower(post.peek(), exp[k]))
               postfix+=post.pop();
            post.push(exp[k]);
         }
             
      }
      while(!post.isEmpty()){
         char curr = post.pop();
         if(curr != '(' && curr != ')')
            postfix+=curr;
      }
      return postfix;
   }
	//returns true if c1 has strictly lower precedence than c2
   public static boolean isLower(char c1, char c2)
   {
      boolean low = false;
      if((c1 == '+' || c1 == '-')&&(c2=='*' || c2=='/' || c2=='(' || c2==')'))
         low = true;
      else if((c1 == '*' || c1 == '/')&&(c2=='(' || c2==')'))
         low = true;
      return low;
   }
   public static boolean isOperator(char ch)
   {
      if(ch=='+' || ch=='-' || ch=='*' || ch=='/')
         return true;
      else
         return false;
   }

}
	
	/*
 Infix  -->  Postfix  -->  Evaluate
 3+4*5       345*+       23
 3*4+5       34*5+       17
 (4+5)-5*3       45+53*-       -6
 (3+4)*(5+6)       34+56+*       77
 (3*(4+5)-2)/5       345+*2-5/       5
 8+1*2-9/3       812*+93/-       7
	*/
