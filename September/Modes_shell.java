	//Name:
	//Date:
   public class Modes_shell
   {
      public static void main(String[] args)
      {
         int[] tally = {0,0,10,5,10,0,7,1,0,6,0,10,3,0,0,1};
         display(tally);
         int[] modes = calculateModes(tally);
         display(modes);
         int sum = 0;
         for(int k = 0; k < tally.length; k++)
            sum += tally[k];
         System.out.println("kth \tindex"); 
         for(int k = 1; k <= sum; k++)
            System.out.println(k + "\t\t" + kthDataValue(tally, k));
      }
      public static int[] calculateModes(int[] tally)
      {
         int cnt = 0;
         int cnt2 = 0;
         int max = findMax(tally);
         for(int n = 0; n<tally.length; n++)
         if(tally[n] == max)
         cnt++;
         int[] modes = new int[cnt];
         for(int m = 0; m<tally.length; m++)
         if(tally[m]==max){
         modes[cnt2] = m;
         cnt2++;
         }
         return modes;
      }
      public static int kthDataValue(int[] tally, int k)
      {
         int kval = 0;
         while(k>0){
         k-= tally[kval];
         kval++;
         }
         return kval-1;
      }
      public static int findMax(int[] nums)
      {
         int pos = 0;
         for(int k = 1; k < nums.length; k++)
            if(nums[k] > nums[pos])
               pos = k;
         return nums[pos];
      }
      public static void display(int[] args)
      {
         for(int k = 0; k < args.length; k++)
            System.out.print(args[k] + " ");
         System.out.println();
         System.out.println();
      }
   }
