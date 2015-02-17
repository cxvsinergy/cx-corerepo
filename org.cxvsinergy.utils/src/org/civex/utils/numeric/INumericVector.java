package org.civex.utils.numeric;

import org.civex.utils.IObjectVector;

public interface INumericVector<V extends Number> extends IObjectVector<Number>
{
		public double getDoubleValue(int index);	
		public double[] copyTo(double[] target);
}
