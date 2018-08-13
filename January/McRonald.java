import java.util.*;
public class McRonald{
   private static Queue<Customer> line = new LinkedList<Customer>();
   private static final int openmins = 1020;
   public static void main(String[] args)
   {
      int numcustomers = 0;
      int high = 0;
      int highwait = 0;
      int highqueue = 0;
      double totalwait = 0;
      double avgwait = 0;
      for(int k = openmins; k>0; k--){
         if(line.size() != 0){
            if(line.peek().getTotalWait()>highwait)
               highwait = line.peek().getTotalWait();
            line.peek().subOrderTime();
            if(line.peek().getOrderTime() == 0){
               totalwait += line.peek().getTotalWait();
               line.remove();
            }
         }
         int chance = (int)(Math.random()*4+1);
         if(chance == 1){
            if(line.size()>highqueue)
               highqueue = line.size();
            numcustomers++;
            int waitadd = 0;
            for(Customer c: line){
               waitadd+= c.getOrderTime();
            }
            line.add(new Customer(waitadd, ""+numcustomers));
            
         }
         System.out.println(line);
      }
      while(line.size() != 0){
         line.peek().subOrderTime();
         if(line.peek().getOrderTime() == 0)
            totalwait += line.poll().getTotalWait();
         System.out.println(line);      
                 
      }
      avgwait = totalwait/numcustomers;
      System.out.println("Total customers served = "+numcustomers);
      System.out.println("Average wait time = "+avgwait);
      System.out.println("Longest wait time = "+highwait);
      System.out.println("Longest queue = "+highqueue);
   }
}
class Customer{
   private int ordertime;
   private int totalwait;
   private String name;
   public Customer(int n, String m){
      ordertime = (int)(Math.random()*5+2);
      totalwait = ordertime + n;
      name = m;
   }
   public int getOrderTime(){
      return ordertime;
   }
   public void subOrderTime(){
      ordertime--;
   }
   public int getTotalWait(){
      return totalwait;
   }
   public void addTotalWait(int x){
      totalwait += x;
   }
   @Override
   public String toString(){
      return name;
   }
}