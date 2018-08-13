 //Name:   Date:
 //modeling a polynomial using a treeMap
import java.util.*;
public class Polynomial_Driver
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println(poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
   	   	
      Polynomial poly2 = new Polynomial();
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1); 
      System.out.println(poly2.toString());
   	
      System.out.println(poly.add(poly2));
      System.out.println(poly.multiply(poly2));
   }
}
interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{
   private Map<Integer, Integer> poly;
   
   public Polynomial(){
      poly = new TreeMap<Integer, Integer>();
   }
   public void makeTerm(Integer exp, Integer coef){
      poly.put(exp, coef);
   }
   public double evaluateAt(double x){
      double ans = 0;
      for(Integer ex: poly.keySet()){
         ans+= Math.pow(x, ex)*poly.get(ex);
      }
      return ans;
   }
   public Polynomial add(Polynomial other){
      Polynomial sum = new Polynomial();
      Set<Integer> exponents = new HashSet<Integer>();
      exponents.addAll(poly.keySet());
      exponents.addAll(other.poly.keySet());
      for(Integer e: exponents){
         int one, two;
         if(poly.containsKey(e))
            one = poly.get(e);
         else
            one = 0;
         if(other.poly.containsKey(e))
            two = other.poly.get(e);
         else
            two = 0;
         sum.makeTerm(e, one+two);
      }
      return sum;
   }
   public Polynomial multiply(Polynomial other){
      Polynomial product = new Polynomial();
      for(Integer key: other.poly.keySet()){
      Polynomial temp = new Polynomial();
         for(Integer curr: poly.keySet()){
         temp.makeTerm(key+curr, (other.poly.get(key)*poly.get(curr)));
         }
         product = product.add(temp);
      }
      return product;
      
   }
   public String toString(){
      String str = "";
      for(int k=poly.size(); k>=0; k--){
         if(poly.containsKey(k)){
            if(k >1)
               str+= ""+poly.get(k)+"x^"+k+"+";
            else if(k == 1)
               str+= ""+poly.get(k)+"x"+"+";
            else 
               str+= ""+poly.get(k);
         }
      }
      return str;
   }
}
/*  
expected output
   2x^3 + -4x + 2
   10.0
   2x^4 + x^2 + -5x + -3
   2x^4 + 2x^3 + x^2 + -9x + -1
   4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 */