package org.civex.utils.datetime;

import org.civex.utils.IRange;

abstract class  AbstractDateTimeRange implements IRange<LocalDateTime>
{
	@Override
	public boolean betweenInclusive(LocalDateTime v) 
	{
		return (!v.before(getRangeMin())) && (!getRangeMax().before(v));
	}

	@Override
	public boolean betweenExclusive(LocalDateTime v) 
	{
		return getRangeMin().before(v) && getRangeMax().before(v);
	}

	@Override
	public LocalDateTime getRangeMean() {return null;}

	@Override
	public String toString() 
	{
		return getRangeMin().toString()+" - "+getRangeMax().toString();
	}
	
	@Override
	public double getRangeLength() { return getRangeMax().getTimeInMillis()-getRangeMin().getTimeInMillis();}

	public long getDuration() { return (long)getRangeLength();}

	
}




public class DateTimeRange extends AbstractDateTimeRange
{
	final private LocalDateTime startTime;
	final private LocalDateTime endTime;
	
	public DateTimeRange(final LocalDateTime startTime, final LocalDateTime endTime)
	{
		this.startTime=startTime;
		this.endTime=endTime;
	}
	
	public DateTimeRange(String startTime,  String endTime)
	{
		this(new LocalDateTime(startTime), new LocalDateTime(endTime));
	}
	
	@Override
	public LocalDateTime getRangeMin() 
	{
		return startTime;
	}

	@Override
	public LocalDateTime getRangeMax() 
	{
		return endTime;
	}

	public static long getDays(IRange<LocalDateTime> range)
	{
		return (long)(range.getRangeLength()/LocalDateTime.ONE_DAY);
	}
	
	

	public static class FromTodayRange extends AbstractDateTimeRange
	{
		final private LocalDateTime endTime;
		final public static  LocalDateTime LIFETIME_DATE=new LocalDateTime("20991231");
		final public static  FromTodayRange LIFETIME_RANGE=new FromTodayRange(LIFETIME_DATE);
		
		public FromTodayRange( final String endTime)	{this(new LocalDateTime(endTime));}
		
		public FromTodayRange( final LocalDateTime endTime)	{this.endTime=endTime;}
		
		@Override
		public LocalDateTime getRangeMin() {return LocalDateTime.TODAY_TIME;}

		@Override
		public LocalDateTime getRangeMax() {return endTime;}
	}

	
}
