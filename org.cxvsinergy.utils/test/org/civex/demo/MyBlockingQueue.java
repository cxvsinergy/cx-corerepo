package org.civex.demo;

import java.util.ArrayList;
import java.util.List;

public class MyBlockingQueue<T> 
{
	private final Object mutex=new Object();
	private final List<T>  queue;
	final private int capacity;
	
	public MyBlockingQueue(int capacity)
	{
		this.capacity=capacity;
		queue=new ArrayList<T>(capacity);
	}
	
	
	public void offer(final T v)
	{
		while(queue.size()==capacity)
		{
			synchronized(mutex) 
			{
				try
				{
					mutex.wait();	
				}
				catch(Exception x)
				{
					x.printStackTrace();
				}			
			}			
		}
		
		synchronized(mutex)
		{
			queue.add(v);
			mutex.notifyAll();	
		}
		
	}
	
	
	public T take()
	{
			synchronized(mutex) 
			{
				while(queue.size()==0)
				{
					try
					{
						mutex.wait();	
					}
					catch(Exception x)
					{
						x.printStackTrace();
					}			
				}
				final T result=queue.remove(0);
				mutex.notifyAll();
				return result;
				
			}
	}
}
