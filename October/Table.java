import java.util.*;
public class Table{
   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      System.out.println("Input player1, player2, then removed tiles, 0 to quit");
      String s = sc.nextLine();
      String[] stop =s.split(" ");
      int one = Integer.parseInt(stop[0]);
      int onerow=0, onecol=0;
      int two = Integer.parseInt(stop[1]);
      int[] removed = new int[stop.length-1];
      for(int k=0; k<removed.length; k++){
         removed[k] = Integer.parseInt(stop[k]);
      }
      int[][] game = makeMat();
      int sk=0;
      while(sk<removed.length){
         for(int i=0;i<game[0].length;i++){
            for(int j=0;j<game.length;j++){
               if(game[i][j]==one){
                  onerow=i;
                  onecol=j;
                  game[i][j] = -2;
               }
               if(game[i][j]==two)
                  game[i][j] = -1;
               if(game[i][j]==removed[sk]){
                  game[i][j]=0;
               }  
            }
         }
         sk++;
      //display(game);
         //System.out.println(longpath(game, onerow, onecol));
      }
      display(game);
      System.out.println(longpath(game, onerow, onecol));

   }
   private static int[][] makeMat(){
      int sum = 0;
      int[][] mc = new int[7][7];
      for(int r=6; r>=0; r--){
         for(int c=0; c<7; c++){
            sum+=1;
            mc[r][c]+= sum; 
         }
      }
      return mc;
   }
   private static void display(int[][] mat){
      for(int r=0; r<7; r++){
         for(int c=0; c<7; c++){
            System.out.print(mat[r][c]+" ");
         }
         System.out.println();
      }
   
   }
   public static String longpath(int [][] a,int r, int c)
   {
      int cntup=0, cntdown=0, cntrt=0, cntlft=0, row=r, col=c;
      String up="", down="",left="", right="", largest;
      while(a[row][col]!=0&&a[row][col]!=-1&&row>0&&col>0&&row<a.length&&c<a[0].length){
         cntup++;
         row--;
         up = up + a[row][col] + " ";
      }
      while(a[row][col]!=0&&a[row][col]!=-1&&row>0&&col>0&&row<a.length&&c<a[0].length){
         cntdown++;
         row++;
         down = down + a[row][col] + " ";
      }
      while(a[row][col]!=0&&a[row][col]!=-1&&row>0&&col>0&&row<a.length&&c<a[0].length){
         cntrt++;
         col++;
         right = right + a[row][col] + " ";
      }
      while(a[row][col]!=0&&a[row][col]!=-1&&row>0&&col>0&&row<a.length&&c<a[0].length){
         cntlft++;
         col--;
         left = left + a[row][col] + " ";
      }
      switch(big(cntup, cntdown, cntlft, cntrt)){
         case "up": 
            largest= up;
            break;
         case "down": 
            largest= down;
            break;
         case "left": 
            largest= left;
            break;
         case "right": 
            largest= right;
            break;
         default: 
            largest= "Multiple";
      }
      return largest;
   }
   private static String big(int up, int down, int left, int right){
      if(up>down&&up>left&&up>right)
         return "up";
      else if(down>up&&down>left&&down>right)
         return "down";
      else if(left>down&&left>up&&left>right)
         return "left";
      else if(right>down&&right>left&&right>up)
         return "right";
      else 
         return "mult";
   }

}
//input player1 player2 then list and find longest list of move
