package org.civex.demo;

import java.util.concurrent.atomic.AtomicBoolean;

public class DbTestDemo 
{

			//some code here
			private static DbTestDemo foo;

			public static DbTestDemo getFoo() 
			{ 
			    if (foo == null) {
			    	
			        synchronized (DbTestDemo.class) 
			        {            
			            if (foo == null) foo = new DbTestDemo();           
			           }   
			         }  
			    return foo;
			}

			public static class Exchanger {
			    
			    private Object value;
			    private volatile boolean ready = false;
			    
			    public void provide(Object value){
			        while(ready){}
			        this.value = value;
			        ready = true;
			    }

			    public Object consume(){
			        while(!ready){}
			        Object value = this.value;
			        ready = false;
			        return value;
			    }
			}




			public static class ThreadTest {

			    public static void main(String[] args) 
			    {
			        CommonObject co = new CommonObject();
			        Thread otherThread = new Thread(new OtherRunnable(co));
			        otherThread.start();
			        try {
			            Thread.sleep(5000);
			        } catch (InterruptedException interupptedEx) {
			            System.out.println("Interrupted. Ignoring.");
			        }       
			       co.func2();
			   }
			}

			public static class OtherRunnable implements Runnable {
			    private CommonObject co;
			    
			    public OtherRunnable(CommonObject co) {
			         this.co = co;
			    }
			    
			    public void run() {
			        co.func1();
			    }
			}

			public static class CommonObject {
			    private Thread waitingThread;
			    
			   synchronized public void func1() {
			       try {
			           waitingThread = Thread.currentThread();
			           wait();
			      } catch (InterruptedException interupptedEx) {
			          System.out.print("A");
			          System.exit(1);
			      }
			   }
			    
			 synchronized public void func2() {
			        while(true) {
			             System.out.print("B"); 
			            waitingThread.interrupt();
			        }   
			   }
			}

			// Implement concurrent stack using singly linked list without blocking?
			    
			    public interface ConcurrentStack {
			        void push(Object e);
			        Object pop();
			    }

			    class MyConcurrentStack
			    {
			       private StackElement els;
			       private AtomicBoolean blocked=new AtomicBoolean(); 
			 
			       // false-close for read
			    
			       public void push(Object e)
			       {
			             while (!blocked.compareAndSet(true,false)) continue;        
			             els=new StackElement(els,e);          
			             blocked.set(true);
			       }
			       
			       
			       public Object pop()
			       {
			           if (els==null) return null;
			           while (!blocked.compareAndSet(false,true)) continue;        
			           Object o=els.curr;    
			           els=els.prev;
			           blocked.set(false);
			           return o;
			       }    

			    }
			     
			    public  static class StackElement
			       {

			           public Object curr;
			           public StackElement prev;
			           
			           public StackElement(StackElement prev, Object e)
			           {
			               this.prev=prev;
			               curr=e;
			           }
			       }
			       

			    }



