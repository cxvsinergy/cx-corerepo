package org.civex.utils.tests;

import java.util.concurrent.locks.Lock;

import junit.framework.TestCase;

public class TestCaseWorker implements Runnable
{
	protected Lock 	   lock;
	protected TestCase tcase;
	protected long counter;
	protected int  state;
	
	public TestCaseWorker(Lock lock, TestCase tcase)
	{
		this.tcase=tcase;
		this.lock=lock;
	}
	
	@Override
	public void run() 
	{
		state=1;
		try
		{
			while(!Thread.interrupted())
		    {
		        runWorker();
		        counter++;
		    }	
		}
		finally
		{
			state=0x100;
			lock.unlock();	
		}
	}
	
	protected void runWorker()
	{
		
	}
	

}
