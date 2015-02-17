package org.civex.messaging;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;

public class DatagramSocketConnector extends NetworkDataConnector<Object>
{
	protected static final ITopicMessageListener DEFAULT_LISTENER=new DefaultTopicListener();
	protected static final int MAX_SIZE=1500;
	final private TOMMessage recMsg,sndMsg;
	final private String topic;
	private ITopicMessageListener topicListener=DEFAULT_LISTENER; 
	private DatagramSocket socket;
	
	
	public DatagramSocketConnector(String host, int port, String topic) throws IOException 
	{
		this(host,port,topic,1000);
	}

	public DatagramSocketConnector(String host, int port,String topic,int delay) throws IOException 
	{
		this(host,port,topic,MAX_SIZE,1000);
	}
	
	
	public DatagramSocketConnector(String host, int port,String topic, int sz,int delay) throws IOException 
	{
		super(host, port);
		sndMsg=new TOMMessage(sz);
		recMsg=new TOMMessage(sz);
		this.topic=topic;
	}


	public void setTopicListener(ITopicMessageListener listener)
	{
		this.topicListener=listener;
	}
	
	public ITopicMessageListener getTopicListener() {return topicListener;}

	public void connect() throws IOException
	{
		socket = new MulticastSocket(port);
		if (socket instanceof MulticastSocket)
		{
			((MulticastSocket)socket).joinGroup(addr);	
		}
		
	}
	
	public void postString(final String s) throws IOException
	{
		postString(this.topic,s);
	}
	
	public void postString(final String topic,final String s) throws IOException
	{
		   final int len=sndMsg.setString(s, sndMsg.getHeaderSize());
		   sndMsg.setMessageType(TOMMessage.MSG_TYPE_STRING,len);
		   sndMsg.setTopic(topic);
		   sndMsg.seal(System.currentTimeMillis());
		   socket.send(sndMsg.requestDatagramPacket(addr, port,len+sndMsg.getHeaderSize()));
	}
	
	public void postRawBytes(byte buff[], int off, int len) throws IOException
	{
		socket.send(new DatagramPacket(buff,off,len,addr, port));
	}
	
	public void sendFromFile(final File f, final int rate)throws IOException
	{
		if (f.isDirectory())
		{
			final File files[]=f.listFiles();
			for (final File ff:files) sendFromFile(ff,rate);
			return;
		}
		FileInputStream fin=new FileInputStream(f);
		sendFromStream(fin,rate);
		fin.close();
	}
	
	/**send data directly from the streams**/
	public void sendFromStream(final InputStream inStream, final int rate) throws IOException
	{
		final byte buff[]=new byte[MAX_SIZE];
		final DatagramPacket packet=new DatagramPacket(buff,0,1);
		int sz=0;
		while((sz=inStream.read(buff))>0)
		{
			packet.setData(buff,0, sz);
			socket.send(packet);
		}
	}
	
	public void disconnect() throws IOException
	{
		if (socket instanceof MulticastSocket)
		{
			((MulticastSocket)socket).leaveGroup(addr);	
		}
		
		socket.close();
	}
	
	
	protected  void processReceivedData() throws IOException
	{

		final DatagramPacket packet =recMsg.requestDatagramPacket();
	    socket.receive(packet);
	    if (topicListener!=null)
	    {
	    	topicListener.onPacketReceive(this,packet,recMsg);
	    	return;
	    }
	 }
	
	
	public  static  class PacketListenerDispatcher implements Runnable
	{
		final private DatagramSocketConnector conn;
		
		
		public PacketListenerDispatcher(DatagramSocketConnector conn)
		{
			this.conn=conn;
		}
		
		public void run()  
		{
			while(conn.isConnected())
			{
				try
				{
					conn.listen();	
				}
				catch(Exception x)
				{
					x.printStackTrace();
					try
					{
						Thread.sleep(5000);	
					}
					catch(Exception x2)
					{
						x2.printStackTrace();
					}
					
				}	
			}
		}
	}

	
	
	
	public static class DefaultTopicListener implements ITopicMessageListener
	{

		@Override
		public void onPacketReceive(DatagramSocketConnector conn, DatagramPacket packet,TOMMessage recMsg) 
		{
			
			 	StringBuilder sb=new StringBuilder(1000);
			    sb.append(packet.getAddress()).append(":").append(packet.getPort());
			    sb.append(" id=").append(recMsg.getTopic());
			    sb.append(" tstamp=").append(recMsg.getTimestamp()%100000);
			    sb.append(" cnt=").append(recMsg.getCounter());
			    sb.append(" size=").append(recMsg.getMessageSize());
			    sb.append(" typ=").append(recMsg.getMessageType());
			    sb.append(" ");
			    sb.append(recMsg.getString(recMsg.getHeaderSize()));
			    System.out.println(sb);
			
		}
		
	}
}

