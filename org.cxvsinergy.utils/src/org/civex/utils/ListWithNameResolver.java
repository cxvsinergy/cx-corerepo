package org.civex.utils;

import java.util.ArrayList;
import java.util.List;
/**********************************************************************
 * Copyright (c) 2015 cxvsinergy.org., and others. All rights reserved.
 * Contributors: 
 * 	@author Vitaly Samoilov - Initial implementation 
 * 	e-mail : nikevit@gmail.com
 * project : com.cxvsinergy.utils
 * History:
 * 		
 * 	
 *******************************************************************/
public class ListWithNameResolver<V> extends AbstractProxyList<V>
{

	final private IObjectResolver<List<V>,String ,V> nameResolver;
	
	public ListWithNameResolver(String names[])
	{
		this(new ArrayList<V>(names.length),new PlainListNameResolver<V>(names));
		ensureListSize(getListImpl(),names.length);
	}
	
	public ListWithNameResolver(PlainListNameResolver<V> nameResolver)
	{
		this(new ArrayList<V>(nameResolver.getSize()),nameResolver);
		ensureListSize(getListImpl(),nameResolver.getSize());
	}
	
	
	public ListWithNameResolver(int sz, IObjectResolver<List<V>,String ,V> nameResolver)
	{
		this(new ArrayList<V>(sz),nameResolver);
		ensureListSize(getListImpl(),sz);
	}
	
	public ListWithNameResolver(List<V> list, IObjectResolver<List<V>,String ,V> nameResolver)
	{
		super(list);
		this.nameResolver=nameResolver;
	}
	
	public IObjectResolver<List<V>,String ,V> getNameResolver()
	{
		return nameResolver; 
	}
	
	protected void ensureListSize(List<V> l,int sz)
	{
		for (int ii=0;ii<l.size();ii++) l.add(null);
	}
	/**simple implementation for names' resolver, no sorting, no indexes, no filter, no transformation**/
	public static class PlainListNameResolver<V> implements  IObjectResolver<List<V>,String ,V>
	{
		final private String names[];
		
		public PlainListNameResolver(String names[])
		{
			this.names=names;
		}
		
		public String [] getNames() {return names;}
		public int		 getSize()  {return names.length;}
		
		@Override
		public V resolveObjectValue(List<V> model, String name)
		{

			for (int ii=0;ii<names.length;ii++)
			{
				if (name.equals(names[ii])) return model.get(ii);
			}
			return null;
		}

		@Override
		public boolean upsertObjectValue(List<V> model, String name, V v)
		{
			for (int ii=0;ii<names.length;ii++)
			{
				if (!name.equals(names[ii])) continue;
				 model.set(ii, v);
				 return true;
			}
			return false;
			
		}
	}
	
}
