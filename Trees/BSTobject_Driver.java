 //name:    date:  
import java.util.*;
import java.io.*;
//////////////////////////////////
interface BSTinterface<E extends Comparable<E>>
{
   public E add( E element );     //returns the object
   public boolean contains( E element );
   public boolean isEmpty();
   public E delete( E element );  //returns the object, not a Node<E>
   public int size();
   public String toString();
}
//////////////////////////////////
public class BSTobject_Driver
{
   public static BSTobject<String> tree = null;
   public static BSTobject<Widget> treeOfWidgets = null;
   public static int numberWidgets = 10;
   public static void main( String[] args ) 
   {
   //day one 
      tree = new BSTobject<String>();
      build(tree, "T");
      System.out.print(tree);
      System.out.println("Size: " + tree.size());
      System.out.println("Is empty: "+ tree.isEmpty());
   		
   	//day two
   	//	Your assignment the second day is to finish the BSTobject class.  
   	//	Specifically, prompt the user for a string, put the characters into a BST, 
   	//	call toString on this tree, and print the size of the tree.
      System.out.println("Enter Word: ");
      Scanner in = new Scanner(System.in);
      String s = in.next();
      tree = new BSTobject<String>();
      build(tree, s);
      System.out.println(tree);
      System.out.println("Size: " + tree.size());
      
      //day two, Widgets			
   	//	Next, fill your BST with 57 Widget objects from widgets.txt.  Display the tree. 
   	//	Then prompt the user to enter pounds and ounces.  If the tree contains that 
   	//	Widget, delete it, of course restoring the BST order. Display the new tree 
   	// and its size. If the tree does not contain that Widget, print "Not found".
      treeOfWidgets = new BSTobject<Widget>();
      build(new File("widget.txt"));
      System.out.println(treeOfWidgets);
      System.out.println("Enter pounds and ounces");
      Widget wid = new Widget(in.nextInt(), in.nextInt());
      if(treeOfWidgets.contains(wid) == false){
         System.out.println("Item not found");
      }
      else{
         treeOfWidgets.delete(wid);
      }
      System.out.println(treeOfWidgets);
      System.out.println("Size: " + treeOfWidgets.size());
   		
      //day three -- AVL tree  -----------------------
      
   }
   public static void build(BSTobject<String> tree, String str)
   {
      for(int k=0; k<str.length(); k++)
         tree.add(""+str.charAt(k));
     
   }
   public static void build(File file)
   {
      Scanner infile = null;
      try{
         infile = new Scanner( file  );
      }
      catch (Exception e)
      {
         System.exit(0);
      }
      for(int i = 0; i < 10; i++)   
      {
         int pounds = infile.nextInt();
         int ounces = infile.nextInt();
         Widget w = new Widget(pounds,ounces);
         treeOfWidgets.add(w);
      }
   }
}
////////////////////////////////
class BSTobject <E extends Comparable<E>> implements BSTinterface<E>
{ 
    // 2 fields 
    // 1 default constructor
   private Node root;
   private int size;
   public BSTobject(){
      root = null;
      size = 0;
   }
   public BSTobject(Node n){
      root = n;
      size = 1;
   }
      
