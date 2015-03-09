package org.civex.utils.stats;

import java.io.Serializable;

/**
 *   * @author nikehome
 *
 */
public class StatsAggregator implements Serializable
{
	private static final long serialVersionUID = -8570371443950894023L;
	protected int counter;
	protected double min=Double.NaN;
	protected double max=Double.NaN;
	protected double avg=Double.NaN;
	protected double sigma=Double.NaN;
	protected double value=Double.NaN;

	
	public double getSigma()
	{
		return Math.sqrt(sigma/getCount());
	}
	
	
	public double getMax()
	{
		return max;
	}
	
	
	public double getMin()
	{
		return min;
	}
	
	public double getDelta()
	{
		return getMax()-getMin();
	}
	
	public double getAvg()
	{
		return avg;
	}
	
	public double getSum()
	{
		return getAvg()*getCount();
	}
	
	public int getCount()
	{
		return counter+1;
	}
	
	
	public void reset()
	{
		counter=0;
		value=min=max=sigma=avg=Double.NaN;
	}
	
	
	public void set(final double v)
	{
		// first value
		if (Double.isNaN(value))
		{
			min=max=avg=value=v;
			sigma=0;
			return;
		}
		min=Math.min(min, v);
		max=Math.max(max, v);
		//calc avg, sigma
		final double prevAvg=avg;
		avg=(avg*counter+v)/(counter+1);
		sigma=sigma+(v-prevAvg)*(v-avg);
		counter++;
		value=v;
		
	}
	
	public double getValue()
	{
		return value;
	}
		
	public void add(double v)
	{
		final double vv=!Double.isNaN(value)?value:0;
		set(vv+v);
	}	
	@Override
	public String toString() 
	{
		return "MinMax [counter=" + getCount() + ", min=" + getMin() + ", max=" + getMax()+ ", avg="+getAvg()+", sigma="+getSigma()+"]";
	}

}
