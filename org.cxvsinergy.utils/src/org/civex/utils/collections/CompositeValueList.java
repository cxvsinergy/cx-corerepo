package org.civex.utils.collections;

import java.util.AbstractList;
import java.util.List;

import org.civex.utils.AbstractProxyList;
import org.civex.utils.IObjectResolver;

public class CompositeValueList<T> extends AbstractProxyList<T>
{
	public CompositeValueList(List<T> list) 
	{
		super(list);
	}

	
	public<G> List<G> getObjectList(IObjectResolver<List<T>,T,G> resolver)
	{
		return new AccessorValueList<List<T>,T,G>(resolver);
	}
	
	
	 class AccessorValueList<M,T,G> extends AbstractList<G>
	{
		
		final private IObjectResolver<M,T,G> resolver;
		
		public AccessorValueList(final IObjectResolver<M,T,G> resolver) 
		{
			this.resolver=resolver;
		}

		@Override
		public G get(int index) 
		{
			return resolver.resolveObjectValue((M)getModelList(), getModelList().get(index));
		}

		@Override
		public int size() 
		{
			return CompositeValueList.this.size();
		}

		protected List<T> getModelList()
		{
			return (List<T>) CompositeValueList.this;
		}
		
	}
}
