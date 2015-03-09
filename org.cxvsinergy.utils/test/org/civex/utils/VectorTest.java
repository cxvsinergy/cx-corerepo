package org.civex.utils;

import junit.framework.TestCase;

import org.civex.utils.numeric.INumericVector;
import org.civex.utils.numeric.RandomVector;
import org.civex.utils.numeric.SequencedVector;
import org.civex.utils.numeric.VectorNumber.AdjustedConstantVector;
import org.civex.utils.numeric.VectorSum;

public class VectorTest extends TestCase
{
	public void testVecSeq()
	{
		SequencedVector v=new SequencedVector(100,200); 
		assertEquals(100,v.vectorSize());
		INumericVector<Number> n=new AdjustedConstantVector<Number>(56,v);
		INumericVector<Number> n2=new AdjustedConstantVector<Number>(44,v);
		assertEquals(56,n.getDoubleValue(67),.00001);
		INumericVector<Number>  n3=new VectorSum(v,v,20,30);
		assertEquals((150+150)*20+30,n3.getDoubleValue(50),.00001);
		
	}
	
	public void testMe1()
	{
		final RandomVector<Number> v=new RandomVector<Number>(10);
		v.setTypeAll('G');
		System.out.println(v.toString());
	}
}
