package org.civex.utils.rt;
/**********************************************************************
 * Copyright (c) 2014 cxvsinergy.org., and others. All rights reserved.
 * Contributors: 
 * 	@author Vitaly Samoilov - Initial implementation 
 * 	e-mail : nikevit@gmail.com
 * project : com.cxvsinergy.utils
 * History:
 * 		
 * 	
 *******************************************************************/
public abstract class SimpleWorker<T> implements Runnable
{
	protected final T ctx;
	
	public SimpleWorker(T obj)
	{
		this.ctx=obj;
	}
	@Override
	public void run()
	{
		try
		{
			beforeStart();
			runSafe();
		}
		catch(Exception x)
		{
			x.printStackTrace();
		}
		finally
		{
			afterFinish();
		}
	}
	
	
	protected abstract void runSafe();
	protected long getTime() {return System.currentTimeMillis();}
	protected void beforeStart() {}
	protected void afterFinish() {}
	protected T getWorkerContext() {return ctx;}	
}
