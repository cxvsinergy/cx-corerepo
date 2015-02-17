package org.civex.utils.numeric;

import org.civex.utils.IObjectFilter;

public interface INumericFilter<M> extends IObjectFilter<M,Number>
{
	public boolean filter(double value, M model);
	public void reset();
}
