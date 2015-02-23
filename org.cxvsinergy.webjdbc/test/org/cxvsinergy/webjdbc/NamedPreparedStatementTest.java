package org.cxvsinergy.webjdbc;

import java.sql.SQLException;

import junit.framework.TestCase;

public class NamedPreparedStatementTest extends TestCase
{
	public void testSimple() throws SQLException
	{
		final NamedPreparedStatement stmt1=new NamedPreparedStatement("a1","SELECT * from testme where a=?,b=?");
		final NamedPreparedStatement stmt2=new NamedPreparedStatement("a1","SELECT * from testme where a=?,b=?");
		assertEquals(stmt1,stmt2);
		assertEquals(2,stmt1.getNumberOfParams());
		assertEquals(1,stmt1.getParameterIndexByName("param1"));
		assertEquals(2,stmt1.getParameterIndexByName("param2"));
	}
}
