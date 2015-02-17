package org.civex.utils.datetime;

import org.civex.utils.IdentifiedNumber;

public class DateTimeNumber<K> extends IdentifiedNumber<K>
{
	private static final long serialVersionUID = 1L;
	final private LocalDateTime dt;
	
	public DateTimeNumber(final K id, LocalDateTime dt, double v) 
	{
		super(id, v);
		this.dt=dt;
	}

	public LocalDateTime getNumberDateTime() { return dt;}
}
