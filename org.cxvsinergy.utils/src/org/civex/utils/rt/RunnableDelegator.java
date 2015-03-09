package org.civex.utils.rt;


public class RunnableDelegator implements Runnable
{
		protected final Runnable rt;
		protected Thread   thread;
	
		public RunnableDelegator(Runnable rt)
		{
			this.rt=rt;
			thread=new Thread(rt);
		}
		
		public Thread getRunnableThread()
		{
			return thread;
		}
		
		/**Gets runnable component associated with this delegator**/
		public Runnable getRunnable()
		{
			return rt;
		}
		
		
		public void run()
		{
			try
			{
				rt.run();	
			}
			catch(RuntimeException x)
			{
				handleThreadException(x);
			}
			
		}

		
		protected void handleThreadException(Exception x)
		{
			if ((x instanceof InterruptedException))
			{
				handleInterruptedException((InterruptedException)x);
				return;
			}
			throw (x instanceof RuntimeException)?(RuntimeException)x:new RuntimeException(x);
		}
		
		
		protected void handleInterruptedException(InterruptedException x)
		{
			throw new RuntimeException(x);
		}
		
		
		@Override
		public int hashCode() 
		{
			return rt.hashCode();
		}

		@Override
		public boolean equals(Object obj) 
		{
			return rt.equals(obj);
		}

		public void safeStart()
		{
			thread.start();
		}
		
		
}
