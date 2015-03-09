package org.civex.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

public class ReflectionUtils
{
	final static private ReflectionHelper eInstance=new ReflectionHelper();
	public ReflectionHelper geInstance() {return eInstance;}
	
	
	public Method getObjectSetter(String name, Class<?> type){ return null;}
	public Method getObjectGetter(String name) { return null;}
	
	
	public <T> T[] createArrayOf(Class<?> clazz, int sz)
	{
		final T r[]=(T[])Array.newInstance(clazz, sz);
		return r;
	}
	
	public <T> T createWithConstructor(Class<T> clazz, Object[] args)
	{
		return null;
	}
}
