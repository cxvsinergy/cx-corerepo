package org.civex.utils.collections;

import org.civex.utils.NumericArray;


public class CircularNumericArray extends NumericArray
{
	private int leader = 0;


	public CircularNumericArray(int capacity) 
	{
		super(capacity);
	    buff=new double[capacity];
	}

	@Override
	protected void ensureCapacity(int len) 
	{
	}

	@Override
	public double get(int i) 
	{
	    return buff[(leader+i)%buff.length];
	}

	
	public void set(int i, double e) 
	{
		buff[(leader+i)%buff.length]=e;
	    return;
	}

	public void add(double e)
	{
	    if ((leader==0) && sz<buff.length)
	    {
	    	leader=0;
	    	buff[sz++]=e;
	    	return;
	    }
	    buff[leader]=e;
	    leader=(leader+1+sz)%sz;
	    
	}
	
	@Override
	public void clear() 
	{
		super.clear();
		this.leader=0;
	}

	@Override
	public double first() 
	{
		return sz>0?get(0):Double.NaN;
	}


	@Override
	public double last() 
	{
		return sz>0?get(sz-1):Double.NaN;
	}



}
