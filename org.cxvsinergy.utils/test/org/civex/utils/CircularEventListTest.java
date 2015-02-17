package org.civex.utils;

import org.civex.utils.collections.CircularArrayList;
import org.civex.utils.collections.CircularNumericArray;

public class CircularEventListTest extends TestCase 
{
	public void testCircularNumeric()
	{
		CircularNumericArray clist=new CircularNumericArray(8);
		for (int ii=0;ii<8;ii++) clist.add(ii);
		assertEquals(8,clist.size());
		assertEquals(2,clist.get(2),0.00001);
		assertEquals(7,clist.get(7),0.00001);
		clist.add(8);
		assertEquals(8,clist.size());
		assertEquals(1,clist.get(0),0.00001);
		assertEquals(8,clist.get(7),0.00001);
		for (int ii=9;ii<17;ii++) 
		{
			clist.add(ii);
			assertEquals(ii,clist.get(7),0.0001);
			assertEquals(ii,clist.last(),0.0001);
		}
		
	}
	
	
	public void testCircular()
	{
		CircularArrayList<Integer> clist=new CircularArrayList<Integer>(8);
		for (int ii=0;ii<8;ii++) clist.add(ii);
		assertEquals(8,clist.size());
		assertEquals(2,clist.get(2),0.00001);
		assertEquals(7,clist.get(7),0.00001);
		clist.add(8);
		assertEquals(8,clist.size());
		assertEquals(1,clist.get(0),0.00001);
		assertEquals(8,clist.get(7),0.00001);
		for (int ii=9;ii<17;ii++) 
		{
			clist.add(ii);
			assertEquals(ii,clist.get(7),0.0001);
			//assertEquals(ii,clist.last(),0.0001);
		}
		
	}
	
	
}
