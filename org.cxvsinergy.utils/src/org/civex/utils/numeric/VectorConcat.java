package org.civex.utils.numeric;

public class VectorConcat<V extends Number> extends ProtoVector<V>
{
	final private INumericVector<V> v1;
	final private INumericVector<V> v2;
	final private double scalarMul;
	final private double scalarSum;
	
	public VectorConcat(INumericVector<V> v1, INumericVector<V> v2, double mul, double sum)
	{
		this.v1=v1;
		this.v2=v2;
		this.scalarMul=mul;
		this.scalarSum=sum;
	}
	
	public VectorConcat(INumericVector<V> v1, INumericVector<V> v2)
	{
		this(v1,v2,1,0);
	}
	
	public VectorConcat(final INumericVector<V> v1, double mul, double sum)
	{
		this(v1,new VectorNumber.ConstantVector<V>(v1.vectorSize(),0D),mul,sum);
	}

	@Override
	public int vectorSize() 
	{
		return v1.vectorSize()+v2.vectorSize();
	}

	@Override
	public double getDoubleValue(int index) 
	{
		if (index<v1.vectorSize()) return v1.getDoubleValue(index)*scalarMul+scalarSum;
		return v2.getDoubleValue(index-v1.vectorSize())*scalarMul+scalarSum;
	}
}
