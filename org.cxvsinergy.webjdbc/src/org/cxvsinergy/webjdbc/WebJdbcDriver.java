package org.cxvsinergy.webjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class WebJdbcDriver implements java.sql.Driver
{
	public static final String PARAM_TGT_URL="target-url";
	public static final String PARAM_TGT_LOGIN="target-login";
	public static final String PARAM_TGT_PASSWORD="target-password";
	@Override
	public boolean acceptsURL(String url) throws SQLException 
	{
		return url!=null && url.startsWith("jdbc:webjdbc://");
	}
	
	
	public Connection connect(String url, String login, String password) throws SQLException
	{
		final Connection conn=DriverManager.getConnection(url,login,password);
		final WebJdbcConnection myConn= new  WebJdbcConnection(conn);
		return myConn;
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException 
	{
		final String turl=info.getProperty(PARAM_TGT_URL);
		final String login=info.getProperty(PARAM_TGT_LOGIN);
		final String pwd=info.getProperty(PARAM_TGT_PASSWORD);
		return connect(turl,login,pwd);
	}

	
	
	@Override
	public int getMajorVersion() 
	{
		return 1;
	}

	@Override
	public int getMinorVersion() {
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException 
	{
		return null;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException 
	{
		return new DriverPropertyInfo[]{};
	}

	@Override
	public boolean jdbcCompliant() 
	{
		return true;
	}

}
