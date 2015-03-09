package org.civex.utils;

import org.civex.utils.tests.ObjectTestCase;

public class KeyNumericPairTest extends ObjectTestCase<IntegerKeyValuePair<String>>
{
  public void testNumericpair()
  {
	  IntegerKeyValuePair<String> o1=new IntegerKeyValuePair<String>(101);
	  IntegerKeyValuePair<String> o2=new IntegerKeyValuePair<String>(101);
	  IntegerKeyValuePair<String> o3=new IntegerKeyValuePair<String>(123);
	  performObjectTest(o1, o2, o3);
	  IntegerKeyValuePair<String> o4=new IntegerKeyValuePair<String>(0xfe347891);
	  assertEquals(0xfe,o4.getKeyInt(3));
	  assertEquals(0x34,o4.getKeyInt(2));
	  assertEquals(0x78,o4.getKeyInt(1));
	  assertEquals(0x91,o4.getKeyInt(0));
	  /*
	  assertEquals(0xf,o4.getKeyInt(7,4));
	  assertEquals(0xe,o4.getKeyInt(6,4));
	  assertEquals(0x3,o4.getKeyInt(5,4));
	  assertEquals(0x4,o4.getKeyInt(4,4));
	  assertEquals(0x7,o4.getKeyInt(3,4));
	  assertEquals(0x8,o4.getKeyInt(2,4));
	  assertEquals(0x9,o4.getKeyInt(1,4));
	  assertEquals(0x1,o4.getKeyInt(0,4));
	  */
  }
  
  
}
