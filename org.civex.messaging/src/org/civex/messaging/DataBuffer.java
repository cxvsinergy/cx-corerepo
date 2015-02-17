package org.civex.messaging;

import java.util.Arrays;
/*
@since 20140601
*/ 
 class DataBuffer 
{
	final protected byte buff[];
	final static protected int bitz[]={0,1,3,7,15,31,63,127,255};
	
	public DataBuffer(int size)
	{
		buff=new byte[size];
	}
	
	public DataBuffer(byte[] buff)
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

		long value = (buff[off] & 0xFFL) << 56L;
		value|= (buff[off+1] & 0xFFL) << 48L;
		value|= (buff[off+2] & 0xFFL) << 40L;
		value|= (buff[off+3] & 0xFFL) << 32L;
		value|= (buff[off+4] & 0xFFL) << 24L;
		value|= (buff[off+5] & 0xFFL) << 16L;
		value|= (buff[off+6] & 0xFFL) << 8L;
		value|= (buff[off+7] & 0xFFL);
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
	
	
	public void setByteArray(final int index, final byte[] bf, final int off, final int sz)
	{
		System.arraycopy(bf, off, buff, index, sz);
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
	
	public int setString(final String s,final int index)
	{
		if (s==null)
		{
			setInt4(-1, index);
			return 4;
		}
		final byte bf[]=s.getBytes();
		return setSerializedArray(bf,0,bf.length,index);
	}
	
	
	public int setSerializedArray(byte bf[], int off, int size,final int index )
	{
		final int l=buff!=null?size:-1;
		setInt4(l, index);
		if (l<0) return 4;
		setByteArray(index+4, bf, off, size);
		return 4+size;
	}
	
	public String getString(int index)
	{
		final int l=getInt4(index);
		if (l<0) return null;
		return new String(buff,index+4,l);
	}
	
	
	public int appendStringTo(StringBuilder sbld, int index)
	{
		final int l=getInt4(index);
		if (l<0) return 0;
		sbld.append(new String(buff,index+4,l));
		return l;
	}
	
	/**gets string size**/
	public int getStringSize(int index)
	{
		return getInt4(index);
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
	
	public String getRawString(int index)
	{
		return new String(buff,index,buff.length-index);
	}

	
	public int putJavaSerObject(Object obj)
	{
		return -1;
	}
	
	
	public Object getJavaSerObject(int index)
	{
		return null;
	}
	
	
	public void fill(int v, int index, int sz)
	{
		Arrays.fill(buff,index,index+sz,(byte)(v));
	}
	
	
	public void resolveArray(int[] arr, int srcOff,int off, int sz)
	{
		
	}
	
	public void resolveArray(long[] arr, int srcOff, int off, int sz)
	{
		
	}
	
	public void moveBack(int off)
	{
		for (int ii=0;ii<buff.length-off;ii++) buff[ii]=buff[off+ii];
	}
}
