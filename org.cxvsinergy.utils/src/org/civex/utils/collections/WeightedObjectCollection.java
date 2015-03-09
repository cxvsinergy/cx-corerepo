package org.civex.utils.collections;

import java.util.List;

import org.civex.utils.IWeightedObject;

public class WeightedObjectCollection<T> extends AggregatedList<T>
{

	public WeightedObjectCollection(List<T> list)
	{
		super(list);
	}
	
	public void addWeightedObject(T t, double weight, boolean remained) 
	{
		
		
	}
	
	
	private static class ListWeightedObject<T> implements IWeightedObject<T,List<T>>
	{
		final private double weight;
		final private T obj;
		
		public ListWeightedObject(T obj, double weight)
		{
			this.weight=weight;
			this.obj=obj;
		}
		@Override
		public double getAbsoluteWeight(List<T> x)
		{
			return 0;
		}

		@Override
		public double getWeightedNuber() {	return weight;}

		@Override
		public T getWeightedObject() {return obj;}
		
	}

}
