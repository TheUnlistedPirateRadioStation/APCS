import java.util.*;
public class Luhn{
   public static void main(String[] args){
      Scanner infile = new Scanner(System.in);
      for(int x = 0; x < 5; x++){
         System.out.println("insert nums");
         String str = infile.next();
         System.out.println(checksum(str));
      }
      infile.close();
   }
   private static String checksum(String str){
      String[] nums = new String[str.length()];
      for(int k=0; k<nums.length; k++){
         nums[k] = ""+ str.charAt(k);
         }
      int sum1 = sum(nums);
      if(sum1 == Integer.parseInt(nums[nums.length-1]))
         return "VALID";
      else{
         for(int x = 0; x < nums.length - 1; x++){
            String ori = nums[x];
            nums[x] = "" + (Integer.parseInt(ori) + 1);
            if(sum(nums) == Integer.parseInt(nums[nums.length - 1]))
               return allah(nums);
            else{
               nums[x] = "" + (Integer.parseInt(ori) - 1);
               if(sum(nums) == Integer.parseInt(nums[nums.length - 1]))
                  return allah(nums);
            }
            nums[x] = ori;
         }
      }
      return "VALID"; 
   }
   private static String allah(String[] nums){
      String output = "";
      for(int k = 0; k < nums.length; k++)
         output += nums[k];
      return output;
   }
   
   private static int sum(String[] arr){
   String[] nums = new String[arr.length];
      for(int k=0; k<nums.length; k++){
         nums[k] = arr[k];
         }

      int sum = 0;
      for(int n= nums.length-2; n>=0; n-=2){
         nums[n] = ""+(Integer.parseInt(nums[n])*2);
         if(Integer.parseInt(nums[n]) > 9)
            nums[n] = ""+(Integer.parseInt(""+nums[n].charAt(0))+Integer.parseInt(""+nums[n].charAt(1)));
      }
      for(int n=0; n<nums.length-1; n++)
         sum += Integer.parseInt(nums[n]);
      sum = (sum*9)%10;
      return sum;
   }
}