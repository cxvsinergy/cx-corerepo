package org.civex.utils;

public class KeyValueFixedPair <K,V> extends AbstractKeyValuePair<K,V>
{
	
	final private K key;
	final private V	value;
	
	public KeyValueFixedPair(K key)
	{
		this(key,null);
	}

	public KeyValueFixedPair(K key, V v)
	{
		super(key,v);
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
