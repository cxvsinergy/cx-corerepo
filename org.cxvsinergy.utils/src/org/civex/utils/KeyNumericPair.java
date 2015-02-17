package org.civex.utils;

public class KeyNumericPair<K> extends AbstractKeyValuePair<K,Double>
{
	public static final KeyNumericPair<?> EMPTY[]={};
	final private K key;
	private double	value;
	
	public KeyNumericPair(K key)
	{
		this(key,0);
	}
	
	public KeyNumericPair(K key, double v)
	{
		this.key=key;
		this.value=v;
	}
	
	public void inc()
	{
		value++;
	}
	
	
	public void dec()
	{
		value--;
	}
	
	public void setValue(int v)
	{
		value=v;
	}
	
	public Double getValue() {return value;}
	
	public int getArraySize() {return 1;}
	
	public double getByIndex(int index) {return value;}
	
	public int getValueAsInt() {	return (int)value; }
	
	public double getNumericValue() 
	{
		return value;
	}
	
	
	public double diff(KeyNumericPair<K> other)
	{
		return value-other.value;
	}
	
	
	
	public double diff(double other)
	{
		return value-other;
	}
	
	public double diffSqr(double other)
	{
		return (value-other)*(value-other);
	}
	
	public double diffAbs(double other)
	{
		return Math.abs(value-other);
	}
	
	public static boolean between(double v, double v1, double v2)
	{
		return v>=v1 && v<=v2;
	}
	
	public boolean between(double v1, double v2)
	{
		return this.value>=v1 && this.value<=v2;
	}
	
	public K getKey()
	{
		return key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final KeyNumericPair<?> other = (KeyNumericPair<?>) obj;
		return key==other.key || key.equals(other.key);
	}
	
	public KeyNumericPair<K> createNew(K key, double v)
	{
		return new KeyNumericPair<K>(key,v);
	}
}
