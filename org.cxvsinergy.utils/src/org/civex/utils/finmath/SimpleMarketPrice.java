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


public class SimpleMarketPrice<E> extends AbstractMarketPrice
{

	private static final long serialVersionUID = 1L;
	protected 	double  volume;
	protected	double	pxOpen;
	protected   double  pxClose;
	protected   double  pxHigh;
	protected   double  pxLow;
	protected 	double  pxLast;
	protected 	long	timestamp;
	
	
	public SimpleMarketPrice()
	{}
	
	public SimpleMarketPrice(double px, double volume, long timestamp)
	{
		this(volume,px,px,px,px,px,timestamp);
	}
	
	public SimpleMarketPrice(double px)
	{
		this(0,px,px,px,px,px,System.currentTimeMillis());
	}
	
	public SimpleMarketPrice(double volume, double pxOpen, double pxHigh, double pxLow,  double pxClose, double pxLast,long timestamp)
	{
		this.volume = volume;
		this.pxOpen = pxOpen;
		this.pxClose = pxClose;
		this.pxHigh = pxHigh;
		this.pxLow = pxLow;
		this.pxLast = pxLast;
		this.timestamp = timestamp;
	}

	@Override
	public long getTimestamp()
	{
		return timestamp;
	}

	

	@Override
	public double getLastPx()
	{
		return pxLast;
	}

	@Override
	public double getPxOpen()
	{
		return pxOpen;
	}

	@Override
	public double getPxClose()
	{
		return pxClose;
	}

	@Override
	public double getPxHigh()
	{
		return pxHigh;
	}

	@Override
	public double getPxLow()
	{
		return pxLow;
	}

	@Override
	public double getPxSpread()
	{
		return getPxHigh()-getPxLow();
	}

	@Override
	public double getVolume()
	{
		return volume;
	}

	public void setVolume(double volume)
	{
		this.volume = volume;
	}

	public void setPxOpen(double pxOpen)
	{
		this.pxOpen = pxOpen;
	}

	public void setPxClose(double pxClose)
	{
		this.pxClose = pxClose;
	}

	public void setPxHigh(double pxHigh)
	{
		this.pxHigh = pxHigh;
	}

	public void setPxLow(double pxLow)
	{
		this.pxLow = pxLow;
	}

	public void setPxLast(double pxLast)
	{
		this.pxLast = pxLast;
	}

	public void setTimestamp(long timestamp)
	{
		this.timestamp = timestamp;
	}

	
	public E getPxObject() {return null;}
	
}
