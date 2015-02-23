package org.cxvsinergy.webjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WebJdbcConnection extends AbstractProxyConnection
{
	final private Connection conn;

	
	public WebJdbcConnection(String url, String login, String pwd) throws SQLException
	{
		this.conn=DriverManager.getConnection(url,login,pwd);;
	}
	
	public WebJdbcConnection(Connection conn)
	{
		this.conn=conn;
	}
	
	@Override
	protected Connection getImpl() 
	{
		return conn;
	}
	
}
