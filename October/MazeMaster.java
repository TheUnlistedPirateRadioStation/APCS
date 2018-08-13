//name:    date:
import java.util.*;
import java.io.*;
public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename: ");
      Maze m = new Maze(sc.next());
      m.display();
      m.solve();
      m.display();  
   } 
}
 

class Maze
{
   private final char wall = 'W';
   private final char path = '.';
   private final char start = 'S';
   private final char exit = 'E';
   private final char step = '*';
   private char[][] maze;
   private int startRow, startCol;
   private boolean S_Exists=false, E_Exists=false;

   public Maze(String filename)  
   {
      Scanner infile = null;
      try{
         infile = new Scanner(new File(filename + ".txt"));
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found");
         System.exit(0);
      }
      //read the file
      maze = new char[infile.nextInt()][infile.nextInt()];
      for(int r=0; r<maze.length; r++){
         char[] line = infile.next().toCharArray();
         for(int c=0; c<maze[0].length; c++){
            maze[r][c] = line[c];
            if(maze[r][c] == 'S'){
            startRow = r;
            startCol = c;
            }
            
         }
      }
   
   }
   
   public void display()
   {
      for(int x=0; x<maze.length; x++){
         for(int y=0; y<maze[0].length; y++)
            System.out.print(maze[x][y]);
         System.out.println(); 
      }   
   System.out.println();
   }
   public void solve()
   {
      /*  1  */
      //solveAndMark(startRow, startCol);
      
      /*  2  */
      //int count = solveAndCount(startRow, startCol);
      //System.out.println("Number of steps = " + count);
      
      /*  3  */
      markThePath(startRow, startCol);
      
      /*  4  */
      //if( !markThePath(startRow, startCol) )
        //System.out.println("No solution");   
              
      /*  5  */
     //markThePathAndCount(startRow, startCol, 0);
      
   }
   public void solveAndMark(int r, int c)
   {
      if(r<0||c<0||r>=maze.length||c>=maze[0].length)
         System.out.print("");
      
       else if(maze[r][c]=='W')
         System.out.print("");
      
       else if(maze[r][c]=='*')
         System.out.print("");
   
      else if(maze[r][c]=='E')
         System.out.println("Solved");
      
      else{
         maze[r][c]='*';
         solveAndMark(r+1, c);
         solveAndMark(r-1, c);
         solveAndMark(r, c+1);
         solveAndMark(r, c-1);
      }
   }
        
   public int solveAndCount(int r, int c)
   {
      if(r<0||c<0||r>=maze.length||c>=maze[0].length)
         return 0;
      
       else if(maze[r][c]=='W')
        return 0;
      
       else if(maze[r][c]=='*')
         return 0;
   
      else if(maze[r][c]=='E')
         return 0;
      
      else{
         maze[r][c]='*';
         return 1+solveAndCount(r+1, c)+
          +solveAndCount(r-1, c)+
          +solveAndCount(r, c+1)+
          +solveAndCount(r, c-1);
      }

   }

   public boolean markThePath(int r, int c)
   {
        if(r<0||c<0||r>=maze.length||c>=maze[0].length)
         return false;
         
         else if(maze[r][c]=='E')
         return true;
      
       else if(maze[r][c]=='W')
        return false;
      
       else if(maze[r][c]=='*')
         return false;
   
      
      else{
         maze[r][c]='*';
         if(markThePath(r+1, c)==true)
         return true;
         if(markThePath(r-1, c)==true)
         return true;
         if(markThePath(r, c+1)==true)
         return true;
         if(markThePath(r, c-1)==true)
         return true;
         else{
         maze[r][c]='.';
         return false;
         }
      }
   }
   
   public boolean markThePathAndCount(int r, int c, int count)
   {
      if(r<0||c<0||r>=maze.length||c>=maze[0].length)
         return false;
         
         else if(maze[r][c]=='E')
         return true;
      
       else if(maze[r][c]=='W')
        return false;
      
       else if(maze[r][c]=='*')
         return false;
  
      else{
         maze[r][c]='*';
         if(markThePathAndCount(r+1, c, count+1)==true)
         return true;
         if(markThePathAndCount(r-1, c, count+1)==true)
         return true;
         if(markThePathAndCount(r, c+1, count+1)==true)
         return true;
         if(markThePathAndCount(r, c-1, count+1)==true)
         return true;
         else{
         maze[r][c]='.';
         return false;
         }
      }

   }
}
