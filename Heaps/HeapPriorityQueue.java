import java.util.*;
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   public HeapPriorityQueue(){
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   public boolean isEmpty(){
   return myHeap.size()<=1;
   }
   private void swap(int a, int b)
   {
     Collections.swap(myHeap, a, b);

   }
   public void add(E e){
      myHeap.add(e);
      if(myHeap.size() >= 3)
      heapUp(myHeap.size()-1);
   
   }
   public E remove(){
   E obj = myHeap.get(1);
   if(myHeap.isEmpty())
   return null;
   if(myHeap.size() < 3)
   myHeap.remove(1);
   else{
      swap(1, myHeap.size()-1);
      myHeap.remove(myHeap.size()-1);
      heapDown(1, myHeap.size());
      }
   return obj;
   }
   private void heapUp(int n){
      int parent = n/2;
      if(parent < 1)
         return;
      else{
         if(myHeap.get(n).compareTo(myHeap.get(parent)) > 0)
            return;
         else{
            swap(n, parent);
            heapUp(parent);
         }
      }
   }
   private void heapDown(int k, int size){
      int left = 2*k;
      int right = 2*k+1;
      if(k>=size ||( left>=size && right >= size))
         return;
      else{
         if(right<size){
            int maxChild = (myHeap.get(left).compareTo(myHeap.get(right))<0) ? left:right;
            if(myHeap.get(k).compareTo(myHeap.get(maxChild)) > 0)
            {
               swap(k,maxChild);
               heapDown(maxChild, size);
            }
         }
      }
   
   }
   public String toString(){
   return myHeap.toString();
   }

     
}
