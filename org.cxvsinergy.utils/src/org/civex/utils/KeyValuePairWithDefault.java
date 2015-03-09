package org.civex.utils;

public class KeyValuePairWithDefault<K,V> extends  KeyValuePair <K,V> 
{
	final private V defaultValue;

	public KeyValuePairWithDefault(K key, V v, V dval)
	{
		super(key, v);
		this.defaultValue=dval;
	}

	public V getDefaultValue() { return this.defaultValue;}
	
}
