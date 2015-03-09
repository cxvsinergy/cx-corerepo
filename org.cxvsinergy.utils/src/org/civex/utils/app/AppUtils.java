package org.civex.utils.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppUtils 
{

	protected static List<String> makeParams(String[] args)
	{
		final List<String> params=new ArrayList<String>(20);
		for (final String arg:args) params.add(arg);
		return params;
	}
	
	public static boolean hasParam(List<String> params, String name)
	{
		final int index=params.indexOf("-"+name);
		return index>=0;
	}

	
	public static double resolveCommandParam(List<String> params, String name, double defaultValue)
	{
		String v=resolveCommandParam(params,name,null);
		return v==null?defaultValue:Double.parseDouble(v);
	}
	
	public static int resolveCommandParam(List<String> params, String name, int defaultValue)
	{
		final String v=resolveCommandParam(params,name,null);
		return v==null?defaultValue:Integer.parseInt(v);
	}
	
	
	public static String resolveCommandParam(List<String> params, String name, String defaultValue)
	{
		final int index=params.indexOf("-"+name);
		if (index<0 || index==params.size()-1) return defaultValue;
		return params.get(index+1);
	}
	
	public static String resolveCommandParam(Map<String,?> params, String name, String defaultValue)
	{
		final Object v=params.get(name);
		return v!=null?v.toString():defaultValue;
	}
	
	public static  int resolveCommandParam(Map<String,?> params, String name, int defaultValue)
	{
		final String v=resolveCommandParam(params,name,null);
		return v==null?defaultValue:Integer.parseInt(v);
	}

	
	
	public static void safeSleep(long millis)
	{
		try
		{
			Thread.sleep(millis);
		}
		catch(InterruptedException x)
		{
			x.printStackTrace();
		}
	}
	
	
	public static  Map<String, ?> list2map(List<String> args) 
	{
		final Map<String,Object> m=new HashMap<String,Object>();
		String prev=null;
		for (int ii=0;ii<args.size();ii++)
		{
			String v=args.get(ii);
			if (v.startsWith("-"))
			{
				prev=v.substring(1);
				m.put(v, Boolean.TRUE);
				continue;
			}
			if (prev==null) continue;
			m.put(prev,v); // adds the last value
		}
		return m;
	}
	
	
	
	public static int parseNumber(String v)
	{	
		return Integer.parseInt(v);
	}

}
