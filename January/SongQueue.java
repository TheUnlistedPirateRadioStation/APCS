///name:    date:
//first program on queues.
import java.io.*;
import java.util.*;
public class SongQueue
{
   private static Scanner infile;
   private static Queue<String> songQueue;
   
   public static void main(String[] args) throws Exception
   { 
      fillPlayList();
      printSongList();
      infile = new Scanner(System.in);
      String prompt = "\tAdd song (A), Play song (P), Delete song (D), Quit (Q):  ";
      System.out.print(prompt);
      String str = infile.next().toUpperCase();
      while(!str.equals("Q"))
      { 
         processRequest( str );
         System.out.print(prompt);
         str = infile.next().toUpperCase();;
      } 
      System.out.println();
      System.out.println("No more music for you today.  Goodbye!");
      infile.close();
   }
   public static void fillPlayList()throws IOException
   {
      infile = new Scanner(new File("songs.txt"));
      songQueue = new LinkedList();
      while(infile.hasNext()){
         String s = infile.nextLine();
         String sub = s.substring(0, s.
            indexOf("-")-1);
         songQueue.add(sub);
      }
   }
   public static void processRequest(String str)
   {
      infile = new Scanner(System.in);
         if(str.equals("A")){
            System.out.print("Song to add? ");
            add(infile.nextLine());
            }
         else if(str.equals("P")){
            System.out.print("Now playing: ");
            play();
            }
         else if(str.equals("D")){
            System.out.print("Enter song to delete (exact match): ");
            delete(infile.nextLine());
            }
         else
            System.out.println("Invalid Input");
      
   }
   public static void add(String newSong)
   {
      songQueue.add(newSong);
      printSongList();
   }
   public static void play()
   {
      System.out.println(songQueue.poll());
      printSongList();
   }
   public static void delete(String song)//need to edit //put output on handout
   {
      Queue<String> delete = new LinkedList();
      int songsize = songQueue.size();
      while(songQueue.peek()!= null){
         if(!songQueue.peek().equals(song))
            delete.add(songQueue.poll());
         else
            songQueue.poll();
      }
      if(songsize == delete.size())
         System.out.println("Song not Found");
      songQueue = delete;
      printSongList();
   }
   public static void printSongList()
   {
      System.out.println(songQueue);
   }
}