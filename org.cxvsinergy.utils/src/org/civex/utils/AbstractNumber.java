package org.civex.utils;

import java.util.Collections;
import java.util.List;

/**
 *  base class for working with numeric data
 *
 */
public abstract class AbstractNumber extends Number implements IObjectVector<Number>
{
	private static final long serialVersionUID = 1L;

	public AbstractNumber() {};
	
	public AbstractNumber(double v) {};
	
	public AbstractNumber(Number v) 
	{
		this(v.doubleValue());
	};
	
	@Override
	public float floatValue() 
	{
		return (float)doubleValue();
	}

	@Override
	public int intValue() 
	{
		return (int)doubleValue();
	}

	@Override
	public long longValue() 
	{
		return (long)doubleValue();
	}
	
	public boolean immutable()
	{
		return true;
	}
	

	public List<AbstractNumber> asList()
	{
		return Collections.singletonList(this);
	}
	
	
	public AbstractNumber asNumber() { return this;}
	
	
	@Override
	public AbstractNumber getValueByIndex(int index) 
	{
		return this;
	}

	@Override
	public int vectorSize() 
	{
		return 1;
	}
	/**describes type of this number**/
	public int getNumberClassifier()
	{
		return 0;
	}
	
	public double delta(Number v2) { return delta(v2.doubleValue());}
	
	public double delta(double v2) { return v2-doubleValue();}
	
	public double deltaPct(double v2) 
	{
		final double v=doubleValue();
		return (v2-v)/v;
	}
	
	public CharSequence getNumberText()
	{
		return toString();
	}
	
	public byte[] getAsBytes()
	{
		return new byte[]{};
	}
	
	protected boolean equalsDouble(Number v)
	{
		return equalsDouble(v);
	}
	
	protected boolean equalsDouble(double v)
	{
		return Double.compare(doubleValue(), v)==0;
	}
	
	protected int compareDouble(double v)
	{
		return Double.compare(doubleValue(), v);
	}
	
	protected int compareDouble(Number v)
	{
		return compareDouble(v.doubleValue());
	}
	
	protected StringBuilder dumpString(StringBuilder bld)
	{
		return bld.append(doubleValue());
	}

	@Override
	public String toString()
	{
		return dumpString(new StringBuilder(100)).toString();
	}
	
	

}
