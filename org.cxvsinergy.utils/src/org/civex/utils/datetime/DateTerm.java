package org.civex.utils.datetime;

public enum DateTerm {
	
	
	SPOT(0),DAY(1), WORKDAY(7),WEEK(7),MONTH(30), YEAR(365), QUOTER(91), HALFYEAR(182);
	
	public static final int ONE_DAY_MS=1000*24*60*60;
	private final int days;
	DateTerm(int sz)
	{
		this.days=sz;
	}
	
	public int getAvgDays()
	{
		return days;
	}
	
	public int getAvgMonths()
	{
		switch(this)
		{
			case MONTH: return 1;
			case YEAR: return 12;
			case HALFYEAR: return 6;
			case QUOTER: return 3;
			default: return 0;
		}
	}
	
	
	public boolean isDefined()
	{
		return true;
	}
	
	
	public int getInMs()
	{
		return getAvgDays()*ONE_DAY_MS;
	}
	

};	