class Test_map
{
	public static void main(String[] args)
	{
		int value=0;
		int key;
		map M = new map();
	     //int mas[] = {5,2,4,7,9,10};

		for(int i = 0; i < 6; i++)
		{
		
			key=i;
			value= 3;
			M.Add(key, value);
		}
		M.print();
		//M.little_right_rotate(M.root.left);
		//M.little_left_rotate(M.root.right.right);
		//System.out.println(M.Acess_value(M.root, M.root.key).toString());
	    //M.print();
		System.out.println(M.description());
	}
}