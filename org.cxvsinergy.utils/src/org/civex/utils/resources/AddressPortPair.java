package org.civex.utils.resources;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.StringTokenizer;

public class AddressPortPair implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected String host;
	final protected char proto=0;
	final protected int port;
	private InetAddress addr;
	
	public AddressPortPair(String host, int port)
	{
		this.host=host;
		this.port=port;
		try
		{
			addr=InetAddress.getByName(host);
		}
		catch(UnknownHostException x)
		{
			
		}
		
	}
	
	
	public AddressPortPair(final int p1,final int p2, final int p3,final int p4, final int port)
	{
		this(p1+"."+p2+"."+p3+"."+p4,port);
	}
	
	
	
	public boolean isMulticast()
	{
		return addr.isMulticastAddress();
	}
	
	
	
	
	public int getPort() {return port;}
	public String getAddrress() { return host;}
	public String getUrl() {	return getAddrress();}
	
	
	public static AddressPortPair parseFromString(String s)
	{
		final int idx=s.indexOf(':');
		if (idx<0) return new AddressPortPair(s,0);
		final int p=Integer.parseInt(s.substring(idx+1));
		return new AddressPortPair(s.substring(0,idx),p);
	}

	public static void parseFromString(String s, List<AddressPortPair> ports)
	{
		StringTokenizer stokens=new StringTokenizer(s,",");
		while(stokens.hasMoreTokens())
		{
			ports.add(parseFromString(stokens.nextToken()));
		}
	}
	
	
	public String toPortSuffix()
	{
		return addr.getAddress()[2]+""+addr.getAddress()[3];
	}

	@Override
	public String toString() 
	{
		return host+":"+port;
	}
	
	
	
	public Object getProto()
	{
		return null;
	}
	
	public DatagramSocket createDatagramSocket() throws IOException
	{
		final InetAddress addr=InetAddress.getByName(getUrl());
		if (isMulticast())
		{
			final MulticastSocket socket=new MulticastSocket(getPort());
			socket.joinGroup(addr);
			return socket;
		}	
		return new DatagramSocket(getPort(),addr);
		
	}
	
	 
}
