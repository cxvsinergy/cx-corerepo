package org.civex.utils;


/**********************************************************************
 * Copyright (c) 2014 cxvsinergy.org., and others. All rights reserved.
 * Contributors: 
 * 	@author Vitaly Samoilov - Initial implementation 
 * 	e-mail : nikevit@gmail.com
 * project : com.cxvsinergy.utils
 * History:
 * 		
 * 	
 *******************************************************************/
public interface INumericList 
{	
	public int getInt(int index);
	
	public long getLong(int index);
	
	public double getDouble(int index);

	public int getDim();
	
	public void add( int index,double v);
	
	public void addAll(double v[],int index,int off, int sz);
	
	public double set(int index, double v);
	
	public int size();

}
