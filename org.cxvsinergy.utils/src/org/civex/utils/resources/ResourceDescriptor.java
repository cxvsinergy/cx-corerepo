package org.civex.utils.resources;

import java.io.File;
/**
 * Resource descriptor
 * @author nikehome
 * @deprecated
 */
public class ResourceDescriptor 
{
   enum STATE {WAITING,STARTING,LOADING,COMPLETED,PAUSED,ABORTED, ERROR, TIMEOUT}	
	
   private STATE state=STATE.WAITING;
   private String url;
   private String basePath;
   private String name;
   private long totalSize=-1;
   private long loadedSize;

   
   public ResourceDescriptor(String url, String basePath)
   {
	   this.url=url;
	   this.name=buildResourceName();
	   this.basePath=basePath;
   }
   /**builds resource name from the URL**/
   protected String buildResourceName()
   {
	   int index=url.lastIndexOf('/');
	   if (index==url.length()-1) index=url.lastIndexOf('/', index-1);
	   String str=index<0?url:url.substring(index+1);
	   index=str.indexOf('?');
	   if (index<0) return str;
	   return str.substring(0,index);
   }
   
   public String getURL()
   {
	   return url;
   }
   
   public String getResourceName()
   {
	   return name;
   }
  
   public String getLocalPath()
   {
	   return basePath+File.separator+getResourceName();
   }
   /**sets resource expected size**/
   public void setTotalSize(long sz)
   {
	   this.totalSize=sz;
   }
   /**add size of the loaded block**/
   public void addLoadedLength(long sz)
   {
	   this.loadedSize+=sz;
   }
   
   
   
   public long getResourceSize()
   {
	   return this.totalSize;
   }
   
   public long getLoadedSize()
   {
	   return this.loadedSize;
   }
   
   public void setState(STATE state)
   {
	   this.state=state;
   }
   
   public STATE getState()
   {
	   return state;
   }
   
   
   public boolean isAlive()
   {
	   return state!=STATE.ERROR && state!=STATE.ABORTED && state!=STATE.COMPLETED;
   }
   
   public int getLoadedSzInPct()
   {
	   if (getResourceSize()<1) return 33;
	   return (int)(100*getLoadedSize()/getResourceSize());
   }

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result +  name.hashCode();
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj == null)	return false;
	if (getClass() != obj.getClass())
		return false;
	ResourceDescriptor other = (ResourceDescriptor) obj;
	return name.equals(other.name);
}
   
   
}
