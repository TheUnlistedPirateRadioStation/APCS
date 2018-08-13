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
public class BSTobject
{
   public static BSTobject<String> tree = null;
   public static BSTobject<Widget> treeOfWidgets = null;
   public static int numberWidgets = 10;
   public static void main( String[] args )  throws Exception
   {
   //day one 
      tree = new BSTobject<String>();
      build(tree, "t");
      System.out.print(tree + "\n");
      System.out.println("Size: " + tree.size());
      System.out.println("Is empty: "+ tree.isEmpty());
   	
      System.out.println("Enter Word: ");
      Scanner scan = new Scanner(System.in);
      String s = scan.next();
      tree = new BSTobject<String>();
      build(tree,s);
      System.out.println(tree);
      System.out.println("Size: " + tree.size());
      
      ArrayList<Widget> apple = new ArrayList<Widget>(20);
      apple = input("widget.txt");
      
     	treeOfWidgets = new BSTobject<Widget>();
      build(treeOfWidgets,apple);
      System.out.println("Delete which object? ");
      Widget w = new Widget(scan.nextInt(),scan.nextInt());
      if(treeOfWidgets.contains(w) == false){
         System.out.println("Item not found");
      }
      else{
         treeOfWidgets.delete(w);
      }
      System.out.println(treeOfWidgets);
      System.out.println("Size: " + treeOfWidgets.size());
      
   	//day two
   	//	Your assignment the second day is to finish the BSTobject class.  
   	//	Specifically, prompt the user for a string, put the characters into a BST, 
   	//	call toString on this tree, and print the size of the tree.
   	
      
      //day two, Widgets			
   	//	Next, fill your BST with 57 Widget objects from widgets.txt.  Display the tree. 
   	//	Then prompt the user to enter pounds and ounces.  If the tree contains that 
   	//	Widget, delete it, of course restoring the BST order. Display the new tree 
   	// and its size. If the tree does not contain that Widget, print "Not found".
   
   		
      //day three -- AVL tree  -----------------------
      
   }
   public static ArrayList<Widget> input(String filename) throws Exception
      {
         Scanner infile = new Scanner( new File(filename) );
         ArrayList<Widget> array = new ArrayList<Widget>(57);	
         for (int k =0; k<10; k++)    		    // read all data in the file
         {								  
            int x = infile.nextInt();
            int y = infile.nextInt();
            Widget w = new Widget(x,y);
            array.add(k, w);
         }
         infile.close();
         return array;
      }
   public static void build(BSTobject<String> tree, String str)
   {
     str = str.toUpperCase();
     for(int i = 0; i < str.length(); i++){
         tree.add(str.substring(i,i+1));
     }
     
   }
   public static void build(BSTobject<Widget> tree, ArrayList<Widget> array){
        for(int i = 0; i < array.size(); i++){
            tree.add(array.get(i));
        }
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
            array.add(k, w);
      
      }
   }
}
////////////////////////////////
class BSTobject <E extends Comparable<E>> implements BSTinterface<E>{ 
    // 2 fields
    int size;
    Node<E> root;
    // 1 default constructor
   public BSTobject(){
      size = 0;
      root = null;
   }   
   public BSTobject(Object item){
      root.setValue(item);
      
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
      if(t == null){
         Node tree = new Node(obj);
         return tree;
         
      }
      Comparable x = (Comparable) t.getValue();
      Comparable y = (Comparable) obj;
      if(x.compareTo(y) >= 0){
         t.setLeft(add(t.getLeft(),obj));
         return t;
      }
      else if(x.compareTo(y) < 0){
         t.setRight(add(t.getRight(),obj));
         return t;
      }
      return root;  
   }
   public int size(){
      return size;
   }
   public boolean isEmpty(){
      if(size == 0)
         return true;
      return false;    
   }
   
