  //your name and date
import java.util.Scanner;
public class ListLab1
{
   public static void main(String[] args)
   {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
         	new ListNode("science",
         		new ListNode("java",
         			new ListNode("coffee", head)
         		)
         	)
         );
      print(head);
      print(head);
      
      /**** uncomment the code below for ListLab1 Extension  ****/
      
   	System.out.println("First = " + first(head));
       System.out.println("Second = " + second(head));
       ListNode p = pointerToLast(head);
       System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
       ListNode c = copyOfLast(head);
       System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);
   		
       Scanner in = new Scanner(System.in);
       System.out.print("Insert what? ");
       String x = in.next();
       head = insertFirst(head, x);
       head = insertLast(head, x);
       print(head);
   }
   public static void print(ListNode head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }
   private static ListNode copyNode(ListNode arg){
      ListNode n = new ListNode(arg.getValue(), arg.getNext()); 
      return n;
   }
      // returns a new list that is a copy of the original list.
      // this method is recursive! 
      // example call:  ListNode head2 = copyList(head);
   private static ListNode copyList(ListNode arg){	
      if(arg.getNext() ==  null)
         return copyNode(arg.getNext());
      else
         return copyList(arg.getNext());	
   }
      //returns a new linked list containing copies of each node in 
      //the original list except the first node, maintaining the 
      //order of the original list.  It is not correct to just 
      //return a pointer to the 2nd node of the original linked 
      //list.  This method is recursive.
   private static ListNode rest(ListNode h){
      return copyList(h.getNext());   
   }
      // returns the value of the first node, or null if the list is empty 
   public static Object first(ListNode head){
      return head.getValue();
   } 
      // returns the value of the second node, or null if the list is empty or if there is only one node.  
      // hint:  second could call the first of rest. 
   public static Object second(ListNode head){
      ListNode sec = head.getNext();
      return sec.getValue();
   } 
      //returns a reference to the last node in the list, or null if the list is empty.
   public static ListNode pointerToLast(ListNode head){
      if(head.getNext().getNext() == null)
         return head;
      else
         return pointerToLast(head.getNext());
   } 
      //returns a copy of the last node (not just its value!).  copyofLast can be recursive.  
   public static ListNode copyOfLast(ListNode head){
      if(head.getNext() == null)
         return copyNode(head);
      else
         return copyOfLast(head.getNext());
   } 
      //returns a reference to a list whose first node's value is specified by the argument, and the 
      //first node's next links to the original list. 
   public static ListNode insertFirst(ListNode head, Object arg){
      ListNode newhead = new ListNode(arg, head);
      return newhead;
   } 
      //returns a reference to a list whose last node's value is specified by the argument, such 
      //that this last node has been appended to the original list and had its next is set to null 
   public static ListNode insertLast(ListNode head, Object arg){
      while(head.getNext() != null){
         head = head.getNext();
      }
      ListNode newlast = new ListNode(arg, null);
      head.setNext(newlast);
      return newlast;
   } 
   public static class ListNode
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
}












































