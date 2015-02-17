package org.civex.utils.numeric;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class ProtoVector <V extends Number> implements INumericVector<V>
{

	@Override
	public final Number getValueByIndex(int index) 
	{
		return getDoubleValue(index);
	}


	@Override
	public double[] copyTo(final double[] target) 
	{
		for (int ii=0;ii<vectorSize();ii++) target[ii]=getDoubleValue(ii);
		return target;
	}
	
	public List<?> asList()
	{
		return Arrays.asList(copyTo(new double[vectorSize()]));
	}

   
	public Iterator<V> getIterator()
	{
		return null;
	}
	
	
	@Override
	public String toString() 
	{
		return asList().toString();
	}

	public abstract static class ProtoFuncVector<V extends Number> extends  ProtoVector<V>
	{
		final protected INumericVector<V> v1;
		final protected INumericVector<V> v2;
		
		public ProtoFuncVector(INumericVector<V> v1, INumericVector<V> v2)
		{
			this.v1=v1;
			this.v2=v2;
		}

		@Override
		public int vectorSize() {return v1.vectorSize();}
	}
	
	public abstract static class ProtoMovingVector<V extends Number> extends  ProtoVector<V>
	{
		final protected INumericVector<V> v1;
		final protected int shift;
		final protected double missed;
		
		
		public ProtoMovingVector(INumericVector<V> v1, int shift, double missed)
		{
			this.v1=v1;
			this.shift=shift;
			this.missed=missed;
		}

		@Override
		public int vectorSize() {return v1.vectorSize();}

		@Override
		public double getDoubleValue(int index) 
		{
			final int newIndex=index+shift;
			if (newIndex<0 || newIndex>=vectorSize()) return missed;
			return v1.getDoubleValue(newIndex);
		}
		
		protected double getValueForFixed()
		{
			return missed;
		}
	}
}
