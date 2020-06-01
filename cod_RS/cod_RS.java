
//GF(256) code (255,255 - 2t) 
//неприводимый полином над GF(256):  al^8 + al^4 + al^3 + al^2 +  1. 
public class cod_RS 
{
	int TMax = 127;// количесвто ошибок, которое можно допустить
	int     t = 25;				//  число ошибок, которые можно исправить
	int     t2 = 50;			// t2 = t*2
	int     bufs = -1;			// текущий размер буфера   < 256
	int[]   c_buf = new int[255];				// буфер, в котором хранится сообщение 
	int[] c_s = new int[TMax * 2 + 1];	// массив для синдрома

	int  c_g[] = { //порождающий полином
	167,109,247,239,215,151,255,235, 62, 61, 47,151,190, 31,157,
 	 87,181, 60, 15,176,239,176,230, 46,162,102,230,157,135,159,
	 36,224,176,100,206,162,  8,156, 25,224,134,162,159,199, 58,
	198,209,213, 51,247,  1,  0,  0};

	int[]  LAM1 = new int[TMax * 2 + 1];
	int[]  LAM2 = new int[TMax * 2 + 1];
// используется для алгоритма евклида при поиске полинома локатора ошибок
	int[]  OMEGA1 = new int[TMax * 2 + 1];
	int[]  OMEGA2 = new int[TMax * 2 + 1];
int     dlam1, dlam2;		// степень полинома LAM1  (z) and LAM2  (z)
int     dom1, dom2;			// степень полинома OMEGA1(z) and OMEGA2(z)
int[]  X = new int[TMax * 2 + 1];	//локаторы ошибок
int[]  e = new int[TMax * 2 + 1];	//  массив ошибок

//таблицы логарифмов и антилогарифма для поля GF(256)
 int[] C_log = { 
	255,0  ,1  ,25 ,2  ,50 ,26 ,198,3  ,223,51 ,238,27 ,104,199,75 , //  0
	4  ,100,224,14 ,52 ,141,239,129,28 ,193,105,248,200,8  ,76 ,113, //  1
	5  ,138,101,47 ,225,36 ,15 ,33 ,53 ,147,142,218,240,18 ,130,69 , //  2
	29 ,181,194,125,106,39 ,249,185,201,154,9  ,120,77 ,228,114,166, //  3
	6  ,191,139,98 ,102,221,48 ,253,226,152,37 ,179,16 ,145,34 ,136, //  4
	54 ,208,148,206,143,150,219,189,241,210,19 ,92 ,131,56 ,70 ,64 , //  5
	30 ,66 ,182,163,195,72 ,126,110,107,58 ,40 ,84 ,250,133,186,61 , //  6
	202,94 ,155,159,10 ,21 ,121,43 ,78 ,212,229,172,115,243,167,87 , //  7
	7  ,112,192,247,140,128,99 ,13 ,103,74 ,222,237,49 ,197,254,24 , //  8
	227,165,153,119,38 ,184,180,124,17 ,68 ,146,217,35 ,32 ,137,46 , //  9
	55 ,63 ,209,91 ,149,188,207,205,144,135,151,178,220,252,190,97 , //  A
	242,86 ,211,171,20 ,42 ,93 ,158,132,60 ,57 ,83 ,71 ,109,65 ,162, //  B
	31 ,45 ,67 ,216,183,123,164,118,196,23 ,73 ,236,127,12 ,111,246, //  C
	108,161,59 ,82 ,41 ,157,85 ,170,251,96 ,134,177,187,204,62 ,90 , //  D
	203,89 ,95 ,176,156,169,160,81 ,11 ,245,22 ,235,122,117,44 ,215, //  E
	79 ,174,213,233,230,231,173,232,116,214,244,234,168,80 ,88 ,175 };

