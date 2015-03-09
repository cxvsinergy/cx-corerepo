package org.civex.utils.collections;

import java.util.List;

import org.civex.utils.AbstractProxyList;

public class AggregatedList<E> extends AbstractProxyList<E>
{

	public AggregatedList(List<E> list)
	{
		super(list);
	}

}
