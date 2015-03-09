package org.civex.utils.collections;

import java.util.Arrays;

public class ByteArray implements Cloneable
{
	final protected byte buff[];
	final static protected int bitz[]={0,1,3,7,15,31,63,127,255};
	
	public ByteArray(int size)
	{
		buff=new byte[size];
	}
	
	protected ByteArray(byte[] buff)
	{
		this.buff=new byte[buff.length];
		System.arraycopy(buff, 0, this.buff, 0, buff.length);
	}
	
	
	public int getSize()
	{
		return buff.length;
	}
	
	public byte[] getBuffer()
	{
		return buff;
	}
	
	public final double getDouble(int off)
	{
		return Double.longBitsToDouble(getLong(off));
	}
	
	
	public long getLong(int off)
	{
		int value = 0;
	    for (int i = 0; i < 8; i++) 
	    {
	        int shift = (8 - 1 - i) * 8;
	        value += (buff[i] & 0x000000FF) << shift;
	    }
	    return value;
	}
	
	public int getInt4(int off)
	{
		int value = (buff[off] & 0x000000FF) << 24;
	    value+=(buff[off+1] & 0x000000FF) << 16;
	    value+=(buff[off+2] & 0x000000FF) << 8;
	    value+=(buff[off+3] & 0x000000FF);
	    return value;
	}
	
	public int getInt2(int off)
	{
		return   ((0x000000FF&buff[off])<<8)+(0x000000FF&buff[off+1]);
	}
	/**Gets bytes**/
	public int getByte(int off)
	{
		return buff[off];
	}
	/**Sets byte value**/
	public void setByte(int v, int off)
	{
		 buff[off] =(byte)v;
	}
	
	public void setInt2(int v, int off)
	{
		  buff[off] = (byte) (v >> 8);
		  buff[off+1] = (byte) (v);
	}
	
	
	public void setInt4(int v, int off)
	{
		  buff[off] 	= (byte) (v >> 24);
		  buff[off+1] = (byte) (v >> 16);
		  buff[off+2] = (byte) (v >> 8);
		  buff[off+3] = (byte) (v /*>> 0*/);
	}
	
	
	public void setLong(long v, int off)
	{
		  buff[off]   = (byte) (v >> 56);
		  buff[off+1] = (byte) (v >> 48);
		  buff[off+2] = (byte) (v >> 40);
		  buff[off+3] = (byte) (v >> 32);
		  buff[off+4] = (byte) (v >> 24);
		  buff[off+5] = (byte) (v >> 16);
		  buff[off+6] = (byte) (v >> 8);
		  buff[off+7] = (byte) (v /*>> 0*/);
	}
	
	
	public void setDouble(double v, int off)
	{
		setLong(Double.doubleToLongBits(v),off);
	}
	
	public void clear()
	{
		Arrays.fill(buff, (byte)0);
	}

	
	
	/**Sets bits for the given byte**/
	public void setBits(final int byteOff, int bitOff, int bitLen, int v)
	{
		v=v&0xff;
		int b=v<<bitOff;
		int mask=~(bitz[bitLen]<<bitOff);
		int b1=(getByte(byteOff)&mask)|b;
		setByte(b1, byteOff);
	}
	
	public int getBits(final int byteOff, int bitOff, int bitLen)
	{
		int b=getByte(byteOff);
		bitOff&=0x7;
		b=b>>bitOff;
		return b & bitz[bitLen];
	}
	
	
	public int getBit(int byteOff, int bitOff)
	{
		return getBits(byteOff, bitOff,1);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException 
	{
		return new ByteArray(buff);
	}
	
	
	
	
}
