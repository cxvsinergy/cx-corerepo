package org.civex.utils;

public class KeyValuePair <K,V> extends AbstractKeyValuePair<K,V>
{
	
	final private K key;
	private V	value;
	
	public KeyValuePair(K key)
	{
		this(key,null);
	}

	public KeyValuePair(K key, V v)
	{
		super(key,v);
		assert(key!=null);
		this.key=key;
		this.value=v;
	}
	

	@Override
	public V getValue() 
	{
		return value;
	}

	@Override
	public K getKey() 
	{
		return key;
	}
}
