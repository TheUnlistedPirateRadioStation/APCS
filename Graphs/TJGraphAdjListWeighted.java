//name:   date: 
//for use with Graphs6: Dijkstra
//             Graphs7: Dijkstra with Cities

import java.io.*;
import java.util.*;

class Edge {
   public final wVertex target;
   public final double weight;
   
   public Edge(wVertex argTarget, double argWeight) {
      target = argTarget;
      weight = argWeight;
   }
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private wVertex previous;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   
   public wVertex(String s){
      name = s;
      adjacencies = new ArrayList<Edge>();
      previous = null;
   }
 
   public String toString(){
      return name;
   }
   
   public String getName(){
      return name;
   }
   
   public double getMinDistance(){
      return minDistance;
   }
   
   public void setMinDistance(double m){
      minDistance = m;
   }
   
   public ArrayList<Edge> getAdjacencies(){
      return adjacencies;
   }
   
   public int compareTo(wVertex other){
      return (int)(minDistance-other.getMinDistance());
   }
   public wVertex getPrevious(){
      return previous;
   }      
    
   public void setPrevious(wVertex v){
      previous = v;
   }
}

interface wVertexInterface 
{
   public String toString();
   
   public String getName();
   
   public double getMinDistance();
   
   public void setMinDistance(double m);
   
   public wVertex getPrevious();         //Graphs 7
    
   public void setPrevious(wVertex v);   //Graphs 7
   
   public ArrayList<Edge> getAdjacencies();
   
   public int compareTo(wVertex other);
}


public class TJGraphAdjListWeighted implements TJGraphAdjListWeightedInterface
{
   private ArrayList<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
  
   public List<wVertex> getVertices(){
      return vertices;
   }
   
   public wVertex getVertex(int i){
      return vertices.get(i);
   }
   
   public wVertex getVertex(String vertexName){
      if(nameToIndex.get(vertexName) == null)
         addVertex(vertexName);
      return getVertex(nameToIndex.get(vertexName));
   }
   
   public void addVertex(String v){
      if(!nameToIndex.containsKey(v)){
         nameToIndex.put(v, vertices.size());
         vertices.add(new wVertex(v));
      }
   }
   
   public void addEdge(String source, String target, double weight){
      getVertex(source).getAdjacencies().add(new Edge(getVertex(target), weight));
   }
     
   public void minimumWeightPath(String vertexName){
      wVertex source = getVertex(vertexName);
      ArrayList<wVertex> visited = new ArrayList<wVertex>();
      source.setMinDistance(0.0);
      ArrayList<Edge> neigh;
      PriorityQueue<wVertex> pq = new PriorityQueue<wVertex>();
      pq.add(source);
      while(!pq.isEmpty()){
         wVertex temp = pq.poll();
         if(!visited.contains(temp)){
            neigh = temp.getAdjacencies();
            for(Edge e: neigh){
               double path = e.weight + temp.getMinDistance();
               if(path < e.target.getMinDistance()){
                  e.target.setMinDistance(path);
                  e.target.setPrevious(temp);
               }
               visited.add(temp);
               pq.add(e.target);
            }
         }
      }
      
   }
   public List<wVertex> getShortestPathTo(String vertexName){
      Stack<wVertex> revpath = new Stack<wVertex>();
      ArrayList<wVertex> path = new ArrayList<wVertex>();
      wVertex temp = getVertex(vertexName);
      while(temp.getMinDistance() != 0 && temp.getPrevious() != null)
      {
         temp = temp.getPrevious();
         revpath.push(temp);
      }
      while(!revpath.isEmpty()){
         path.add(revpath.pop());
      }
      path.add(getVertex(vertexName));
      return path; 
   }
       
   public List<wVertex> getShortestPathTo(wVertex v){
      return getShortestPathTo(v.getName());   
   }
    
   public TJGraphAdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException {
      TJGraphAdjListWeighted g = new  TJGraphAdjListWeighted();
      Scanner infile = new Scanner(vertexNames);
      int length = infile.nextInt();
      for(int k=0; k<length; k++)
         g.addVertex(infile.next());
      infile = new Scanner(edgeListData);
      while(infile.hasNext()){
         g.addEdge(infile.next(), infile.next(), infile.nextInt());
      }
      return g;
   }
   
}    
interface TJGraphAdjListWeightedInterface 
{
   public List<wVertex> getVertices();
   
   public wVertex getVertex(int i);
   
   public wVertex getVertex(String vertexName);
   
   public void addVertex(String v);
   
   public void addEdge(String source, String target, double weight);
     
   public void minimumWeightPath(String vertexName);  //Dijkstra's
   
   /*  Graphs 7 */
   public List<wVertex> getShortestPathTo(String vertexName);
       
   public List<wVertex> getShortestPathTo(wVertex v);
    
   public TJGraphAdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException; 
 
}