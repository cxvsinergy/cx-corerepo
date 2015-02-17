package org.civex.utils.numeric;

import java.util.Map;
import java.util.TreeMap;


public class NamedVektorNumber extends VektorNumber
{
	private static final long serialVersionUID = 1L;
	final private Map<String,Integer> names=new TreeMap<String,Integer>();
	
	public NamedVektorNumber(int d, String names[]) 
	{
		super(d);
		for (int ii=0;ii<names.length;ii++)
		{
			this.names.put(names[ii], ii);
		}
	}
	
	public double getValueByName(String name)
	{
		final int index=names.get(name);
		return getVektorValue(index);
		
	}
	
	public void setValueByName(String name, double value)
	{
		final int index=names.get(name);
		setVektorValue(index,value);
	}

	
	public void addValueByName(String name, double value, double scalar)
	{
		final int index=names.get(name);
		double v=getVektorValue(index);
		this.setVektorValue(index, v+scalar*value);
	}
	
	
	public void add(NamedVektorNumber nv, double scalar) 
	{
		for (Map.Entry<String,Integer> entry:nv.names.entrySet())
		{
			addValueByName(entry.getKey(), nv.getVektorValue(entry.getValue()),1);
		}
	}

	
	public void mul(NamedVektorNumber nv, double scalar) 
	{
		// TODO Auto-generated method stub
		super.mul(nv, scalar);
	}

	
	
}
