import java.util.*;
public class test_graph_a < T extends Comparable <T> >
{
	public static void main(String[] args) 
	{
	    int INF = 10000;
		graph_a G = new graph_a(4,8,"directed");
		int [][] array = {{INF,INF,1,5},{4,INF,3,INF},{INF,INF,INF,1},{2,2,INF,INF}}; 
		graph_a G1 = new graph_a (array);
		G.create_graph(0,3,7);
		G.create_graph(0,2,10);
		G.create_graph(1,2,1);
		G.create_graph(1,3,4);
		G.create_graph(1,0,15);
		G.create_graph(2,3,8);
		G.create_graph(3,0,23);
		G.create_graph(3,1,9);
		G.Print();
		G.BFS(0);
		System.out.println("\nDepth-first search");
		G.DFS(0);
		G.kruskal();
		G1.dijkstra(0);
		G1.Prim();
		G1.Floid();
	}
}
