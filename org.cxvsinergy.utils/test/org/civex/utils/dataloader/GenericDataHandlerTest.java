package org.civex.utils.dataloader;

import java.util.List;
import java.util.Map;

import org.civex.utils.resources.GenericDataHandler;

import junit.framework.TestCase;

public class GenericDataHandlerTest extends TestCase
{
	
	public void testFixMessages()
	{		
		String s1="ABC=1;DFG;color=12;sze=14";
		String s2="ABC=2;DFG;color=32;sze=18";
		GenericDataHandler<List<Map<CharSequence,CharSequence>>> h=new GenericDataHandler.StringListMapDataHandler<CharSequence,CharSequence>(GenericDataHandler.DIRECT_STRING_RESOLVER,";");
		h.upsertObjectValue(null, 1, s1);
		h.upsertObjectValue(null, 2, s2);
		List<Map<CharSequence,CharSequence>> lm=h.getCollectorObject();
		assertEquals(lm.size(),2);
		assertEquals(lm.get(1).get("sze"),"18");
	}
	
	
	public void testPropertyFormat()
	{		
		String s1="ABC=10-01";
		GenericDataHandler<Map<CharSequence,CharSequence>> h=new GenericDataHandler.StringMapDataHandler<CharSequence>(GenericDataHandler.DIRECT_STRING_RESOLVER);
		h.upsertObjectValue(null, 1, s1);
		Map<CharSequence,CharSequence> lm=h.getCollectorObject();
		assertEquals(lm.size(),1);
		assertEquals(lm.get("ABC"),"10-01");
	}
}
