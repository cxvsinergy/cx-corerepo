package org.civex.utils.datetime;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;



public interface ITimePeriod 
{
	public TimeZone getTimeZone();
	public long		getTimeStart();
	public long 	getTimeEnd();	
	public TimeUnit	getTimeUnit();
	public long getDuration();
	public long getDurationMs();  
	public long getDurationNanos(); 
}
