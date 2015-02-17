package org.civex.utils.datetime;

import java.util.TimeZone;

public class LocalTime extends LocalDateTime
{

	public LocalTime(long v) {
		super(v);
		// TODO Auto-generated constructor stub
	}

	public LocalTime(String tzstr, CharSequence s) {
		super(tzstr, s);
		// TODO Auto-generated constructor stub
	}

	public LocalTime(String tzstr, long v) {
		super(tzstr, v);
		// TODO Auto-generated constructor stub
	}

	public LocalTime(TimeZone tz, CharSequence s) {
		super(tz, s);
		// TODO Auto-generated constructor stub
	}

	public LocalTime(TimeZone tz, long v) {
		super(tz, v);
		// TODO Auto-generated constructor stub
	}

}
