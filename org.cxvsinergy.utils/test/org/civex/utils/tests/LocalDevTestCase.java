package org.civex.utils.tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;

abstract class LocalDevTestCase extends TestCase
{
	protected boolean local=true;
	protected boolean rtEclipse=false;
	
	public LocalDevTestCase()
	{
		try
		{
			Class<?> clazz=Class.forName("org.eclipse.jdt.internal.junit.runner.TestExecution");
			rtEclipse=clazz!=null;
		}
		catch(Exception x)
		{
			rtEclipse=false;
		}

	}
	

	public void testMe()
	{
		assertTrue(1==2);
	}

	protected boolean isLocalTest() {return rtEclipse==true && local;}

	@Override
	public void run(TestResult result) 
	{
		
		if (!isLocalTest())
		{
			result.startTest(new TestWrapper());
			return;
		}
		super.run(result);
	}

	
	public static class TestWrapper implements Test
	{

		protected int counter=0;
		@Override
		public int countTestCases() 
		{
			return 0;
		}

		@Override
		public void run(TestResult result)
		{
			counter++;
		}
		
		
	}
	
}
