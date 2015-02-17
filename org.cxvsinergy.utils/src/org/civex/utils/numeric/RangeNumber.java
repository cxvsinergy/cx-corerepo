package org.civex.utils.numeric;

public class RangeNumber extends SimpleNumber
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double min;
	private double max;
	
	
	public RangeNumber()
	{
		this(Double.MIN_NORMAL,Double.MAX_VALUE,0);
	}
	
	public RangeNumber(double min, double max, double v)
	{
		this.set(min);
		this.min=min;
		this.max=max;
		this.set(v);
	}
	
	
	public RangeNumber(double v, double max)
	{
		this.set(0);
		this.min=0;
		this.max=max;
		this.set(v);
	}

	@Override
	public void set(double v) 
	{
		super.set(Math.min(Math.max(v, min),max));
	}
	
	public double getMin() {return min;}
	public double getMax() {return max;}
	
	
}
