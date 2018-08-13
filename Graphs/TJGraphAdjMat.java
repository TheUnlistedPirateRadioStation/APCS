//name:   date:   
//for use with Graphs0: Intro
//             Graphs1: Wardhall
//             Graphs2: Floyd
import java.util.*;
import java.io.*;

public class TJGraphAdjMat implements AdjacencyMatrix ,Warshall ,Floyd
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   
     
   public TJGraphAdjMat(int size){
      vertices = new TreeMap<String, Integer>();
      grid = new int[size][size];
      for(int r=0; r<grid.length; r++)
         for(int c=0; c<grid[0].length; c++)
            grid[r][c] = 0;
   } 
   public void addEdge(int source, int target){
      grid[source][target] = 1;
   }
   public void removeEdge(int source, int target){
      grid[source][target] = 0;
   }
   public boolean isEdge(int from, int to){
      return grid[from][to] > 0 && grid[from][to] < 9999;
   }
   public void displayGrid(){
      for(int r=0; r<grid.length; r++){
         for(int c=0; c<grid[0].length; c++){
            System.out.print(grid[r][c]+" ");
         }
         System.out.println();
      }
   }
   public int edgeCount(){
      int cnt = 0;
      for(int r=0; r<grid.length; r++)
         for(int c=0; c<grid[0].length; c++)
            if(isEdge(r, c))
               cnt++;
      return cnt;
   }
   public List<Integer> getNeighbors(int source){
      List<Integer> neigh = new ArrayList<Integer>();
      for(int c=0; c<grid[0].length; c++){
         if(grid[source][c] == 1 && c != source)
            neigh.add(c);
      }
      return neigh;
   }
   
   public boolean isEdge(String from, String to){
      return isEdge(vertices.get(from), vertices.get(to));
   }
   public Map<String, Integer> getVertices(){
      return vertices;
   }
   public void readNames(String fileName) throws FileNotFoundException{
      Scanner infile = new Scanner(new File(fileName));
      int length = infile.nextInt();
      for(int k=0; k<length; k++)
         vertices.put(infile.next(), k);
   }
   public void readGrid(String fileName) throws FileNotFoundException{
      Scanner infile = new Scanner(new File(fileName));
      int length = infile.nextInt();
      for(int r=0; r<length; r++)
         for(int c=0; c<length; c++)
            grid[r][c] = infile.nextInt();
   }
   public void displayVertices(){
      for(String str: vertices.keySet())
         System.out.println(vertices.get(str)+"-"+str);
      System.out.println();  
   }   
   public void allPairsReachability(){
      for(int k=0; k<grid.length; k++){
         for(int r=0; r<grid.length; r++){
            for(int c=0; c<grid[0].length; c++){
               if(isEdge (r, k) && isEdge(k, c))
                  addEdge(r, c);
            }
         }
      }
   }
   public int getCost(int from, int to){
      return grid[from][to];
   }
   public int getCost(String from, String to){
      return getCost(vertices.get(from), vertices.get(to));
   }
   public void allPairsWeighted(){
   int temp = 0;
   for(int f=0; f<2; f++){
      for(int k=0; k<grid.length; k++){
         for(int r=0; r<grid.length; r++){
            for(int c=0; c<grid[0].length; c++){
               if(isEdge(r, k) && isEdge(k, c)){
                  temp = grid[r][k]+grid[k][c];
                  if(temp < grid[r][c])
                  grid[r][c] = temp;
                  
            }
         }
      }
   }
   }
}

interface AdjacencyMatrix
{
   public void addEdge(int source, int target);
   public void removeEdge(int source, int target);
   public boolean isEdge(int from, int to);
   public void displayGrid();
   public int edgeCount();
   public List<Integer> getNeighbors(int source);
   
  /**********  User-friendly    **************/
   public boolean isEdge(String from, String to);
   public Map<String, Integer> getVertices();     
   public void readNames(String fileName) throws FileNotFoundException;
   public void readGrid(String fileName) throws FileNotFoundException;
   public void displayVertices();
 /************* end  User-friendly   **************/
}

interface Warshall
{
   public void allPairsReachability();
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted(); 
}
