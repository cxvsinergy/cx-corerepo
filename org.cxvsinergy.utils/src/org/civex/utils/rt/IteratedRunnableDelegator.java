package org.civex.utils.rt;

public class IteratedRunnableDelegator extends RunnableDelegator
{
	
	public IteratedRunnableDelegator(Runnable rt) 
	{
		super(rt);
	}

	@Override
	public void run() 
	{
		try
		{			
			while(thread!=null && !thread.isInterrupted()) rt.run();
		}
		catch(Exception x)
		{
			handleThreadException(x);
		}
		
	}

}
