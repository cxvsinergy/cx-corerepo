package org.civex.utils;

public interface IRange<T> 
{
	public T getRangeMin();
	public T getRangeMax();
	public boolean betweenInclusive(T v);
	public boolean betweenExclusive(T v);
	public T getRangeMean();
	public double getRangeLength();
}
