package org.civex.utils;

import java.util.Random;

import junit.framework.TestCase;

import org.civex.utils.accessors.SimpleFuncAccessor;
import org.civex.utils.numeric.func.DirectMathFunc;

public class GenGenTest extends TestCase
{
	
	public void testOne()
	{
		double rate=0.03;
		int time=2;
		int principal=260000;
		// 1.34->
	}
	
	public void testFuncNumeric()
	 {
		NumericArray list =new NumericArray(10);
		list.set(0,10);
		list.set(1,20);
		list.set(2,100);
		//
		SimpleFuncAccessor da=new SimpleFuncAccessor(list);
		assertEquals(10,da.getAsNumeric(0),0.0001);
		assertEquals(20,da.getAsNumeric(1),0.0001);
		assertEquals(100,list.get(2),0.0001);
		assertEquals(100,da.getAsNumeric(2),0.0001);
		da.setResultFunction(DirectMathFunc.SQR);
		assertEquals(100,da.getAsNumeric(0),0.0001);
		assertEquals(400,da.getAsNumeric(1),0.0001);
		assertEquals(10000,da.getAsNumeric(2),0.0001);
	 }
	
	 public void testNumeric()
	 {
		 Random r=new Random();
		 NumericArray arr=new NumericArray(10);
		 for (int ii=0;ii<1000;ii++)
		 {
			int s=r.nextInt(37);
			//System.out.println(s);
		 }
		 
		 for (int ii=0;ii<arr.size();ii++)
		 {
			 
		 }
		 
	 }
}
