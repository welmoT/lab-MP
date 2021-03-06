package priority_queue;
import java.util.ArrayList;


public class priority_queue < T extends Comparable<T>>
{
	private  ArrayList < T > Array = new ArrayList < T > ();

	priority_queue()
	{
		  this.Array = Array;
	}


	 void Add(T value)
     {
	     Array.add(value);
	     SiftUp(Array.size()-1,Array);
     }

	 void SiftUp(int i, ArrayList<T> Ar)
     {
		 		int parent = (i-1)/2;
		 		while (i>0 && Ar.get( i ).compareTo( Ar.get(parent) ) > 0 )
		 		{
		 			swap(i,parent,Ar);
		 			i=parent;
		 			parent=(i-1)/2;
		 		}
      }
	 void SiftDown(int i, ArrayList<T> Ar)
	  {
		 int left=2*i+1;
		 int right=2*i+2;
		 int largest=i;
		if ((left < Ar.size())&& Ar.get( left ).compareTo( Ar.get(i) ) > 0)
				{

			largest=left;

				}

		if( ( right < Ar.size() ) && Ar.get(right).compareTo(Ar.get(largest)) > 0)
		{
			largest=right;
		}
		if(largest != i && largest != -1)
		{
			swap(i,largest,Ar);
			SiftDown(largest, Ar);
		}
	  }
	 void swap(int i,int largest, ArrayList<T> Ar)
	 {
		 	T tmp;
			tmp = Ar.get(i);
			Ar.set(i, Ar.get(largest));
			Ar.set(largest, tmp);
	 }
	 void Print()
	    {
	        for(int i = 0; i < Array.size(); i++)
	          System.out.println("element" + i + ": "+ Array.get(i));
	    }
	 boolean empty()//���������� true ���� ������ ����
	 {
		 if (Array.isEmpty() == true) return true;
		 else return false;
	 }
	 int Count_elements()
	 {
		 return Array.size();
	 }
	 T acess ()
	 {
		 return Array.get(0);
	 }
	 void Delete()
	 {
		 Array.set(0, Array.get(Array.size()-1) );
		 Array.remove(Array.size()-1);
		 SiftDown( 0, Array);
	 }
}
