package org.civex.utils;

import java.util.ArrayList;
import java.util.List;

public class ListWithResolver<V> extends AbstractProxyList<V>
{

	final private IObjectResolver<List<V>,String ,V> nameResolver;
	
	public ListWithResolver(int sz, IObjectResolver<List<V>,String ,V> nameResolver)
	{
		this(new ArrayList<V>(sz),nameResolver);
	}
	
	public ListWithResolver(List<V> list, IObjectResolver<List<V>,String ,V> nameResolver)
	{
		super(list);
		this.nameResolver=nameResolver;
	}
	
	public IObjectResolver<List<V>,String ,V> getNameResolver()
	{
		return nameResolver; 
	}
	
	
	
}
