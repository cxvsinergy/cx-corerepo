package org.civex.utils.numeric;

import org.civex.utils.AbstractMutableNumber;

public class SimpleNumber extends AbstractMutableNumber
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double value;

	public SimpleNumber()
	{
		
	}
	
	public SimpleNumber(double v)
	{
		this.value=v;
	}
	
	@Override
	public void set(double v) 
	{
		value=v;
	}

	@Override
	public double doubleValue() 
	{
		return value;
	}
	
	

}
