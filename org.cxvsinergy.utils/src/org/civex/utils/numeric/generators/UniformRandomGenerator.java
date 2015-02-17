package org.civex.utils.numeric.generators;

import java.util.Random;

public class UniformRandomGenerator extends AbstractSeqGenerator<Double>
{
	final protected Random r;
	
	public UniformRandomGenerator()
	{
		r=new Random();
	}
	
	@Override
	public double nextNumeric() 
	{
		return r.nextDouble();
	}

	@Override
	public boolean hasNext() 
	{	
		return true;
	}

	@Override
	protected Double generateNext()
	{
		return nextNumeric();
	}

	
}
