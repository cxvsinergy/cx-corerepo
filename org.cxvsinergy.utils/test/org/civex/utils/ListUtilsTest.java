package org.civex.utils;

import java.util.List;

import junit.framework.TestCase;

import org.civex.utils.collections.StringList;

public class ListUtilsTest extends TestCase
{
   public void testStringList()
   {
	   List<String> l=new StringList("a;b;c;d");
	   assertEquals(4,l.size());
	   assertEquals("a",l.get(0));
	   assertEquals("d",l.get(3));
	   assertEquals(4,l.size());
	   
	   StringList l1=new StringList("a=1;b;c=3;d;");
	   assertEquals("1",l1.getByKey("a"));
	   assertEquals("3",l1.getByKey("c"));
	   l1.add("ee=56;hjk=789;");
	   assertEquals("56",l1.getByKey("ee"));
	   

   }
}
