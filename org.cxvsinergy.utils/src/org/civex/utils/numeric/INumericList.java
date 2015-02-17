package org.civex.utils.numeric;

public interface INumericList 
{	
	enum ListType {VALUES,FUNCTION,MATRIX, SEQUENCE}
	
	
	public int getInt(int index);
	
	public long getLong(int index);
	
	public double getDouble(int index);

	public int getDim();
	
	public void add( int index,double v);
	
	public void addAll(double v[],int index,int off, int sz);
	
	public double set(int index, double v);
	
	public ListType getType();
	
	public int size();

}
