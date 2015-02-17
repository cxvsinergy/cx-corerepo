package org.civex.utils.collections;

import java.util.Arrays;


public class CircularByteArray implements Cloneable
{
	private final int n; // buffer length
	private int leader = 0;
	protected byte buff[];
	protected int sz;


	public CircularByteArray(int capacity) 
	{
	    buff=new byte[capacity];
	    n=capacity;
	}

	

	public byte get(int i) 
	{
	    return buff[(leader+i)%sz];
	}

	

	public void add(byte e)
	{
	    if ((leader==0) && sz<n)
	    {
	    	leader=0;
	    	buff[sz++]=e;
	    	return;
	    }
	    buff[leader]=e;
	    leader=(leader+1+sz)%sz;
	    
	}
	
	public void clear() 
	{
		Arrays.fill(buff, (byte)0);
		this.leader=0;
	}

	public byte first() 
	{
		return sz>0?get(0):0;
	}


	public byte last() 
	{
		return sz>0?get(sz-1):0;
	}

	public Object clone() throws CloneNotSupportedException 
	{
		return super.clone();
	}

	

}
