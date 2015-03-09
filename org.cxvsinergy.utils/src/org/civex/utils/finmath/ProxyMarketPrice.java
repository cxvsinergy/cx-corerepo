package org.civex.utils.finmath;


/**********************************************************************
 * Copyright (c) 2015 cxvsinergy.org., and others. All rights reserved.
 * Contributors: 
 * 	@author Vitaly Samoilov - Initial implementation 
 * 	e-mail : nikevit@gmail.com
 * project : com.cxvsinergy.utils
 * History:
 * 		
 * 	
 *******************************************************************/

public class ProxyMarketPrice<E> extends AbstractMarketPrice<E>
{
	private static final long serialVersionUID = 1L;
	final private IMarketPrice px;
	
	
	public ProxyMarketPrice(IMarketPrice px)
	{
		this.px=px;
	}
	
	protected IMarketPrice getMarketPriceImpl() { return px;}

	public long getTimestamp()
	{
		return getMarketPriceImpl().getTimestamp();
	}

	public long getTimestampNanos()
	{
		return getMarketPriceImpl().getTimestampNanos();
	}

	public long getReceivedNanos()
	{
		return getMarketPriceImpl().getReceivedNanos();
	}

	public int getPxType()
	{
		return getMarketPriceImpl().getPxType();
	}

	public int getPxLevel()
	{
		return getMarketPriceImpl().getPxLevel();
	}

	public double getLastPx()
	{
		return getMarketPriceImpl().getLastPx();
	}

	public double getPxOpen()
	{
		return getMarketPriceImpl().getPxOpen();
	}

	public double getPxClose()
	{
		return getMarketPriceImpl().getPxClose();
	}

	public double getPxHigh()
	{
		return getMarketPriceImpl().getPxHigh();
	}

	public double getPxLow()
	{
		return getMarketPriceImpl().getPxLow();
	}

	public double getPxSpread()
	{
		return getMarketPriceImpl().getPxSpread();
	}

	public double getVolume()
	{
		return getMarketPriceImpl().getVolume();
	}

	public double getAdjClose()
	{
		return getMarketPriceImpl().getAdjClose();
	}

	public double getPxRef()
	{
		return getMarketPriceImpl().getPxRef();
	} 
	
	
}
