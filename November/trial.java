public class trial{
   public static void main(String[] args){
      int[]one={1,2,3,4,5};
      System.out.println(makeList(one));  
   }
   public static ListNode makeList(int[] one){
   ListNode temp = new ListNode(one[one.length-1], null);
      for(int i=one.length-2;i>=0;i--){
      temp = new ListNode(one[i], temp);
      }
      return temp;
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