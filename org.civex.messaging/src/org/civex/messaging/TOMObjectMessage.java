package org.civex.messaging;

public class TOMObjectMessage extends TOMMessage
{

	public TOMObjectMessage(int sz) 
	{
		super(sz);
	}
	
	public long getTarget() {return -1;}

	public Object getTargetObject() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
