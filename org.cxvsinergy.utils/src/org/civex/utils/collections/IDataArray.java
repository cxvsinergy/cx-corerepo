package org.civex.utils.collections;

public interface IDataArray<T> 
{
	  public int dimensions();
	  public int size();
	  public int size(int dimension);
	  public void setElement(T v, int...pos);
	  
}
