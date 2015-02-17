package org.civex.messaging.proto;

public @interface udpmsg 
{
	int ack() 	default 0;
	int code()	default 0;
	boolean transacted() default false;
}
