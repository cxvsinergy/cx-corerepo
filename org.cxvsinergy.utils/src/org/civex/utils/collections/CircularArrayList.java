package org.civex.utils.collections;

import java.util.AbstractList;
import java.util.Collection;


public class CircularArrayList<E> extends AbstractList<E>
{
	final private Object buff[];
	private int leader = 0;
	private int sz;
	

	public CircularArrayList(int capacity) 
	{
	    buff=new Object[capacity];
	}

	
	protected void ensureCapacity(int len) 
	{
	}

	
	public E exch(int index, E e)
	{
		E r=(E)(buff[(leader+index)%buff.length]);
		set(index,e);
		return r;
	}
	
	@Override
	public E get(int i) 
	{
	    return (E)(buff[(leader+i)%buff.length]);
	}

	
	public void set(int i, double e) 
	{
		buff[(leader+i)%buff.length]=e;
	    return;
	}

	public boolean add(final E e)
	{
	    if ((leader==0) && sz<buff.length)
	    {
	    	leader=0;
	    	buff[sz++]=e;
	    	return true;
	    }
	    buff[leader]=e;
	    leader=(leader+1+sz)%sz;
	    return true;
	}
	
	
	@Override
	public boolean addAll(final int index, final Collection<? extends E> c) 
	{
		int counter=index;
		for(final E el:c)
		{
			if (counter++<sz)  add(index,el); else add(el);
		}
		return true;
	}


	@Override
	public void add(int index, E element) 
	{
		if (index==sz) {add(element);return;}
		set(index,element);
	}


	
	
	@Override
	public void clear() 
	{
		super.clear();
		this.leader=0;
	}


	public E first() 
	{
		return sz>0?get(0):null;
	}



	public E last() 
	{
		return sz>0?get(sz-1):null;
	}


	@Override
	public int size() 
	{
		return sz;
	}

}
