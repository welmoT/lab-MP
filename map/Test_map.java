class Test_map
{
	public static void main(String[] args)
	{
		int key,value=0;
		map M = new map();
		for(int i = 12; i>0; i--)
		{
		
			key=i;
			value=3;
			M.Add(key, value);
		}
		//M.print();
		M.little_right_rotate(M.root.left);
		//M.little_left_rotate(M.root.right.right);
		//System.out.println(M.Acess_value(M.root, M.root.key).toString());
		M.print();
	}
}