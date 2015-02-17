package org.civex.messaging.proto;

import java.lang.reflect.Method;

public class MethodWrapper 
{
	final private Method method;
	final private Object paramVals[];
	final private Object paramTypes[];
	
	public MethodWrapper(Method method)
	{
		this.method=method;
		paramTypes=method.getParameterTypes();
		paramVals=new Object[paramTypes.length];
	}
	
	public Method getMethod() {return method;}
	public int getParamLength() { return paramVals.length;}
	
	public boolean isGetterMethod() {return false;}
	public boolean isSetterMethod() {return false;}
	
	public Object[] paramsFromBytes(byte buff[], int off, int sz)
	{
		return paramVals;
	}

	public Object executeUnSafe(Object obj, Object[] vals) 
	{
		try
		{
			return method.invoke(obj, vals);		
		}
		catch(Exception x)
		{
			return void.class;
		}
	}
	
	
}
