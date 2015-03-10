package org.civex.utils.resources;
/**********************************************************************
 * Copyright (c) 2014 cxvsinergy.org., and others. All rights reserved.
 * Contributors: 
 * 	@author Vitaly Samoilov - Initial implementation 
 * 	e-mail : nikevit@gmail.com
 * project : com.cxvsinergy.utils
 * History:
 * 		
 * 	
 *******************************************************************/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.civex.utils.IObjectResolver;
/***
 *  Describes the basic approach for data loaders and implement basic collectors for properties, key=values pairs and their combinations
 * @author nikehome
 *
 * @param <T> an object used for collecting results
 */
public class GenericDataHandler<T> implements IObjectResolver<IResourceDescriptor,Integer,CharSequence>
{
	
	public static final GenericDataHandler.DirectObjectResolver<CharSequence,CharSequence> DIRECT_STRING_RESOLVER=new GenericDataHandler.DirectObjectResolver<CharSequence,CharSequence>();
	protected String commentPfx="#";
	protected boolean ignoreEmptyLine=true;
	final protected T collector;
	protected char quotes=0;
	
	
	public GenericDataHandler(T collector)
	{
		this.collector=collector;
	}
	@Override
	public CharSequence resolveObjectValue(IResourceDescriptor model, Integer obj)
	{
		return null;
	}
	
	
	public T getCollectorObject() {return collector;}

	@Override
	public boolean upsertObjectValue(IResourceDescriptor model, Integer line, CharSequence str)
	{
		String s=str.toString();
		if (s.startsWith(commentPfx)) return false;
		if (s.trim().length()==0) return false;
		
		processStringContent(line.intValue(),s);
		return true;
	}

	protected void processStringContent(int line, String str)
	{
		
	}
	
	
	public static class ListDataHandler<K> extends GenericDataHandler<List<K>>
	{
		final protected IObjectResolver<List<K>,CharSequence,K> resolver;
		
		public ListDataHandler(List<K> collector, IObjectResolver<List<K>,CharSequence,K> resolver)
		{
			super(collector);
			this.resolver=resolver;
		}

		@Override
		protected void processStringContent(int line, String str)
		{
			collector.add(resolver.resolveObjectValue(collector, str));
		}
	}
	
	public static class StringMapDataHandler<K> extends GenericDataHandler<Map<CharSequence,K>>
	{
		final protected IObjectResolver<CharSequence,CharSequence,K> resolver;
		
		public StringMapDataHandler(IObjectResolver<CharSequence,CharSequence,K> resolver)
		{
			this(new HashMap<CharSequence,K>(),resolver);
		}
		
		public StringMapDataHandler(final Map<CharSequence,K> collector,IObjectResolver<CharSequence,CharSequence,K> resolver)
		{
			super(collector);
			this.resolver=resolver;
		}

		@Override
		protected void processStringContent(int line, String str)
		{
			int index=str.indexOf('=');
			if (index<0) return;
			final String name=str.substring(0,index);
			final String value=str.substring(index+1);
			collector.put(name, resolver.resolveObjectValue(name, value));
		}
	}
	
	
	public static class StringListMapDataHandler<S extends CharSequence,K> extends GenericDataHandler<List<Map<S,K>>>
	{
		final private String delimeter;
		final protected IObjectResolver<CharSequence,CharSequence,K> resolver;

		public StringListMapDataHandler(IObjectResolver<CharSequence,CharSequence,K> resolver,String delimeter)
		{
			this(new ArrayList<Map<S,K>>(1000),resolver,delimeter);
		}
		
		
		public StringListMapDataHandler(List<Map<S, K>> collector,  IObjectResolver<CharSequence,CharSequence,K> resolver,String delimeter)
		{
			super(collector);
			this.delimeter=delimeter;
			this.resolver=resolver;
		}

		@Override
		protected void processStringContent(int line, String str)
		{
			collector.add(parseMapString(str,prepareRecordMap()));
		}
		
		protected  Map<S,K> parseMapString(String str, Map<S,K> m)
		{
			final int len=str.length();
			int start=0,end=0;
			
			while(start<len)
			{
			  end=str.indexOf(delimeter,start);
			  end=end>0?end:len;
			  int pidx=str.indexOf('=', start);
			  if (pidx<0 || pidx>end) // ignore, if we have any broken part between delimeters
			  {
				  start=end+1;
				  continue;
			  }
			  final S name=(S)str.substring(start, pidx).trim();
			  final String value=str.substring(pidx+1,end);
			  m.put(name, resolver.resolveObjectValue(name, value));
			  start=end+1;
			}
			return m;
		}
		
		protected Map<S,K> prepareRecordMap()
		{
			return new HashMap<S,K>();
		}
	}
	
	
	
	public static class DirectObjectResolver<M,Z> implements IObjectResolver<M,Z,Z> 
	{
		@Override
		public Z resolveObjectValue(M model, Z obj)
		{
			return obj;
		}

		@Override
		public boolean upsertObjectValue(M model, Z obj, Z v)
		{
			return false;
		}
	}
	
	
	public static class NumericObjectResolver<M,Z> implements IObjectResolver<M,Z,Double> 
	{
		@Override
		public Double resolveObjectValue(M model, Z obj)
		{
			return obj==null?Double.NaN:Double.valueOf(obj.toString());
		}

		@Override
		public boolean upsertObjectValue(M model, Z obj, Double v)
		{
			return false;
		}
	}
}
