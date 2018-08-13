	//This code uses raw arrays.  Change it to use ArrayList<E> instead.

   import java.io.*;      //the File class
   import java.util.*;    //the Scanner class
   import java.io.*;      //the File class
   import java.util.*;    //ArrayList & the Scanner class in Java 1.5
     
    public class SortingWidgets
   {
       public static void main(String[] args) throws Exception
      {
         ArrayList apple = input("widget.txt");  
         sort(apple);
         output(apple);
      }
       public static ArrayList input(String filename) throws Exception
      {
         Scanner infile = new Scanner( new File(filename) );
         ArrayList array = new ArrayList();	
         while(infile.hasNext()){								  
            int x = infile.nextInt();
            int y = infile.nextInt();
            array.add(new Widget(x, y));
         }
         infile.close();
         return array;
      }
       public static void sort(ArrayList array)
      {
         int maxPos;
         for(int k = 0; k < array.size(); k++)		
         {
            maxPos = findMax(array, array.size() - k - 1);
            swap(array, maxPos, array.size() - k - 1);
         }
      }
       public static int findMax(ArrayList array, int upper)
      {
         int maxPos = 0;
         for(int j = 1; j <= upper; j++){
         Widget a = (Widget)(array.get(j));			
            if(a.compareTo((Widget)(array.get(maxPos))) > 0)	
               maxPos = j;
               }					
         return maxPos;
      }
       public static void swap(ArrayList array, int a, int b)
      {
         Object temp = array.get(a);				
         array.set(a, array.get(b));
         array.set(b, temp);
      }
       public static void output(ArrayList array)
      {
         for(Object w: array)		//use the for-each loop
            System.out.println((Widget)(w));
      }
   }

///////////////////////////////////////////////////////////////

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