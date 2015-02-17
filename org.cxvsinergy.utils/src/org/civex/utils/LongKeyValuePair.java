package org.civex.utils;

public class LongKeyValuePair<K> extends AbstractKeyValuePair<Long,K> implements Comparable<AbstractKeyValuePair<Long,?>>
{
	protected final long key;
	private K value;

	
	public LongKeyValuePair() 
	{
		this(0,null);
		
	}

	public LongKeyValuePair(long k, K v) 
	{
		this.key=k;
		this.value=v;
	}
	
	
	public LongKeyValuePair(float k1, float k2, K v)
	{
		this(Float.floatToIntBits(k1),Float.floatToIntBits(k2),v);
	}
	
	public LongKeyValuePair(int k1, int k2, K v) 
	{
		this.key=(k1<<32L) | k2;
		this.value=v;
	}
	
	public LongKeyValuePair(double k, K v) 
	{
		this.key=Double.doubleToLongBits(k);
		this.value=v;
	}

	public LongKeyValuePair(Long k) 
	{
		this(k,null);
	}

	@Override
	public K getValue() 
	{
		return value;
	}

	@Override
	public Long getKey() 
	{
		return key;
	}

	
	public int getKeyInt(int index,int len,int sz)
	{
		return (int)(((key>>(sz*len)) & (2L<<sz*len)-1L));
	}
	
	
	
	public long getKeyLong()
	{
		return key;
	}
	
	public double getKeyDouble()
	{
		return Double.longBitsToDouble(key);
	}
	
	@Override
	public void setValue(K v) 
	{
			this.value=v;
	}


	@Override
	public int hashCode() 
	{
		return (int)(key & 0xffffffff);
	}

	@Override
	public boolean equals(Object obj) 
	{
			if (obj.hashCode()!=hashCode()) return false;
			return obj instanceof AbstractKeyValuePair;
	}

	@Override
	public int compareTo(AbstractKeyValuePair<Long, ?> o) 
	{
		if (this==o || hashCode()==o.hashCode()) return 0;
		return Long.compare(key, o.getKey());
	}
}