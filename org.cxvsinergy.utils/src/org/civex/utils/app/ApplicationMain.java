/**********************************************************************
 * Copyright (c) 2014 cxvsinergy.org., and others. All rights reserved.
 * Contributors: 
 * 	@author Vitaly Samoilov - Initial implementation 
 * 	e-mail : nikevit@gmail.com
 * project : com.cxvsinergy.utils
 * History:
 * 		
 * 	
 *******************************************************************/
package org.civex.utils.app;

import java.util.Arrays;

/* This package should be kep as a standalone package, without(w minimum dependencies)*/
/** basic implementation for standalone application**/
public abstract class ApplicationMain

{
	 public static void runMain(String[] args, Class<?> clazz) 
	  {
		 int runCounter=0;
		 ApplicationMain app=null;
		 try
		 {
			 app=(ApplicationMain)clazz.newInstance();
			 if (!app.validateParameters(args))
			 {
				 app.printLegend();
				 System.exit(-1);
			 }
			 	 
		 }
		 catch(Exception x)
		 {
			 x.printStackTrace();
			 System.exit(-1);
		 }
		 // runnable part
		 while(true)
		 {
			 try
			 {
				
				
				 runCounter++;
				 app.loadConfig(null);
				 app.runInstance(args);	 
			 }
			 catch(Exception x)
			 { 
				 app.handleInstanceError(x,runCounter,0);
			 }	 
		 }
		 
		 
	  }
	 
	 
	 
	 public abstract void runInstance(String[] args) throws Exception;
	
	 
	 protected  void printLegend()
	 { }
	 
	 
	 
	 public void printMessage(int level, String msg, Object params[])
	 {System.out.println(msg);	}
	 
	 public void printMessage(int level, String msg, Exception x)
	 {
		 printMessage(1,msg+"\n"+x.getMessage(),(Object[])null);
	 }
	 
	 
	 public static String resolveParam(String args[] ,String name, String defaultValue)
	 {
		 return AppUtilsHelper.resolveCommandParam(Arrays.asList(args),name,defaultValue);
	 }
	 
	 
	 public static double resolveParam(String args[] ,String name, double defaultValue)
	 {
		 return AppUtilsHelper.resolveCommandParam(Arrays.asList(args),name,defaultValue);
	 }
	 
	 public void loadConfig(String[] args)
	 {
		 
	 }
	 
	 
	 protected boolean validateParameters(String args[])
	 {
		 return args.length>1;
	 }
	 
	 
	 public static void sleep(long time)
	 {
		 try
		 {
			 Thread.sleep(time);			 
		 }
		 catch(Exception x)
		 {
			 x.printStackTrace();
		 }

	 }
	 
	 
	 public long getCurrentTime()
	 {
		 return System.currentTimeMillis();
	 }
	 
	 
	 protected void handleInstanceError(Exception x, long  runCounter, long prevTime)
	 {
		 x.printStackTrace();
		 System.exit(0);
	 }
	  
}
