// Name: 
// Date:

    public class MatrixRecreate
   {
       public static void main(String[] args)
      {
         int[][] matrix = create();
         int[] rowcount = new int[matrix.length];
         int[] colcount = new int[matrix[0].length];
         count(matrix, rowcount, colcount);
         display(matrix, rowcount, colcount);
         re_create(rowcount, colcount);
      }
       public static int[][] create()
      {
      int x = (int)(Math.random()*5+2);
      int y = (int)(Math.random()*5+2);
      int[][] mx = new int[x][y];
      for(int r=0; r<mx.length; r++)
        for(int c=0; c<mx[0].length;c++)
        mx[r][c] = ((int)(Math.random()*2+1))-1;
        
        
        return mx;

      }
       public static void count(int[][] matrix, int[] rowcount, int[] colcount)
      {
      int colcnt=0, rowcnt=0;
      for(int c=0; c<matrix[0].length;c++){
       for(int r=0; r<matrix.length; r++){
       colcnt+=matrix[r][c];
      }
      colcount[c] = colcnt;
      colcnt=0;
      }
       for(int r=0; r<matrix.length; r++){
        for(int c=0; c<matrix[0].length;c++){
       rowcnt+=matrix[r][c];
      }
      rowcount[r] = rowcnt;
      rowcnt=0;
      }
       
      
      }
       public static void display(int[][] matrix, int[] rowcount, int[] colcount)
      {
      System.out.print("    ");
      for(int k=0; k<colcount.length; k++)
      System.out.print(colcount[k]+" ");
      System.out.println();
      System.out.print("   ");
      for(int a=0; a<colcount.length*2; a++)
      System.out.print("-");
      System.out.println();
      
      for(int r=0; r<matrix.length; r++){
      System.out.print(rowcount[r]+" | ");
        for(int c=0; c<matrix[0].length;c++){
       System.out.print(matrix[r][c]+" ");
      }
      System.out.println();
      }
      System.out.println();

      }
       public static void re_create(int[] rowcount, int[] colcount)
      {
         int[][] remx = new int[rowcount.length][colcount.length];
      recur(remx, rowcount, colcount, 0, 0);

      }
       private static void recur(int[][] m, int[] rowcount, int[] colcount, int row, int col) //recursive helper method
      {
      
      if(m[0].length == col){                     //i.e. check if we have recurred through all of the columns
            if(compare(m, rowcount, colcount))    //base case: if new matrix works
            {
               display(m, rowcount, colcount);    //we're done!
               System.exit(0);
            }
         }
         else if(row == rowcount.length)
            recur(m,rowcount,colcount,0,col+1); 
         else {          
            m[row][col] = 1;
            recur(m,rowcount,colcount,row+1,col);
            m[row][col] = 0;
            recur(m,rowcount,colcount,row+1,col);
         }

      }
       private static boolean compare(int[][] m, int[] rowcount, int[] colcount)
      {
      int[] temprowcount = new int[rowcount.length];
      int[] tempcolcount = new int[colcount.length];
      boolean one = true;
      boolean two = true;
      boolean fin = false;
      count(m, temprowcount, tempcolcount);
      for(int a=0; a<temprowcount.length; a++){
      if(rowcount[a]!=temprowcount[a])
      one = false;
      }
      for(int b=0; b<tempcolcount.length; b++){
      if(colcount[b]!=tempcolcount[b])
      two = false;
      }
      if(one&&two)
      fin=true;
      return fin;

      }
   }
