import java.util.*;
import java.io.*;
import java.util.Scanner;

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
	int count_ver, count_ver1 = 4; //count_edge - количество ребер count_ver - количество вершин
	LinkedList <top> [] list_adj; // список смежных вершин
	int[][] graph;// матрица смежностей
	boolean directed; // для того чтобы определить направленность. в зависимости от того направленный или нет список смежности будет разный
	graph_a (int [][] graph)
	{
		this.graph = graph;
	}
	graph_a (int count/*, int count_edge*/, String s_dir)
    {
		this.count_ver = count;
		//this.count_ver1 = count;
		 //System.out.println("h " + count_ver);
       // this.count_edge = count_edge ; 
		//this.current_ce = 0; //Это нужно для тогочтобы чмотреть каково текущее число ребер в графе. в тесте ты указываешь какое 
							//число вершин и ребер у тебя будет в графе, если после create_graph current_ce получилось больше чем заказывали, вызвать эксепшн(я не делала)
		//el_array = new top[count_el]; //для отслеживания списка ребер (я использовала для алгоритма крускала)
        list_adj = new LinkedList[count_ver];

		for(int i=0; i < count_ver; i++) list_adj[i] = new LinkedList();
			
		if(s_dir=="directed") directed=true;  else directed=false; //weighted в принципе не обязателен, алгоритмы то у нас все равно требуют взвешенный граф, но для полноты можно использовать
		
    }
	boolean[] temp = new boolean[4]; 
	void create_graph(int top1, int top2, int weight)
	{
			top new_ft = new top(top1,top2,weight);

			if (directed==false)
			{
				list_adj[top1].add(new_ft);
				top new_tf = new top(top2,top1,weight);//Здесь была ошибка. строчки 49-50 нужны для того чтобы ... если граф направленный то запись добавить(0, 1, 5) - говорит о том, что
														//Нужно сделать ссылку с 0 до 1. Если граф ненаправленный - слыка будет не только с 0 до 1, но и с 1 до 0, причем такого же веса
 				list_adj[top2].add(new_tf);
			}
			
			else {System.out.println(new_ft.top1+" - "+new_ft.top2);list_adj[top1].add(new_ft);}
			//el_array [current_ce] = new_ft;
		//	current_ce++;
	}

	public void Print()
    { 
		for(int i=0; i < count_ver; i++)
        {
            System.out.print("["+i+"]:");
            for(top C: list_adj[i])
            {
               System.out.print("  "+C.top2 +" "+"("+C.top1+","+C.top2+","+C.weight+")"); //в скобках записывается полное имя ребра с весом. необязательно. классически только вершины указывать
            }
            System.out.println();
        }
	}
	//boolean [] temp;
	//boolean [] temp = new boolean [count_ver];


	public void DFS (int index)// поиск в глубину
	{
		
		System.out.println(temp[0]);
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
/*	boolean check (boolean [] array)
	{
		int k = 0;
		for(int i = 0; i< array.length;i++)
         {
         	if(array[i] == false) k++;
         }
         if(k = 0) return false;
         return true;
	}
	*/
	/*public void dijkstra(int start)
	{
        boolean [] U = new boolean[count_ver];
         int[] D = new int[count_ver];
         for(int i = 0; i< D.length;i++)
         {
         	D[i] = INF;
         	U[i] = false;
         }
         D[start] = 0;
         while(U.check != false)
        {
         	for(int i=0; i < count_ver; i++)
        	{
            	for(top C: list_adj[i])
            	{

            	}
            }
        }

		
	}
   */
	void dijkstra(int start) {
		System.out.println("h " + count_ver1);
         boolean[] used = new boolean [count_ver1]; // массив пометок
        int[] dist = new int [count_ver1]; // массив расстояния. dist[v] = минимальное_расстояние(start, v)
         for(int i = 0; i< count_ver1;i++)
         {
         	dist[i] = INF;
         	//U[i] = false;
         }
        // fill(dist, INF); // устанаавливаем расстояние до всех вершин INF
         //System.out.println('h');
        dist[start] = 0; // для начальной вершины положим 0
         
         for (;;) {
             int v = -1;
             for (int nv = 0; nv < count_ver1; nv++) // перебираем вершины
                 if (!used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv])) // выбираем самую близкую непомеченную вершину
                     v = nv;
             if (v == -1) break; // ближайшая вершина не найдена
             used[v] = true; // помечаем ее
             for (int nv = 0; nv < count_ver1; nv++)
                 if (!used[nv] && graph[v][nv] < INF) // для всех непомеченных смежных
                     dist[nv] = Math.min(dist[nv], dist[v] + graph[v][nv]); // улучшаем оценку расстояния (релаксация)
         }
         for(int i = 0; i< count_ver1; i++)
         {
         	System.out.println("dist"+i+dist[i]);
         }
    }

    public void Prim( )
   {
      int i, j, k, x, y;
      boolean[] Reached = new boolean[count_ver1];
      int[] predNode = new int[count_ver1];
      Reached[0] = true;
      for ( k = 1; k < count_ver1; k++ )
      {
         Reached[k] = false;
      }
      predNode[0] = 0;
     // printReachSet( Reached );
      for (k = 1; k < count_ver1; k++)
      {
         x = y = 0;
         for ( i = 0; i < count_ver1; i++ )
            for ( j = 0; j < count_ver1; j++ )
            {
                if ( Reached[i] && !Reached[j] &&
                     graph[i][j] < graph[x][y] )
                {
                   x = i;
                   y = j;
                }
            }
         System.out.println("Min cost edge: (" +
                                + x + "," +
                                + y + ")" +
                                "cost = " + graph[x][y]);
         predNode[y] = x;
         Reached[y] = true;
         //printReachSet( Reached );
         System.out.println();
      }
      int[] a= predNode;
   for ( i = 0; i < count_ver1; i++ )
          System.out.println( a[i] + " --> " + i );
   }
  /* void printReachSet(boolean[] Reached )
   {
      System.out.print("ReachSet = ");
      for (int i = 0; i < Reached.length; i++ )
         if ( Reached[i] )
           System.out.print( i + " ");
      //System.out.println();
   }
   */
}
 