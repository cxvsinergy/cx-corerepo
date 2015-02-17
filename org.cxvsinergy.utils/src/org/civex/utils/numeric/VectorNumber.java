package org.civex.utils.numeric;

import java.util.Arrays;


public class VectorNumber<V extends Number> extends ProtoVector<V>
{

	final private double vals[];
	
	public VectorNumber(int sz)
	{
		vals=new double[sz];
	}
	
	public void setElementValue(int index, double v)
	{
		vals[index]=v;
	}

	@Override
	public int vectorSize() 
	{
		return vals.length;
	}

	@Override
	public double getDoubleValue(int index) 
	{
		return vals[index];
	}

	
	public void reset()
	{
		Arrays.fill(vals, Double.NaN);
	}

	@Override
	public double[] copyTo(double[] target) 
	{
		System.arraycopy(vals, 0, target, 0, vals.length);
		return target;
	}
	
	
	public static class ConstantVector<V  extends Number> extends ProtoVector<V>
	{
		final private int sz;
		final private double val;
		
		public ConstantVector(int sz, double val)
		{
			this.sz=sz;
			this.val=val;
		}
		@Override
		public double getDoubleValue(int index) 
		{
			return val;
		}

		@Override
		public int vectorSize() 
		{
			return sz;
		}	
	}
	
	public static class AdjustedConstantVector<V  extends Number> extends ProtoVector<V>
	{
		final private INumericVector<?> vector;
		final private double value;
		
		public AdjustedConstantVector(final double value,final INumericVector<?> vector)
		{
			this.vector=vector;
			this.value=value;
		}
		@Override
		public double getDoubleValue(final int index) 
		{
			return value;
		}

		@Override
		public int vectorSize() 
		{
			return vector.vectorSize();
		}
		
	}
	
}
