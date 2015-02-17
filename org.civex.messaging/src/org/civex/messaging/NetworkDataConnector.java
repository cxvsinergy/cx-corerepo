package org.civex.messaging;

import java.io.IOException;
import java.net.InetAddress;

public abstract class NetworkDataConnector<T> extends AbstractDataConnector
{
	final protected  int port;
	final protected  String host;
	final protected InetAddress addr;
	
	public NetworkDataConnector()
	{
		port=-1;
		host=null;
		addr=null;
	}
	
	public NetworkDataConnector(String host, int port) throws IOException
	{
		this.port=port;
		this.host=host;
		this.addr=InetAddress.getByName(host);					
	}
	
	public void sendRaw(byte buff[], int sz, InetAddress addr) throws IOException
	{
		
	}
	
	public void sendRaw(String s, InetAddress addr) throws IOException
	{
		byte[] buff=s.getBytes();
		sendRaw(buff,buff.length,addr);
	}
	
	
	
	@Override
	public void listen() throws IOException
	{
		while(isConnected())
		{
			processReceivedData();
		}
	}
	
	
	protected  abstract void processReceivedData() throws IOException;

}
