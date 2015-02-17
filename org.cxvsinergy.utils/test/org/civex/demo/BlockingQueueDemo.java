package org.civex.demo;

import org.civex.utils.app.ThreadWorker;



public class BlockingQueueDemo 
{
	final private MyBlockingQueue<String> queue=new MyBlockingQueue<String>(10);
	
	public static void main(String args[]) throws Exception
	{
		BlockingQueueDemo qd=new BlockingQueueDemo();
		qd.runApp();
		//Exchanger exch;	//Semaphore sem;	//Phaser ph;
		//CopyOnWriteArrayList l;
		
	}
	
	
	protected void runApp() throws Exception
	{
		new Thread(new SimpleConsumer()).start();
		new Thread(new SimpleConsumer()).start();
		new Thread(new SimpleConsumer()).start();
		new Thread(new SimpleProducer()).start();
		Thread th=new Thread(new SimpleProducer());
		th.start();
		th.join();
		
	}
	
	class SimpleConsumer extends ThreadWorker
	{
		public void runIteration(long counter) throws Exception
		{
				String s=queue.take();
				System.out.println("A -> "+s+"   "+Thread.currentThread());
		}
	}
	
	class SimpleProducer extends ThreadWorker
	{
		public void runIteration(long counter)
		{
			String s=String.valueOf(Math.random());
			queue.offer(s);
		}
	}
}
