package org.cxvsinergy.webjdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NamedPreparedStatement extends AbstractProxyStatement
{
	final private String queryID; 
	final private List<NamedSqlParameter> params=new ArrayList<NamedSqlParameter>();
	
	NamedPreparedStatement(String id,String sql) throws SQLException {
		super(sql);
		this.queryID=id;
		precompileSQL(sql);
	}
	
	
	public NamedPreparedStatement(AbstractProxyConnection conn, String id,String sql) throws SQLException {
		super(conn, sql);
		this.queryID=id;
		precompileSQL(sql);
	}

	
	public String getQueryID() {return this.queryID;}

	protected void precompileSQL(String sql)
	{
		int pos=0;
		int p=1;
		while (pos<sql.length())
		{
			final int index=sql.indexOf('?',pos);
			if (index<0) break;
			params.add(new NamedSqlParameter(index,"param"+p,null));
			p++;
			pos=index+1;
		}
	}


	public int getParameterIndexByName(final String name)
	{
		int i=0;
		for (final NamedSqlParameter p:params)
		{	i++;
			if (name.equalsIgnoreCase(p.getParameterName())) return i;
		}
		return -1;
	}


	public int getNumberOfParams() {return params.size();}
	
	@Override
	public int hashCode() 
	{
		return queryID.hashCode()+47;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (obj==null) return false;
		if (obj==this) return true;
		final NamedPreparedStatement other=(NamedPreparedStatement)obj;
		return getQueryID().equals(other.getQueryID());
	}


	public int getQueryType()
	{
		final String sql2=sql.toLowerCase();
		if (sql2.startsWith("select ")) return QUERY_TYPE_SELECT;
		if (sql2.startsWith("update ")) return QUERY_TYPE_UPDATE;
		if (sql2.startsWith("insert ")) return QUERY_TYPE_INSERT;
		if (sql2.startsWith("delete ")) return QUERY_TYPE_DELETE;
		if (sql2.startsWith("exec ")) 	return QUERY_TYPE_STOREDPROC;
		return QUERY_TYPE_UNKNOWN;
	}
}
