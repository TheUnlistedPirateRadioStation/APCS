 //name:Shreyas Angara     date:   
import java.text.DecimalFormat;
public class SmartCard_Driver
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
   private double amount;
   private int bzone = 0;
   private String boardStat = "";
   private boolean present = false;
   DecimalFormat d = new DecimalFormat("$0.00");
  
   public SmartCard(double m){
      amount = m;
   }
   public void addMoney(double x){
      amount += x;
   }  
   public void board(Station s){
      boardStat = s.getName();
      bzone = s.getZone();
      if(present)
         System.out.println("Warning: Boarded without Disembarking");
      if(amount<0.50)
         System.out.println("Not enough balance");
      else
         System.out.println("Boarded at: "+boardStat);
      present = true;
   }
   public double cost(Station t){
      int temp = t.getZone();
      double cost = (Math.abs(temp - bzone))*0.75+0.50;
      return cost;
   }
   public void disembark(Station r){
      amount-= cost(r);
      if(present==false)
         System.out.println("Please see the station manager!");
      else{
         System.out.println("From "+boardStat+" to "+r.getName()+" costs "+d.format(cost(r))+".");
         System.out.println("Balance: "+d.format(amount));
         present = false;
      }
   }
}
class Station
{
   private String name;
   private int zone;
   
   public Station(){
      name = "Arnav";
      zone = 1;
   
   }
   public Station(String n, int z){
      name = n;
      zone = z;
   }
   public String getName(){
      return name;
   }
   public void setName(String s){
      name = s;
   }
   public int getZone(){
      return zone;
   }
   public void setZone(int n){
      zone = n;
   }
   public String toString(){
      return ""+name+", "+zone;
   }
  
}

 
