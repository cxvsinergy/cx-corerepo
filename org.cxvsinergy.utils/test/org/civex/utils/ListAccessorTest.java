package org.civex.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

public class ListAccessorTest extends TestCase
{
		public void testKeyValuePair()
		{
			final List<KeyValuePair<String,Double>> base=new ArrayList <KeyValuePair<String,Double>>();
			final CompositeValueList<KeyValuePair <String,Double>> l=new CompositeValueList <KeyValuePair<String,Double>>(base);
			l.add(new KeyValuePair<String,Double>("one",1000D));
			l.add(new KeyValuePair<String,Double>("two",2000D));
			l.add(new KeyValuePair<String,Double>("three",3000D));
			List<Double> ld=l.getObjectList(new KeyValuePair.PairValueObjectResolver<List<KeyValuePair<String,Double>>,String,Double>());
			System.out.println(ld.get(1));
			Iterator<Double> it=ld.iterator();
			System.out.println(it.next());
		}
}
