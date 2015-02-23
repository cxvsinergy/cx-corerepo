package org.cxvsinergy.webjdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import junit.framework.TestCase;

public class WebJdbcConnectionTest extends TestCase
{
	public void testMe() throws SQLException
	{
		
		Properties props=new Properties();
		props.setProperty("webjdbc-driver", "com.mysql.jdbc.Driver");
		props.setProperty("webjdbc-conn", "jdbc:mysql://192.168.1.71/test?user=monty&password=greatsqldb");
		props.setProperty("webjdbc-user", "");
		props.setProperty("webjdbc-password", "");
		
		WebJdbcDriver driver=new WebJdbcDriver();
		Connection conn=driver.connect("jdbc:mysql://192.168.1.71/test", "mysql","mysql");
		
		
	}
}
