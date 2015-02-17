package org.civex.utils;

import org.civex.testfwk.ObjectTestCase;
import org.civex.utils.collections.PrimeNumberSeq;
import org.civex.utils.numeric.AlgebraicNumber;
import org.civex.utils.numeric.MathUtils;
import org.civex.utils.numeric.SimpleNumber;
import org.civex.utils.stats.StatDeltaNumber;
import org.civex.utils.stats.StatsNumber;

public class StatNumberTest extends ObjectTestCase {
	public static byte primes[] = {2,3,5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,53};
	
	
	public void testPow()
	{
		long v=MathUtils.pow(10, 4);
		assertEquals(10000, v);
		assertEquals(10, MathUtils.pow(10, 1));
		assertEquals(1, MathUtils.pow(10, 0));
	}
	
	public void testPrimeSeq()
	{
		PrimeNumberSeq seq=new PrimeNumberSeq();
		performCollectionTest(seq, seq, seq);
		assertEquals(41,seq.getAsDouble(11),0.000001);
		assertEquals(7,seq.getAsDouble(2),0.000001);
		assertEquals(11,seq.getAsDouble(3),0.000001);
		assertEquals(11L,seq.findPrimeDiviser(11*17));
		assertEquals(10091L,seq.findPrimeDiviser(10091*10093));	
	}
	
	public void testFindMaxPrimeMul()
	{
		long r=1;
		int ii=0;
		for (;ii<primes.length;ii++)
			{
				if (r*primes[ii]<0) break;
				r*=primes[ii];
			}
		long v= 2L*3L*5*7*11*13*17*19*23*29*31*37*41*43*47;
		long v2=MathUtils.pow(41, 10);
		System.out.println(Long.toHexString(v2));
		System.out.println(Long.toHexString(r)+ "  ff->"+ii+"last-"+(int)(primes[ii-1]));		
	}

	public void testMe() {
		StatsNumber n = new StatsNumber(100);
		n.set(120);
		assertEquals(2, n.getCounter(), 0.001);
		assertEquals(110, n.getMean(), 0.0001);
		assertEquals(100, n.getMin(), 0.0001);
		assertEquals(120, n.getMax(), 0.0001);
	}

	public void testMe2() {
		final StatDeltaNumber sm = new StatDeltaNumber();
		sm.set(100);
		sm.set(120);
		sm.set(80);
		System.out.println(sm.getDeltaNumber());
		System.out.println(sm.getSqDeltaNumber());
	}

	public void testAlgebraicNumber() {
		final AlgebraicNumber ta = new AlgebraicNumber();
		Double d1 = 2D, d2 = 3D, d3 = 4D;
		SimpleNumber d4 = new SimpleNumber(2D);
		ta.add(d1);
		ta.mul(d2);
		ta.add(d3);
		ta.div(d4);
		assertEquals(5, ta.doubleValue(), 0.00001);
		d4.set(5);
		assertEquals(2, ta.doubleValue(), 0.00001);
		System.out.println(ta.printFormula());
	}
}
