import java.util.ArrayList;

class element < T extends Comparable <T> >
{
	T key;
	T value;
	element (T key, T value)
	{
		this.key = key;
		this.value = value;
	}
}
class hash_map < T extends Comparable <T> >
{
	element e;
	ArrayList <element> [] Array;
	int count_element;
	int count_list;
	double level;
	hash_map (int p)
	{
		this.count_element = 0;
		this.count_list = 0;
		this.level = 2.0;
		Array = new ArrayList [p];
		for(int i = 0; i < p; i++)
		{
			Array[i] = new ArrayList();
		}

	}
	void add(element E)
	{
		int count = 0;
		if(level > 4)
		{
		 rehash(Array.length*2+1);
		}
		int temp = E.key.hashCode();
		int h = temp % Array.length; //здесь делю на размерность массива, а не на количество списков, как указано в описании ЛР
		if(h == 0) h = Array.length -1;
		else h--;
		//System.out.println(h);
		for (int i = 0; i < Array[h].size();i++)
		{
			if(Array[h].get(i).key == E.key) count++;
		}
		if (count == 0)
		{
			if(Array[h].isEmpty() == true) count_list++;
			Array[h].add(E);
			count_element++;
		}
		else System.out.println ("this element = " + E.key + " is containing in hash_map");
		level = crowding();
	}
	//private  ArrayList < T > Array = new ArrayList < T > ();
	void print()
	{
		System.out.println("hash map:  ");
		if(count_list == 0) System.out.println("hash map is Empty");
		for(int i = 0; i < Array.length; i++)
		{
			for(int j = 0; j<Array[i].size();j++)
			{
				System.out.println("element List  " + j + ": " + Array[i].get(j).key.toString());
			}
		}
		System.out.println("count_list " + count_list);
		System.out.println("count_element " + count_element);
	}
	void remove_all()
	{
		for(int i = 0; i < Array.length; i++)
		{
			while(Array[i].size() != 0)
			{
				 Array[i].remove(0);
			}
		}
		count_list = 0;
		count_element = 0;
	}
	int count ()
	{
		return count_element;
	}
	double crowding()
	{
		if(count_list != 0)
		{
			double temp = count_element/count_list;
			return temp;
		}
		return 0;
		
	}
	T get_value(T key)
	{
		element E = new element(key,key);
		int temp = E.key.hashCode();
		int h = temp % Array.length-1;
		for(int i = 0; i < Array[h].size(); i++)
		{
			if(Array[h].get(i).key.compareTo(key) == 0) 
			{
				T val  = (T)Array[h].get(i).value;
				return val;
			}

		}
		return null;

	}
	void remove(T key)
	{
		int hash = key.hashCode();
		int h = hash % Array.length - 1;
		int k = 0;
		for(int i = 0; i < Array[h].size(); i++)
		{
			if(Array[h].get(i).key.compareTo(key) == 0) 
			{
				Array[h].remove(i);
				if(Array[h].isEmpty() == true) count_list--;
				k++;
			}

		}
		if(k != 0)
		{
			count_element--;
		} 
		else System.out.println("this key not found");		
	}
	void change_level (double lev)
		{
			this.level = lev;
			System.out.println("level "+level);
		}
	void rehash(int countList)
	{
		//System.out.println("count list "+countList);
		if(countList > count_list)
		{
			rehashing( countList );
		}
		else System.out.println("this count = " + countList +" List less then current count List");
	}
	private void rehashing (int countList)
	{
		count_element = 0;
		count_list = 0;
		ArrayList <element> [] Temp = new ArrayList[Array.length];
		Temp = Array;
		this.Array = new ArrayList[countList];
		element E;
		for(int i = 0; i < countList; i++)
		{
			this.Array[i] = new ArrayList();
		}
		for(int i = 0; i < Temp.length; i++)
		{
			while(Temp[i].isEmpty() != true )
			{
				E = Temp[i].get(0);
				add(E);
				Temp[i].remove(0);
			}
		}
		level = 2;
	}

}