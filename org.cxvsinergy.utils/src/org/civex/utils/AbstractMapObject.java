package org.civex.utils;

import java.util.Map;

public abstract class AbstractMapObject<K,V>
{
	protected abstract Map<K,V> getMapImpl();
	protected V getMapValue(K k) {return getMapImpl().get(k);}
	protected void setMapValue(K k, V v) {getMapImpl().put(k, v);}
	
}
