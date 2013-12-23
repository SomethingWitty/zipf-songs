import java.io.*;
import java.util.*;

public class ZipfSongs
{ 
  public void doSomething() throws Exception
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String line = br.readLine();
    String[] splitArray = line.split(" ");
    
    int total = Integer.parseInt(splitArray[0]);
    int num = Integer.parseInt(splitArray[1]);
    
    PriorityQueue<Node> queue = new PriorityQueue<Node>(num);
    
    for(int i = total - 1; i >= 0; i--)
    {
      line = br.readLine();
      
      splitArray = line.split(" ");
      long count = Long.parseLong(splitArray[0]);
      String name = splitArray[1].trim();
      
      Node n = new Node(name, count * (total - i + 1) );
      
      if(queue.size()<num)
        queue.add(n);
      else
      {
        if(n.count>queue.peek().count)
        {
          queue.poll();
          queue.add(n);
        }
      }
    }
    
    Node[] output = new Node[num];
    int outCount = 0;
    
    Node head = queue.poll();
    
    while(head!=null)
    {
      output[outCount] = head;
      head = queue.poll();
      outCount++;
    }
    
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    
    for(int i = output.length-1; i>0; i--)
    {
      out.write(output[i].name);
      out.newLine();
    }
    out.write(output[0].name);
    //System.out.println(out);
    out.flush();
    //out.append(output[0].name);
    //System.out.print(out);
  }
  public static void main(String [] args) throws Exception
  {
    ZipfSongs s = new ZipfSongs();
    s.doSomething();
  }
  
  class Node implements Comparable<Node>
  {
    String name;
    long count;
    
    Node(String nameIn, long countIn)
    {
      name = nameIn;
      count = countIn;
    }
    public int compareTo(Node o1)
    {
      if(count-o1.count<0)
        return -1;
      if(count==o1.count)
        return 0;
      return 1;
    }
  }
}