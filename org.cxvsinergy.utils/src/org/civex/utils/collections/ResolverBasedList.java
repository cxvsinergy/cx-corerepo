package org.civex.utils.collections;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;

import org.civex.utils.IObjectResolver;

public class ResolverBasedList<V,K> extends AbstractList<V>
{
	final private List<K> list;
	final private IObjectResolver<List<K>,K ,V> resolver;
	final private IObjectResolver<List<K>,V ,K> revResolver=null;
	
	public ResolverBasedList(final List<K> list,IObjectResolver<List<K>,K ,V> resolver)
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
	
	
	
	
   @Override
	public Iterator<V> iterator()
	{
		return new ResolverBasedListIterator(list.iterator());
	}




class ResolverBasedListIterator<V1,K1> implements Iterator<V>
   {
	 final Iterator<K> it;
	   
	 public ResolverBasedListIterator(Iterator<K> it)
	 {
		 this.it=it;
	 }
	@Override
	public boolean hasNext()
	{
		return it.hasNext();
	}

	@Override
	public V next()
	{
		final K r=it.next();
		return resolver.resolveObjectValue(list, r);
	}
	   
   }
}
