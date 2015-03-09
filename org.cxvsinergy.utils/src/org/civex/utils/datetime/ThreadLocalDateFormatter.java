package org.civex.utils.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class ThreadLocalDateFormatter extends ThreadLocal<DateFormat>
{
	private final static Map<String,ThreadLocalDateFormatter> formatters=new HashMap<String,ThreadLocalDateFormatter>();
	public final static TimeZone GMT=TimeZone.getTimeZone("GMT");
	public final static String   FIX_SHORTDATE="YYYYMMDD";
	public final static String   FIX_DATE="YYYY-MM-DD";
	public final static String   FIX_DATETIMEMS="YYYYMMDD-hh:mi:ss.SSS";
	public final static String   FIX_DATETIME="YYYYMMDD-hh:mi:ss";
	
	
	private final String 	pattern;
	private final TimeZone	tzone;
	
	public ThreadLocalDateFormatter(String pattern, TimeZone tzone)
	{
		this.pattern=pattern;
		this.tzone=tzone;
	}
	@Override
	protected DateFormat initialValue() 
	{
		final SimpleDateFormat formatter=new java.text.SimpleDateFormat(pattern);
		formatter.setTimeZone(tzone);
		return formatter;
	}
	
	
	static
	{
		requestByPattern(FIX_SHORTDATE);
		requestByPattern(FIX_SHORTDATE);
	}
	//
	public static ThreadLocal<DateFormat> requestByPattern(final String pattern,final TimeZone tzone)
	{
		if (pattern==null) return null;
		ThreadLocalDateFormatter formatter=formatters.get(pattern);
		if (formatter!=null) return formatter;
		formatter=new ThreadLocalDateFormatter(pattern, tzone);
		// we do that seldom, not a problem if we overwrite previous 
		synchronized(formatters)
		{
			formatters.put(pattern, formatter);
		}
		return formatter;
		
	}

	
	public static ThreadLocal<DateFormat> requestByPattern(final String pattern)
	{
		return requestByPattern(pattern,GMT);
	}
}
