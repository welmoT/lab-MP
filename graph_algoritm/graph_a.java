import java.util.*;
import java.io.*;
import java.util.Scanner;

class element 
{
	int top;
	int weight;
}
public class graph_a < T extends Comparable <T> >
{
	PriorityQueue [] Array_top_graph;
	boolean[] temp = new boolean[Array_top_graph.length]; 
	graph_a(int count)
	{
		Array_top_graph = new PriorityQueue[count];
		
	}
	void graph (T [] array, PriorityQueue Q)//функция для добавление вершин в графе
	{
		for (int i = 0; i < array.length;i++)
		{
			Q.add(array[i]);
		}
	}

	void DFS (int index)// поиск в глубину
	{
		temp[index] = true;
		System.out.println(index);
		for(int next = 0; next < Array_top_graph[index].size(); next++)
		{
			if(!temp[next])
			{
				DFS(next);
			}
		}
	}
} 