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
package org.civex.utils.finmath;

public interface IMarketPrice
{
	
		public long getTimestamp();
		public long getTimestampNanos();
		public long getReceivedNanos();
		public int 	getPxType();
		public int getPxLevel();
		public double getLastPx();
		public double getPxOpen();
		public double getPxClose();
		public double getPxHigh();
		public double getPxLow();
		public double getPxSpread();
		public double getVolume();
		public double getAdjClose();
		public double getPxRef();
}
