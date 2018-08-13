// name:     date:  

import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class Josephus
{
   private static String WINNER = "Josephus";
   public static void main(String[] args) throws FileNotFoundException
   {
      /* run it first with J_numbers.txt  */
      ListNode p = null;
      int size = Integer.parseInt(JOptionPane.showInputDialog("How many names (2-20)?"));
      File f = new File("J_numbers.txt");
      p = readNLinesOfFile(size,f);
      int countOff = Integer.parseInt(JOptionPane.showInputDialog("How many names to count off each time?"));
      countingOff(p, countOff, size);
      
   	/* run it next with J_names.txt  */
      System.out.println("\n ****  Now start all over.  Enter the winning position in the JOptionPane.  *** \n");
      p = readNLinesOfFile(size, new File("J_names.txt"));
      int winPos = Integer.parseInt(JOptionPane.showInputDialog("Enter Josephus's preferred position."));
      replaceAt(p, WINNER, winPos);
      countingOff(p, countOff, size);
      System.out.println("Josephus wins!");    
   }
   /* reads the names, builds the linked list.
	  */
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
   {
      Scanner sc = new Scanner(f);
      ListNode last = new ListNode(sc.next(), null);
      ListNode head = last;
      for(int i = 0; i < n-2; i++){
         ListNode temp = new ListNode(sc.next(), null);
         head.setNext(temp);
         head = temp;
      }
      head.setNext(last);
      return head;
   }
  /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
     Ends with one remaining name, who is the winner. 
	  */
   public static void countingOff(ListNode p, int startSpot, int size)
   {
      while(size > 1){
         size--;
         print(p);
         p = remove(p, startSpot);
      }
   
   }
   /* removes the node p after counting n nodes.
	  */
   private static ListNode remove(ListNode p, int n)
   {
      for(int k=0; k<n-1; k++)
         p = p.getNext();
      p.setNext(p.getNext().getNext());
      return p.getNext();
   }
   /* prints the circular linked list.
	  */
   public static void print(ListNode p)
   {
     System.out.print("[");
         ListNode head = p;
         System.out.print(p.getValue());
         p = p.getNext();
         while (!(p.equals(head)))
         {
            System.out.print(" , " + p.getValue());
            p = p.getNext();
         }
         System.out.println("]");
   }
 /* helper method to build the list.  Creates the node, then
    inserts it in the circular linked list.
	 */
   private static ListNode insert(ListNode p, Object obj)
   {
      ListNode head = p;
      while (!(p.getNext().equals(head)))
      {
         p = p.getNext();
      }
      ListNode temp = new ListNode(obj, head);
      p.setNext(temp);
      return head;
   }

	/* replaces the value (the string) at the winning node.
	   */
   private static void replaceAt(ListNode p, Object obj, int pos)
   {
      for(int k=0; k<pos-1; k++)
         p = p.getNext();
      p.setValue(obj);
   }
}

  //the College Board's standard ListNode class
class ListNode
{
   private Object value;
   private ListNode next;
   public ListNode(Object v, ListNode n)
   {
      value=v;
      next=n;
   }
   public Object getValue()
   {
      return value;
   }
   public ListNode getNext()
   {
      return next;
   }
   public void setValue(Object newv)
   {
      value=newv;
   }
   public void setNext(ListNode newn)
   {
      next=newn;
   }
}