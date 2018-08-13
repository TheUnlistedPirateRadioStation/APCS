   //name:
   //date:
   
import java.util.Scanner;
import java.io.*;
public class AreaFill
{
   public static char[][] grid = null;
   
   public static void main(String[] args) throws FileNotFoundException
   {
      Scanner sc = new Scanner(System.in);
      
      System.out.print("Filename(-1 to quit): ");
      String filename = sc.next();
      grid = read(filename);
      display(grid);
      System.out.print("\nEnter ROW COL to fill from: ");
      int row = sc.nextInt();
      int col = sc.nextInt(); 
         //fill(grid, row, col, grid[row][col]);
         //display(grid);
         //  Extension
      int count = fillAndCount(grid, row, col, grid[row][col]);
      display(grid);
      System.out.println("count = " + count);
      System.out.println();
      sc.close();
      
   }
   public static char[][] read(String filename)throws FileNotFoundException
   {
      char[][] matrix;
      Scanner infile = new Scanner(new File(filename));
      matrix = new char[infile.nextInt()][infile.nextInt()];
      for(int r=0; r<matrix.length; r++){
         char[] line = infile.next().toCharArray();
         for(int c=0; c<matrix[0].length; c++){
            matrix[r][c] = line[c];
         }
      }
      return matrix;
   }
      
   public static void display(char[][] g)
   {    
      for(int x=0; x<g.length; x++){
         for(int y=0; y<g[0].length; y++)
            System.out.print(g[x][y]);
         System.out.println(); 
      }   
   }
   public static void fill(char[][] g, int r, int c, char ch) //recursive method
   {     
      if(r<0 || r>g.length-1 || c<0 || c>g[0].length-1)
         System.out.print("");
      else{
         if(g[r][c] == ch){
            g[r][c] = '*';
            fill(g, r+1, c, ch);
            fill(g, r-1, c, ch);
            fill(g, r, c+1, ch);
            fill(g, r, c-1, ch);
         }
      }
   }
   	
   	// Extension-- count and return the number of asterisks
   public static int fillAndCount(char[][] g, int r, int c, char ch)
   {
      if(r<0 || r>g.length-1 || c<0 || c>g[0].length-1)
         return 0;
      else{
         if(g[r][c] == ch){
            g[r][c] = '*';
            return 1+fillAndCount(g, r+1, c, ch)+
            fillAndCount(g, r-1, c, ch)+
            fillAndCount(g, r, c+1, ch)+
            fillAndCount(g, r, c-1, ch);
         
         }
      }
      return 0;
   }
}