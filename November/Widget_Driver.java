//name:    date:  

import java.io.*;      //the File class
import java.util.*;    //the Scanner class
   
public class Widget_Driver
{
   public static final int numberOfWidgets = 57;
   public static void main(String[] args) throws Exception
   {
      Comparable[] wid = input("widget.txt");
      Selection.sort(wid);
      print(wid);
   }
          	
   public static Comparable[] input(String filename) throws Exception
   {
      int size = numberOfWidgets;
      Scanner sc = new Scanner(new File(filename));
      Comparable[] arrayStr = new Comparable[size];
      for(int k = 0; k < arrayStr.length; k++){
      arrayStr[k] = new Widget(sc.nextInt(), sc.nextInt());
      }
      return arrayStr;	
   }
   	  
   public static void print(Object[] mango)
   {
      for(int k=0; k<mango.length; k++)
         System.out.println(""+mango[k]);
   }
}
   /////////////////////////////////////////////////////
	//name:    date:

class Widget implements Comparable<Widget>
{
   private int pounds, ounces;
   public Widget(int p, int o){
      pounds = p;
      ounces = o;
   }
   public int getPounds(){
      return pounds;
   }
   public int getOunces(){
      return ounces;
   }
   public void setPounds(int x){
      pounds = x;
   }
   public void setOunces(int y){
      ounces = y;
   }

   public int compareTo(Widget w){
   int comp = 0;
      if(pounds == w.getPounds()){
         if(ounces == w.getOunces())
            comp = 0;
         else if(ounces > w.getOunces())
            comp = 1;
         else if(ounces < w.getOunces())
            comp = -1;
      }
      else if(pounds < w.getPounds())
         comp = -1;
      else if(pounds > w.getPounds())
         comp = 1;
         return comp;
   }
   @Override
   public String toString(){
      return pounds+" lbs., "+ounces+" oz.";
   } 
}