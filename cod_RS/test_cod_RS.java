public class test_cod_RS 
{
	public static void main(String[] args) 
	{
		int[] a = {0,0,0,0,0,0,0,3,0};
		int [] b = {2,4,4,0,3,1,1};
		int [] message = {1,1,0,1};
		cod_RS R1 = new cod_RS(message);
		//int [] c = R1.mod(a,b);
		R1.coder();
		for(int i = 0;i <R1.code.length;i++)
		{
			System.out.println(i+"  "+R1.code[i]);
		}
		int[] temp = R1.mod(R1.addition(R1.code,a),R1.polinom);
		for(int i = 0;i <temp.length;i++)
		{
			System.out.println(i+"  "+temp[i]);
		}
		//System.out.println(R1.div(4,3));
	}
}
