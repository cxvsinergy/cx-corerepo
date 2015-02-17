package org.civex.utils;


public interface IObjectVector<V>
{
		//public S getVectorSpace();
		public V getValueByIndex(int index);
		public int vectorSize();
		//public IRange<Double> getRange()
		//public int roundFactor();
}
