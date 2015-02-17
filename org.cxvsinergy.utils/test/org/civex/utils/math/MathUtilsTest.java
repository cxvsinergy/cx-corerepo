package org.civex.utils.math;

import java.io.FileWriter;
import java.math.BigInteger;

import junit.framework.TestCase;

import org.civex.utils.numeric.MathUtils;
import org.civex.utils.numeric.func.BigIntegerComposite;

public class MathUtilsTest extends TestCase
{
		public void testMe1()
		{
			long v= MathUtils.factorial(23);
			BigInteger bd=MathUtils.factBigInteger(172);
		}
		
		public void t1estPermut()
		{
			assertEquals(6,MathUtils.permut(3, 2));
			assertEquals(24,MathUtils.permut(4, 2));
			System.out.println(Long.MAX_VALUE-MathUtils.permut(10,10));
		}
		
		
		public void t1estPrimes() throws Exception
		{
			final int arr[]=new int[0x4fffff];
			arr[0]=310248241;
			MathUtils.primeNumberGen(arr,0);
			FileWriter wr=new FileWriter("c:\\workspaces\\primes8M3.txt");
			for (int ii=0;ii<arr.length;ii++)
			{
				wr.write(String.valueOf(arr[ii]));
				wr.write("\n");
				//System.out.println(arr[ii]);	
			}
			wr.flush();
			//System.out.println(arr[ii]);
		}
		
		public void t1estBigIntegerComposite()
		{
			
			BigIntegerComposite c=new BigIntegerComposite();
			//for (long l=0;l<Integer.MAX_VALUE;l++)
			{
				c.inc();
			}
			//assertEquals(Integer.MAX_VALUE,c.getBigInteger().longValue());
			
		}
}
