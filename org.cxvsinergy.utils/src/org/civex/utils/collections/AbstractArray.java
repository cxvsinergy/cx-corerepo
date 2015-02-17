package org.civex.utils.collections;

import java.io.IOException;
import java.util.AbstractList;
import java.util.Iterator;

import org.civex.utils.NumericArray;

public abstract class AbstractArray<T> implements Cloneable,Iterable<T>
{

	protected int sz;
	protected int extension;
	protected int off;
	protected int maxsz;

	
	public int size() {	return sz;}
	@Override
	public Object clone() throws CloneNotSupportedException 
	{
		return super.clone();
	}
	
	
	protected void ensureCapacity(int len)
	{
		
	}
	
	public Iterator<T> getIterator() {return null;}
	public final Iterator<T> iterator() {return this.getIterator();}
	
	
	public int getDim()  {return 1;}

	
	public abstract T getByIndex(long index);
	
	public String getAsString(long index) 
	{
		return getByIndex(index).toString();
	}

	
	public int saveAsBytes(byte buff[], int off) throws IOException
	{
		return -1;
	}
	
	public int loadAsBytes(byte buff[], int off, int sz) throws IOException
	{
		return -1;
	}

	public void remove(int index)
	{}
	
	public void shrink()
	{ }
	
	
	public void exch(long t1, long t2)
	{
		T v1=getByIndex(t1);
		T v2=getByIndex(t2);
		set(t1,v2);
		set(t2,v1);
	}
	
	protected abstract void set(long index, T v); 
	

	public static class NumericArrayList extends AbstractList<Number>
	{
		private NumericArray arr;

		public NumericArrayList()
		{
			arr=new NumericArray(100);
		}
		@Override
		public Number get(int index) 
		{
			return arr.get(index);
		}

		@Override
		public int size() 
		{
			return arr.size();
		}
		
		public double getDouble(int index)
		{
			return arr.get(index);
		}
	
		public double set(int index, double v) 
		{
			arr.set(index, v);
			return v;
		}
		
	}
}
