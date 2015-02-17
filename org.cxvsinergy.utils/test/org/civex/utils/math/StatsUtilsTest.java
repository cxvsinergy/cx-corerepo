package org.civex.utils.math;

import junit.framework.TestCase;

import org.civex.utils.datetime.LocalDateTime;

public class StatsUtilsTest extends TestCase
{
	public void testMe()
	{
		
		final double v1=StatsUtils.pdfPoisson(5,1,1/250D); // 80 avg=10; p=10/100000 250d 10
		final double v3=StatsUtils.pdfPoisson(5,4,125/250D); // 80 avg=10; p=10/100000 250d 10
		//for (int ii=8;ii<12;ii++) System.out.println(StatsUtils.pdfPoisson(10,ii,1));
		final double v2=StatsUtils.cdfPoisson(10,9,11,1D,false); // 80 avg=10; p=10/100000 250d 10
		final double v4=StatsUtils.pdfPoisson(1,0); // 80 avg=10; p=10/100000 250d 10
		System.out.println(v1+ "  "+v4);
		final int days=LocalDateTime.diff("20140916","20140808");
		final int nyd=LocalDateTime.diff("20141225","20140916"); // 70 business days to NY
		System.out.println(nyd*5/7);
		
	}

}
