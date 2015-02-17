package org.civex.utils.numeric;

public class SequencedVector<V extends Number> extends ProtoVector<V>
{
	final private double min;
	final private double max;
	final private double step;
	
	public SequencedVector() 
	{
		this(0D,1D,0.01);
	}
	
	public SequencedVector(int min, int max)
	{
		this(min,max,1);
	}
	
	public SequencedVector(double min, double max, double step) 
	{
		super();
		this.min=min;
		this.max=max;
		this.step=step;
	}

	@Override
	public double getDoubleValue(int index) 
	{
		return min+index*step;
	}

	@Override
	public int vectorSize() 
	{
		return (int)((max-min)/step);
	}

}
