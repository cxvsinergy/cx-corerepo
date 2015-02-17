package org.civex.messaging.proto;

import java.lang.reflect.Method;
import java.util.Map;

import org.civex.messaging.TOMObjectMessage;

public class ServiceMessageController 
{
	private Map<Long,ObjectTypeWrapper> registeredObj; 
	
	public void process(TOMObjectMessage msg)
	{
			ObjectTypeWrapper wrapper=registeredObj.get(msg.getTargetObject());
			if (wrapper==null) return;
			while(true)
			{
				int objCode=0;
				int mthCode=0;
				final Method m=wrapper.getMethod(objCode);
				final MethodWrapper mwr=null;//wrapper.getMethodWrapper(mthCode);
				final Object vals[]=mwr.paramsFromBytes(msg.getBuffer(), 0,0);
				mwr.executeUnSafe(null,vals);
			}
	}
}


class ObjectWrapper
{

	public Method getMethod(int code) 
	{
		return null;
	}
	
	
	public boolean convert(Method m)
	{
		return false;
	}
}
