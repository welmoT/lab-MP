import java.util.Random;
public class test_hash_map 
{
	public static void main(String[] args) 
	{
		example();
		example1();
	}
	 static void example ()
	{
		int array []= {1,7,7,5556,99,6,4848,78,25,6,5,89,65,33,11,9594,63,588,45,85,333,4,8,67,598,7264,90,66544,787,45878};//24
		String Ar [] = {"hello","frog","set","take","cat","unit","true","be","pen","book","swim","ion","like","dislike","house","map","put","get","a","b","c","d","h","e","big","dont","can","should","must","very","often"};
		hash_map M = new hash_map(20);
		element E;
		for(int i = 0; i < array.length; i++)
		{
			 E = new element(array[i], Ar[i]);
			M.add(E);
		}
		M.print();
		//M.rehash(41);
		 System.out.println("current level: " + M.crowding());
		System.out.println("get value 99: "+ M.get_value(99));
		M.remove(67);
		System.out.println("delete element 67:");
		System.out.println("get value 67 "+ M.get_value(67));
		System.out.println("change level = 5 and add element");
		M.change_level(5);
		E = new element(88888888,2);
		M.add(E);
		M.print();
        System.out.println("current level: " + M.crowding());

	}
	static void example1 ()
	{
		int min = 0;
		int max = 1000;
		int diff = max - min;
		Random random = new Random();
		int rnd;
		hash_map M = new hash_map(50);
		element E;
		for(int i = 0; i < 100; i++)
		{
			
			rnd = random.nextInt(diff + 1) + min;
			E = new element(rnd,rnd+21);
			M.add(E);
		}
		M.print();
		M.rehash(40);
		System.out.println("after remove all elements");
		M.remove_all();
		M.print();	
	}
}