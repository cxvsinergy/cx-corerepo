package org.civex.utils;

public interface IdentifiedObject<T> 
{
	public T getObjectId();
	public Class<?> getObjectClassifier();
	public boolean isDefined();
	public long getLongHash();
	public long getObjectVersion();

}