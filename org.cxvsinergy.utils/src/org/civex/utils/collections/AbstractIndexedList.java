package org.civex.utils.collections;

import java.util.Comparator;
import java.util.List;

import org.civex.utils.AbstractProxyList;

public abstract class AbstractIndexedList<E> extends AbstractProxyList<E>
{
	protected ListBaseArray indexArr;
	
	public AbstractIndexedList(List<E> list) 
	{
		super(list);
	}

	@Override
	public E get(int index) 
	{
		return super.get(convertToNaturalIndex(index));
	}
	
	@Override
	public int indexOf(Object o) 
	{
		final int index=super.indexOf(o);
		return index<0?index:convertFromNaturalIndex(index);
	}

	protected abstract int convertToNaturalIndex(int index);
	protected abstract int convertFromNaturalIndex(int index);
	protected abstract void reindex();
	
		
	public class ComparatorBasedIndexedList<E> extends AbstractProxyList<E>
	{
		final private  Comparator<E> c;
		
		public ComparatorBasedIndexedList(List<E> list, Comparator<E> c)
		{
			super(list);
			this.c=c;
			
		}
		
		
	}
}
