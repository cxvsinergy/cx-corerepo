package org.civex.utils;

import org.civex.testfwk.LocalDevTestCase;

public class MultiDimNavigatorTest extends LocalDevTestCase 
{
	public void testMe2()
	{
		
		MultiSpaceIterator hotup=new MultiSpaceIterator(new int[]{255,255,255,1});
		for (int ii=0;ii<1000;ii++)
		{
			hotup.next();	
		}
		
		
		long t=System.currentTimeMillis();
		
		MultiSpaceIterator it=new MultiSpaceIterator(new int[]{255,255,255,255,255,255,1,1,1,1});
		long total=0;
		while (it.hasNext())
		{
			  final long[] result=it.next();
			  total++;
		}
		System.out.println("completed -> "+(System.currentTimeMillis()-t)+"  "+Long.toHexString(total));
		
	}
	
	
	public void t1estMe()
	{
		//for (int ii=0;ii<48;ii++)
		printPtr(28);
	}
	
	
   public void printPtr(int ptr)
   {
	
	  final int d[]={3,4,6,3};
	  
	  final int len=d.length;
	  int result[]=new int[len];
	  int dsz[]=new int[len];
	  dsz[0]=d[0];
	  result[0]=ptr%d[0];
	  //
	  for (int ii=1;ii<len-1;ii++)
	  {
		  dsz[ii]=d[ii]*dsz[ii-1];  
	  }
	  //
	  int m=0;
	  int p=ptr;
	  for (int ii=len-1;ii>0;ii--)
	  {
		  result[ii]=p/dsz[ii-1];
		  p-=dsz[ii-1]*result[ii];
		  
	  }
	  
	  
	  for (int ii=len-1;ii>=0;ii--)
	  {
		 System.out.print("-"+dsz[ii]);  
	  }
	  System.out.println();
	  
	  for (int ii=len-1;ii>=0;ii--)
	  {
		  System.out.print("-"+result[ii]);  
	  }
	  System.out.println();
	}
}