   public String toString(){
    String tree = "";
    if(root == null)
      return tree;
      
    tree+= toString(root.getRight(),1); //recurse right

    tree+= root.getValue().toString();
      
    tree+= "\n" + toString(root.getLeft(),1); //recurse left
    
    return tree;
   }
   private String toString(Node t,int level){
    String tree = "";
    if(t == null)
      return tree;
      
    tree+= toString(t.getRight(), level + 1); //recurse right
      
    for(int k = 0; k < level; k++)
      tree+= ("\t");
    tree+=t.getValue().toString() + "\n";
      
    tree+= toString(t.getLeft(), level + 1); //recurse left
    
    return tree;
   }
   
   
   public E delete(E t){
      Node rootNode = root;
      Node parent = null;
      Node current = root;
      int compare = 0;
      Comparable target = t;
      while(current !=null){
         compare = target.compareTo((Comparable)rootNode.getValue());
         if(compare > 0){
            parent = rootNode;
            rootNode = rootNode.getRight();
         }
         else if(compare < 0){
            parent = rootNode;
            rootNode = rootNode.getLeft();
         }
         else if(compare == 0){ 
            if(parent == null && rootNode.getLeft() == null && rootNode.getRight() == null)
            {
              rootNode.setValue("");
              size--;
              return (E)current.getValue();
            }   
            if(rootNode.getLeft() == null && rootNode.getRight() == null){ //If the node is a leaf
               if(parent.getLeft() == rootNode)
               {
                  parent.setLeft(null);
                  size--;
                  return (E)current.getValue();
               }
               if(parent.getRight() == rootNode)
               {
                  parent.setRight(null);
                  size--;
                  return (E)current.getValue();
               }
            }
            if((rootNode.getLeft() != null && rootNode.getRight() == null) || (rootNode.getRight () != null && rootNode.getLeft() == null)){
               if(parent.getLeft() == rootNode)
               {
                  if(rootNode.getLeft() != null)
                     parent.setLeft(rootNode.getLeft());
                  else
                     parent.setLeft(rootNode.getRight());
                  size--;
                  return (E)current.getValue();
               
               }
               else if(parent.getRight() == rootNode)
               {
                  if(rootNode.getLeft() != null)
                     parent.setRight(rootNode.getLeft());
                  else
                     parent.setRight(rootNode.getRight());
                  size--;
                  return (E)current.getValue();
               
               }
            }
            if(rootNode.getLeft() != null && rootNode.getRight() != null){
                //smallest value in the right subtree
               Node largestLeft = rootNode;
               while(rootNode.getRight() != null)
               {
                  parent = largestLeft;
                  rootNode = rootNode.getRight();
                  if(largestLeft.getRight() != null)
                  {
                     largestLeft.setValue((rootNode.getValue())); 
                     if(rootNode.getRight() != null)       
                        largestLeft = largestLeft.getRight();
                  }
               }             
               target = (E)rootNode.getValue();
            }
         }     
      }
      size--;
      return (E)rootNode.getValue();  //never reached
   }

   
    public boolean contains(E t){
         Comparable x = (Comparable)root.getValue();
         if(t == null)
            return false;
         Comparable temp = (Comparable) t;   
         if(temp.compareTo(x) == 0){
            return true;
         }
         else if(temp.compareTo(x) > 0){
            return find(root.getRight(),(Comparable)t);
         }
         else if(temp.compareTo(x) < 0){
            return find(root.getLeft(),(Comparable)t);
         }
          
         return false;    
    }
    public boolean find(Node t, Comparable x)
      {
         if(t == null)
            return false;
         Comparable temp = (Comparable) t.getValue();   
         if(temp.compareTo(x) == 0){
            return true;
         }
         else if(temp.compareTo(x) < 0){
            return find(t.getRight(),x);
         }
         else if(temp.compareTo(x) > 0){
            return find(t.getLeft(),x);
         }
          
         return false;    
      }
}   
   
   
   
   /* implement the interface here.  Use TreeNode as an example,
    but root is a field. You need add, contains, isEmpty, 
    delete, size, and toString.  */
   


    
    /***************************private inner class **************/  
    class Node<E>{
      // 3 fields 
      private Object value; 
      private Node left, right;   
      // 2 constructors, one-arg and three-arg
      
      public Node(Object initValue){ 
         value = initValue; 
         left = null; 
         right = null; 
      }
   
      public Node(Object initValue, Node initLeft, Node initRight){ 
         value = initValue; 
         left = initLeft; 
         right = initRight; 
      }
      public Object getValue(){ 
         return value; 
      }
      
      public Node getLeft(){ 
         return left; 
      }
      
      public Node getRight() { 
         return right; 
      }
      
      public void setValue(Object theNewValue){ 
         value = theNewValue; 
      }
      
      public void setLeft(Node theNewLeft){ 
         left = theNewLeft;
      }
      
      public void setRight(Node theNewRight){ 
         right = theNewRight;
      }   
            
            
            
      //methods--Use TreeNode as an example. See the cheat sheet.
   
   }
class Widget implements Comparable<Widget>
   {
   	//data fields
      private String myName;
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
