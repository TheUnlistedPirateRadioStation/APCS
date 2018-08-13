import java.util.*;
import java.util.PriorityQueue;
public class McRonaldPQ
{
   private static int time = 0;
   static PriorityQueue<Student> line = new PriorityQueue<Student>();
    int highsenior = 0,  highjunior = 0, highsoph = 0, highfresh = 0; 
   int totalsenior = 0, totaljunior = 0, totalsoph = 0, totalfresh = 0;
   int numsenior = 0, numjunior = 0, numsoph = 0, numfresh = 0;
  
 int totalt = 0;
   static ArrayList<Integer> wait = new ArrayList<Integer>();
   
   public static void main(String[] args)
   {
      for(int x = 0; x < 3; x++)
      {
         wait.add(0);
      }
         
      for(int x = 0; x < 1080; x++)
      {
         if(Math.random() < .25)
            add(x);
         
         if( !line.isEmpty() )
            action(x);
         
         System.out.println(x + ": " +line.toString());          
      }
      while( !line.isEmpty() )
      { 
         action(time);     
         time++;
         System.out.println(time + ": " +line.toString());   
      }
      print();
   }
   public static void print()
   {
      System.out.println("Customer"+"\t"+"Total Served"+"\t"+"Longest Wait"+"\t"+"Average Wait");
      System.out.println("Senior"+"\t"+totalsenior+"\t"+highsenior+"\t"+avgsenior);
      System.out.println("Junior"+"\t"+totaljunior+"\t"+highjunior+"\t"+avgjunior);
      System.out.println("Sophomore"+"\t"+totalsoph+"\t"+highsoph+"\t"+avgsoph);
      System.out.println("Freshman"+"\t"+totalfresh+"\t"+highfresh+"\t"+avgfresh);
   }
   public static void add(int waittime)
   {
      int s = (int)(Math.random()*4)+1;
      if(s == 1)
      {
         line.add(new Student("Senior", i));
         numSen++;
      }
      if(s == 2)
      {
         line.add(new Student("Junior", i));
         numJun++;
      }
      if(s == 3)
      {
         line.add(new Student("Sophomore", i));
         numSoph++;
      }
      if(s == 4)
      {
         line.add(new Student("Freshman", i));
         numFresh++;
      }
   }
   
   public static void action(int i)
   {
      int one = 0;
      int rand = (int)(Math.random() *6) + 2;
      for(int x = one; x < 3; x++)
      {
         if(wait.get(x) == one && line.size() > x)
            wait.set(x, rand + i);  
      }
      
      for(int x = one; x < 3; x++)
      {
         if(wait.get(x) == i && line.size() > x)
         {
            PriorityQueue<Student> temp = new PriorityQueue<Student>();
            int c = one;
            int grade = one;
            int rem = one;
            
            while( !line.isEmpty() )
            {
               Student s = line.remove();
               if(c == x)
               {
                  grade = s.getGrade();
                  rem = s.getTime();
               }
               else
                  temp.add(s);
               
               
               c++;
            }
            wait.remove(x);
            wait.add(one);
            line = temp;
            totalt = i - rem;
            x--;
            check(grade);
         }
      }
     
   }
   ////////////////////////////////////////////////////////
   public static void check(int grade)
   {
      if(grade == 12)
      {
         tSeniorTime += totalt;
         if(totalt > senLong)
         {
            senLong = totalt;
         }            
      }
      else if(grade == 11)
      {
         tJuniorTime += totalt;
         if(totalt > junLong)
         {
            junLong = totalt;   
         }         
      }
      else if(grade == 10)
      {
         tSophTime += totalt;
         if(totalt > sophLong)
         {
            sophLong = totalt; 
         }           
      }
      else if(grade == 9)
      {
         tFreshTime += totalt;
         if(totalt > freshLong)
         {
            freshLong = totalt;
         }
      }  
   }
}
class Student implements Comparable<Student>
{
   private int year;
   private int time;
   public Student(String y, int t)
   {
      time = t;
      if(y.equals("Senior"))
         year = 12;
      
      else if(y.equals("Junior"))
         year = 11;
      
      else if(y.equals("Sophomore"))
         year = 10;
      
      else if(y.equals("Freshman"))
         year = 9;
      
      else 
         year = -1;
   }
   public Student(int yr, int t)
   {
      year = yr;
      time = t;
   }
   public int getGrade()
   {
      return year;
   }
   public int getTime()
   {
      return time;
   }
   public int compareTo(Student s)
   {
      int time = s.getTime();
      int time1 = this.getTime();
      int grade = s.getGrade();
      int grade1 = this.getGrade();
      
      if(grade1 > grade)
         return -1;
      
      if(grade1 == grade)
      {
         if(time1 < time)
            return -1;
         else if(time1 > time)
            return 1;
         else
            return 0;
      }
      else
         return 1;
      
   }
   public String toString()
   {
      return this.getGrade() + "-" + this.getTime();
   }
}