package org.civex.utils;

import java.util.Arrays;

import org.civex.utils.collections.AbstractArray;
import org.civex.utils.numeric.INumericFunction;


public class NumericArray extends AbstractArray<Double>  implements Comparable<NumericArray>,  IDataAccessor<Double>
{
	protected double buff[];
	
	
	public NumericArray(int sz)
	{
		this(sz, 10);
	}

	public NumericArray(int sz, int extension)
	{
		this.extension=extension;
		buff=new double[sz];
		ensureCapacity(sz);
	}
	
	public double get(final int index)
	{ 
		return buff[index];
	}
	
	public int getAsInt(final int index)
	{
		return (int)get(index);
	}
		
	public void add(double v)
	{
		ensureCapacity(sz+1);
		buff[sz++]=v;
	}
	
	
	public void set(int index,double v)
	{
		ensureCapacity(index);
		buff[index]=v;
	}
	
	
	public double exch(int index, double v)
	{
		final double old=get(index);
		set(index, v);
		return old;
	}
	
	protected void ensureCapacity(int len)
	{
		if (buff.length>len) return;
		final double buff2[]=new double[len];
		System.arraycopy(buff, 0, buff2, 0, sz);
		buff=buff2;
	}
	
	public void clear()
	{
		this.sz=0;
		Arrays.fill(buff, Double.NaN);
	}
	
	
	@Override
	public int compareTo(NumericArray other) 
	{
		int d=0;
		if ((d=size()-other.size())!=0) return d;
		for (int ii=0; ii<sz;ii++)
		{
			if ((d=Double.compare(buff[ii], other.buff[ii]))!=0) return d; 
		}
		return 0;
	}

		
	@Override
	public Double getByIndex(long index) 
	{
		return buff[(int)index];
	}

	@Override
	public long getCollectionSize() 
	{
		return buff.length;
	}

	@Override
	public double getAsNumeric(double v) 
	{
		return buff[(int)v];
	}

	public double first()
	{
		long  sz=size();
		return sz>0?buff[0]:Double.NaN;
	}
	
	public double last()
	{
		return sz>0?buff[sz-1]:Double.NaN;
	}
	
	@Override
	public Double getLastElement() 
	{
		int sz= buff.length;
		return sz>0?getByIndex(sz-1):null;
	}
	
	
	@Override
	public Object apply(final Object ff, final IObjectFilter<Object, Double> filter, final Object... params) 
	{
		
		final INumericFunction func=(INumericFunction) ff;
		for (int ii=0;ii<buff.length;ii++)
		{
			if (!filter.filter(this,(double)ii)) continue;
			func.calcScalarValue(get(ii));
		}
		return null;
	}

	@Override
	protected void set(long index, Double v) 
	{
		
	}
}
