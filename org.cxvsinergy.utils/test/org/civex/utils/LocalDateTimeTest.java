package org.civex.utils;

import junit.framework.TestCase;

import org.civex.utils.datetime.LocalDateTime;
import org.civex.utils.datetime.LocalDateTimeCountdown;
import org.civex.utils.datetime.TimeIterator;

public class LocalDateTimeTest extends TestCase
{
	public void testMe()
	{
		LocalDateTime l1=new LocalDateTime("Asia/Singapore","20140827-21:45");
		LocalDateTime l2=new LocalDateTime("Asia/Singapore","20140827-21:45:00");
		LocalDateTime l3=new LocalDateTime("Asia/Singapore","20140827-21:45:05.234");
		LocalDateTime l4=new LocalDateTime("Asia/Singapore","20140827");
		LocalDateTime l5=new LocalDateTime("Asia/Singapore","20150101-00:00");
		assertEquals(20140827,l4.getLocalDate());
		assertEquals(214505234,l3.getLocalTime());
		assertEquals(20150101,l5.getLocalDate());
		assertEquals(0,l5.getLocalTime());
		
		LocalDateTime l6=new LocalDateTime("Asia/Singapore",l3.getLocalDay());
		LocalDateTime l7=new LocalDateTime("Asia/Singapore","20370101-23:59");
		
		LocalDateTime l8=new LocalDateTime.CurrentDateTime("GMT"); 
		//System.out.println(l6);
		//System.out.println(l3.getStringTime()+"  "+l7.getStringDate());
		//System.out.println(LocalDateTime.TIME_MSK.getStringTime());
	}
	
	
	public void t1estT2()
	{
		final LocalDateTimeCountdown t1=new LocalDateTimeCountdown("20140821",55);
		final LocalDateTimeCountdown indoVisa1=new LocalDateTimeCountdown("20140821",30);
		final LocalDateTimeCountdown indoVisa2=indoVisa1.addDays(30);
		final LocalDateTimeCountdown sgtime=new LocalDateTimeCountdown("20140227",180);
		//
		final LocalDateTimeCountdown life1=new LocalDateTimeCountdown("20220827");
		final LocalDateTimeCountdown life2=new LocalDateTimeCountdown("20270827");
		final LocalDateTimeCountdown life3=new LocalDateTimeCountdown("20370827");
		//
		System.out.println(t1+"  "+t1.getDaysRemained());
		System.out.println("IndoVisa 1:  "+indoVisa1);
		System.out.println("IndoVisa 2:  "+indoVisa2);
		System.out.println("SingTime:  "  +sgtime);
		//
		System.out.println("biz life:\t"  +life1.getBusinessDaysRemained()+" "+life2.getBusinessDaysRemained()+"  "+life3.getBusinessDaysRemained());
		System.out.println("life:\t\t"    +life1.getDaysRemained()+" "+life2.getDaysRemained()+"  "+life3.getDaysRemained());
		
	}
	
	public void testT3()
	{
		LocalDateTime dt=new LocalDateTime("20140925");
		TimeIterator it=new TimeIterator.BusinessDayIterator(dt);
		
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
	}
}
