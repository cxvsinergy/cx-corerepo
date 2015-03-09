package org.civex.utils.resources;

public class ConnectionCredentials 
{
	private String id;
	private String url;
	private String user;
	private String pwd;
	private AddressPortPair locport;
	
	public AddressPortPair getAddressPortPair() { return locport;}
	public Object getConnecionProperty(String id) {return id;}
	
	public String getUrl() {return url;}
	
	public String getUser()
	{
		return user;
	}

	public String getPwd()
	{
		return pwd;
	}
	
	
	public String getId() {return id;}
}
