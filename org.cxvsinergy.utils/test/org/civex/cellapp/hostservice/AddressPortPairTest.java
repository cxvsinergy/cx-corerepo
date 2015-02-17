package org.civex.cellapp.hostservice;

import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.civex.utils.resource.AddressPortPair;

public class AddressPortPairTest extends TestCase
{
	public void testMe()
	{
		AddressPortPair p1=AddressPortPair.parseFromString("239.16.77.23:7723");
		assertEquals(7723,p1.getPort());
		//
		MulticastSocket s;
		
		List<AddressPortPair> l=new ArrayList<AddressPortPair>();
		AddressPortPair.parseFromString("239.1.1.1:1,239.1.1.2:345",l);
		System.out.println(l);
		
	}
}
