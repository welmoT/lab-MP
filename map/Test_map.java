class Test_map
{
	public static void main(String[] args)
	{
		map M = new map();
		field F= new field();
		for(int i = 0; i<12; i++)
		{
			F.key=i;
			F.value=i+3;
			M.Add(F);
		}
		M.print();
	}
}