 int[] C_alog = { 
	    1,  2,  4,  8, 16, 32, 64,128, 29, 58,116,232,205,135, 19, 38, //  0
	   76,152, 45, 90,180,117,234,201,143,  3,  6, 12, 24, 48, 96,192, //  1
	  157, 39, 78,156, 37, 74,148, 53,106,212,181,119,238,193,159, 35, //  2
	   70,140,  5, 10, 20, 40, 80,160, 93,186,105,210,185,111,222,161, //  3
	   95,190, 97,194,153, 47, 94,188,101,202,137, 15, 30, 60,120,240, //  4
	  253,231,211,187,107,214,177,127,254,225,223,163, 91,182,113,226, //  5
	  217,175, 67,134, 17, 34, 68,136, 13, 26, 52,104,208,189,103,206, //  6
	  129, 31, 62,124,248,237,199,147, 59,118,236,197,151, 51,102,204, //  7
	  133, 23, 46, 92,184,109,218,169, 79,158, 33, 66,132, 21, 42, 84, //  8
	  168, 77,154, 41, 82,164, 85,170, 73,146, 57,114,228,213,183,115, //  9
	  230,209,191, 99,198,145, 63,126,252,229,215,179,123,246,241,255, //  A
	  227,219,171, 75,150, 49, 98,196,149, 55,110,220,165, 87,174, 65, //  B
	  130, 25, 50,100,200,141,  7, 14, 28, 56,112,224,221,167, 83,166, //  C
	   81,162, 89,178,121,242,249,239,195,155, 43, 86,172, 69,138,  9, //  D
	   18, 36, 72,144, 61,122,244,245,247,243,251,235,203,139, 11, 22, //  E
	   44, 88,176,125,250,233,207,131, 27, 54,108,216,173, 71,142,  1 };
	private   int c_log(int x) 
	     {
	     	return C_log[x];
	     }
	private     int c_alog(int x)
	     {
	     	return C_alog[x];
	     }

