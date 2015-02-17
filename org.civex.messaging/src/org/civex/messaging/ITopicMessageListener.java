package org.civex.messaging;

import java.net.DatagramPacket;

public interface ITopicMessageListener 
{
	void onPacketReceive(DatagramSocketConnector conn, DatagramPacket packet,TOMMessage recMsg);
}
