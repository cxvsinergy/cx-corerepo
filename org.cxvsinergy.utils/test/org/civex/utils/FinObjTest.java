package org.civex.utils;

import junit.framework.TestCase;

import org.civex.utils.datetime.Term;
import org.civex.utils.fin.Rate;

public class FinObjTest extends TestCase
{
	
		public void testTerm()
		{
			Term t1=Term.M1;
			Term y1=Term.Y1;
			Term w1=Term.W1;
			Term q1=Term.Q1;
			Term h1=Term.H1;
			Term h2=Term.H2;
			assertTrue(y1.compareTo(t1)>0);
			assertEquals(12,y1.getNumberOfTerms(t1));
			assertEquals(52,y1.getNumberOfTerms(w1));
			assertEquals(4,y1.getNumberOfTerms(q1));
			assertTrue(Term.Y1.compare(Term.M12)==0);
			assertTrue(Term.Y1.compare(Term.H2)==0);
			assertTrue(Term.Y1.compare(Term.Q4)==0);
			assertTrue(Term.Y2.compare(Term.H4)==0);
			//assertTrue(Term.M1.compare(Term.W4)==0);
			
		}
		
		
		public void testMe()
		{
			Rate r1=new Rate(0.05,Term.Y1);
			Rate r2=new Rate(0.05,Term.M6);
			Rate r3=new Rate(0.05,Term.M2);
			assertEquals(0.025,r1.getRate(Term.M6));
			System.out.println(r2.getEffectiveRate());
		}
}
