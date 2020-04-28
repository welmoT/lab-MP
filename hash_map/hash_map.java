import java.util.ArrayList;

class element <T extends Comparable<T>>
{
	T key;
	T value;
	element (T key,T value)
	{
		this.key = key;
		this.value = value;
	}
}
class hash_map <T extends Comparable<T>>
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
		int temp;
		temp = (int)E.key;
		int h = temp%Array.length-1; //здесь делю на размерность массива, а не на количество списков, как указано в описании ЛР
		if(Array[h].isEmpty() == true) count_list++;
		Array[h].add(E);
		count_element++;
	}
	//private  ArrayList < T > Array = new ArrayList < T > ();
	void print()
	{
		for(int i = 0; i < Array.length; i++)
		{
			for(int j = 0; j<Array[i].size();j++)
			{
				System.out.println("element" + j + ": "+ Array[i].get(j).key.toString());
			}
		}
		System.out.println("count_list "+count_list);
		System.out.println("count_element "+count_element);
	}
	void remove_all()
	{
		for(int i = 0; i < Array.length; i++)
		{
			while(Array[i].size()!=0)
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
		return count_element/Array.length;
	}
	T get_value(T key)
	{
		int temp;
		element E = new element(key,key);
		temp = (int)E.key;
		int h = temp%Array.length-1;
		for(int i = 0; i < Array[h].size(); i++)
		{
			if(Array[h].get(i).key.compareTo(key) == 0) 
			{
				T val =(T)Array[h].get(i).value;
				return val;
			}

		}
		return null;
		

	}

}