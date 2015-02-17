package org.civex.utils.collections;

import java.util.Comparator;
import java.util.List;

import org.civex.utils.AbstractProxyList;
import org.civex.utils.IObjectFilter;

public class SortedList<E, C extends Comparator<E>> extends AbstractProxyList<E>
{
	final protected Comparator<E> cmp;
	protected IObjectFilter<?,E> filter;
	protected boolean hasdup=true;
	
	public SortedList(final List<E> list, final Comparator<E> c) 
	{
		super(list);
		cmp=c;
	}	
	
	
	@Override
	public boolean add(E e) 
	{
		if (!filter.filter(null,e)) return false;
		super.add(e);
		resort(size()-1);
		return true; 
	}

	protected void resort(int index)
	{
		
	}

	@Override
	public void add(int index, E element) 
	{
		if (!filter.filter(null,element)) return;
		super.add(index, element);
	}



	public boolean isOrdered() {return true;}
	
	
	public boolean hasDuplicates()	{return hasdup;	}
	
	public void removeWithFilter(IObjectFilter<?,E> filter)
	{
		final int sz=size();
		for (int ii=sz-1;ii>=0;ii--)
		{
			if (!filter.filter(null,get(ii))) continue;
			remove(ii);
		}
	}

}
