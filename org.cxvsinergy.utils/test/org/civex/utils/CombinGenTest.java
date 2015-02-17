package org.civex.utils;

import junit.framework.TestCase;

import org.civex.utils.generators.CombinationGenerator;
import org.civex.utils.numeric.MathUtils;

public class CombinGenTest extends TestCase
{
	public void testMe()
	{
		if (true) return;
		CombinationGenerator gen=new CombinationGenerator(37,11);
		
		assertEquals(4,MathUtils.combin(4, 3));
		assertEquals(6,MathUtils.combin(4, 2));//ab, ac,ad,bc,bd,cd
		System.out.println(MathUtils.combin(37,11));
		
		while(gen.hasNext())
		{
			int v[]=gen.next();
			//gen.debug(v);
		}
	}
}
