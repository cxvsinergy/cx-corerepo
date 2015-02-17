package org.civex.utils.datetime;

import java.util.TimeZone;

public class LocalDate extends LocalDateTime
{
	private static final long serialVersionUID = 1L;

	public LocalDate(long v) 
	{
		super(v);
	}

	public LocalDate(String tzstr, CharSequence s) 
	{
		super(tzstr, s);
	}

	public LocalDate(String tzstr, long v) 
	{
		super(tzstr, v);
	}

	public LocalDate(TimeZone tz, CharSequence s) 
	{
		super(tz, s);
	}

	public LocalDate(TimeZone tz, long v) 
	{
		super(tz, v);
	}

	@Override
	public long getTimeInMillis() {	return super.getLocalDay();};

}
