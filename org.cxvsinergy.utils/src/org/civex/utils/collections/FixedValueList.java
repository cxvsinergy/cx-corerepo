package org.civex.utils.collections;

import java.util.List;

import org.civex.utils.AbstractProxyList;

public class FixedValueList<E> extends AbstractProxyList<E>
{
	private E el;
	
	public FixedValueList(List<E> list,E el)
	{
		super(list);
		this.el=el;
	}

	@Override
	public boolean contains(Object o)
	{
		return el.equals(o);
	}

	@Override
	public E get(int index)
	{
		return el;
	}

	@Override
	public E set(int index, E element)
	{
		el=element;
		return el;
	}
	
	
}
