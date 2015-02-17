package org.civex.messaging;

/***
 * 8  bits=type
 * 1  bit=ack number
 * 10 typed array
 * 01 primitive array
 * 11 reserved 
 *  
 */
public enum MessageFieldType 
{
	
	BOOLEAN(1,0),HALFBYTE(2,1),BYTE(3,8),
	SHORT(4,16),CHAR(5,16),
	INT(6,32),FLOAT(7,32),
	LONG(8,64),DOUBLE(9,64),
	//
	BYTE_ARR(3,1,true),SHORT_ARR(4,2,true),
	INT_ARR(6,4,true),STR_ARR(5,2,true),
	BYTE_ARR_TYPED(0x1F+7,1),// 4 bytes<-class hash
	JSTR_ARR(0x1F+8,1),KEY_VAL_ARR(0,0);
	
	
		
	int BIT_TYPED=0; //1bit=type
	int BIT_ACK=0; 	 //1bit=included acknowledgement
	int BIT_ARR=0;	 //size=11bits---2048bytes
	int RESERVED_BIT1=0; // boolean flag
	int RESERVED_BIT2=0;	
	int RESERVED_BIT3=0;
	
	final int type;
	final int elsz;
	final boolean useAck;
	final boolean isArr;
	
	private MessageFieldType(int type,int sz)
	{
		this(type,sz,false);
	}
	
	private MessageFieldType(int type,int sz, boolean isArr)
	{
		this.type=type;
		this.elsz=sz;
		this.useAck=false; // ack has
		this.isArr=isArr;
	}
	
	public boolean hasAckBit()   	  {return false;}
	public boolean hasTypeHash() 	  {return false;}
	public boolean isArrayValue() 	  {return false;}
	public int 	   getPrimitiveCode() {return 0;}
}
