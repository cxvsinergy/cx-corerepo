package org.civex.utils.numeric;

import java.util.ArrayList;
import java.util.List;

public class VectorTable<V extends Number> extends ProtoVector<V> 
{
	protected List<INumericVector<V>> list=new ArrayList<INumericVector<V>>();
	
	@Override
	public double getDoubleValue(int index) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int vectorSize() 
	{
		return list.size();
	}

}
