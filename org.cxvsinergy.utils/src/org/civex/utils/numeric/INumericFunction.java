package org.civex.utils.numeric;

public interface INumericFunction 
{
	public double calcScalarValue(double arg);
	public double calcScalarValue(double arg1, double arg2);
	public double calcScalarValue(double arg1, double arg2, double arg3);
	public double calcScalarValue(double... args);
	//public String getFuncSignature();
	public int getParamNumber();
}
