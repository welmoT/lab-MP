import java.util.*;
public class test_graph_a < T extends Comparable <T> >
{
	public static void main(String[] args) 
	{
		graph_a G = new graph_a(4/*,5*/,"directed");
		int [][] array = {{100,100,1,5},{4,100,3,100},{100,100,100,1},{2,2,100,100}}; 
		graph_a G1 = new graph_a (array);
		//ArrayList Q1 = new ArrayList();
		//Q1.add(2);
		//int [] a0 = {1,2,0,2,1};
		//int [] a1 = {2,2,1};
		//Q1.add(a0);
		//Q1.add(a1);
		//Q1.add(a2);
		//G.create_graph(0,2,10);
		G.create_graph(1,2,4);
		G.create_graph(2,3,8);
		G.create_graph(3,0,23);
		G.create_graph(3,1,0);
		G.create_graph(1,0,0);
		G.create_graph(0,3,0);
		G.Print();
		int i = 0;
		G.BFS(i);
		G.DFS(i);
		G1.dijkstra(0);
		G1.Prim();
		//G.print_listedges();
		//G.create_graph(3,1,0);


		//G.DFC(0);


	}
}
