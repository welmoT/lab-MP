package priority_queue;

public class Test_priority_queue {

	public static void main(String[] args) {
		Test_priority_queue Test= new Test_priority_queue();
		Test.example1();
		Test.example2();
		Test.example3();
	}
	void example1()
	{
		int int_array[]= { 5, 11, 2, 9, 3, 7 };
		priority_queue Test1= new priority_queue();
		if(Test1.empty() == true) System.out.println("priority_queue is empty");
		for(int i=0; i < int_array.length; i++)
		{
			Test1.Add(int_array[i]);
		}

		Test1.Print();
		System.out.println("count elements in priority_queue: "+Test1.Count_elements());

	}

	void example2()
	{
		priority_queue Test2= new priority_queue();
		char char_array [ ]= {'b','w','a','g','t','f','l','q'};
		for(int i=0; i < char_array.length; i++)
		{
			Test2.Add(char_array[i]);
		}

		Test2.Print();

		System.out.println("max element in priority_queue: "+ Test2.acess());

	}

	void example3()
	{
		priority_queue Test3= new priority_queue();
		String string_array []= {"ball","lamp","cat","put","come","flower","like","line"};
		for(int i=0; i < string_array.length; i++)
		{
			Test3.Add(string_array[i]);
		}

		Test3.Print();
		Test3.Delete();
		System.out.println("after delete: ");
		Test3.Print();

	}
}