   //instance methods
   public E add( E obj )
   {
      root = add( root, obj );
      size++;
      return obj;
   }
    //recursive helper method
   private Node<E> add( Node<E> t, E obj )
   {
      if(t==null)
         return new Node(obj);
      if(((Comparable)obj).compareTo((Comparable)t.getValue())<0)
         t.setLeft(add(t.getLeft(), obj));
      else
         t.setRight(add(t.getRight(), obj));
      return t;   
   }
   /* implement the interface here.  Use TreeNode as an example,
    but root is a field. You need add, contains, isEmpty, 
    delete, size, and toString.  */
   public boolean contains( E element ){
      Node current = root;
      while(current != null)
      {
         int compare = ((Comparable)(element)).compareTo((Comparable)current.getValue());
         if( compare == 0 )
            return true;
         else if(compare<0)
            current = current.getLeft();
         else if(compare>0)
            current = current.getRight();
      }
      return false;
   }
   public boolean isEmpty(){
      if(size == 0)
         return true;
      else
         return false;
   }
   public E delete( E element ){
   size--;
      Node rt = root;
      Node current = root;
      Node parent = null;
      if(rt.getLeft()==null && rt.getRight() == null){
         return element;
      }
      while(current !=null)
      {
         int compare = ((Comparable)element).compareTo((Comparable)current.getValue());
        // ------->  your code goes here
         if( compare == 0 ){
            if(current.getLeft() == null && current.getRight() == null){
               if(parent.getLeft().equals(current))
                  parent.setLeft(null);
               else
                  parent.setRight(null);
               return element;
            }
            if(current.getLeft() == null && current.getRight() != null){
               if(parent.getLeft().equals(current))
                  parent.setLeft(current.getRight());
               else
                  parent.setRight(current.getRight());
               return element;
            }
            else if(current.getLeft() != null && current.getRight() == null){
               if(parent.getLeft().equals(current))
                  parent.setLeft(current.getLeft());
               else
                  parent.setRight(current.getLeft());
               return element;
            }
            if(current.getLeft() != null && current.getRight() != null){
               Node max = current.getLeft();
               parent = current;
               if(max.getRight() != null){
                  max = max.getRight();
                  parent = parent.getLeft();
               }
               else{
                  current.setValue(max.getValue());
                  parent.setLeft(null);
                  return element;
               }
               while(max.getRight() != null){
                  max = max.getRight();
                  parent = parent.getRight();
               }
               current.setValue(max.getValue());
               current = max;  
               parent.setRight(null); 
               return element;
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
      return element;  //never reached
   }                               //returns the object, not a Node<E>
   public int size(){
      return size;
   }
   public String toString(){
      String str = "";
      if(root == null)
         return str;  
      str += toStringHelper(root.getRight(),1); //recurse right
      str += ""+root.getValue();
      str += "\n" + toStringHelper(root.getLeft(),1); //recurse left
      return str;
   }
   private String toStringHelper(Node n,int level){
      String str = "";
      if(n == null)
         return str;
      str+= toStringHelper(n.getRight(), level+1); //recurse right    
      for(int k = 0; k < level; k++)
         str+= "\t";
      str += n.getValue()+"\n";
      str+= toStringHelper(n.getLeft(), level+1); //recurse left
      return str;
   }
   

    /***************************private inner class **************/  
   private class Node<E>
   {
      // 3 fields 
      private Object value;
      private Node left, right;
      // 2 constructors, one-arg and three-arg
      public Node(Object o){
         value = o;
      }
      public Node(Object o, Node setleft, Node setright){
         value = o;
         left = setleft;
         right = setright;
      }
      public Object getValue()
      { 
         return value; 
      }
   
      public Node getLeft() 
      { 
         return left; 
      }
   
      public Node getRight() 
      { 
         return right; 
      }
   
      public void setValue(Object theNewValue) 
      { 
         value = theNewValue; 
      }
   
      public void setLeft(Node theNewLeft) 
      { 
         left = theNewLeft;
      }
   
      public void setRight(Node theNewRight)
      { 
         right = theNewRight;
      }
   
      //methods--Use TreeNode as an example. See the cheat sheet.
   
   }
}
class Widget implements Comparable<Widget>
{
   	//data fields
   private int myPounds, myOunces;
   
   	//constructors
   public Widget()
   {
      myPounds = myOunces = 0;
   }
   public Widget(int x)
   {
      myPounds = x;
      myOunces = 0;
   }
   public Widget(int x, int y)
   {
      myPounds = x;
      myOunces = y;
   }
   public Widget(Widget arg)
   {
      myPounds = arg.getPounds();
      myOunces = arg.getOunces();
   }
   
   	//accessors and modifiers
   public int getPounds()
   {
      return myPounds;
   }
   public int getOunces()
   {
      return myOunces;
   }
   public void setPounds(int x)
   {
      myPounds = x;
   }
   public void setOunces(int x)
   {
      myOunces = x;
   }
   
   	//other methods
   public int compareTo(Widget w)
   {
        // Widget w = (Widget)arg;     no need to cast
      if(myPounds < w.getPounds())
         return -1;
      if(myPounds > w.getPounds())
         return 1;
      if(myOunces < w.getOunces())
         return -1;
      if(myOunces > w.getOunces())
         return 1;
      return 0;
   }
   public boolean equals(Widget arg)
   {
      return compareTo(arg) == 0;
   }
   public String toString()
   {
      return myPounds + " lbs. " + myOunces + " oz.";
   }
}
   
