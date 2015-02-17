package org.civex.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.civex.utils.collections.FrequencyBag;

import junit.framework.TestCase;

public class FrequencyBagTest extends TestCase
{
		public void testMe1()
		{
			List<KeyNumericPair<Double>> l=new ArrayList<KeyNumericPair<Double>>();
			for (int ii=0;ii<10;ii++) l.add(new KeyNumericPair<Double>(ii*1D));
			FrequencyBag<Double,KeyNumericPair<Double>> fb=new FrequencyBag<Double,KeyNumericPair<Double>>(l);
			Random r=new Random();
			for (int ii=0;ii<10000;ii++) 
			{
				fb.add(r.nextInt(10)*1D);
			}
			System.out.println(fb.getIndexRow(1)+"   "+fb.getStdDev()+"  ");
			for (int ii=0;ii<10;ii++)
			{
				System.out.println(fb.getRandomBagElement());
			}
		}
}
