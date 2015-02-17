package org.civex.utils.numeric;

import org.civex.utils.numeric.ProtoVector.ProtoFuncVector;

public class VectorDotProduct<V extends Number> extends ProtoFuncVector<V>
{

	final private double scalarMul;
	final private double scalarSum;
	
	public VectorDotProduct(INumericVector<V> v1, INumericVector<V> v2, double mul, double sum)
	{
		super(v1,v2);
		this.scalarMul=mul;
		this.scalarSum=sum;
	}
	
	public VectorDotProduct(INumericVector<V> v1, INumericVector<V> v2)
	{
		this(v1,v2,1,0);
	}
	
	public VectorDotProduct(final INumericVector<V> v1, double mul, double sum)
	{
		this(v1,new VectorNumber.ConstantVector<V>(v1.vectorSize(),0D),mul,sum);
	}

	@Override
	public double getDoubleValue(int index) 
	{
		return v1.getDoubleValue(index)*v2.getDoubleValue(index)*scalarMul+scalarSum;
	}
}
