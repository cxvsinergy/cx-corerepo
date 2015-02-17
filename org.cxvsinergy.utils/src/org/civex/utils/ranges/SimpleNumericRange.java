package org.civex.utils.ranges;

import org.civex.utils.IRange;



public class SimpleNumericRange implements IRange<Number>
{
	private final double min;
	private final double max;
	
	public SimpleNumericRange(double min, double max)
	{
		this.min=Math.min(min, max);
		this.max=Math.max(min, max);
	}
	@Override
	public Number getRangeMin() 
	{
		return min;
	}
	@Override
	public Number getRangeMax() 
	{
		return max;
	}

	public boolean betweenInclusive(double v) 
	{
		return  v>=min & v<=max;
	}
	
	public boolean betweenExclusive(double v) 
	{
		return v>min & v<max;
	}
	@Override
	public boolean betweenInclusive(Number v) 
	{
		return betweenInclusive(v.doubleValue());
	}
	@Override
	public boolean betweenExclusive(Number v) 
	{
		return betweenExclusive(v.doubleValue());
	}
	@Override
	public Number getRangeMean() 
	{
		return 0;
	}
	@Override
	public double getRangeLength() 
	{
		return getRangeMax().doubleValue()-getRangeMin().doubleValue();
	}
	
	
}
