import java.util.Random;
class Test_map
{
	public static void main(String[] args)
	{

		Test_map Test = new Test_map();
		Test.example();
		Test.example1();
		Test.example2();
	}
	
	void example()
	{
		int min = 0;
		int max = 1000000;
		int diff = max - min;
		Random random = new Random();
		int rnd;
		map M = new map();
		for(int i = 0; i<100; i++)
		{
			
			rnd = random.nextInt(diff + 1) + min;
			M.root = M.insert(M.root, rnd, rnd+4);
		}
		System.out.println(M.description());
		System.out.println("check balance tree: "+M.CheckBalanceTree(M.root));
		map Mt = new map(M);
		System.out.println("print copy map: \n");
		System.out.println(Mt.description());
		System.out.println("check balance copy tree: "+Mt.CheckBalanceTree(Mt.root));
		System.out.println("Is tree empty?: "+M.empty());

	}
	void example1()
	{
		map M = new map();
		for(int i = 0; i < 100; i++)
		{
			M.root = M.insert(M.root, i, i+7);
		}
		System.out.println(M.description());
		System.out.println("check balance tree: "+M.CheckBalanceTree(M.root));
		for(int i = 0; i < 20; i++)
	    {
			M.delete(M.root, i);
		}
		System.out.println("Tree after delete twenty elements: \n");
		System.out.println(M.description());
		System.out.println("check balance tree: "+M.CheckBalanceTree(M.root));
		TreeIterator T = new  TreeIterator(M.root);
		T.next();
		T.next();
		System.out.println ("iterator "+ T.next().key);
		System.out.println("Search element 6 in tree: "+M.find(6));
	}
	void example2()
	{
		map M = new map();
		for(int i = 100; i > 0; i--)
		{
			M.root = M.insert(M.root, i, i+7);
		}
		System.out.println("Check balance tree: "+M.CheckBalanceTree(M.root));
		System.out.println(M.description());
		System.out.println("get value for key=46 value="+M.value(46));
		M.ChangeValue(46, 104);
		System.out.println("change value for key=46 value="+M.value(46));
		M.DeleteAll();
		System.out.println("tree dele all: ");
		M.print();
		System.out.println("Is tree empty?: "+M.empty());

	}
}