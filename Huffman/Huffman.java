// name:        date: 
import java.util.*;
import java.io.*;
public class Huffman
{
static String last = "";
   public static void main(String[] args) throws IOException
   {
      	//Read the string
      Scanner infile = new Scanner(System.in);
      String str = infile.next();
         //Make a frequency table of the letters
      Map<Character, Integer> ht = new HashMap<Character, Integer>();
      for(int k=0; k<str.length(); k++){
         if(ht.containsKey(str.charAt(k)))
            ht.put(str.charAt(k), ht.get(str.charAt(k))+1);
         else
            ht.put(str.charAt(k), 1);
      }
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).
      PriorityQueue<HuffmanTreeNode> pq = new PriorityQueue<HuffmanTreeNode>();
      for(char ch: ht.keySet())
         pq.add(new HuffmanTreeNode(ch, ht.get(ch)));
      	//Use the priority queue of nodes to build the Huffman tree
      while(pq.size() != 1){
         HuffmanTreeNode child1 = pq.remove();
         HuffmanTreeNode child2 = pq.remove();
         HuffmanTreeNode parent = new HuffmanTreeNode('*', child1.getFreq()+child2.getFreq());
         parent.setLeft(child1);
         parent.setRight(child2);
         pq.add(parent);
      }
      HuffmanTreeNode root = pq.remove();
      	//Process the string letter-by-letter and search the tree for the 
   		//       letter.  As you go, build the binary path, where going 
   		//       left is 0 and going right is 1.  
      Map<Character, String> code = new HashMap<Character, String>();
      
      for(int n=0; n<str.length(); n++){
      code.put(str.charAt(n), scheme(root, ""+str.charAt(n), ht));
      }
      PrintStream ps = new PrintStream("message.shreyas.txt");
      PrintStream ns = new PrintStream("scheme.shreyas.txt");
      for(int f=0; f<str.length(); f++){
      ps.print(code.get(str.charAt(f)));     
      }
      for(char ch: ht.keySet())
      ns.println(ch+": "+code.get(ch));
      
            	//Write the binary path to the hard drive as message.xxx.txt
      	//Write the scheme to the hard drive as scheme.xxx.txt
      
  }
private static String scheme(HuffmanTreeNode t, String x, Map map)
   {
      if(t == null)
      return "";
       char temp = x.charAt(0);
       int cop = t.getValue().toString().compareTo(x);
      if(cop == 0)
      return "";
      else{
      int div = t.getFreq()/2;
      int freq = Integer.parseInt(""+map.get(temp));
         if(freq == div){
            if(cop>0)
            return "0"+scheme(t.getLeft(), x, map);
            else
            return "1"+scheme(t.getRight(), x, map);
         }
         else if(freq < div)
            return "0"+scheme(t.getLeft(), x, map);
         else
            return "1"+scheme(t.getRight(), x, map);
      }
   }
   }
   
      


	/*
	  * This node stores two values.  
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private Object value; 
   private int freq;
   private HuffmanTreeNode left, right;
   
   public HuffmanTreeNode(Object initValue, int initfreq)
   { 
      value = initValue;
      freq = initfreq;
      left = null; 
      right = null; 
   }
   
   public HuffmanTreeNode(Object initValue, int initfreq, HuffmanTreeNode initLeft, HuffmanTreeNode initRight)
   { 
      value = initValue; 
      freq = initfreq;
      left = initLeft; 
      right = initRight; 
   }
   
   
   public Object getValue()
   { 
      return value; 
   }
   
   public HuffmanTreeNode getLeft() 
   { 
      return left; 
   }
   
   public HuffmanTreeNode getRight() 
   { 
      return right; 
   }
   public int getFreq(){
      return freq;
   }
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(HuffmanTreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(HuffmanTreeNode theNewRight)
   { 
      right = theNewRight;
   }
   public int compareTo(HuffmanTreeNode other){
      if(freq < other.getFreq())
         return -1;
      else if(freq > other.getFreq())
         return 1;
      return 0;
   }
}
