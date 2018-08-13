// Name:    Date:
import java.util.*;
import java.io.*;
public class SetsOfLetters
{
   public static void main(String[] args) throws FileNotFoundException
   {
      //Scanner sc = new Scanner(System.in);
      //System.out.print("Enter the file name: ");
      //String fileName = sc.next();
      String fileName = "declarationLast.txt";
      fillTheSets(fileName);
   }
   public static void fillTheSets(String fn) throws FileNotFoundException
   {
      Scanner infile = new Scanner(new File(fn));
      Iterator<Set<String>> it;
      Set<Set<String>> uctot = new HashSet<Set<String>>();
      Set<Set<String>> lctot = new HashSet<Set<String>>();
      Set<Set<String>> otot = new HashSet<Set<String>>();
      while(infile.hasNext()){
         Set<String> upper = new TreeSet<String>(); 
         Set<String> lower = new TreeSet<String>();
         Set<String> other = new TreeSet<String>();
         String line = infile.nextLine();
         for(int k=0; k<line.length(); k++){
            char ch = line.charAt(k);
            if(Character.isUpperCase(ch))
               upper.add(""+ch);
            else if(Character.isLowerCase(ch))
               lower.add(""+ch);
            else
               other.add(""+ch);
            
         }
         System.out.println(line);
         System.out.println("Lower Case: "+lower);
         System.out.println("Upper Case: "+upper);
         System.out.println("Other: "+other);
         System.out.println();
         uctot.add(upper);
         lctot.add(lower);
         otot.add(other);
      }
      Set<String> comupper; 
      Set<String> comlower;
      Set<String> comother;
      it = uctot.iterator();
      comupper = it.next();
      while(it.hasNext()){
         Set<String> curr = it.next();
         curr.retainAll(comupper);
         comupper = curr;
      }
      it = lctot.iterator();
      comlower = it.next();
      while(it.hasNext()){
         Set<String> curr = it.next();
         curr.retainAll(comlower);
         comlower = curr;
      }
   
      it = otot.iterator();
      comother = it.next();
      while(it.hasNext()){
         Set<String> curr = it.next();
         curr.retainAll(comother);
         comother = curr;
      }
      System.out.println("Common Lower Case: "+comlower);
      System.out.println("Common Upper Case: "+comupper);
      System.out.println("Common Other: "+comother);
      System.out.println();
   }
}