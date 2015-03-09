package org.civex.utils.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.civex.utils.rt.IteratedRunnableDelegator;
import org.civex.utils.rt.RunnableDelegator;

public abstract class CivexAppMain 
{
	final private List<RunnableDelegator> 	     threads	 =new ArrayList<RunnableDelegator>();
	private static Thread mainThread;
	
	public abstract void launchInstance(Map<String,Object> paramMap) throws Exception;
	
	public int appExit(int code) throws Exception
	{
		return code;
	}
	
	
	public static void startApp(String args[], Class<? extends CivexAppMain> clazz)
	{
		final Map<String,Object> paramMap=new HashMap<String,Object>();
		mainThread=Thread.currentThread();
		
		CivexAppMain app=null;
		try
		{
			app=clazz.newInstance();
			app.launchInstance(paramMap);
			app.appExit(0);
			System.exit(0);
		}
		catch(Exception x)
		{
			if (app==null)
			{
				x.printStackTrace();
				return;
			}
			app.handleInstanceFatal(x);
		}
		
	}
	
	protected void parseAppParams(String args[], Map<String,Object> params)
	{
		
	}
	
	protected void handleInstanceFatal(Exception x)
	{
		x.printStackTrace();
	}
	
	protected void runServiceThread(final Runnable r, boolean iterated, int counter)
	{
		createThread(r,iterated);
		if (counter>1) throw new RuntimeException("Does not support>1 threads");
		// for cloneable runnable
		for (int ii=1;ii<counter;ii++)
		{
			//Cloneable cl=(Cloneable)r;
			//createThread(().c);
		}
	}
	
	
	protected void createThread(Runnable r, boolean iterated)
	{
		final RunnableDelegator rd=iterated?new IteratedRunnableDelegator(r):new RunnableDelegator(r);
		threads.add(rd);
		rd.safeStart();
		
	}
	
	protected  RunnableDelegator getRunnableDelegator(final Runnable r)
	{
		for(RunnableDelegator rd:threads)
		{
			if (rd.getRunnable()==r) return rd;
		}
		return null;
	}
	
	protected void stopServiceThread(final Runnable r)
	{
		
		final RunnableDelegator rd=getRunnableDelegator(r);
		if (rd==null) return;
		final Thread.State state=rd.getRunnableThread().getState();
		if (state==Thread.State.NEW) return;
		if (state==Thread.State.TERMINATED) 
		{
			threads.remove(r);
			return;
		}
		rd.getRunnableThread().interrupt();
		threads.remove(r);
		
	}
}
