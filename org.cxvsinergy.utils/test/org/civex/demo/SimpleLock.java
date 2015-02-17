package org.civex.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SimpleLock implements Lock
{
	private final AtomicInteger mutex=new AtomicInteger(0); 
	@Override
	public void lock() 
	{
		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException 
	{
		
	}

	@Override
	public boolean tryLock() 
	{
		return mutex.compareAndSet(0,1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException 
	{
		return tryLock();
	}

	@Override
	public void unlock() 
	{
	   mutex.set(0);	
	}

	@Override
	public Condition newCondition() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
