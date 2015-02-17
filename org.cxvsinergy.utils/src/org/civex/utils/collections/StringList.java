package org.civex.utils.collections;

import java.util.AbstractList;
import java.util.StringTokenizer;


public class StringList extends AbstractList<String>
{
	final private String delimeter;
	private String s;
	private int lastSize=-1;
	

	public StringList(byte buff[],int off,int sz)
	{
		this(new String(buff,off,sz));
	}
	
	public StringList(String s)
	{
		this(s,";");
	}
	
	public StringList(String s, String delimeter)
	{
		this.s=s;
		this.delimeter=delimeter;
	}
	
	@Override
	public boolean add(final String e) 
	{
		if (s==null) s="";
		s+=e;
		return true;
	}

	@Override
	public String get(int index) 
	{
		final StringTokenizer stoken=new StringTokenizer(s,delimeter);
		int counter=0;
		while(stoken.hasMoreTokens())
		{
			final String v=stoken.nextToken();
			if (counter==index) return v;
			counter++;
		}
		return null;
	}

	@Override
	public int size() 
	{
		if (lastSize<0) recompile();
		return lastSize;
	}
	
	protected void recompile()
	{
		final StringTokenizer stoken=new StringTokenizer(s,delimeter);
		int counter=0;
		while(stoken.hasMoreTokens())
		{
			stoken.nextToken();
			counter++;
		}
		lastSize=counter;
		
	}

	@Override
	public String toString() 
	{
		return s.toString();
	}

	@Override
	public int hashCode() 
	{
		return s.hashCode();
	}

	public int copyAsBytes(byte buff[], int off, int sz)
	{
		final byte b[]=s.getBytes();
		System.arraycopy(b, 0, buff, off, sz);
		return b.length;
	}
	/**gets  string value as a number**/
	public double getAsNumber(int index)
	{
		final String v=get(index);
		return Double.parseDouble(v);
	}
	
	
	public String getByKey(final String key)
	{
		final StringTokenizer stoken=new StringTokenizer(s,delimeter);
		while(stoken.hasMoreTokens())
		{
			final String v=stoken.nextToken();
			if (!v.startsWith(key)) continue;
			final int index=v.indexOf("=");
			if (index!=key.length()) continue;
			return v.substring(index+1);
		}
		return null;
		
	}
}