package org.civex.utils.numeric;

import org.civex.utils.numeric.ProtoVector.ProtoFuncVector;

public class VectorLog<V extends Number> extends ProtoFuncVector<V>
{
	public VectorLog(INumericVector<V> v1, INumericVector<V> v2)
	{
		super(v1,v2);
	}
	
	@Override
	public int vectorSize() {return v1.vectorSize();}

	@Override
	public double getDoubleValue(int index) 
	{
			return Math.pow(v1.getDoubleValue(index), v2.getDoubleValue(index));
	}
}
