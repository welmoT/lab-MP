class Test_map
{
	public static void main(String[] args)
	{
		int value=0;
		int key;
		map M = new map();
	     int mas[] = {5,2,4,7,9,10};

		for(int i = 0; i < 6; i++)
		{
		
			key = i;
			value= 3;
			M.root = M.insert(M.root,key, value);
		}
		if (M.find(7)!= null) System.out.println("yes");
		else System.out.println("no");
		M.print();
		System.out.println(M.description());
	}
}