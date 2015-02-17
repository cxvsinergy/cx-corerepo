package org.civex.utils;

import java.io.Serializable;


public class ByteStringSequence implements CharSequence, Serializable
{
	private static final long serialVersionUID = 1L;
	final private byte buff[];
	final private int off;
	final private int len;
	private int hash;
		
	public ByteStringSequence(final byte buff[])
	{
		this.buff=buff;
		this.off=0;
		this.len=buff.length;
	}
	
	public ByteStringSequence(ByteStringSequence other, int start, int end)
	{
		this.buff=other.buff;
		this.off=other.off+start;
		this.len=end-start;
	}
	
	public void setCharAt(int index, byte v)
	{
		buff[off+index]=v;
		hash=0;
	}
	
	public void setDigitAt(int index, int v)
	{
		buff[off+index]=(byte)(48+v&0xF);
		hash=0;
	}
	
	@Override
	public int length()  {return len;}
	@Override
	public char charAt(int index) {return (char)(buff[index]);}
	@Override
	public CharSequence subSequence(int start, int end) 
	{
		return new ByteStringSequence(this,start,end);
	}
	@Override
	public String toString() {return new String(buff,off,len);}

	@Override
	public int hashCode() 
	{
		return hash>0?hash:(hash=recalcHash());
	}

	private int recalcHash()
	{
		return 0;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (hashCode()!=obj.hashCode()) return false;
		if (!(obj instanceof ByteStringSequence)) return false;
		return true;
	}
	
	
}
