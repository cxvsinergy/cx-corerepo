package org.civex.utils.collections;
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
import java.util.ArrayList;
import java.util.List;

import org.civex.utils.AbstractProxyList;


public class AutoGeneratedList<E> extends  AbstractProxyList<E>
{
	final protected E protoObj;

	public AutoGeneratedList(E protoObj)
	{
		this(new ArrayList<E>(),protoObj);
	}
	
	public AutoGeneratedList(List<E> list, E protoObj)
	{
		super(list);
		this.protoObj=protoObj;
	}

	@Override
	public E get(int index)
	{
		while (size()<index+1)
		{
			super.add(cloneListObject());
		}
		return super.get(index);
	}
	
	public E getProtoObject()
	{
		return protoObj;
	}
	
	
	protected E cloneListObject()
	{
		try
		{
			return (E)protoObj.getClass().newInstance();
		} 
		catch (InstantiationException | IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
	}
}
