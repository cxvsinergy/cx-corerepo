package org.civex.utils.collections;

import java.util.Iterator;
import java.util.List;

import org.civex.utils.AbstractProxyList;

public class ListWithExclusion<E> extends  AbstractProxyList<E> 
{
	private int firstExcludedElement=0;
	
	public ListWithExclusion(final List<E> list) 
	{
		super(list);
	}
	
	

	@Override
	public boolean remove(Object o) 
	{
		final int index=getListImpl().indexOf(o);
		if (index<0) return false;
		return remove(index)==o;
	}
	

	@Override
	public E remove(int index) 
	{
		final int last=size()-1;
		exchangeElements(index, last);
		firstExcludedElement++;
		return super.get(last);
	}
	
	

	@Override
	public int size() {	return super.size()-firstExcludedElement;}

	@Override
	public void clear() {super.clear(); firstExcludedElement=0;}
	
	
	public void renew()
	{
		firstExcludedElement=0;
	}

	@Override
	public boolean contains(Object o) 
	{
		final int index=indexOf(o);
		return hasIndexInRange(index);
	}
	
	
	public boolean recover(E o)
	{
		final int index=getListImpl().indexOf(o);
		if (index<0) return false;
		if (index<size()) return false; // nothing to recover		
		exchangeElements(size(), index);
		firstExcludedElement--;
		return true;
	}

	@Override
	public Iterator<E> iterator() 
	{
		return new BasicListIterator<E>(this);
	}
		
}
