//name:     date:
import java.util.*;
  
/*******************
Represents a binary expression tree.
The BXT can build itself from a postorder expression.  It can
evaluate and print itself. It also prints an inorder string and a preorder string.  
**********************/
class BXT
{
   private int count;
   private TreeNode root;
   public BXT()
   {
      count = 0;
      root = null;
   }
   public void buildTree(String str){
      StringTokenizer stok = new StringTokenizer(str);
      Stack<TreeNode> stk = new Stack<TreeNode>();
      TreeNode parent;
      TreeNode current;
      while(stok.hasMoreTokens())
         stk.push(new TreeNode(stok.nextToken()));
      root = stk.pop();
      parent = root;
      current = null;
      while(!stk.isEmpty()){
         current = stk.pop();
         if(isOperator(current.getValue()+"")){
            if(parent.getLeft() == null && parent.getRight() != null)
               parent.setLeft(current);
            else if(parent.getLeft() != null && parent.getRight() == null)
               parent.setRight(current);
            else
               parent.setRight(current);
            parent = current;
         }
         else{
            if(parent.getLeft() == null && parent.getRight() != null)
               parent.setLeft(current);
            else if(parent.getLeft() != null && parent.getRight() == null)
               parent.setRight(current);
            else
               parent.setRight(current);
            if(parent.getLeft() != null && parent.getRight() != null)
            parent = root;
         }
      }
   }
   public double evaluateTree(){
      return evaluateNode(root);
   }
   private double evaluateNode(TreeNode root){
      if(root == null)
         return 0;
      if(!isOperator(""+root.getValue()))
         return Integer.parseInt(""+root.getValue());
      else if(isOperator(""+root.getValue()))
         return computeTerm(""+root.getValue(), evaluateNode(root.getLeft()), evaluateNode(root.getRight()));
      return 0;
   }
   private double computeTerm(String s, double a, double b){
      char c = s.charAt(0);
      if(c == '+')
         return a+b;
      else if(c == '-')
         return a-b;
      else if(c == '*')
         return a*b;
      else if(c == '/')
         return a/b;
      else
         return 0;
   }
   private boolean isOperator(String s){
   if(s.length() > 1)
   return false;
   else{
      char ch = s.charAt(0);
      if(ch=='+' || ch=='-' || ch=='*' || ch=='/')
         return true;
      else
         return false;
         }
   
   }
   public void display(){
      displayNode(root, 0);
   }
   private static void displayNode(TreeNode t, int level)
   {
      if(t == null)
         return;
      displayNode(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         System.out.print("\t");
      System.out.println(t.getValue());
      displayNode(t.getLeft(), level + 1); //recurse left
   }
   public void preorderTraverse(){
      xpreorderTraverse(root);
   }
   private static void xpreorderTraverse(TreeNode t)
   {
      if(t == null)
         return;
      System.out.print(t.getValue() + " ");  //preorder visit
      xpreorderTraverse(t.getLeft());         //recurse left
      xpreorderTraverse(t.getRight());        //recurse right
   }
   public void inorderTraverse(){
      xinorderTraverse(root);
   }
   private static void xinorderTraverse(TreeNode t)
   {
      if(t == null)
         return;
      xinorderTraverse(t.getLeft());
      System.out.print(t.getValue() + " ");         
      xinorderTraverse(t.getRight()); 
   
   }

  /*  enter your instance methods here.  
      buildTree, display, inorderTraverse, 
      preorderTraverse, evaluateTree  */

}
/*******************
Driver for a binary expression tree class.
Input: a postfix string, each token separated by a space.
**********************/
public class BXT_Driver
{
   public static void main(String[] args)
   {
      ArrayList<String> postExp = new ArrayList<String>();
      postExp.add("14 -5 / ");
      postExp.add("20 3 -4 + * ");
      postExp.add("2 3 + 5 / 4 5 - *");
   
      for( String postfix : postExp )
      {
         System.out.println("Postfix Exp: "  + postfix);
         BXT tree = new BXT();
         tree.buildTree( postfix );
         System.out.println("BXT: "); 
         tree.display();
         System.out.print("Infix order:  ");
         tree.inorderTraverse();
         System.out.print("\nPrefix order:  ");
         tree.preorderTraverse();
         System.out.print("\nEvaluates to " + tree.evaluateTree());
         System.out.println( "\n------------------------");
      }
   }
}

/***************************************
 Postfix Exp: 14 -5 / 
 BXT: 
 	-5
 /
 	14
 Infix order:  14 / -5 
 Prefix order:  / 14 -5 
 Evaluates to -2.8
 ------------------------
 Postfix Exp: 20 3 -4 + * 
 BXT: 
 		-4
 	+
 		3
 *
 	20
 Infix order:  20 * 3 + -4 
 Prefix order:  * 20 + 3 -4 
 Evaluates to -20.0
 ------------------------
 Postfix Exp: 2 3 + 5 / 4 5 - *
 BXT: 
 		5
 	-
 		4
 *
 		5
 	/
 			3
 		+
 			2
 Infix order:  2 + 3 / 5 * 4 - 5 
 Prefix order:  * / + 2 3 5 - 4 5 
 Evaluates to -1.0
 ------------------------
 */