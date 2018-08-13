// Name:              Date:
import java.util.Scanner;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode root = new TreeNode(null);
      TreeNode temp;
      while(huffLines.hasNext()){
         temp = root;
         String line = huffLines.nextLine();
         char val = line.charAt(0);
         for(int k=1; k<line.length(); k++){
            if(line.charAt(k) == '0'){
               if(temp.getLeft() == null)
                  temp.setLeft(new TreeNode(null));
               temp = temp.getLeft();
            }
            else if(line.charAt(k) == '1'){
               if(temp.getRight() == null)
                  temp.setRight(new TreeNode(null));
               temp = temp.getRight();
            }
         }
         temp.setValue(val);
      }
      return root;
   }
   public static String dehuff(String text, TreeNode root)
   {
      String res = "";
      int check = 0;
      TreeNode temp = root;
      while(check < text.length()){
         if(temp.getValue() != null){
            res+= temp.getValue();
            temp = root;
         }
         else if(text.charAt(check) == '0'){
            check++;
            temp = temp.getLeft();
         }
         else if(text.charAt(check) == '1'){
            check++;
            temp = temp.getRight();
         }
      }
      res+= temp.getValue();
      return res;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
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
