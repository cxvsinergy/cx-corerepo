package org.civex.utils.numeric;

import java.util.Random;

public class RandomVector<V  extends Number> extends ProtoVector<V>
{
	final private Random[] rr;
	final private double pp1[], pp2[];
	final private int sz;
	final private int typ[];
	
	public RandomVector(int sz)
	{
		this.sz=sz;
		rr=new Random[sz];
		pp1=new double[sz];
		pp2=new double[sz];
		typ=new int[sz];
		for (int ii=0;ii<sz;ii++) rr[ii]=new Random();
	}
	
	
	public void setTypeAll(int type)
	{
		for (int ii=0;ii<sz;ii++) this.typ[ii]=type;
	}
	
	public void setBinary(int index)
	{
		this.typ[index]='B';
	}
	
	public void setValueRange(int index, double min, double max, int stepping)
	{
		this.typ[index]='U';
		this.pp1[index]=min;	
		this.pp2[index]=max;
	}
	
	public void setGaussianRange(int index, final double mean, double sigma)
	{
		this.typ[index]='G';
		this.pp1[index]=mean;	
		this.pp2[index]=sigma;
	}

	@Override
	public int vectorSize() 
	{
		return sz;
	}

	@Override
	public double getDoubleValue(int index) 
	{
		switch(typ[index])
		{
			case 'G': return pp1[index]+rr[index].nextGaussian();
			case 'U': return rr[index].nextDouble();
			case 'B': return rr[index].nextInt(2)<2?0:1;
		}
		return 0;
	  
	}

}
