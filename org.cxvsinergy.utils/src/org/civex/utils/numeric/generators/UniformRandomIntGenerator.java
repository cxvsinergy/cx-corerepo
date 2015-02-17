package org.civex.utils.numeric.generators;

import java.util.List;
import java.util.Random;

public class UniformRandomIntGenerator extends AbstractSeqGenerator<Integer>
{
	protected Random r;
	private int min;
	private int max;
	
	
	public UniformRandomIntGenerator(int min, int max)
	{
		this();
		this.max=max;
		this.min=min;	
	}
	
	public UniformRandomIntGenerator()
	{
		reset();
	}
	
	@Override
	public double nextNumeric() 
	{
		return min+r.nextInt(max-min+1);
	}

	@Override
	public boolean hasNext() 
	{	
		return true;
	}

	@Override
	protected Integer generateNext()
	{
		return (int)nextNumeric();
	}

	@Override
	public void reset() 
	{
		r=new Random();
	}
	
	public static <T> T randomSelect(final List<T> arr, final int min, final int max)
	{
		final Random r=new Random();
		final int index=min+r.nextInt(max-min+1);
		return arr.get(min+index);
	}

	
	public static <T> T randomSelect(List<T> arr, int min)
	{
		return randomSelect(arr,min,arr.size()-1);
	}
	
}