	private	int c_mod255(int x) 
		{
		    if(x > 254 )
		    	return x-255;
		    else return x;
		}
	private	int c_mul(int a,int b)
		{
			if(a == 0||b == 0)
				return 0;
			else return c_alog(c_mod255(c_log(a)+c_log(b)));
		} 
	private	int c_mulk(int a,int k)
		{
			if(a == 0)
				return 0;
			else return  c_alog(c_mod255(c_log(a)+k));
		}
	private int c_revers(int a) 
		{
			return c_alog(255-c_log(a));
		}
	private void c_clear(int[] f, int maxdeg) 
	{
	for (int i = 0; i <= maxdeg; i++) f[i] = 0;
	}

//функция определяет максимальную степень, поступившего многочлена
 private int c_degree(int[] f, int maxdeg) {
	int i, d;
	for (i = maxdeg, d = -1; i >= 0; i--)
		if (f[i] != 0) { d = i; break; }
	return d;
}
// функция поиска синдрома ошибки
 public void c_sindrom( ) {
	int i, j, bi;
	int bt;
	for (i = 0; i<t2; i++) c_s[i] = 0;
	for (i = 0; i<bufs; i++) {
		bt = c_buf[i];
		if (bt == 0) continue;
		c_s[0] ^= bt;
		bi = c_log(bt);
		for (j = 1; j<t2; j++) {
			bi += i; if (bi > 254) bi -= 255;
			c_s[j] ^= c_alog(bi);
		}
	}
}
// функция производит кодирование сообщения
// В позициях t2..254 - информация;
// 0 ..t2-1 - управляющие байты
//информация уже должна быть подготовлена, функция c_code дополняет свои управляющие байты.
// В c_buf [0..t2-1] сохраняем остаток от деления кода на полином
// сумма (i = t2..254; c_buf [i] * x ^ i) по полиному g (x).
private void c_code0() {
	int  i, j;
	int bt;
	for (i = 0; i < t2; i++) c_buf[i] = c_s[i] = 0;
	for (i = bufs - 1; i >= 0; i--) {
		bt = c_s[t2 - 1];
		for (j = t2 - 1; j>0; j--)
			c_s[j] = c_s[j - 1] ^ c_mul(c_g[j], bt);
		c_s[0] = c_buf[i] ^ c_mul(c_g[0], bt);
	};
	for (i = 0; i<t2; i++) c_buf[i] = c_s[i];
}
// функцию вызывает пользователь для кодирования
public void c_code(int[] buf) {
	for(int i = t2; i< bufs;i++)
	{
		c_buf[i] = buf[i-t2]; 
	}
	c_code0();
}
// функция создает производящий полином относительно заданного количества ошибок
//g (x) = (x-1) * (x-al) * ... * (x-al ^ t2) = x ^ t2 + g [t2-1] * x ^ (t2-1) + .. . + g [1] * x + g [0]
// Возвращает 0, если все в порядке, в противном случае отрицательное число
public int c_form(int NErr, int bufsize) {
	int i, j, t0=NErr;
	if (bufsize>255 || bufsize <3) return -1;
	bufs = bufsize;
	if (t0<0 || 2 * (t0 + 1) >= bufsize) return -1;
	if (t0>TMax) return -1;
	if (t == t0)                     return  0;
	t = t0;
	t2 = t * 2;
	c_clear(c_g, t2);
	c_g[0] = 1; c_g[1] = 1; c_g[2] = 0; // initial polynomial (x-1)
	// В следующем цикле переменная i является текущей степенью полинома g.
	// На одном шаге цикла из полинома (x-1) * ... * (x-al ^ (i-1))
	// получаем полином (x-1) * ... * (x-al ^ (i-1)) * (x-al ^ i)
	for (i = 2; i <= t2; i++) {
		for (j = i; j>0; j--)
			c_g[j] = c_g[j - 1] ^ c_mulk(c_g[j], i - 1);
		c_g[0] = c_mulk(c_g[0], i - 1);
	};
	return 0;
}
// меняет массивы местами LAM1 и LAM2
private void  c_swap() {
	int co;
	int  i, te;
	if (dom1 < dom2) {
		for (i = 0; i <= t2; i++) {
			co = OMEGA1[i];
			OMEGA1[i] = OMEGA2[i];
			OMEGA2[i] = co;
		};
		for (i = 0; i <= t2; i++) {
			co = LAM1[i];
			LAM1[i] = LAM2[i];
			LAM2[i] = co;
		};
		te = dom1;  dom1 = dom2;  dom2 = te;
		te = dlam1; dlam1 = dlam2; dlam2 = te;
	}
}
//один цикл алгоритма Евклида
private void c_Evklid1() {
	int i;
	int  co;
	for (i = 0; i <= dom2; i++)
		OMEGA1[i + dom1 - dom2] ^= OMEGA2[i];
	for (i = 0; i <= dom2; i++)
		LAM1[i + dom1 - dom2] ^= LAM2[i];
	dom1 = c_degree(OMEGA1, t2);
	dlam1 = c_degree(LAM1, t2);
	co = c_revers(OMEGA1[dom1]);

	for (i = 0; i <= dom1; i++)
		OMEGA1[i] = c_mul(OMEGA1[i], co);
	for (i = 0; i <= dlam1; i++)
		LAM1[i] = c_mul(LAM1[i], co);
	c_swap();
}
/* декодирование
     Алгоритм декодирования заключается в следующем.
    Пусть ошибка локатора полинома равна:
        e (x) = e [1] * x ^ i [1] + ... + e [nu] * x ^ i [nu],
    где nu количество ошибок, nu <= t,
    i [k] - позиция ошибки,
    e [k] значение ошибки.
    Обозначим через X [k] число (из F256) al ^ i [k].
    Мы определяем синдромный степенной ряд
        S(z) = s[0] + s[1]*z + ....
    по формуле:
                nu
        s[j] = SUM e[k]*al^( j*i[k] ).
               k=1
    тогда
               infin         nu
        S(z) =  SUM   z^j * SUM e[k]*( (al^i[k])^j )  =
                j=0         k=1

                 nu        infin
                SUM e[k] *  SUM  z^j * X[k]^j         =
                k=1         j=0

                 nu             1
                SUM e[k] * -----------                =
                k=1         1 - z*X[k]

                 nu     e[k]
                SUM  ----------- .
                k=1  1 - z*X[k]
     Функция c_sindrom вычисляет коэффициенты
	s[0], ... s[t2-1].

	Введем локатор ошибок полинома по формуле

        LAM(z) = (1 - X[1]*z) * ... * (1 - X[nu]*z) =
                LAM[0] + LAM[1]*z + ... + LAM[nu]*z^nu .
    тогда
                       nu
        S(z)*LAM(z) = SUM e[k]* PROD (1 - X[j]*z) .
                      k=1       j!=k

      Этот многочлен мы обозначим через OMEGA (z), его степень <= nu-1.
    Наша задача на s [0], ... s [t2-1] найти LAM [*], X [*], e [*].
    Чтобы найти полином LAM (z), обратите внимание, что в полиноме
    OMEGA (z) = S (z) * LAM (z) коэффициенты при z ^ nu, ..., z ^ (t2-1) равны нулю.
	После нахождения LAM (z) локаторы ошибок находятся как обратные значения корней многочлена LAM (z).
    Чтобы найти значения ошибок, используйте формулу:


                OMEGA( 1/X[k] )
        e[k] =  --------------- * X[k].
                LAM' ( 1/X[k] )

 	функция возвратит количество ошибок
 	Если возвращено -1, то количество ошибок> t, и, следовательно, мы не можем их исправить
*/
private int c_decode0() {
	int i, j, k;
	int co, co1;
	int degs;                   // степень полинома S(z)
	int degs2;                  // следующий не нулевой коэффциент 
	c_sindrom();                // поиск синдрома 
	degs = c_degree(c_s, t2 - 1);
	if (degs < 0) return 0;
	degs2 = c_degree(c_s, degs - 1);
	if (degs2< 0) return 0;				
	c_clear(LAM1, t2 + 1);
	c_clear(LAM2, t2 + 1);
	c_clear(OMEGA1, t2 + 1);
	c_clear(OMEGA2, t2 + 1);
	// Начальное значение многочлена LAM1 (z), с точностью до коэффициента, z ^ (t2-degs)
	dlam1 = t2 - degs;
	dom1 = degs2 + dlam1;
	co = LAM1[dlam1] = c_revers(c_s[degs2]);
	for (i = 0; i <= degs2; i++)
		OMEGA1[i + dlam1] = c_mul(c_s[i], co);
	// Начальное значение многочлена LAM2 (z) является постоянным
	dlam2 = 0;
	dom2 = degs;
	co = LAM2[0] = c_revers(c_s[degs]);
	for (i = 0; i <= degs; i++)
		OMEGA2[i] = c_mul(c_s[i], co);
	c_swap();
	//LAM1, LAM2, OMEGA1, OMEGA2  готовы
	while ((dom2 >= t) && (dlam2 <= t))
		c_Evklid1();
	// Полиномы LAM2 и OMEGA2 готовы.
// Если t> = dlam2> dom2, то количество ошибок равно dlam2,
// в противном случае количество ошибок> t, и, следовательно, мы не можем их исправить.
	if ((t >= dlam2 && dlam2 > dom2)!= true)
	{
		return -1;
	}
	// ноль не может быть локатором ошибки, поэтому возвращаем -1
	if (LAM2[0] == 0)
	{
		return -1;
	}

	//Теперь вычислите локаторы ошибок X [..], то есть корни LAM2 (1 / z)
	co = c_revers(LAM2[0]);
	for (i = 0; i <= dlam2; i++)
		LAM2[i] = c_mul(LAM2[i], co);
	for (i = 0; i <= dom2; i++)
		OMEGA2[i] = c_mul(OMEGA2[i], co);
	for (i = 0, j = 0; j < bufs; j++) {
		// test if al^j is a root of LAM2(1/z)
		for (k = 1, co = LAM2[0]; k <= dlam2; k++)
			co = c_mulk(co, j) ^ LAM2[k];
		if (co==0) {
			X[i] = j;
			i++;
			if (i >= dlam2) break;
		}
	}
	System.out.println(dlam2);
	//Если число корней меньше степени LAM2 (z), то ошибка
	if (i<dlam2)
	{
		
		return -1;
	}
	// локатор ошибок X[ 0..(dlam2-1) ] найден.
	//найдем коэффициентв ошибок
	for (i = 0; i < dlam2; i++) {
		j = 2 * (255 - X[i]);
		if (j > 255) j -= 255;
		for (k = dom2 - 1, co = OMEGA2[dom2]; k >= 0; k--)
			co = c_mulk(co, 255 - X[i]) ^ OMEGA2[k];
		// сейчас co = OMEGA2( 1/X[i] )
		for (k = (dlam2 & (-2)) - 1, co1 = LAM2[k + 2]; k >= 0; k -= 2)
			co1 = c_mulk(co1, j) ^ LAM2[k];
		if (co1 == 0) 
		{
			return -1;
		}
		// сейчас co1 = LAM2'( 1/X[i] )
		e[i] = c_mul(co, c_revers(co1));
		e[i] = c_mulk(e[i], X[i]);
		c_buf[X[i]] ^= e[i];
		// e[i] = co/co1
	}
	System.out.println(dlam2);
	return dlam2;
}
//функцию вызывает пользователь для декодирования сообщения 
public int c_decode(int[] buf) {
	int i, nerr;
	c_buf =  buf;
	nerr = c_decode0();
	for (i = t2; i<bufs; i++) c_buf[i - t2] = c_buf[i];
	return nerr;
}
}
