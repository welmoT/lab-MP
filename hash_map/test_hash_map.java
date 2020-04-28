
public class test_hash_map 
{
	public static void main(String[] args) 
	{
		hash_map H = new hash_map(50);
		element R = new element(57,90);
		element R1 = new element(45,30);
		element R2 = new element(7,51);
		H.add(R);
		H.add(R1);
		H.add(R2);
		H.print();
		System.out.println(H.crowding());
		System.out.println("get value "+H.get_value(57));
		//H.remove_all();
		//H.print();
	}
}