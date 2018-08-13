//name:    date:
//for use with Graphs3: EdgeList
//             Graphs4: DFS-BFS
//             Graphs5: EdgeListCities

import java.io.*;
import java.util.*;
/*********************  Graphs 3:  EdgeList *******************************/
interface VertexInterface
{
   public String toString();     //just return the name
   public String getName();
   public ArrayList<Vertex> getAdjacencies();
   public void addEdge(Vertex v);
}  

interface TJGraphAdjListInterface 
{ 
   public List<Vertex> getVertices();
   public Vertex getVertex(int i) ;
   public Vertex getVertex(String vertexName);
   public Map<String, Integer> getVertexMap();
   public void addVertex(String v);
   public void addEdge(String source, String target);
   public String toString();
  
  
   /*********************Graphs 4:  DFS and BFS ****************************/
   public List<Vertex> depthFirstSearch(String name);
   public  List<Vertex> breadthFirstSearch(String name);
    //public  List<Vertex> depthFirstRecur(String name);


   /****************Graphs 5:  EdgeList with Cities  *********/
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException;
   public int edgeCount();
   public boolean isReachable(String source, String target);
   public boolean isConnected();
}
/***********************************************************/
class Vertex implements VertexInterface 
{
   private final String name;
   private ArrayList<Vertex> adjacencies;
  
   public Vertex(String s){
      name = s;
      adjacencies = new ArrayList<Vertex>();
   }
   public String toString(){
      return name;
   }     
   public String getName(){
      return name;
   }
   public ArrayList<Vertex> getAdjacencies(){
      return adjacencies;
   }
   public void addEdge(Vertex v){
      adjacencies.add(v);
   }
  
}  
/*******************************************************/
public class TJGraphAdjList implements TJGraphAdjListInterface
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
  
   public List<Vertex> getVertices(){
      return vertices;
   }
   public Vertex getVertex(int i){
      return vertices.get(i);
   }
   public Vertex getVertex(String vertexName){
      if(nameToIndex.get(vertexName) == null)
         addVertex(vertexName);
      return getVertex(nameToIndex.get(vertexName));
   }
   public Map<String, Integer> getVertexMap(){
      return nameToIndex;
   }
   public void addVertex(String v){
      if(!nameToIndex.containsKey(v)){
         nameToIndex.put(v, vertices.size());
         vertices.add(new Vertex(v));
      }
   }
   public void addEdge(String source, String target){
      getVertex(source).addEdge(getVertex(target));
   }
   public String toString(){
      String str = "";
      for(Vertex v: vertices){
         str+= v.getName()+" "+v.getAdjacencies().toString()+"\n";
      }
      return str;
   }
   public List<Vertex> depthFirstSearch(String name){
      return depthFirstSearch(getVertex(name));
   }
   private List<Vertex> depthFirstSearch(Vertex v){
      Vertex use = v;
      List<Vertex> reach = new ArrayList<Vertex>();
      Stack<Vertex> stor = new Stack<Vertex>();
      stor.push(use);
      while(!stor.isEmpty()){
         Vertex temp = stor.pop();
         if(!reach.contains(temp))
            reach.add(temp);
         List<Vertex> templist = temp.getAdjacencies();
         for(Vertex vx: templist){
            if(!reach.contains(vx))
               stor.push(vx);
         }
      }
      return reach;
        
   }
   public List<Vertex> breadthFirstSearch(String name){
      return breadthFirstSearch(getVertex(name));
   }
   private List<Vertex> breadthFirstSearch(Vertex v){
      Vertex use = v;
      List<Vertex> reach = new ArrayList<Vertex>();
      Queue<Vertex> queue = new LinkedList<Vertex>();
      queue.add(use);
      while(!queue.isEmpty()){
         Vertex temp = queue.remove();
         if(!reach.contains(temp))
            reach.add(temp);
         List<Vertex> templist = temp.getAdjacencies();
         for(Vertex vx: templist){
            if(!reach.contains(vx))
               queue.add(vx);
         }
      }
      return reach;    
   }
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException{
      Scanner infile = new Scanner(new File(fileName));
      while(infile.hasNext()){
         addEdge(infile.next(), infile.next());
      }
   }
   public int edgeCount(){
   int cnt = 0;
    for(Vertex one: vertices)
    for(Vertex two: one.getAdjacencies())
    cnt++;
    return cnt;
   }
   public boolean isReachable(String source, String target){
      List<Vertex> reach = depthFirstSearch(source);
      return reach.contains(getVertex(target));
   }
   public boolean isConnected(){
    return depthFirstSearch(vertices.get(0)).size() == vertices.size();
   }
}


