package org.civex.utils.finmath;
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

import java.io.Serializable;



public abstract class AbstractMarketPrice<E> implements IMarketPrice,Cloneable, Serializable, Comparable<IMarketPrice>
{
	private static final long serialVersionUID = 1L;

	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	
	@Override
	public String toString()
	{
		final StringBuilder bld=new StringBuilder(100);
		bld.append("px[");
		bld.append(getPxLow());
		bld.append('-');
		bld.append(getPxHigh());
		bld.append(']');
		return bld.toString();
	}

	@Override
	public int compareTo(final IMarketPrice other)
	{
		int r=0;
		if((r=Integer.compare(getPxType(), other.getPxType()))!=0) return r;
		if((r=Long.compare(getTimestamp(), other.getTimestamp()))!=0) return r;
		if((r=Double.compare(getPxRef(), other.getPxRef()))!=0) return r;
		return 0;
	}

	@Override
	public int hashCode()
	{
		final long timestamp=getTimestamp();
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(getPxRef());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)	return true;
		if (obj == null)	return false;
		SimpleMarketPrice other = (SimpleMarketPrice) obj;
		return compareTo(other)==0;
	}
	
	@Override
	public double getAdjClose()
	{
		return this.getPxClose();
	}

	@Override
	public double getPxRef()
	{
		return getPxClose();
	}
	
	@Override
	public long getTimestampNanos()
	{
		return 0;
	}

	@Override
	public long getReceivedNanos()
	{
		return 0;
	}

	@Override
	public int getPxType()
	{
		return 0;
	}

	@Override
	public int getPxLevel()
	{
		return Integer.MIN_VALUE;
	}

	public E getPxObject() {return null;}
}
