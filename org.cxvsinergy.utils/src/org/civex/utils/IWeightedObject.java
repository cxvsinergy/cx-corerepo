package org.civex.utils;

public interface IWeightedObject<K,T>
{
	public double getAbsoluteWeight(T ctx);
	public double getWeightedNuber();
	public K getWeightedObject();
	
}
