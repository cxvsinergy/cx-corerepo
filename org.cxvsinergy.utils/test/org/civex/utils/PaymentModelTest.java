package org.civex.utils;

import org.civex.utils.fin.FinFormulas;

import junit.framework.TestCase;

public class PaymentModelTest extends TestCase 
{
		public void testPmt()
		{
			double p=FinFormulas.calcPayments(10000, 0.05, 4);
			System.out.println(p);
			final double p1=FinFormulas.calcPaymentsMonthly(260E3, 0.03,23);
			final double p2=FinFormulas.calcPaymentsMonthly(260E3, 0.05,23);
			final double p3=FinFormulas.calcPaymentsMonthly(260E3, 0.08,23);
			System.out.println(p1+" "+p2+"  "+p3);
		}
		
		public void testRate()
		{
			double p=FinFormulas.calcInterestRate(1000, 1100, 2);
			System.out.println(p);
		}
}
