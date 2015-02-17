package org.civex.utils.numeric;

import org.civex.utils.AbstractNumber;

public interface INumberChangedListener 
{
	public boolean filterNumber(AbstractNumber n,double v);
	public void onNumberChanged(AbstractNumber n,double v);
	public void onNumberChanged(AbstractNumber n, int dim, double v);
}
