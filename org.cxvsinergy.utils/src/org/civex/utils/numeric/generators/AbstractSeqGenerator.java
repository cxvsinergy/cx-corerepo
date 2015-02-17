package org.civex.utils.numeric.generators;

import java.util.List;

public abstract class AbstractSeqGenerator<T>
{

	public void reset()
	{
		
	}
	
	public double nextNumeric()
	{
		return Double.NaN;
	}
	
	public T next()
	{
		return generateNext();
	}
	
	public abstract boolean hasNext();
	
	protected abstract T generateNext();
	
	public static<T> void debug(List<T> v)
	{
	  for (int ii=0;ii<v.size();ii++) System.out.print(v.get(ii)+",");
	  System.out.println();
	}
}
