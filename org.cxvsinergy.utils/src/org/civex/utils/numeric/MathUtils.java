package org.civex.utils.numeric;

import java.math.BigInteger;

public class MathUtils 
{
	private static long  POW_10[]={1L,10L,100L,1000L,10000L,100000,1000000L,};
	private static long  POW_02[]={1L,2L,4L,8,16,32,64,128,256,512,1024,2048,4096,8192,16384};
	private static byte  MUL_PRIMES[] = {2,3,5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,53};
	
	
public static final int MAX_LONG_FACTORIAL=23;
	
	


	public static long permut(int n, int k)
	{
		long r=1;
		for (int ii=k;ii<=n;ii++) r*=ii;
		return r;
	}
	
	public static long combin(final int n, final int k)
	{
		return MathUtils.prod(n-k+1,n)/factorial(k);		
	}

	
	
	public static long fibonachi(long v)
	{
		long p1=1, p2=2;
		while(true)
		{
			long p=p1+p2;
			if (p1+p2>=v) return p1+p2;
			p1=p2;p2=p;
		}
	}
	
	
	public static long factorial(int v)
	{
		if (v>MAX_LONG_FACTORIAL) throw new ArithmeticException("Factorial is too big ...");
		long r=1;
		for (int ii=2;ii<=v;ii++) r*=ii;
		return r;
	}
	
	
	public static BigInteger factBigInteger(int v)
	{
		if (v<=MAX_LONG_FACTORIAL) return  BigInteger.valueOf(factorial(v)); 
		BigInteger r=BigInteger.valueOf(1);
		for (int ii=1;ii<v;ii++) 
			{
				r=r.multiply(BigInteger.valueOf(ii));
			}
		return r;
	}
	
	
	public static void primeNumberGen(final int arr[], final int off)
	{
		int v=arr[off];
		int ci=off+1;
		final int l=arr.length-1;
		while(ci<l)
		{
			v++;v++;
			if (v%5==0 || v%3==0 || findPrimeDiviser(v)!=0) continue;
			arr[ci++]=v;
		}
		
	}
	
	
	public static long nextPrimeNumberGen(final long off)
	{
		long v=off;
		while(v>0)
		{
			v++;v++;
			if (v%5==0 || v%3==0 || findPrimeDiviser(v)!=0) continue;
			return v;
		}
		return -1;
		
	}
	
	protected static int findPrimeDiviser(int v)
	{
		final int k=(int)(Math.sqrt(v));
		for (int kk=7;kk<=k;) //4,2,4,2,4,2,4,2,4,2 
		{
			if (v%kk==0) return kk;
			kk++;kk++;
		}
		return 0;
	}

	
	protected static int findPrimeDiviser(long v)
	{
		final long k=(int)(Math.sqrt(v));
		for (int kk=7;kk<=k;) //4,2,4,2,4,2,4,2,4,2 
		{
			if (v%kk==0) return kk;
			kk++;kk++;
		}
		return 0;
	}
	
	public static long pow(int v, int p)
	{
		if (p==0) return 1;
		long r=v;
		for (int ii=2;ii<=p;ii++) r*=v;
		return r;
	}
	
	public static double calcDelta(double v1, double v2)    {return v2/v1-1;}
	public static double calcDeltaAbs(double v1, double v2) {return Math.abs(v2/v1-1);}
	public static double sqr(double v1) {return v1*v1;}
	public static long   sqr(long v1) {return v1*v1;}
	public static double gammaFunc() {return Double.NaN;}
	public static long prod(long first, long last) {long r=1; for (long ii=first;ii<=last;ii++) r*=ii; return r;}
	public static double sum(double v[]) {return Double.NaN;}
	
}
