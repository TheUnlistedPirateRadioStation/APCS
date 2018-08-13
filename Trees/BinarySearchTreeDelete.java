 //name:    date: 
import java.util.Scanner;
/****************************************************************
 Practice with a Binary Search Tree. Uses TreeNode.
Prompt the user for an input string.  Build a BST from the letters.
Display it as a sideways tree.  Prompt the user for a target and delete
 that node, if it exists. Show the updated tree.
*****************************************************************/
public class BinarySearchTreeDelete
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Input string: ");
      String s = sc.nextLine();
   	         				//Case 1:  		ECSBPWANR 
   								//Case 2a:  	SNTPOR    
   								//Case 2b:	   HBRNVJSZIK  
   								//Case 3.a.i:  DBNACFSEJHM 
   								//Case 3.a.ii: NFSAKPXGQ 
   								//Case 3b:     RNVGPCAE 
   								//Case root:   N
   								//on the handout: HDJAGKFEOLTMNSU
   							    
      TreeNode root = null;
      root = buildTree( root, s );
      display(root, 0);
      System.out.print("Delete? ");
      String target = sc.next();
      if( contains( root, target ) )
      {
         root = delete( root, target );
         System.out.println("\n" + target+" deleted.");
      }
      else
         System.out.println("\n" + target+" not found");
      display(root, 0);        
   }
   public static TreeNode buildTree(TreeNode t, String s)
   {
      for(int k = 0; k < s.length(); k++)
         t = insert(t, "" + s.charAt(k));
      return t;
   }
	/**************************
	Recursive algorithm to build a BST:  if the node is null, insert the 
	new node.  Else, if the item is less, set the left node and recur to 
	the left.  Else, if the item is greater, set the right node and recur 
	to the right.   
	*****************************/
   public static TreeNode insert(TreeNode t, String s)
   {  	
      if(t==null)
         return new TreeNode(s);
      if(s.compareTo(t.getValue()+"")<0)
         t.setLeft(insert(t.getLeft(), s));
      else
         t.setRight(insert(t.getRight(), s));
      return t;
   }
   public static void display(TreeNode t, int level)
   {
      if(t == null)
         return;
      display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         System.out.print("\t");
      System.out.println(t.getValue());
      display(t.getLeft(), level + 1); //recurse left
   }
   
   public static boolean contains( TreeNode current, String target )
   {
      while(current != null)
      {
         int compare = target.compareTo((String)current.getValue());
         if( compare == 0 )
            return true;
         else if(compare<0)
            current = current.getLeft();
         else if(compare>0)
            current = current.getRight();
      }
      return false;
   }
   public static TreeNode delete(TreeNode current, String target)
   {
      TreeNode root = current;
      TreeNode parent = null;
      if(root.getLeft()==null && root.getRight() == null){
      return new TreeNode("");
      }
      while(current !=null)
      {
         int compare = target.compareTo((String)current.getValue());
        // ------->  your code goes here
         if( compare == 0 ){
            if(current.getLeft() == null && current.getRight() == null){
               if(parent.getLeft().equals(current))
                  parent.setLeft(null);
               else
                  parent.setRight(null);
               return root;
            }
            if(current.getLeft() == null && current.getRight() != null){
               if(parent.getLeft().equals(current))
                  parent.setLeft(current.getRight());
               else
                  parent.setRight(current.getRight());
               return root;
            }
            else if(current.getLeft() != null && current.getRight() == null){
               if(parent.getLeft().equals(current))
                  parent.setLeft(current.getLeft());
               else
                  parent.setRight(current.getLeft());
               return root;
            }
            if(current.getLeft() != null && current.getRight() != null){
               TreeNode max = current.getLeft();
               parent = current;
               if(max.getRight() != null){
               max = max.getRight();
               parent = parent.getLeft();
               }
               else{
               current.setValue(max.getValue());
               parent.setLeft(null);
               return root;
               }
               while(max.getRight() != null){
                  max = max.getRight();
                  parent = parent.getRight();
                  }
               current.setValue(max.getValue());
               current = max;  
               parent.setRight(null); 
               return root;
            }
            
         }
         else if(compare<0){
            parent = current;
            current = current.getLeft();
         }
         else if(compare>0){
            parent = current;
            current = current.getRight();
         }
        
        
      }
      return root;  //never reached
   }
}
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
