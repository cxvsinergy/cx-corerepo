package org.civex.utils.numeric;

import org.civex.utils.AbstractNumber;

public class NumberChangedListenerAdapter implements INumberChangedListener 
{
	public static final INumberChangedListener STUB=new NumberChangedListenerAdapterStub();
	
	@Override
	public void onNumberChanged(AbstractNumber n,double v) {}
	

	
	@Override
	public boolean filterNumber(AbstractNumber n, double v) 
	{
		return true;
	}



	public static final class NumberChangedListenerAdapterStub extends NumberChangedListenerAdapter
	{
		public final void onNumberChanged(AbstractNumber n,double v) {}	
	}



	@Override
	public void onNumberChanged(AbstractNumber n, int dim, double v) 
	{
		
	}
	
	
	
}
