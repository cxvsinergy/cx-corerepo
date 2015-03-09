package org.civex.utils;

import org.civex.utils.tests.ObjectTestCase;

public class ObjectConformanceTest extends ObjectTestCase
{
	public void testQualifiedName()
	{
		QualifiedName q1=new QualifiedName("aaa","bbb");
		QualifiedName q2=new QualifiedName("aaa","bbb");
		QualifiedName q3=new QualifiedName("ccc","bbb");
		performCompareTest(q1, q2, q3);
		performObjectTest(q1,q2,q3);
		
	}
	
	
	public void testKeyVakuePair()
	{
		KeyValuePair<String,String> q1=new KeyValuePair<String,String>("aaa","bbb");
		KeyValuePair<String,String> q2=new KeyValuePair<String,String>("aaa","bbb");
		KeyValuePair<String,String> q3=new KeyValuePair<String,String>("ccc","bbb");
		performCompareTest(q1, q2, q3);
		performObjectTest(q1,q2,q3);
		
	}
	
	public void testCurrencyNumber()
	{
		
		
		CurrencyNumber q1=new CurrencyNumber("a","usd",23);
		CurrencyNumber q2=new CurrencyNumber("b","usd",23);
		CurrencyNumber q3=new CurrencyNumber("c","usd",24);
		performCompareTest(q1, q2, q3);
		performObjectTest(q1,q2,q3);
		
		
	}
}
