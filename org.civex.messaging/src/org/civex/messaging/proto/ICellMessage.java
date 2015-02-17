package org.civex.messaging.proto;

public interface ICellMessage 
{
		@udpmsg(code=3, ack=0)
		public int 		getTemperature(int index);
		@udpmsg(code=3, ack=0)
		public double	getGlobalCoord();
		@udpmsg(code=3, ack=0)
		public double	getLocalCoord();
		@udpmsg(code=3, ack=0)
		public long		getScreen(int index);
		@udpmsg(code=3, ack=0)
		public long 	getSoundChannel();
		//s
}
