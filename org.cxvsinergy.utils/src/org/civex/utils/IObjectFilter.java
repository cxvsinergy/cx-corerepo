package org.civex.utils;

public interface IObjectFilter<M,T> 
{
	public boolean filter(M model, T element);
	public boolean filter(T element);
	public void reset();
}
