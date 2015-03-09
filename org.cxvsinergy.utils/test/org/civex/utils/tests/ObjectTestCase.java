package org.civex.utils.tests;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;

public abstract class ObjectTestCase<T> extends TestCase 
{
		protected void performObjectTest(T o1, T o2, T o3)
		{
			assertTrue(o1.equals(o2));
			assertFalse(o1.equals(o3));
			assertFalse(o2.equals(o3));
			assertEquals(o1.hashCode(),o2.hashCode());
			assertNotSame(o1.hashCode(),o3.hashCode());
			//
			performCompareTest(o1,o2,o3);
		}
		
		
		protected void performCompareTest(T o1, T o2, T o3)
		{
			if (!(o1 instanceof Comparable<?>)) return;
			Comparable<T> c=(Comparable<T>)o1;
			Comparable<T> c2=(Comparable<T>)o3;
			assertEquals(c.compareTo(o2),0);
			assertTrue(c.compareTo(o3)<0);
			assertTrue(c2.compareTo(o1)>0);
		}
		
		protected void performCollectionTest(T o1, T o2, T o3)
		{
			if (!(o1 instanceof Collection<?>)) return;
			Collection<T> coll=(Collection<T>)o1;
			final Iterator<T> it=coll.iterator();
			assertNotNull(it);
			it.hasNext();	
		}
		
		protected void assertEq(double expected, double actual)
		{
			assertEquals(expected, actual, 1e-8);
		}
		
		public void assertBetween(double v, double l1, double l2)
		{
			assertTrue(v>=l1);
			assertTrue(v<=l2);
		}
		
		public void performCloneableTest(Object o, boolean comparable)
		{
			if (!(o instanceof Cloneable)) return;
			try
			{
				Method mth=o.getClass().getMethod("clone", new Class[]{});
				Object o2=mth.invoke(o, new Object[]{});	
				assertFalse(o2==o);
				assertTrue(o2.getClass()==o.getClass());
			}
			catch(Exception x)
			{
				 throw new RuntimeException(x);
			}
			
			
		}
}
