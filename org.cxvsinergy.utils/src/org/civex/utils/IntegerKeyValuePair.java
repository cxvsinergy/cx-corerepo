package org.civex.utils;

public class IntegerKeyValuePair<K> extends AbstractKeyValuePair<Integer,K> implements Comparable<AbstractKeyValuePair<Integer,?>>
{
	private final int key;
	private K value;

	
	public IntegerKeyValuePair() 
	{
		this(0,null);
		
	}

	public IntegerKeyValuePair(int k, K v) 
	{
		this.key=k;
		this.value=v;
	}

	public IntegerKeyValuePair(Integer k) 
	{
		this(k,null);
	}

	@Override
	public K getValue() 
	{
		return value;
	}

	@Override
	public Integer getKey() 
	{
		return key;
	}

	
	public int getKeyInteger()
	{
		return key;
	}
	@Override
	public void setValue(K v) 
	{
			this.value=v;
	}


	@Override
	public int hashCode() 
	{
		return key*10111;
	}

	@Override
	public boolean equals(Object obj) 
	{
			if (obj.hashCode()!=hashCode()) return false;
			return obj instanceof AbstractKeyValuePair;
	}

	@Override
	public int compareTo(AbstractKeyValuePair<Integer, ?> o) 
	{
		if (this==o || hashCode()==o.hashCode()) return 0;
		return Integer.compare(key, o.getKey());
	}
	
	// NOT Tested
	protected int getKeyInt(int ptr,int sz)
	{
		return (int)((key>>>((ptr*sz))) & ((2L<<sz)-1));
	}
	
	public int getKeyInt(int ptr)
	{
		return getKeyInt(ptr,8);
	}

}