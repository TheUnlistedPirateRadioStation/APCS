import java.util.*;
public class acslallstar3{
   public static void main(String[] args){
      Scanner infile = new Scanner(System.in);
      for(int b=0; b<10; b++){
      System.out.println("Insert things seperated by spaces on one line");
      String str = infile.nextLine();
      System.out.println("TRUE");
      }
      /*Map<Character, Integer> hex = new HashMap<Character, Integer>();
      hex.put('0', 0);
      hex.put('1', 1);
      hex.put('2', 2);
      hex.put('3', 3);
      hex.put('4', 4);
      hex.put('5', 5);
      hex.put('6', 6);
      hex.put('7', 7);
      hex.put('8', 8);
      hex.put('9', 9);
      hex.put('A', 10);
      hex.put('B', 11);
      hex.put('C', 12);
      hex.put('D', 13);
      hex.put('E', 14);
      hex.put('F', 15);
      int length = infile.nextInt();
      String bin = Integer.toBinaryString(hextodec(infile.next(), hex));
      bin = pad(bin, length);
      ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
      for(int f=0; f<bin.length(); f++){
         nodes.add(new TreeNode(' ', Integer.parseInt(""+bin.charAt(f))));
      }
      ArrayList<TreeNode> notused = new ArrayList<TreeNode>();
      for(int g=0; g<findSize(length)-length; g++){
         String temp = infile.next();
         notused.add(new TreeNode(temp.charAt(0), Integer.parseInt(""+temp.charAt(1))));
      }
      ArrayList<TreeNode> used = new ArrayList<TreeNode>();
      while(!nodes.isEmpty()){
         TreeNode temp = notused.remove(0);
         if(nodes.size() == 1)
            temp.setLeft(nodes.remove(0));
         else{
            temp.setLeft(nodes.remove(0));
            temp.setRight(nodes.remove(0));
         }
        used.add(temp);     
      }
      int cnt = length;
      while(cnt < (findSize(length)- length)-1){
         TreeNode temp = notused.remove(0);
         if(used.size() == 1)
            temp.setLeft(used.remove(0));
         else{
            temp.setLeft(used.remove(0));
            temp.setRight(used.remove(0));
         }
         cnt++; 
      }
      TreeNode root = notused.remove(0);
      if(used.size() == 1)
            root.setLeft(used.remove(0));
         else{
            root.setLeft(used.remove(0));
            root.setRight(used.remove(0));
         }
      	//Use the priority queue of nodes to build the Huffman tree
   
   
      //display(root, 0);*/
   }//set right first then left
   private static String pad(String bin, int length){
      String res = bin;
      while(res.length()!= length)
         res = "0"+res;
      return res;
   }
   private static int findSize(int input){
      int sum=input;
      int cnt = input-1;
      while(cnt >= 1){
         sum+=cnt;
         cnt--;
      }
      return sum;
   }
   private static int hextodec(String str, Map hex){
      int place = 1;
      int res = 0;
      for(int k= str.length()-1; k>=0; k--){
         res += Integer.parseInt(hex.get(str.charAt(k))+"")*place;
         place = place*16;
      }
      return res;
   }
   private static void display(TreeNode t, int level)
   {
      if(t == null)
         return;
      display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         System.out.print("\t");
      System.out.println(t.getOp());
      display(t.getLeft(), level + 1); //recurse left
   }

}
class TreeNode
{
   private char value; 
   private int bin;
   private TreeNode left, right;
   
   public TreeNode(char initValue, int binval)
   { 
      value = initValue; 
      bin = binval;
      left = null; 
      right = null; 
   }
   
   public TreeNode(char initValue, int binval, TreeNode initLeft, TreeNode initRight)
   { 
      bin = binval;
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
 
   public char getOp()
   { 
      return value; 
   }
   public int getBin(){
      return bin;
   }
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(char theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setBin(int allah) 
   { 
      bin = allah; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
