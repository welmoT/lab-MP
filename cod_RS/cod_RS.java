import java.util.HashSet;
//GF(5) code (10;4)
public class cod_RS 
{
	int N, K, D,q;
	int[] message = new int[K];
	int[] polinom = {2,4,4,0,3,1,1};
	int [] code = new int [N];
	cod_RS(int[] message)
	{
		this.message = message;
		//polinom = {2,4,4,0,3,1,1};
		N = 10;
		K = 4;
		D = N-K-1;
		q = 5;

	}
	 public static int minus(int a, int b) {
		int c = a - b % 5;
		if( c >= 0 ) return c;
		return (5 + c) % 5;
	}
	
	public static int add(int a, int b) {
		return (a + b) % 5;
	}
	
	public static int mult(int a, int b) {
		int c  = (a * b) % 5;
		return c;
	}
	public static int div(int a, int b) {
		for(int i = 1; i< 5;i++)
		{
			if(mult(b,i) == a) return i;
		}
		return -1;
	}
	int degree(int [] f) {
	int i, d;
	for (i = f.length-1, d = -1; i >= 0; i--)
		if (f[i] != 0) { d = i; break; };
	//System.out.println(d);
	return d;
}
	/*void polinom ()
	{
		int [] temp = {2,1};

		//int [] temp1 = {1,4}
		int [] mass = new int[polinom.length];
		for(int i = 0; i < mass.length ;i++) mass[i] = 0;
		mass[0] = 2;
		mass[1] = 1;
		for(int i = 0; i < mass.length - 2;i++)
		{
			//int[] c = new int temp2[(temp.length-1)*];
			temp[0] = temp[0]*2;
			//temp[1] = Math.pow(2,i+2);
			mass = multiplicate(temp,mass);
		}
	}
	*/
	int [] subtracts (int [] a,int [] b)
	{
		int [] c = new int [Math.max(a.length,b.length)];
		for(int i = 0;i < Math.min(a.length,b.length);i++)
		{
			c[i] = minus(a[i], b[i]);
			//System.out.println(i);
		}
		/*for(int i = Math.min(a.length,b.length) ;i < Math.max(a.length,b.length);i++)
		{
			c[i] = a[i];
			System.out.println(i);
		}
		*/
		return c;
	}

