package org.civex.utils.collections;

import org.civex.utils.collections.AbstractArray.NumericArrayList;
import org.civex.utils.numeric.INumericList;


public abstract class FuncNumericList extends NumericArrayList
{
	final protected INumericList[]  params;
	protected String symbolic;
	protected String description;
	
	public FuncNumericList(int paramLen)
	{
		params=new INumericList[paramLen];
	}
	
	
	public FuncNumericList(INumericList param1)
	{
		params=new INumericList[1];
		params[0]=param1;
	}

	
	
	public FuncNumericList(INumericList param1,INumericList param2)
	{
		params=new INumericList[2];
		params[0]=param1;
		params[1]=param2;
	}
	
	
	@Override
	public double getDouble(int index) 
	{
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int size() 
	{
		return params.length==0?Integer.MAX_VALUE:params[0].size();
	}
	
	public void setParam(final int index, final INumericList param)
	{
		params[index]=param;
	}

	

	@Override
	public double set(int index, double v) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public String getFuncDescription()
	{
		return "not defined";
	}
	
	protected double getParamValue(int param, int index)
	{
		return params[param].getDouble(index);
	}
}
