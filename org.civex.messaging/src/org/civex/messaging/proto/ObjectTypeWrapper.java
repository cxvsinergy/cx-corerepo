package org.civex.messaging.proto;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

public class ObjectTypeWrapper 
{
	final private Class<?> clazz;
	final private Map	setters=null;
	final private Map	getters=null;
	
	
	public ObjectTypeWrapper(Class<?> clazz)
	{
		this.clazz=clazz;
	}
	
	public MethodWrapper getCodedMethodWrapper(int index)
	{
		return null;
	}
	
	
	
	public Method getMethod(int code) {	return null;}
	
	public Class<?> getObjectType(){return clazz;}
	
	public Map<String,?> getAsMap(Object o)
	{ 
		return Collections.emptyMap();
	}
	
	public void setObjectValue(Object o, String name, Object val)
	{
	
	}
	
	public Object getObjectValue(Object o, String name)
	{
		return null;
	}
	
}
