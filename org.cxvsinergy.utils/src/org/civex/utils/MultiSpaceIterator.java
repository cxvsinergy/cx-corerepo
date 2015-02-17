package org.civex.utils;

import java.util.Iterator;

public class MultiSpaceIterator implements Iterator<long[]>
{
	protected long ptr;//[];
    protected final int len;
	protected final long dsz[];
	protected final long result[];
	protected final int space[];
	
	
	
	public MultiSpaceIterator(int[] d)
	{
		ptr=0;
		space=d;
		len=d.length;
		result=new long[len];
		dsz=new long[len];
		dsz[0]=d[0];
		result[0]=ptr%d[0];
		
		for (int ii=1;ii<len-1;ii++)
		{
		  dsz[ii]=d[ii]*dsz[ii-1];  
		}

	}
	@Override
	public boolean hasNext() 
	{
		return ptr<dsz[len-2]*space[len-1];
	}

	@Override
	public long[] next() 
	{	
		  long p=ptr;
		  ptr++;
		  //
		  result[0]=ptr%dsz[0];
		  for (int ii=len-1;ii>0;ii--)
		  {
			  final int prev=ii-1;
			  result[ii]=p/dsz[prev];
			  p-=dsz[prev]*result[ii];	  
		  }
		  
		  return result;
	}

	
	public void requestAbsolute(long ptr, final long rr[])
	{
		  rr[0]=ptr%dsz[0];
		  for (int ii=len-1;ii>0;ii--)
		  {
			  rr[ii]=ptr/dsz[ii-1];
			  ptr-=dsz[ii-1]*rr[ii];	  
		  }
	}
	
	@Override
	public void remove() 
	{
		throw new RuntimeException("not supported");
	}

	public int sizeDim()
	{
		return len;
	}
	
	public double getSpaceSize()
	{
		return 1L*dsz[len-2]*space[len-1]; 
	}
	
	public void reset()
	{
		ptr=0;
	}
	
	public double getDimMax(int dim) 
	{
		return dsz[dim];
	}
	
	public double getDimMin(int dim) 
	{
		return 0;
	}
}
