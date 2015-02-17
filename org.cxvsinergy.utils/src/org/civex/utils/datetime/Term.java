package org.civex.utils.datetime;

import java.util.concurrent.TimeUnit;


public enum Term implements Comparable<Term>
{
	SPOT(DateTerm.DAY,0),
	PERPETUAL(DateTerm.DAY,365*100),
	UNDEFINED(DateTerm.DAY,Integer.MAX_VALUE),
	T1(DateTerm.DAY,1),T2(DateTerm.DAY,2),T3(DateTerm.DAY,3),
	D1(DateTerm.DAY,1),D2(DateTerm.DAY,2),D3(DateTerm.DAY,3),D4(DateTerm.DAY,4),D5(DateTerm.DAY,5),
	D6(DateTerm.DAY,6), D7(DateTerm.DAY,7),
	W1(DateTerm.WEEK,1),W2(DateTerm.WEEK,2),W3(DateTerm.WEEK,3),W4(DateTerm.WEEK,4),
	M1(DateTerm.MONTH,1),M2(DateTerm.MONTH,2),M3(DateTerm.MONTH,3),M4(DateTerm.MONTH,4),
	M5(DateTerm.MONTH,5),M6(DateTerm.MONTH,6),M7(DateTerm.MONTH,7),M8(DateTerm.MONTH,8),
	M9(DateTerm.MONTH,9),M10(DateTerm.MONTH,10),M11(DateTerm.MONTH,11),M12(DateTerm.MONTH,12),
	Y1(DateTerm.YEAR,1),Y2(DateTerm.YEAR,2),Y3(DateTerm.YEAR,3),Y4(DateTerm.YEAR,4),
	Y5(DateTerm.YEAR,5),Y6(DateTerm.YEAR,6),Y7(DateTerm.YEAR,7),Y8(DateTerm.YEAR,8),Y9(DateTerm.YEAR,9),
	Y10(DateTerm.YEAR,10),Y12(DateTerm.YEAR,12),Y15(DateTerm.YEAR,15),Y20(DateTerm.YEAR,20),
	IMM1(DateTerm.QUOTER,1),IMM2(DateTerm.QUOTER,2),IMM3(DateTerm.QUOTER,3),IMM4(DateTerm.QUOTER,4),
	Q1(DateTerm.QUOTER,1),Q2(DateTerm.QUOTER,2),Q3(DateTerm.QUOTER,3),Q4(DateTerm.QUOTER,4),
	Q5(DateTerm.QUOTER,5),Q6(DateTerm.QUOTER,6),Q7(DateTerm.QUOTER,7),Q8(DateTerm.QUOTER,8),
	H1(DateTerm.HALFYEAR,1),H2(DateTerm.HALFYEAR,2),H3(DateTerm.HALFYEAR,3),H4(DateTerm.HALFYEAR,4);
	
	final private DateTerm term;
	final private int len;
	final private int days;
	
	private Term(DateTerm term, int len)
	{
		this.term=term;
		this.len=len;
		this.days=getTermCalendarDays();
	}
	
	
	public DateTerm getDateTerm()
	{
		return term;
	}
	
	public int compare(final Term t)
	{
		final int v=getDays()-t.getDays();
		if (v==0) return v;	
		return 1D*Math.abs(v)/getDays()<0.03?0:v;
	}
	
	
	public int getDays() {return days;}
	
	private int getTermCalendarDays()
	{
		switch(term)
		{
			case SPOT:     return 0;
			case DAY:      return len;
			case WEEK:     return len*7;
			case MONTH:    return len*30+(len*30)/90;
			case QUOTER:   return 91*len;
			case HALFYEAR: return 182*len;
			case YEAR:     return 365*len;
		}
		throw new RuntimeException("Unsupported "+term);
	}
	
	
	public int getNumberOfTerms(Term other)
	{
		if (compare(other)<0) return 0;
		return getDays()/other.getDays();
	}
	
	public int getLen() {return len;}
	
	
	public boolean greater(Term other)
	{
		return getDays()>other.getDays();
	}
	
	public boolean greaterEquals(Term other)
	{
		return getDays()>=other.getDays();
	}
	
	
	public boolean isDefined()    {return this!=UNDEFINED;}
	public TimeUnit getTimeUnit() {return TimeUnit.DAYS;}
	public long getDurationMs()   {return getDays()*DateTerm.ONE_DAY_MS;}
	
	
	public static Term parseFromString(final CharSequence seq)
	{
		return UNDEFINED;
	}
} 
