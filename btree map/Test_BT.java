import java.util.Random;
class Test_BT
{
public static void main(String [] args)
	{
		int [] array = {10,20,5,1,30,15,25,26,27,28,29,24,31,40,41,42,43,44,45,46,47,50};
		btree BT = new btree();
		for(int i = 0;i<array.length;i++)
		{
			uint temp = new uint(array[i],array[i]+5);
			BT.put(temp);
		}
		System.out.println("our btree: ");
		BT.print(BT.root,1);
		btree BT1 = new btree(BT);
		System.out.println("copy btree: ");
		BT1.print(BT1.root,1);
		System.out.println("Search element 100: ");
		System.out.println(BT.TreeSearch(100));
		System.out.println("Search element 15: ");
		System.out.println(BT.TreeSearch(15));
		System.out.println("What is the key 15 value? ");
		System.out.println(BT.getValue(15, BT.root));
		System.out.println("Let`s change the  value of key 15 to 100");
		System.out.println(BT.ChangeValue(15,100, BT.root));
		System.out.println("btree after delete all elements:");
		BT.deleteAll(BT.root);
		System.out.println(BT.empty(BT.root));

	}
}