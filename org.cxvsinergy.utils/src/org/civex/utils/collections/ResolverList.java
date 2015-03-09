package org.civex.utils.collections;

import java.util.AbstractList;
import java.util.List;

import org.civex.utils.IObjectResolver;

public class ResolverList<V,K> extends AbstractList<V>
{
	final private List<K> list;
	final private IObjectResolver<List<K>,K ,V> resolver;
	final private IObjectResolver<List<K>,V ,K> backResolver=null;
	
	public ResolverList(final List<K> list,IObjectResolver<List<K>,K ,V> resolver)
	{
		this.list=list;
		this.resolver=resolver;
	}
	
	@Override
	public V get(int index)
	{
		final K r=list.get(index);
		return resolver.resolveObjectValue(list, r);
	}
	@Override
	public int size()
	{
		return list.size();
	}
	
	
	
}
