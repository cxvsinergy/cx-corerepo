package org.civex.utils.datetime;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class LocalDateTime  implements Comparable<LocalDateTime> , Serializable
{
	private static final long serialVersionUID = 1L;
	private static final int DIGIT_OFF=48;
	public static final int ONE_DAY=3600*24*1000;
	private static final int MASK[]={0,0,0,Calendar.YEAR,0,Calendar.MONTH,0,Calendar.DATE,-1,0,Calendar.HOUR_OF_DAY,-1,0,Calendar.MINUTE,-1,0,Calendar.SECOND,-1,0,0,Calendar.MILLISECOND};
	private static final int MASK2[]={0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	private static final int MASKLEN[]={8,14,17,21};	
	public static final TimeZone GMT=TimeZone.getTimeZone("GMT");
	public static final TimeZone TMZ_DEFAULT=TimeZone.getDefault();	
	final  private  TimeZone tz;
	final  private  long time;	
	public static final CurrentDateTime TIME_GMT=new CurrentDateTime("GMT");
	public static final CurrentDateTime TIME_MSK=new CurrentDateTime("Europe/Moscow");
	public static final CurrentDateTime TIME_LON=new CurrentDateTime("Europe/London");
	public static final CurrentDateTime TIME_NYC=new CurrentDateTime("America/New_York");
	public static final CurrentDateTime TIME_TOK=new CurrentDateTime("Asia/Tokyo");
	public static final CurrentDateTime TIME_SIN=new CurrentDateTime("Asia/Singapore");
	final static public LocalDateTime TODAY_TIME=new LocalDateTime.CurrentDateTime();
	
	public LocalDateTime(Calendar cal)
	{
		this(cal.getTimeZone(),cal.getTimeInMillis());
	}
	
	public LocalDateTime(CharSequence s)
	{
		this(TimeZone.getDefault(),s);
	}
	
	public LocalDateTime(final long v)
	{
		this(GMT,v);
	}
	
	public LocalDateTime(TimeZone tz,final long v)
	{
		this.tz=tz;
		this.time=v;
	}
	
	public LocalDateTime(String tzstr,final long v)
	{
		this(TimeZone.getTimeZone(tzstr),v);
	}
	
	public LocalDateTime(final String tzstr,final CharSequence s)
	{
		this(TimeZone.getTimeZone(tzstr),s);
	}	 
	
	
	
	public LocalDateTime(final TimeZone tz,final CharSequence s)
	{
		 final int l=s.length();
		 if (l!=MASKLEN[0] && l!=MASKLEN[1] && l!=MASKLEN[2] && l!=MASKLEN[3]) throw new RuntimeException("Invalid date/time format ..."+s);
		 this.tz=tz;
		 time=parseDateTime(s);
	}	 
	
	public long getTimeInMillis()   {return time;}
	public long getTimeInNanos()    {return getDayTime()*1000000L;}
	public TimeZone getTimeZone()   {return tz;}
	public boolean hasTimeValue()   {return  true;}
	public boolean hasDateValue()   {return  true;}
	public Date  getAsDate()  	    {return new Date(getTimeInMillis());}
	public TimeUnit  getTimeUnit()  {return TimeUnit.MILLISECONDS;}
	
	
    public LocalDateTime toWeekDay(int weekday)
    {
    	final Calendar c=getMyCalendar();
    	c.set(Calendar.DAY_OF_WEEK, weekday);
    	return new LocalDateTime(this.getTimeZone(),c.getTimeInMillis());
    }
    
    
    
    public LocalDateTime addDays(int dd)
    {
    	Calendar c=this.getMyCalendar();
    	c.add(Calendar.DAY_OF_YEAR, dd);
    	return new LocalDateTime(getTimeZone(),c.getTimeInMillis());
    }

	@Override
	public int compareTo(LocalDateTime other) 
	{
		if (!tz.hasSameRules(other.tz)) return tz.getID().compareTo((other.tz.getID()));
		return Long.compare(getTimeInMillis(), other.getTimeInMillis());
	}
	
	
	public boolean hasPassed(long t)
	{
		return t>getTimeInMillis();
	}
	
	
	public boolean isInFuture(long t)
	{
		return t<getTimeInMillis();
	}
	
	
	public long getRemainedTime(long t,Term term)
	{
		if (!isInFuture(t)) return -1;
		final long delta=getTimeInMillis()-t;
		return delta/term.getDurationMs()+1;
	}
	
	
	public long diffDays(long time)
	{
		final long delta=time-getTimeInMillis();
		return delta/ONE_DAY;
	}
	
	public long getLocalDay()
	{
		return getTimeInMillis()-getDayTime();
	}
	
	public int getDayTime()
	{
		int v=0;
		final Calendar cal=getMyCalendar();
		v+=cal.get(Calendar.HOUR_OF_DAY)*3600*1000;
		v+=cal.get(Calendar.MINUTE)*60000;
		v+=cal.get(Calendar.SECOND)*1000;
		v+=cal.get(Calendar.MILLISECOND);
		return v;
	}
	
	public int getLocalTime()
	{
		int v=0;
		final Calendar cal=getMyCalendar();
		v+=cal.get(Calendar.HOUR_OF_DAY)*10000000;
		v+=cal.get(Calendar.MINUTE)*100000;
		v+=cal.get(Calendar.SECOND)*1000;
		v+=cal.get(Calendar.MILLISECOND);
		return v;
	}
	
	public int getLocalDate()
	{
		final Calendar cal=getMyCalendar();
		int v=0;
		v+=cal.get(Calendar.YEAR)*10000;
		v+=(cal.get(Calendar.MONTH)+1)*100;
		v+=cal.get(Calendar.DAY_OF_MONTH);
		return v;
	}
	
	
	protected Calendar getCalendar()
	{
		final Calendar c=Calendar.getInstance(tz);
		c.setTimeInMillis(0);
		return c;
	}
	
	protected Calendar getMyCalendar()
	{
		final Calendar c=Calendar.getInstance(tz);
		c.setTimeInMillis(getTimeInMillis());
		return c;
	}
	
	public long parseDateTime(CharSequence s)
	{
		 final Calendar cal=getCalendar(); //yyyyMMDD; yyyyMMDD-hh:mm:ss
		 int v=0, ii=0;
		 for (ii=0;ii<MASK.length && ii<s.length();ii++)
		 {
			 if (MASK[ii]<0) continue;
			 v*=10;
			 v+=(s.charAt(ii) & 0x3F)-48;
			 if (MASK[ii]==0) continue;
			 cal.set(MASK[ii], v+MASK2[ii]);
			 v=0;
		 }
		 for (;ii<MASK.length;ii++)
		 {
			 if (MASK[ii]<=0) continue;
			 cal.set(MASK[ii], 0);
		 }
		 return cal.getTimeInMillis();
	}
	
	public boolean after(LocalDateTime other) {return other.getTimeInMillis()<=getTimeInMillis();}
	public boolean before(LocalDateTime other) {return other.getTimeInMillis()>=getTimeInMillis();}
	//public LocalDateTime addDays(int v, int time) {return null;}
	//public LocalDateTime addDays(int v) {return addDays(v,getLocalTime());}
	//public LocalDateTime nextDay() {return null;}
	//public LocalDateTime prevDay() {return null;}

	
	public final boolean isToday(LocalDateTime other)
	{
		return isToday(other.getTimeInMillis());
	}
	
	public int getWeekDay()
	{
		return -1;
	}
	
	public int getWeekNumber()
	{
		return -1;
	}
	
	
	public Calendar getDateCalendar()
	{
		return getMyCalendar();
	}
	
	public boolean isToday(final long time)
	{
		long midnight=getLocalDay();
		return time>=midnight && time<midnight+ONE_DAY;  // summer time is affected
	}
	
	public String toString()
	{
		return getStringDate()+"-"+getStringTime();
	}
	
	public CharSequence getStringTime()
	{
		final Calendar c=getMyCalendar();
		final StringBuilder bld=new StringBuilder("00:00:00.000");
		final int hh=c.get(Calendar.HOUR_OF_DAY);
		final int mm=c.get(Calendar.MINUTE);
		final int ss=c.get(Calendar.SECOND);
		int ms=c.get(Calendar.MILLISECOND);
		bld.setCharAt(0, (char)(DIGIT_OFF+hh/10));
		bld.setCharAt(1, (char)(DIGIT_OFF+hh%10));
		bld.setCharAt(3, (char)(DIGIT_OFF+mm/10));
		bld.setCharAt(4, (char)(DIGIT_OFF+mm%10));
		bld.setCharAt(6, (char)(DIGIT_OFF+ss/10));
		bld.setCharAt(7, (char)(DIGIT_OFF+ss%10));
		bld.setCharAt(9, (char)(DIGIT_OFF+ms/100));
		bld.setCharAt(10, (char)(DIGIT_OFF+(ms%100)/10));
		bld.setCharAt(11, (char)(DIGIT_OFF+(ms%100)%10));
		return bld;
	}
	
	public CharSequence getStringDate()
	{
		final Calendar c=getMyCalendar();
		final int yy=c.get(Calendar.YEAR);
		final int mm=c.get(Calendar.MONTH)+1;
		final int dd=c.get(Calendar.DAY_OF_MONTH);
		final StringBuilder bld=new StringBuilder("00000000");
		
		bld.setCharAt(0, (char)(DIGIT_OFF+yy/1000));
		bld.setCharAt(1, (char)(DIGIT_OFF+(yy%100)/100));
		bld.setCharAt(2, (char)(DIGIT_OFF+(yy%100)/10));
		bld.setCharAt(3, (char)(DIGIT_OFF+(yy%10)));
		bld.setCharAt(4, (char)(DIGIT_OFF+mm/10));
		bld.setCharAt(5, (char)(DIGIT_OFF+mm%10));
		bld.setCharAt(6, (char)(DIGIT_OFF+dd/10));
		bld.setCharAt(7, (char)(DIGIT_OFF+dd%10));
		return bld;
	}
	
	public static void resetTime(Calendar cal)
	{
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
	}
	
	public static void resetToMonthStart(Calendar cal)
	{
		
	}
	
	public static void resetToWeekStart(Calendar cal)
	{
		cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
	}
	
	public static void resetToWeekEnd(Calendar cal, boolean biznessWeek)
	{
		resetToWeekStart(cal);
		//cal.add(Calendar.DAY,Calendar.MONDAY);
	}
	
	public static void resetToMonthEnd(Calendar cal)
	{
		resetToMonthStart(cal);
		cal.add(Calendar.DATE, -1);
	}
	
	
	public static class CurrentDateTime extends LocalDateTime
	{
		private static final long serialVersionUID = 1L;

		
		public CurrentDateTime()
		{
			this(TMZ_DEFAULT);
		}
		
		public CurrentDateTime(String tz)
		{
			super(TimeZone.getTimeZone(tz),0);
		}
		
		public CurrentDateTime(TimeZone tz)
		{
			super(tz,0);
		}

		@Override
		public long getTimeInMillis() 
		{
			return System.currentTimeMillis();
		}
		
		public static long getCurrentTime() {return System.currentTimeMillis();}
	}
	
	public static class FilteredCurrentDateTime extends CurrentDateTime
	{
		private static final long serialVersionUID = 1L;
		public FilteredCurrentDateTime(String tz) {	super(tz);}
		public FilteredCurrentDateTime(TimeZone tz) {super(tz);}
		
	}

	public static LocalDateTime createDate(String strDate) 
	{
		return new LocalDateTime(strDate);
	}

	public static long currentTime() 
	{
		return System.currentTimeMillis();
	}
	
	public static LocalDateTime todayLN()
	{
		return new LocalDateTime(System.currentTimeMillis());
	}
	
	public static LocalDateTime todayNY()
	{
		return new LocalDateTime(System.currentTimeMillis());
	}
	
	public static LocalDateTime todayTK()
	{
		return new LocalDateTime(System.currentTimeMillis());
	}

	public static int diff(final String s1, final String s2) 
	{
		LocalDateTime l1=new LocalDateTime(s1);
		LocalDateTime l2=new LocalDateTime(s2);
		return (int)l2.diffDays(l1.getTimeInMillis());
	}
}
