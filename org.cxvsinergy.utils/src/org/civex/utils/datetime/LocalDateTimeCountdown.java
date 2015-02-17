package org.civex.utils.datetime;


public class LocalDateTimeCountdown extends AbstractCountdown
{
	private static final long serialVersionUID = 1L;
	final private LocalDateTime t;

	public LocalDateTimeCountdown(final LocalDateTime t)
	{
		this.t=t;
	}
	
	public LocalDateTimeCountdown(final String st)
	{
		this(new LocalDateTime(st));
	}
	
	public LocalDateTimeCountdown(final long timeMS)
	{
		this(new LocalDateTime(timeMS));
	}
	
	public LocalDateTimeCountdown(final String st, final int days)
	{
		this(new LocalDateTime(st).addDays(days));
	}
	
	@Override
	public LocalDateTime getTargetTime() 
	{
		return t;
	}

	
	public LocalDateTimeCountdown addDays(final int days)
	{
		return new LocalDateTimeCountdown(t.addDays(days));
	}
	
	
	
}
