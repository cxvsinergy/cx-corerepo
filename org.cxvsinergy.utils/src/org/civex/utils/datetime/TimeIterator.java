package org.civex.utils.datetime;

import java.util.Calendar;
import java.util.Iterator;

public class TimeIterator implements Iterator<LocalDateTime> 
{

	final protected static int ADJ_NONE=0;
	final protected static int ADJ_FIRST=1;
	final protected static int ADJ_LAST=2;
	final protected static int ADJ_ON_FIRST=0x100;
	final protected static int ADJ_ON_END=0x200;
	
	
	final protected LocalDateTime targetDateTime;
	final protected int adjusted=ADJ_ON_END | ADJ_ON_FIRST;
	final protected Calendar cal;
	final protected int amount;
	final protected int field; 
	
	
	public TimeIterator(final String strDate)
	{
		this(new LocalDateTime(strDate),Calendar.SECOND,1);
	}
	
	public TimeIterator(final LocalDateTime targetDateTime)
	{
		this(targetDateTime,Calendar.SECOND,1);
	}
	
	protected TimeIterator(final LocalDateTime targetDateTime, int field, int counter)
	{
		this.targetDateTime=targetDateTime;
		cal=targetDateTime.getDateCalendar();
		cal.setTimeInMillis(LocalDateTime.CurrentDateTime.getCurrentTime());
		this.field=field;
		this.amount=counter;
		adjustCalendarOnInit(cal);
	}
	
	
	public TimeIterator(final LocalDateTime sourceDateTime,final LocalDateTime targetDateTime, int field, int counter)
	{
		this.targetDateTime=targetDateTime;
		cal=sourceDateTime.getDateCalendar();
		this.field=field;
		this.amount=counter;
		this.adjustCalendarOnInit(cal);
	}
	
	@Override
	public boolean hasNext() 
	{
		return cal.getTimeInMillis()<targetDateTime.getTimeInMillis();
	}

	@Override
	public LocalDateTime next() 
	{	
		final LocalDateTime dt=new LocalDateTime(targetDateTime.getTimeZone(),cal.getTimeInMillis());
		cal.add(field, amount);		
		adjustCalendarOnNext(cal);
		return dt;
	}

	@Override
	public void remove() 
	{
		throw new RuntimeException("Not Implemented ...");
	}
	
	
	protected void adjustCalendarOnInit(Calendar cal)
	{
		cal.set(Calendar.MILLISECOND, 0);
	}
	
	protected void adjustCalendarOnNext(Calendar cal)
	{
	
	}
	
	public static class DayIterator extends TimeIterator
	{
		public DayIterator(LocalDateTime dt) {super(dt, Calendar.DATE,1);}

		
		public DayIterator(LocalDateTime sourceDateTime, LocalDateTime targetDateTime, int field, int counter) 
		{
			super(sourceDateTime, targetDateTime, field, counter);
		}


		public DayIterator(String strDate) {super(strDate);}


		public DayIterator(LocalDateTime targetDateTime, int field, int counter)
		{
			super(targetDateTime, field, counter);
		}

		@Override
		protected void adjustCalendarOnInit(Calendar cal) 
		{
			LocalDateTime.resetTime(cal);
		}
		
	}

	
	public static class WeekIterator  extends DayIterator
	{
		public WeekIterator(LocalDateTime dt) {super(dt, Calendar.WEEK_OF_YEAR,1);}
	}
	
	public static class MonthIterator extends DayIterator
	{
		public MonthIterator(LocalDateTime dt) {super(dt,Calendar.MONTH,1);}
	}
	
	public static class QuoteIterator extends DayIterator
	{
		public QuoteIterator(LocalDateTime dt) {super(dt, Calendar.MONTH,3);}

		public QuoteIterator(String strDate) {	super(new LocalDateTime(strDate), Calendar.MONTH,3);	}
		
		
	}
	
	public static class YearIterator extends DayIterator
	{
		public YearIterator(LocalDateTime dt) {super(dt, Calendar.YEAR,1);}
	}
	
	public static class BusinessDayIterator extends TimeIterator
	{
		public BusinessDayIterator(LocalDateTime targetDateTime) {super(targetDateTime, Calendar.DATE,1);}

		@Override
		protected void adjustCalendarOnNext(Calendar cal) 
		{
			super.adjustCalendarOnNext(cal);
			if (cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY) 
				{cal.add(Calendar.DATE, 2); return;}
			if (cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) 
			{cal.add(Calendar.DATE, 1); return;}
		}	
	}
	
	
}


