//y0u g   date:  
import java.util.*;
import java.io.*;
public class BSTobject
{
   public static void main( String[] args ) throws Exception
   {
         //day one
      BSTobject<String> tree = new BSTobject<String>("F");
      System.out.print(tree);
      System.out.println(tree.size());
      System.out.println(tree.isEmpty()); 
   		
   		//day two
   		//	Your assignment the second day is to finish the BSTobject class.  
   		//	Specifically, prompt the user for a string, put the characters into a BST, 
   		//	call toString on this tree, and print the size of the tree.
   		// 			
   		//	Next, fill your BST with 57 Widget objects from widgets.txt.  Display the tree. 
   		//	Then prompt the user to enter pounds and ounces.  If the tree contains that 
   		//	Widget, delete it, of course restoring the BST order. Display the new tree 
   		// and its size. If the tree does not contain that Widget, print "Not found".
   
   
   		
   		
   		//day three--AVL tree
   }
}
   	////////////////////////////////
class BSTObject <E extends Comparable<E>> implements BSTinterface<E>
{ 
   private static int count;
   private static Node<E> root;
   
   public BSTObject()
   {
      count = 0;
      root = null;
   }
   public BSTObject(E obj)
   {
      count = 1;
      root = new Node<E>(obj, null, null);
   }
//    public E add( E element );     //returns the object
//    public boolean contains( E element );
//    public boolean isEmpty();
//    public E delete( E element );  //returns the object, not a Node<E>.  
//    public int size();
//    public String toString();
   public E add ( E element )
   {
      if (root == null)
         root = new Node<E>(element);
      while (root != null)
      {
         int compare = element.compareTo(root.getValue());
      
     
      /***************************private inner class **************/  
   private class Node<E>
   {
      private Object value; 
      private Node<E> left, right;
         
      public Node(Object initValue)
      { 
         value = initValue; 
         left = null; 
         right = null; 
      }
   
      public Node(Object initValue, TreeNode initLeft, TreeNode initRight)
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
}
	//////////////////////////////////
interface BSTinterface<E extends Comparable<E>>
{
   public E add( E element );     //returns the object
   public boolean contains( E element );
   public boolean isEmpty();
   public E delete( E element );  //returns the object, not a Node<E>.  
   public int size();
   public String toString();
}
//////////////////////////////////