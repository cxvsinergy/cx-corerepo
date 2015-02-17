package org.civex.utils.datetime;

import java.io.Serializable;
import java.util.Iterator;

public abstract class AbstractCountdown implements ITargetTime,Serializable
{
	private static final long serialVersionUID = 1L;
	//
	
	@Override
	public abstract LocalDateTime getTargetTime();
	public LocalDateTime getSourceTime() {return new LocalDateTime(getTargetTime().getTimeZone(),getCurrentTime());}
	
	public final long getTimeRemained()
	{
		final long t=getTargetTime().getTimeInMillis()-getCurrentTime();
		return t>0?t:0;
	}
	
	
	public final int getBusinessDaysRemained()
	{
		return 5*getDaysRemained()/7;
	}
	
	public final int getDaysRemained()
	{
		final long t=getTargetTime().getLocalDay()-1-getCurrentTime();
		if (t<0) return 0;
		return (int)(t/LocalDateTime.ONE_DAY);
	}
	
	protected long getCurrentTime()
	{
		return System.currentTimeMillis();
	}
	
	@Override
	public String toString() 
	{
		return "target=["+getTargetTime()+" :: "+getDaysRemained()+" days, passed="+getPassedDays()+"]";
	}
	
	public final long getPassedTime() {return 0;}
	public final int getPassedDays(){return 0;}
	
	
	public Iterator<LocalDateTime> getCountdownIterator()
	{
		return getDayIterator();
	}
	public Iterator<LocalDateTime> getDayIterator() {return null;}
}
