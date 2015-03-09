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
package org.civex.utils;

import java.util.Iterator;
/**sugar wrapper for foreach construction**/
public abstract class IteratableObjectWrapper<T> implements Iterable<T>
{
	final protected Iterator<T> it;
	
	public IteratableObjectWrapper(Iterator<T> it)
	{
		this.it=it;
	}

	@Override
	public Iterator<T> iterator()
	{
		return it;
	}
		
}
