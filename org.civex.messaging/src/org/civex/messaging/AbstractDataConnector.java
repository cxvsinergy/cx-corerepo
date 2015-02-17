package org.civex.messaging;

import java.io.IOException;

public abstract class AbstractDataConnector 
{
	private long lastAccess;
	private int connectedState=1;
	
	public void startListen(Runnable r)
	{
		
	}
	
	public void connect() throws IOException
	{
		
	}
	
	public void disconnect() throws IOException
	{
		
	}
	
	public void listen() throws IOException
	{
		
	}
	
	public boolean isConnected()
	{
		return connectedState==1;
	}
	
}
