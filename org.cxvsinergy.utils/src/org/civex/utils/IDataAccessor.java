package org.civex.utils;

import java.util.Iterator;

public interface IDataAccessor<T> 
{
	public T getByIndex(long index);
	public long getCollectionSize();
	public double getAsNumeric(double index);
	public String getAsString(long index);
	public T getLastElement();
	public Iterator<T> getIterator();
	public Object apply(Object func, IObjectFilter<Object,T> filter, Object... params);
	
}
