package org.civex.utils;

import java.util.Comparator;

public class ObjectHashComparator<K> implements Comparator<K>
{
	final public static Comparator<Object> HASH_COMPARATOR=new DirectHashComparator<Object>(); 
	
	@Override
	public int compare(K o1, K o2) 
	{
		if (o1==o2) return 0;
		if (o1!=null && o2==null) return -1;
		if (o1==null && o2!=null) return  1;
		if (o1 instanceof Comparable<?>) return ((Comparable<K>)o1).compareTo(o2);
		return Integer.compare(o1.hashCode(), o2.hashCode());
	}
	
	
	public static class DirectHashComparator<T> implements Comparator<T>
	{
		@Override
		public int compare(T o1, T o2) 
		{
			int d=o1.hashCode()-o2.hashCode();
			if (d!=0) return d;
			return o1.equals(o2)?0:-1;
		}

	}
}