	int [] addition (int [] a,int [] b)
	{
		int [] c = new int [Math.max(a.length,b.length)];
		for(int i = 0;i < Math.min(a.length,b.length);i++)
		{
			c[i] = add(a[i], b[i]);
		}
		/*for(int i = Math.min(a.length,b.length) ;i < Math.max(a.length,b.length);i++)
		{
			c[i] = a[i];
			//System.out.println(i);
		}
		*/
		return c;
	}
	int [] multiplicate (int [] a,int [] b){ //Умножение многочленов.
		int[] c = new int[a.length+b.length-1];
    for(int i = 0; i < c.length; i++) c[i] = 0;
    for(int i = 0; i < a.length; i++){
        for(int j = 0; j < b.length; j++){
        	//System.out.println(a[i]+"   "+ b[j]);
             c[i + j] = add(mult(a[i], b[j]), c[i+j]);
		}
			}
		return c;
	}
	int [] mod (int [] a,int [] b)
	{
		int[] r = new int[a.length];//остаток от деления
		int[] n = new int[a.length];
		int[] sub = new int[a.length];
		int k = degree(a) - degree(b);
		while(k >= 0)
		{
			n[k] = div(a[degree(a)],b[degree(b)]);
			sub = multiplicate(n,b);
			r = subtracts(a, sub);
			for(int i = 0; i < a.length; i++) a[i] = r[i];
			//b = r;
			n[k] = 0;
			k = degree(a) - degree(b);
		}
		
		return r;
	}
	void coder()
	{
		int [] n_k = new int [D+2];
		int [] c = new int [N];
		int [] temp = new int [c.length];
		for(int i = 0;i< n_k.length;i++) n_k[i] = 0;
		n_k[D+1] = 1;
		c = multiplicate(n_k,message);
		int [] temp2 = mod(c,polinom);
		code = addition(temp, temp2);

	}
}

 /*class GF257 {
	public static int[][] adds;
	public static int[][] subtracts;
	public static int[][] mults;
	public static int[] inverse;
	public static boolean[][] addsDone;
	public static boolean[][] subtractsDone;
	public static boolean[][] multsDone;
	public static boolean[] inverseDone;
	
	public static final char PX = (char)0x11B;
	public static void init() {
		int max = 257;
		adds = new int[max][max];
		mults = new int[max][max];
		subtracts = new int[max][max];
		addsDone = new boolean[max][max];
		multsDone = new boolean[max][max];
		subtractsDone = new boolean[max][max];
		inverse = new int[max];
		inverseDone = new boolean[max];
		for(int i = 0; i < adds.length; i++) {
			inverse[i] = 0;
			inverseDone[i] = false;
			for(int j = 0; j < adds[0].length; j++) {
				adds[i][j] = 0; //add(i,j);
				mults[i][j] = 0;
				subtracts[i][j] = 0;
				addsDone[i][j] = false;
				multsDone[i][j] = false;
				subtractsDone[i][j] = false;
			}
		}
	}
	public static int minus(int a, int b) {
		int c = a - b % 257;
		if( c >= 0 ) return c;
		return (257 + c) % 257;
	}
	
	public static int add(int a, int b) {
		return (a + b) % 257;
	}
	
	public static int mult(int a, int b) {
		int c  = (a * b) % 257;
		return c;
	}
	
	
	int val;
	public GF257(int val) {
		this.val = val;
	}
	
	public GF257 add(int b) {
		if(!addsDone[val][b]) {
			adds[val][b] = add(val,b);
			adds[b][val] = adds[val][b];
			addsDone[val][b] = true;
			addsDone[b][val] = true;
		}
		return new GF257(adds[val][b]);
	}
	
	public GF257 add(GF257 b) {
		return add(b.val);
	}
	
	public GF257 minus(int b) {
		if(!subtractsDone[val][b]) {
			subtracts[val][b] = minus(val,b);
			subtractsDone[val][b] = true;
		}
		return new GF257(subtracts[val][b]);
	}
	
	public GF257 minus(GF257 b) {
		return minus(b.val);
	}
	
	public GF257 mult(int b) {
		
		if(!multsDone[val][b]) {
			mults[val][b] = mult(val,b);
			mults[b][val] = mults[val][b];
			multsDone[val][b] = true;
			multsDone[b][val] = true;
		}
		return new GF257(mults[val][b]);
	}
	public GF257 mult(GF257 b) {
		return mult(b.val);
	}
	
	public GF257 getInverse() {
		return inverse(val);
	}
	public static GF257 inverse(int a) {
		if(!inverseDone[a]) {
			GF257 ga = new GF257(a);
			for(char i = 0; i <= (1<<8); i++) {
				if(ga.mult(i).val == 1) {
					inverseDone[a] = true;
					inverseDone[i] = true;
					inverse[a] = i;
					inverse[i] = a;
					break;
				}
			}
		}
		return new GF257(inverse[a]);
	}
	
	public static String getBinaryString(int c) {
		String s = "";
		for(int i = 0; i < 9; i++) {
			s = (c % 2) + s;
			c = (c >> 1);
		}
		return "0b" + s;
	}
	
	public static HashSet<Integer> findGenerators() {
		HashSet<Integer> gs = new HashSet<Integer>();
		for(int j = 1; j < 257; j++) {
			GF257 gz = new GF257(j);
			HashSet<Integer> hs = new HashSet<Integer>();
			hs.add(gz.val);
			for(int i = 2; i < 257; i++) {
				gz = gz.mult(j);
				hs.add(gz.val);
			}
			if(hs.size() == 256) gs.add(j);
		}
		return gs;
	}
	

}
*/