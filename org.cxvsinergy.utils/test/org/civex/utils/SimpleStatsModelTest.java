package org.civex.utils;

import java.util.Random;

import junit.framework.TestCase;

public class SimpleStatsModelTest extends TestCase
{
	public void testMe()
	{
		double d1[]={1.48,1.45,1.42,1.38,1.35,1.32,1.29,1.26,1.23,1.20};
		double d2[]={5,8,10,15,20,30,20,12,20,12,};
		double d3[]=new double[d2.length];
		double sum=0;
		for (double d:d2) sum+=d;
		//sum=0;
		double msum=0;
		for (int ii=1;ii<d3.length;ii++)
		{
			msum+=d2[ii];
			d3[ii]=msum/sum;
			d3[ii]=d3[ii]<0.5?d3[ii]:1-d3[ii];
		}
		for (double d:d3) System.out.println("  "+d);
		
		double rate=1.29;
		double vv=0.12;
		Random rr1=new Random();
		Random rr2=new Random();
		
		for (int ii=0;ii<250;ii++)
		{
			
			int x=findIndex(rate,d1);
			double r1=Math.random();
			double r2=fromZ(rr2.nextGaussian(),0,0.007);
			if (r1>d3[x]) rate+=r2;
			else		
			{
				rate+=x>5?0.01+r2:-0.01-r2;
			}
			
				
			//
			rate+=r2;
			System.out.println(r(rate)+"  "+r(r1)+" "+r(r2)+"  "+d3[x]+"  "+x);
				
		}
	}
	
	
	protected int findIndex(double v, double arr[])
	{
		
		for (int ii=1;ii<arr.length;ii++)
		{
			if (arr[ii]==v) return ii;
			if (arr[ii-1]>v && arr[ii]<v) return ii;
			if (arr[ii-1]<v && arr[ii]>v) return ii;
		}
		return arr.length-1;
	}
	
	public double fromZ(double v, double mean, double sigma)
	{
	return mean+v*sigma;	
	}
	
	protected double r(double v) {return (int)(v*10000D)/10000D;}
}
