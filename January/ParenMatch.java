// name:     date:

import java.util.*;
public class ParenMatch
{
   public static final String left  = "([{<";
   public static final String right = ")]}>";
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      parenExp.add("5+7");
      parenExp.add("(5+7)");
      parenExp.add(")5+7(");
      parenExp.add("((5+7)*3)");
      parenExp.add("<{5+7}*3>");
      parenExp.add("[(5+7)*]3");
      parenExp.add("(5+7)*3");
      parenExp.add("5+(7*3)");
      parenExp.add("((5+7)*3");
      parenExp.add("[(5+7)*3])");
      parenExp.add("([(5+7)*3]");
                  
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
   public static boolean checkParen(String exp)
   {
      boolean check = false;
      int rightcnt = 0;
      int leftcnt = 0;
      int prcnt = 0;
      int plcnt = 0;
      int brcnt = 0;
      int blcnt = 0;
      int crcnt = 0;
      int clcnt = 0;
      int vrcnt = 0;
      int vlcnt = 0;
      char[] expr = exp.toCharArray();
      Stack<Character> par = new Stack<Character>();
      for(int k=0; k<expr.length; k++){
         if(!Character.isDigit(expr[k]) && !isOperator(expr[k]))
            par.push(expr[k]);
      }
      if(par.isEmpty())
      check = true;
      else{
      while(par.peek()!= '(' && par.peek()!= '{' && par.peek()!= '[' && par.peek()!= '<'){
      if(par.peek()== ')')
      prcnt++;
    else if(par.peek()== ']')
      brcnt++;
    else if(par.peek()== '}')
      crcnt++;
    else if(par.peek()== '>')
      vrcnt++;
         par.pop();
         rightcnt++;
      }
      while(!par.isEmpty()){
       if(par.peek()== '(')
      plcnt++;
    else if(par.peek()== '[')
      blcnt++;
    else if(par.peek()== '{')
      clcnt++;
    else if(par.peek()== '<')
      vlcnt++;
         leftcnt++;
         par.pop();
      }
      if(leftcnt == rightcnt && plcnt == prcnt && brcnt == blcnt && crcnt == clcnt
      && vrcnt == vlcnt)
         check = true;
         }
      return check;
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
 Parentheses Match
 5+7	 good!
 (5+7)	 good!
 )5+7(	 BAD
 ((5+7)*3)	 good!
 <{5+7}*3>	 good!
 [(5+7)*]3	 good!
 (5+7)*3	 good!
 5+(7*3)	 good!
 ((5+7)*3	 BAD
 [(5+7]*3)	 BAD
 [(5+7)*3])	 BAD
 ([(5+7)*3]	 BAD
 */
