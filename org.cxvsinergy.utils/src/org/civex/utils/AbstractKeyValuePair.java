package org.civex.utils;

public abstract class AbstractKeyValuePair<K, V> implements IdentifiedObject<K>
{
	
	
	protected AbstractKeyValuePair()
	{}
	
	public AbstractKeyValuePair(K k)
	{
		this(k,null);
	}
	
	public AbstractKeyValuePair(K k, V v)
	{
		
	}
	
public void setValue(V v)
{
}

public abstract V getValue();
public abstract K getKey();


public boolean isDefined() {return hasNullValue();}
public boolean hasNullValue() {return getValue()!=null;}
public V getDefaultValue() {return null;}
public V getAnyValue() {final V v=getValue(); return v!=null?v:getDefaultValue();}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((getKey() == null) ? 0 : getKey().hashCode());
	return result;
}

@Override
public boolean equals(Object obj) 
{
	if (this == obj) return true;
	if (obj == null) return false;
	if (getClass() != obj.getClass()) return false;
	final KeyValuePair<?,?> other = (KeyValuePair<?,?>) obj;
	return this.getKey()==other.getKey() || this.getKey().equals(other.getKey());
}

@Override
public String toString() 
{
	return getKey()+" :: "+getValue();
}

@Override
public K getObjectId() {return this.getKey();}

@Override
public Class<?> getObjectClassifier() { return this.getKey().getClass();}


@Override
public long getLongHash() {return 0;}

@Override
public long getObjectVersion() {return 0;}



}
