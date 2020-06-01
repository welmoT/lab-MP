public class test_cod_RS 
{
	public static void main(String[] args) 
	{
		test1();
		test2();
		
	}
		static void test1()
		{
		cod_RS R1 = new cod_RS();
		int[] array = new int[256];
		for(int i = 0; i<235; i++) array[i] = i;
		int NEr = 10;              // максимальное количесто ошибок
		R1.c_form(NEr, 255);               // будем исправлять 10 ошибок, в буфере длиной 255 байт
		R1.c_code(array);
		System.out.println("after coding:");
		for(int i = 0;i<R1.c_buf.length;i++) System.out.print("  "+R1.c_buf[i]);
		for (int i = 0; i < 10; i++)   // создаеем  NEr ошибок в сообщении
    		R1.c_buf[3 + 4 * i]++;
    	int nErr = R1.c_decode(R1.c_buf);
    	System.out.println("count Error "+nErr);
    	System.out.println("after decoding:");
    	for(int i = 0;i<R1.c_buf.length;i++) System.out.print("  "+R1.c_buf[i]);
		}
	static void test2()
		{
			cod_RS R1 = new cod_RS();
			int[] array = new int[256];
			for(int i = 0; i<235; i++) array[i] = i;
			int NEr = 10;
			R1.c_form(NEr, 255);
			R1.c_code(array);
			int nErr = R1.c_decode(R1.c_buf);
			System.out.println("\ncount Error if e(x) = 0 : "+nErr); 
		}
	
}

	
