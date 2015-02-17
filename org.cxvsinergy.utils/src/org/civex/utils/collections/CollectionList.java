package org.civex.utils.collections;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;



public class CollectionList<T> extends AbstractList<T>
{
	final private Collection<T> coll;
	
	public CollectionList(Collection<T> coll)
	{
		this.coll=coll;
	}
	@Override
	public T get(int index)
	{
		if (coll instanceof List<?>) return ((List<T>)coll).get(index);
		final Iterator<T> it=coll.iterator();
		int ii=0;
		while(it.hasNext())
		{
			final T el=it.next();
			if(ii==index) return el;
			ii++;
		}
		throw new java.lang.IndexOutOfBoundsException(); 
	}

	@Override
	public int size()
	{
		return coll.size();
	}

}
