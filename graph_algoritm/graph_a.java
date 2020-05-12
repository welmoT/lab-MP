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
		this.top2 = top2;//здесь было неправильно top2 = top1
		this.weight = weight;
	}
}
public class graph_a 
{
	public static int INF = Integer.MAX_VALUE;
	int count_adj/*, count_el*/;  //count_el - для непосредственно реализации структуры графа не нужно
	LinkedList <top> [] list_adj;
	boolean[] temp;
	//top [] el_array; //- даля непосредственно реализации структуры графа не нужно
	boolean directed; // для того чтобы определить направленность. в зависимости от того направленный или нет список смежности будет разный
	graph_a (int count_adj/*, int count_el*/, String s_dir)
    {
		this.count_adj = count_adj;
      //  this.count_el = count_el; 
		//this.current_ce = 0; //Это нужно для тогочтобы чмотреть каково текущее число ребер в графе. в тесте ты указываешь какое 
							//число вершин и ребер у тебя будет в графе, если после create_graph current_ce получилось больше чем заказывали, вызвать эксепшн(я не делала)
		//el_array = new top[count_el]; //для отслеживания списка ребер (я использовала для алгоритма крускала)
        list_adj = new LinkedList[count_adj];
		for(int i=0; i < count_adj; i++) list_adj[i] = new LinkedList();
			
		if(s_dir=="directed") directed=true;  else directed=false; //weighted в принципе не обязателен, алгоритмы то у нас все равно требуют взвешенный граф, но для полноты можно использовать
		temp = new boolean [count_adj];
    }
	//boolean[] temp = new boolean[3]; 
	void create_graph(int top1, int top2, int weight)
	{
		//if(weighted == false && weight != 0) System.out.println("This edge can't be created, because graph is unweighted");
		//else
		{
			
			
		//	if(current_ce == count_el) System.out.println("Exception");
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
	}

	public void Print()
    { 
		System.out.println("GRAPH AS LIST_ADJ");
		for(int i=0; i < count_adj; i++)
        {
            System.out.print("["+i+"]:");
            for(top pCrawl: list_adj[i])
            {
			//	System.out.print(pCrawl.top1+" "+pCrawl.top2+" "+pCrawl.weight);
               System.out.print("  "+pCrawl.top2 +" "+"("+pCrawl.top1+","+pCrawl.top2+","+pCrawl.weight+")"); //в скобках записывается полное имя ребра с весом. необязательно. классически только вершины указывать
            }
            System.out.println();
        }
	}
	
	/*public void print_listedges()
    { 
		for(int i=0; i<count_el; i++)
        {
			System.out.println("edge ["+ i+"] : "+el_array[i].top1+" - "+el_array[i].top2+" weight "+el_array[i].weight);
		}
	}*/
	

	public void DFS (int index)// поиск в глубину
	{
		temp[index] = true;
		System.out.println(index);
		for(int next = 0; next < count_adj; next++)
		{
			if(temp[next]!=true && )
			{
				DFS(next);
			}
		}
	}
	
} 