 //name:     date:   
import java.text.DecimalFormat;
public class SmartCards
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
     
      SmartCard jimmy = new SmartCard(20.00); //bought with $20.00 
      jimmy.board(center);            		    //boarded in zone 1
      jimmy.disembark(suburbia);					 //disembark in zone 4
      jimmy.disembark(uptown);					 //disembark without having boarded
   	
   	//lots more test cases!				
   }
}
class SmartCard 
{
    static double amount;
    static int bzone;
    static String boardStat;
    static boolean present = false;
   public SmartCard(double m){
      amount = m;
   }
   public static void addMoney(double x){
      amount += x;
   }  
   public static void board(Station s){
      boardStat = s.getName();
      bzone = s.getZone();
      if(present)
         System.out.println("Warning: Boarded without Disembarking");
      if(amount<0.50)
         System.out.println("Not enough balance");
      present = true;
   }
   public static double cost(Station t){
      int temp = t.getZone();
      double cost = (Math.abs(temp - bzone)-1)*0.75+0.50;
      return cost;
   }
   public static void disembark(Station r){
      amount-= cost(r);
      if(present=false)
         System.out.println("Warning: Disembarked without Boarding");
      present = false;
      System.out.println("Boarded at: "+boardStat);
      System.out.println("Disembarked at: "+r.getName());
      System.out.println("Cost: "+cost(r));
   }
}
class Station
{
    static String name;
    static int zone;
   
   public Station(){
      name = "Arnav";
      zone = 1;
   
   }
   public Station(String n, int z){
      name = n;
      zone = z;
   }
   public static String getName(){
      return name;
   }
   public static void setName(String s){
      name = s;
   }
   public static int getZone(){
      return zone;
   }
   public static void setZone(int n){
      zone = n;
   }
   public String toString(){
      return ""+name+", "+zone;
   }
  
}

 
