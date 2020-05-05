import java.util.*;
public class test_graph_a < T extends Comparable <T> >
{
	public static void main(String[] args) 
	{
		graph_a G = new graph_a(3);
		T [] a1 = new {2,1};
		T [] a2 = {2};
		T [] a3 = {0};
		/*for(int i = 0; i < 10; i++)
		{
			Prio
		}
		*/
		G.graph(a1);
		G.graph(a2);
		G.graph(a3);
		G.DFC(0);


	}
}