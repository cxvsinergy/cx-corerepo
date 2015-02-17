package org.civex.utils.numeric;

import java.util.Arrays;

import org.civex.utils.AbstractMutableNumber;

public class VektorNumber extends AbstractMutableNumber
{
    final private double v[]; 
	
    
	public VektorNumber(int d)
	{
		v=new double[d];
	}

	@Override
	public double doubleValue() 
	{
		double r=0;
		for (int ii=0;ii<v.length;ii++) r+=v[ii]*v[ii];
		return Math.sqrt(r);		
	}

	
	public void setVektorValue(int dim, double val)
	{
		v[dim]=val;
		listener.onNumberChanged(this, dim,val);
	}
	
	public double getVektorValue(int dim)
	{
		return v[dim];
	}
	
	public void add(double nv)
	{
		for (int ii=0;ii<v.length;ii++) v[ii]+=nv;
	}

	
	public void mul(double nv)
	{
		for (int ii=0;ii<v.length;ii++) v[ii]*=nv;
	}
	
	public void add(VektorNumber nv, double scalar)
	{
		for (int ii=0;ii<v.length;ii++) 
			{
					v[ii]+=nv.v[ii]*scalar;
			}
	}
	
	public void mul(VektorNumber nv,double scalar)
	{
		for (int ii=0;ii<v.length;ii++) 
			{
					v[ii]*=nv.v[ii]*scalar;
			}
	}
	
	public double[] getAsArray()
	{
		return v;
	}
	
	public VektorNumber getZeroVektor()
	{
		return new VektorNumber(v.length);
	}
	
	public VektorNumber getOneVektor()
	{
		VektorNumber rv=new VektorNumber(v.length);
		rv.setAll(1);
		return rv;
	}
	
	protected void setAll(double v)
	{
		Arrays.fill(this.v, 1);
	}

	
	public VektorNumber getAsVektor(int dim)
	{
		final VektorNumber vn=new VektorNumber(1);
		vn.setVektorValue(0, getVektorValue(dim));
		return vn;
	}
	
	public long copyToBytes(byte buff[], int off, int len)
	{
		return -1;
	}
	
	public void loadFromBytes(byte buff[], int off, int len)
	{
		//return -1;
	}

	@Override
	public void set(double v) 
	{
		// should not be used
	}


	public int getDims() 
	{
		return v.length;
	}	
}
