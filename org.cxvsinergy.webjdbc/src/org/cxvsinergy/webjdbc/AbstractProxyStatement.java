package org.cxvsinergy.webjdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class AbstractProxyStatement implements java.sql.PreparedStatement
{
	final private PreparedStatement stmt;
	final private Connection conn;
	final  int type;
	protected String sql;
	
	final static public int QUERY_TYPE_SELECT=0x11;
	final static public int QUERY_TYPE_INSERT=1;
	final static public int QUERY_TYPE_UPSERT=2;
	final static public int QUERY_TYPE_UPDATE=3;
	final static public int QUERY_TYPE_DELETE=4;
	final static public int QUERY_TYPE_STOREDPROC=5;
	final static public int QUERY_TYPE_UNKNOWN=0;
	
	
	public AbstractProxyStatement(AbstractProxyConnection conn,String sql) throws SQLException
	{
		this.conn=conn;
		stmt=conn.getImpl().prepareStatement(sql);
		this.sql=sql;
		this.type=-1;
		
	}

	protected AbstractProxyStatement(String sql) throws SQLException
	{
		this.conn=null;
		stmt=null;
		this.sql=sql;
		this.type=-1;
		
	}
	
	
	
	public AbstractProxyStatement(Connection conn,PreparedStatement stmt)
	{
		this.conn=conn;
		this.stmt=stmt;
		this.type=0;
	}
	
	protected PreparedStatement getImpl() { return stmt;}
	
	
	public void addBatch() throws SQLException {stmt.addBatch();}

	public void addBatch(String arg0) throws SQLException {
		stmt.addBatch(arg0);
	}

	public void cancel() throws SQLException {
		stmt.cancel();
	}

	public void clearBatch() throws SQLException {
		stmt.clearBatch();
	}

	public void clearParameters() throws SQLException {
		stmt.clearParameters();
	}

	public void clearWarnings() throws SQLException {
		stmt.clearWarnings();
	}

	public void close() throws SQLException {
		stmt.close();
	}

	public void closeOnCompletion() throws SQLException {
		stmt.closeOnCompletion();
	}

	public boolean execute() throws SQLException {
		return stmt.execute();
	}

	public boolean execute(String arg0, int arg1) throws SQLException {
		return stmt.execute(arg0, arg1);
	}

	public boolean execute(String arg0, int[] arg1) throws SQLException {
		return stmt.execute(arg0, arg1);
	}

	public boolean execute(String arg0, String[] arg1) throws SQLException {
		return stmt.execute(arg0, arg1);
	}

	public boolean execute(String arg0) throws SQLException {
		return stmt.execute(arg0);
	}

	public int[] executeBatch() throws SQLException {
		return stmt.executeBatch();
	}

	public  long[] executeLargeBatch() throws SQLException {
		return stmt.executeLargeBatch();
	}

	public  long executeLargeUpdate() throws SQLException {
		return stmt.executeLargeUpdate();
	}

	public  long executeLargeUpdate(String arg0, int arg1) throws SQLException {
		return stmt.executeLargeUpdate(arg0, arg1);
	}

	public  long executeLargeUpdate(String arg0, int[] arg1) throws SQLException {
		return stmt.executeLargeUpdate(arg0, arg1);
	}

	public  long executeLargeUpdate(String arg0, String[] arg1) throws SQLException {
		return stmt.executeLargeUpdate(arg0, arg1);
	}

	public  long executeLargeUpdate(String arg0) throws SQLException {
		return stmt.executeLargeUpdate(arg0);
	}

	public ResultSet executeQuery() throws SQLException {
		return stmt.executeQuery();
	}

	public ResultSet executeQuery(String arg0) throws SQLException {
		return stmt.executeQuery(arg0);
	}

	public int executeUpdate() throws SQLException {
		return stmt.executeUpdate();
	}

	public int executeUpdate(String arg0, int arg1) throws SQLException {
		return stmt.executeUpdate(arg0, arg1);
	}

	public int executeUpdate(String arg0, int[] arg1) throws SQLException {
		return stmt.executeUpdate(arg0, arg1);
	}

	public int executeUpdate(String arg0, String[] arg1) throws SQLException {
		return stmt.executeUpdate(arg0, arg1);
	}

	public int executeUpdate(String arg0) throws SQLException {
		return stmt.executeUpdate(arg0);
	}

	public Connection getConnection() throws SQLException {
		return stmt.getConnection();
	}

	public int getFetchDirection() throws SQLException {
		return stmt.getFetchDirection();
	}

	public int getFetchSize() throws SQLException {
		return stmt.getFetchSize();
	}

	public ResultSet getGeneratedKeys() throws SQLException {
		return stmt.getGeneratedKeys();
	}

	public  long getLargeMaxRows() throws SQLException {
		return stmt.getLargeMaxRows();
	}

	public  long getLargeUpdateCount() throws SQLException {
		return stmt.getLargeUpdateCount();
	}

	public int getMaxFieldSize() throws SQLException {
		return stmt.getMaxFieldSize();
	}

	public int getMaxRows() throws SQLException {
		return stmt.getMaxRows();
	}

	public ResultSetMetaData getMetaData() throws SQLException {
		return stmt.getMetaData();
	}

	public boolean getMoreResults() throws SQLException {
		return stmt.getMoreResults();
	}

	public boolean getMoreResults(int arg0) throws SQLException {
		return stmt.getMoreResults(arg0);
	}

	public ParameterMetaData getParameterMetaData() throws SQLException {
		return stmt.getParameterMetaData();
	}

	public int getQueryTimeout() throws SQLException {
		return stmt.getQueryTimeout();
	}

	public ResultSet getResultSet() throws SQLException {
		return stmt.getResultSet();
	}

	public int getResultSetConcurrency() throws SQLException {
		return stmt.getResultSetConcurrency();
	}

	public int getResultSetHoldability() throws SQLException {
		return stmt.getResultSetHoldability();
	}

	public int getResultSetType() throws SQLException {
		return stmt.getResultSetType();
	}

	public int getUpdateCount() throws SQLException {
		return stmt.getUpdateCount();
	}

	public SQLWarning getWarnings() throws SQLException {
		return stmt.getWarnings();
	}

	public boolean isCloseOnCompletion() throws SQLException {
		return stmt.isCloseOnCompletion();
	}

	public boolean isClosed() throws SQLException {
		return stmt.isClosed();
	}

	public boolean isPoolable() throws SQLException {
		return stmt.isPoolable();
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return stmt.isWrapperFor(iface);
	}

	public void setArray(int parameterIndex, Array x) throws SQLException {
		stmt.setArray(parameterIndex, x);
	}

	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		stmt.setAsciiStream(parameterIndex, x, length);
	}

	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		stmt.setAsciiStream(parameterIndex, x, length);
	}

	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		stmt.setAsciiStream(parameterIndex, x);
	}

	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		stmt.setBigDecimal(parameterIndex, x);
	}

	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		stmt.setBinaryStream(parameterIndex, x, length);
	}

	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		stmt.setBinaryStream(parameterIndex, x, length);
	}

	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		stmt.setBinaryStream(parameterIndex, x);
	}

	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		stmt.setBlob(parameterIndex, x);
	}

	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		stmt.setBlob(parameterIndex, inputStream, length);
	}

	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		stmt.setBlob(parameterIndex, inputStream);
	}

	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		stmt.setBoolean(parameterIndex, x);
	}

	public void setByte(int parameterIndex, byte x) throws SQLException {
		stmt.setByte(parameterIndex, x);
	}

	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		stmt.setBytes(parameterIndex, x);
	}

	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		stmt.setCharacterStream(parameterIndex, reader, length);
	}

	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		stmt.setCharacterStream(parameterIndex, reader, length);
	}

	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		stmt.setCharacterStream(parameterIndex, reader);
	}

	public void setClob(int parameterIndex, Clob x) throws SQLException {
		stmt.setClob(parameterIndex, x);
	}

	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		stmt.setClob(parameterIndex, reader, length);
	}

	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		stmt.setClob(parameterIndex, reader);
	}

	public void setCursorName(String arg0) throws SQLException {
		stmt.setCursorName(arg0);
	}

	public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
		stmt.setDate(parameterIndex, x, cal);
	}

	public void setDate(int parameterIndex, Date x) throws SQLException {
		stmt.setDate(parameterIndex, x);
	}

	public void setDouble(int parameterIndex, double x) throws SQLException {
		stmt.setDouble(parameterIndex, x);
	}

	public void setEscapeProcessing(boolean arg0) throws SQLException {
		stmt.setEscapeProcessing(arg0);
	}

	public void setFetchDirection(int arg0) throws SQLException {
		stmt.setFetchDirection(arg0);
	}

	public void setFetchSize(int arg0) throws SQLException {
		stmt.setFetchSize(arg0);
	}

	public void setFloat(int parameterIndex, float x) throws SQLException {
		stmt.setFloat(parameterIndex, x);
	}

	public void setInt(int parameterIndex, int x) throws SQLException {
		stmt.setInt(parameterIndex, x);
	}

	public  void setLargeMaxRows(long arg0) throws SQLException {
		stmt.setLargeMaxRows(arg0);
	}

	public void setLong(int parameterIndex, long x) throws SQLException {
		stmt.setLong(parameterIndex, x);
	}

	public void setMaxFieldSize(int arg0) throws SQLException {
		stmt.setMaxFieldSize(arg0);
	}

	public void setMaxRows(int arg0) throws SQLException {
		stmt.setMaxRows(arg0);
	}

	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		stmt.setNCharacterStream(parameterIndex, value, length);
	}

	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		stmt.setNCharacterStream(parameterIndex, value);
	}

	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		stmt.setNClob(parameterIndex, value);
	}

	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		stmt.setNClob(parameterIndex, reader, length);
	}

	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		stmt.setNClob(parameterIndex, reader);
	}

	public void setNString(int parameterIndex, String value) throws SQLException {
		stmt.setNString(parameterIndex, value);
	}

	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
		stmt.setNull(parameterIndex, sqlType, typeName);
	}

	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		stmt.setNull(parameterIndex, sqlType);
	}

	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
		stmt.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
	}

	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		stmt.setObject(parameterIndex, x, targetSqlType);
	}

	public  void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength)
			throws SQLException {
		stmt.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
	}

	public  void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
		stmt.setObject(parameterIndex, x, targetSqlType);
	}

	public void setObject(int parameterIndex, Object x) throws SQLException {
		stmt.setObject(parameterIndex, x);
	}

	public void setPoolable(boolean arg0) throws SQLException {
		stmt.setPoolable(arg0);
	}

	public void setQueryTimeout(int arg0) throws SQLException {
		stmt.setQueryTimeout(arg0);
	}

	public void setRef(int parameterIndex, Ref x) throws SQLException {
		stmt.setRef(parameterIndex, x);
	}

	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		stmt.setRowId(parameterIndex, x);
	}

	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		stmt.setSQLXML(parameterIndex, xmlObject);
	}

	public void setShort(int parameterIndex, short x) throws SQLException {
		stmt.setShort(parameterIndex, x);
	}

	public void setString(int parameterIndex, String x) throws SQLException {
		stmt.setString(parameterIndex, x);
	}

	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
		stmt.setTime(parameterIndex, x, cal);
	}

	public void setTime(int parameterIndex, Time x) throws SQLException {
		stmt.setTime(parameterIndex, x);
	}

	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
		stmt.setTimestamp(parameterIndex, x, cal);
	}

	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		stmt.setTimestamp(parameterIndex, x);
	}

	public void setURL(int parameterIndex, URL x) throws SQLException {
		stmt.setURL(parameterIndex, x);
	}

	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
		stmt.setUnicodeStream(parameterIndex, x, length);
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return stmt.unwrap(iface);
	}
	
	
}
