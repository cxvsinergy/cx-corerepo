package org.civex.messaging;

import java.net.DatagramPacket;
import java.net.InetAddress;



public class TOMMessage extends DataBuffer
{
	public static final int   header=0xabba;
	public static final int   HDR_TOPIC=2;
	public static final int   HDR_TIMESTAMP=HDR_TOPIC+16;
	public static final int   HDR_COUNTER=HDR_TIMESTAMP+8;
	public static final int   HDR_MSGSIZE=HDR_COUNTER+8;
	public static final int   HDR_MSGTYPE=HDR_MSGSIZE+4;
	public static final int   HDR_FRAME=HDR_MSGTYPE+2;
	public static final int   HDR_TOPICLEN=HDR_TIMESTAMP-HDR_TOPIC;
	
	public static final int   HDR_SIZE=64;
	
	public static final int   MSG_TYPE_BINARY=0x1;
	public static final int   MSG_TYPE_STRING=0x2;
	public static final int   MSG_TYPE_JAVAOBJ=0x3;
	public static final int   MSG_TYPE_SRVOBJ=0xFF;
	
	private DatagramPacket packet;
	long  counter;
	short msgtype;
	short bodysz;
	
	public TOMMessage(int sz)
	{
		super(sz);
		setInt2(header, 0);
	}
	
	
	public void incCounter()
	{
		setLong(++counter, HDR_COUNTER);
	}
	
	public void setTimestamp(long timestamp)
	{
		setLong(timestamp, HDR_TIMESTAMP);
	}
	
	
	public void setTopic(String topic)
	{
		fill(0x20, HDR_TOPIC,HDR_TOPICLEN);
		final byte bf[]=topic.toUpperCase().getBytes();		
		setByteArray(HDR_TOPIC, bf,0,bf.length>HDR_TOPICLEN?HDR_TOPICLEN:bf.length);
	}
	
	
	public String getTopic()
	{
		return new String(buff,HDR_TOPIC,HDR_TOPICLEN).trim();
	}
	
	public void setTopicId(long id)
	{
		setLong(id, HDR_TOPIC);
	}
	
	public DatagramPacket requestDatagramPacket()
	{
		if (packet!=null) return packet;
		packet=new DatagramPacket(buff,buff.length);
		return packet;
	}
	
	
	public DatagramPacket requestDatagramPacket(InetAddress addr, int port, int size)
	{
		if (packet!=null) return packet;
		packet=new DatagramPacket(buff,size,addr,port);
		return packet;
	}
	
	public void seal(long timestamp)
	{
		setTimestamp(timestamp);
		incCounter();
	}
	
	public static int getHeaderSize()
	{
		return 48;
	}


	public long getCounter() 
	{
		return getLong(HDR_COUNTER);
	}


	public long getTimestamp() 
	{
		return getLong(HDR_TIMESTAMP);
	}


	public long getTopicId() 
	{
		return getLong(HDR_TOPIC);
	}
	
	public int getMessageSize()
	{
		return getInt4(HDR_MSGSIZE);
	}
	
	public int getMessageType()
	{
		return getInt2(HDR_MSGTYPE);
	}
	
	
	public void setMessageSize(int size)
	{
		setInt4(size,HDR_MSGSIZE);
	}
	
	
	public void setMessageType(final int type, final int size)
	{
		setMessageType(type);
		setMessageSize(size);
	}
	
	public void setMessageType(int type)
	{
		 setInt2(type,HDR_MSGTYPE);
	}
	
	public long getMessageFrame()
	{
		return getLong(HDR_FRAME);
	}
	
	
	public void setMessageFrame(long size)
	{
		setLong(size,HDR_FRAME);
	}
}
