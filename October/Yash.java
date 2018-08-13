import java.util.*;
import java.text.*;
public class Yash{
   public static void main(String[] args){
      
      Hashtable<String, Integer> sai = new Hashtable<String, Integer>();
      sai.put("A", 0);
      sai.put("B", 450);
      sai.put("C", 590);
      sai.put("D", 710);
      sai.put("E", 1030);
      sai.put("F", 1280);
      sai.put("G", 1360);
      Enumeration e = sai.elements();
      while(e.hasMoreElements())
      e.nextElement();
      Scanner infile = new Scanner(System.in);
      System.out.println("Enter miles per gallon, price per gallon, and average speed, seperated by spaces");
      double mpg, price, speed;
      char first, second;
      mpg = infile.nextDouble();
      price = infile.nextDouble();
      speed = infile.nextDouble();
      for(int k=0; k<10; k++){
      System.out.println("Input Starting and ending seperated by a comma");
            String in = infile.next();
      String part1 = ""+in.charAt(0);
      String part2 = ""+in.charAt(2);
      int one = sai.get(part1);
      int two = sai.get(part2);
      int dist = two-one;
      String saiteej = findPrice(mpg, price, dist);
      String yasharms = findTime(speed,dist);
      System.out.println(dist+", "+yasharms+", "+saiteej); 
      in = null;
      part1 = null;
      part2 = null;
       one = 0;
       two = 0;
       dist = 0;
      }
         
   }
   static public String findPrice(double mileage, double gasCost, double distance)
   {
      double price = 0;
      price = distance/mileage*gasCost;
      DecimalFormat d = new DecimalFormat("$00.00");
      String formatted = d.format(price);
      return formatted;
   }
   static public String findTime(double speed, double distance)
   {
      double time = 0;
      time = distance/speed*3600;
      DecimalFormat d = new DecimalFormat("00");
      //d.setMinimumIntegerDigits(2);
      int hours = (int)(time/3600);
      int minutes = (int)((time-hours*3600)/60);
      String formatted = (d.format(hours) + ":" + d.format(minutes));
      return formatted;
   }

}