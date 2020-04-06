
class Test_map
{
	public static void main(String[] args)
	{
		int value=0;
		int key;
		map M = new map();
	     //int mas[100] ;
	    // double random();
	    // HashSet<Integer> myHashSet = new HashSet<Integer>();
	   //  for (int i = 0; i < 1500; i++)
	    // {
	    // 	myHashSet.add(0 + (int) (Math.random() * 10000));
	    // }

		for (int i=0; i< 1500; i++)
		{
		
			key = i;
			value= i+3;
			M.root = M.insert(M.root,key, value);
		}
		//if (M.find(4) != null) System.out.println("yes");
		//else System.out.println("no");
		System.out.println("value = " + M.value(5));
		M.print();
		System.out.println(M.description());
		M.delete(M.root, 3);
		System.out.println(M.description());
		TreeIterator T = new  TreeIterator(M.root);
		T.next();
		System.out.println ("iterator"+ T.next().key);

	}
}