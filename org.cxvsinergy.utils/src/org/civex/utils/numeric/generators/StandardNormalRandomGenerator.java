package org.civex.utils.numeric.generators;

public class StandardNormalRandomGenerator extends UniformRandomGenerator
{
	private double mean=0;
	private double sigma=1;
	@Override
	public double nextNumeric() 
	{
		return r.nextGaussian();
	}

}
