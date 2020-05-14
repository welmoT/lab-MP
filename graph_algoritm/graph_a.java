import java.util.*;
import java.io.*;

class top 
{
	int top1, top2;
	int weight;
	top(int top1,int top2,int weight)
	{
		this.top1 = top1;
		this.top2 = top2;
		this.weight = weight;
	}
}
public class graph_a 
{
	public static int INF = Integer.MAX_VALUE;
	int count_ver, count_ver1 = 4,count_edge; //count_edge - количество ребер count_ver - количество вершин
	LinkedList <top> [] list_adj; // список смежных вершин
	int[][] graph;// матрица смежностей
	boolean directed; // для того чтобы определить направленность.
	//boolean[] temp = new boolean[4]; 
	boolean[] temp;
	graph_a (int [][] graph)
	{
		this.graph = graph;
	}
	graph_a (int count, int count_edge, String s_dir)
    {
		this.count_ver = count;
		this.count_edge = count_edge;
		//count_ver1 = count;
        list_adj = new LinkedList[count_ver];
		for(int i=0; i < count_ver; i++) 
			list_adj[i] = new LinkedList();
		if(s_dir=="directed") 
			directed=true;  
		else directed=false; 
		temp = new boolean[count_ver];
		
    }
	void create_graph(int top1, int top2, int weight)
	{
		top new_ft = new top(top1,top2,weight);
		if (directed==false)
		{
			list_adj[top1].add(new_ft);
			top new_tf = new top(top2,top1,weight);										
 			list_adj[top2].add(new_tf);
		}
			
		else 
		{
			//System.out.println(new_ft.top1+" - "+new_ft.top2);
			list_adj[top1].add(new_ft);
		}
	}

	public void Print()
    { 
		System.out.println("adjacent vertex graph");
		for(int i=0; i < count_ver; i++)
        {
            System.out.print("["+i+"]:");
            for(top C: list_adj[i])
            {
               System.out.print("    "+C.top2 +" "+"("+C.top1+","+C.top2+","+C.weight+")"); 
            }
            System.out.println();
        }
	}
	//boolean [] temp;
	//boolean [] temp = new boolean [count_ver];


	public void DFS (int index)// поиск в глубину
	{
		//System.out.println("Depth-first search");
		temp[index] = true;
		System.out.println(index);
		 ListIterator<top> i = list_adj[index].listIterator();
        while (i.hasNext()) 
        {
            top tmp = i.next();
            if (temp[tmp.top2] == false)
            DFS(tmp.top2);
        }
	}
	public void BFS (int index )// поиск в ширину
	{
		System.out.println("breadth-first search");
		boolean [] tmp = new boolean[count_ver];
		LinkedList<Integer> queue = new LinkedList();
		queue.add(index);
		tmp[index] = true;
		while (queue.size() != 0)
        {
           index = queue.poll();
           System.out.print(index+" ");
		   ListIterator<top> i = list_adj[index].listIterator();
           while (i.hasNext())
           {
               top tmp1 = i.next();
               if (!tmp[tmp1.top2])
               {
                   tmp[tmp1.top2] = true;
                   queue.add(tmp1.top2);
               }

            }
         }
	}
	public void dijkstra(int start) 
	{
    System.out.println("algoritm Dijkstra");
    boolean[] used = new boolean [count_ver1]; // массив пометок
    int[] dist = new int [count_ver1]; // массив расстояния. dist[v] = минимальное_расстояние(start, v)
    for(int i = 0; i < count_ver1; i++)
      dist[i] = INF;
    dist[start] = 0;
    for (;;) 
    {
      int v = -1;
      for (int i = 0; i < count_ver1; i++) 
        if (!used[i] && dist[i] < INF && (v == -1 || dist[v] > dist[i])) 
          v = i;
        if (v == -1) break; 
          used[v] = true; 
      for (int i = 0; i < count_ver1; i++)
        if (!used[i] && graph[v][i] < INF) 
          dist[i] = Math.min(dist[i], dist[v] + graph[v][i]); 
    }
    for(int i = 0; i< count_ver1; i++)
      System.out.println("for "+start+" "+"to " +i+": "+" weight "+dist[i]);
  }
  public void Prim() 
  {
    System.out.println("algoritm Prim");
    int  temp1, temp2;
    boolean[] used = new boolean[count_ver1];
    int[] parent = new int[count_ver1];
    used[0] = true;
    for ( int k = 1; k < count_ver1; k++ )
    {
      used[k] = false;
    }
    parent[0] = 0;
    for (int k = 1; k < count_ver1; k++)
    {
      temp1 = temp2 = 0;
      for ( int i = 0; i < count_ver1; i++ )
        for ( int j = 0; j < count_ver1; j++ )
        {
          if ( used[i] && !used[j] &&graph[i][j] < graph[temp1][temp2])
          {
            temp1 = i;
            temp2 = j;
          }
        }
        //System.out.println("Min cost edge: (" + + temp1 + "," + + temp2+ ")" +"cost = " + graph[temp1][temp2]);
      parent[temp2] = temp1;
      used[temp2] = true;
    }
    int[] a = parent;
   	for ( int i = 0; i < count_ver1; i++ )
      System.out.println( "for: "+a[i] + " - "+"to "+ i );
  }

  public void kruskal()//https://algorithms.tutorialhorizon.com/kruskals-algorithm-minimum-spanning-tree-mst-complete-java-implementation/
  {
    System.out.println("algoritm Kruskal");
    PriorityQueue<top> queue = new PriorityQueue<>(count_edge, Comparator.comparingInt(c -> c.weight));
    for(int i=0; i < count_ver1; i++)
    {
      for(top C: list_adj[i])
      {
        queue.add(C);
      }
    }
    int [] parent = new int[count_ver1];
    parent(parent);
		ArrayList<top> mst = new ArrayList<>();
    int index = 0;
    while(index < count_ver1 - 1)
    {
      top edge = queue.remove();
      int x_set = find(parent, edge.top1);
      int y_set = find(parent, edge.top2);
      if(x_set != y_set)
      {
        mst.add(edge);
        index++;
        union(parent,x_set,y_set);
      }
    }
      printGraph(mst);
  }

  public void parent(int [] parent)
  {
    for (int i = 0; i < count_ver1 ; i++) 
    {
      parent[i] = i;
    }
  }
  public int find(int [] parent, int top)
  {
    if(parent[top] != top)
      return find(parent, parent[top]);;
    return top;
  }
  public void union(int [] parent, int x, int y)
  {
    int x_set_parent = find(parent, x);
    int y_set_parent = find(parent, y);
    parent[y_set_parent] = x_set_parent;
  }
  public void printGraph(ArrayList<top> edgeList)
  {
    for (int i = 0; i <edgeList.size() ; i++) 
    {
      top edge = edgeList.get(i);
      System.out.println("Edge-" + i + " for: " + edge.top1 +" to: " + edge.top2 +" weight: " + edge.weight);
    }
  }
 		public void Floid ()
 		{
      System.out.println("Floid algoritm");
 			for(int k = 0; k < count_ver1; k++)
 			{
 				for (int i = 0;i< count_ver1; i++) 
 				{
 					for(int j = 0; j<count_ver1;j++)
 					{
 						graph[i][j] = Math.min(graph[i][j],graph[i][k]+graph[k][j]);
 					}
 				}
 			}
      for (int i = 0;i < graph.length;i++ ) 
      {
        graph[i][i] = 0;
      }
      System.out.println(Arrays.deepToString(graph));
 		}
}
 