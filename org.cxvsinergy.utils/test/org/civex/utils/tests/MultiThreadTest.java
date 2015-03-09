package org.civex.utils.tests;

import java.util.concurrent.locks.Lock;

public class MultiThreadTest  
{

	
	protected void performMultiThreading(final TestCaseWorker workers[], Lock l,final int time)
	{
		
		for (TestCaseWorker w:workers)
		{
			new Thread(w).start();
		}
	}
}
